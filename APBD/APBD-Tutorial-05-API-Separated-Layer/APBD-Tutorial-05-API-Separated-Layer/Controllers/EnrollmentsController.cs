
using APBD_Tutorial_05_API_Separated_Layer.DTO.Request;
using APBD_Tutorial_05_API_Separated_Layer.Services;
using Microsoft.AspNetCore.Mvc;



namespace s19551Assingment4.Controllers
{
    [ApiController]
    [Route("api/enrollment")]
    public class EnrollmentsController : ControllerBase
    {
        private readonly IStudentServiceDb _db;

        public EnrollmentsController(IStudentServiceDb db)
        {
            _db = db;
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
    }
}
