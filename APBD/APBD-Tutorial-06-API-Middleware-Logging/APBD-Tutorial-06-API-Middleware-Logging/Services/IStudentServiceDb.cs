
using APBD_Tutorial_06_API_Middleware_Logging.DTO;
using APBD_Tutorial_06_API_Middleware_Logging.DTO.Request;
using APBD_Tutorial_06_API_Middleware_Logging.DTO.Response;
using APBD_Tutorial_06_API_Middleware_Logging.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace APBD_Tutorial_06_API_Middleware_Logging.Services
{
    public interface IStudentServiceDb
    {
        static string connectionString =
              @"Data Source=DESKTOP-6AJN4SI;Initial Catalog=school;Integrated Security=True";
        EnrollStudentResponse EnrollStudent(EnrollStudentRequest request);
        PromoteResponse PromoteStudent(PromoteRequest request);
        Student GetStudent(string indexNumber);
        IEnumerable<Student> GetStudents();
    }
}
