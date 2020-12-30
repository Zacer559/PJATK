
using APBD_Tutorial_06_API_Middleware_Logging.DTO.Request;
using APBD_Tutorial_06_API_Middleware_Logging.Services;
using Microsoft.AspNetCore.Mvc;



namespace APBD_Tutorial_06_API_Middleware_Logging.Controllers
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
