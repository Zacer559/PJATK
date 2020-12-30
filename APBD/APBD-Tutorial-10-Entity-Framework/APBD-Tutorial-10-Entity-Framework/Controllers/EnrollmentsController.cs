
using APBD_Tutorial_10_Entity_Framework.DTO.Request;
using APBD_Tutorial_10_Entity_Framework.DTO.Response;
using APBD_Tutorial_10_Entity_Framework.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace APBD_Tutorial_10_Entity_Framework.Controllers
{
    [ApiController]
    [Authorize(Roles = "employee")]
    [Route("api/enrollment")]
    public class EnrollmentsController : ControllerBase
    {
        private readonly IStudentServiceDb _db;
        public IConfiguration config { get; set; }

        public EnrollmentsController(IStudentServiceDb db, IConfiguration configuration)
        {
            _db = db;
            config = configuration;
        }

        [HttpPost]
        public IActionResult EnrollStudents(EnrollStudentRequest request)
        {
            var response = _db.EnrollStudent(request);
            if (response == null) return NotFound();
            return Ok(response);
        }

        [HttpPost("promotion")]
        public IActionResult PromoteStudents(PromoteRequest request)
        {
            var r = _db.PromoteStudent(request);
            if (r != null)
                return Ok(r);
            else return BadRequest("Semester or study does not exists!");
        }

        [HttpGet("login")]
        [AllowAnonymous]
        public IActionResult Login(LoginRequest login)
        {
            //check if login data is correct
            var response = _db.Login(login);
            if (response != null)
            {
                //generete Claims
                Claim[] Cliams = new[] {

                new Claim(ClaimTypes.NameIdentifier,response.Login),
                new Claim(ClaimTypes.Name,response.Name),
                new Claim(ClaimTypes.Role,"employee") };
                //generete key and sign credential
                SymmetricSecurityKey key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(config["Key"]));
                SigningCredentials creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);


                //generete login token
                JwtSecurityToken token = new JwtSecurityToken(
                    issuer: "Dominik",
                    audience: "employee",
                    claims: Cliams,
                    expires: DateTime.Now.AddMinutes(10),
                    signingCredentials: creds

                    );
                //get refresh token
                Guid refreshToken = Guid.NewGuid();

                //save refresh token to DB
                _db.SaveToken(response.Login, refreshToken.ToString());

                return Ok(new FinalTokenResponse
                {
                    RefreshToken = refreshToken,
                    LoginToken = new JwtSecurityTokenHandler().WriteToken(token)
                });
            }
            else
            {
                return BadRequest("Bad login or password");
            }

        }
        [AllowAnonymous]
        [HttpPost("refresh/{token}")]
        public IActionResult RefreshToken(string token)

        {
            var data = _db.CheckToken(token);
            //if token exist
            if (data != null)
            {
                //Generate claims
                Claim[] Cliams = new[] {
                new Claim(ClaimTypes.NameIdentifier,data.Login),
                new Claim(ClaimTypes.Role,"employee") };
                // Generete key and sign credentials
                var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(config["Key"]));
                var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);


                //create new token
                JwtSecurityToken loginToken = new JwtSecurityToken(

                    issuer: "Dominik",
                    audience: "employee",
                    claims: Cliams,
                    expires: DateTime.Now.AddMinutes(10),
                    signingCredentials: creds

                    );
                // obtain new refresh token
                Guid refreshToken = Guid.NewGuid();
                _db.SaveToken(data.Login, refreshToken.ToString());
                //return new tokens
                return Ok(new FinalTokenResponse
                {
                    RefreshToken = refreshToken,
                    LoginToken = new JwtSecurityTokenHandler().WriteToken(loginToken)
                });
            }
            //if token not exist
            else
            {
                return BadRequest("Token does not exist");
            }


        }
    }
}