
using APBD_Tutorial_07_API_Authentication.DTO;
using APBD_Tutorial_07_API_Authentication.DTO.Request;
using APBD_Tutorial_07_API_Authentication.DTO.Response;
using APBD_Tutorial_07_API_Authentication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace APBD_Tutorial_07_API_Authentication.Services
{
    public interface IStudentServiceDb
    {
        static string connectionString =
              @"Data Source=localhost;Integrated Security=True";
        EnrollStudentResponse EnrollStudent(EnrollStudentRequest request);
        PromoteResponse PromoteStudent(PromoteRequest request);
        Student GetStudent(string indexNumber);
        IEnumerable<Student> GetStudents();
        public LoginResponse Login(LoginRequest loginRequest);
        public void SaveToken(string login, string token);
        public TokenResponse CheckToken(string token);
    }
}
