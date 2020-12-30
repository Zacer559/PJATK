using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Test_02.DAO.Request;
using Test_02.Services;

namespace Test_02.Controllers
{




    /// <summary>
    /// READ README !!!! THERE ARE INPORTANT INFORMATIONS!!!!
    /// </summary>
    [ApiController]
    [Route("api")]
    public class OrderController : ControllerBase
    {
        private readonly IDbService _dbService;


        public OrderController(IDbService dbService)
        {
            _dbService = dbService;
        }

    
        [HttpGet("artists/{id}")]
        public IActionResult GetOrder(int id)
        {
            var res = _dbService.GetArtist(id);
            if (res.ToList().Count() != 0)
                return Ok(res);
            else
                return NotFound("There are no artist with that id");
        }
        
        //All data is passed in body so im not using arguments in url
        [HttpPut("artists/update")]
        public IActionResult UpdateArtist(UpdateRequest request)
        {
            var res = _dbService.UpdatePerformanceDate(request);
            if (res == "ok")
            {
                return Ok("Updated sucessfull");
            }
            else return BadRequest(res);
        }
    }
    /// <summary>
    /// READ README !!!! THERE ARE INPORTANT INFORMATIONS!!!!
    /// </summary>
}