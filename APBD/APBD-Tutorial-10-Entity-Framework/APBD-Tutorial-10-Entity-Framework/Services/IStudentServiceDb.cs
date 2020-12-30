
using APBD_Tutorial_10_Entity_Framework.DTO;
using APBD_Tutorial_10_Entity_Framework.DTO.Request;
using APBD_Tutorial_10_Entity_Framework.DTO.Response;
using APBD_Tutorial_10_Entity_Framework.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace APBD_Tutorial_10_Entity_Framework.Services
{
    public interface IStudentServiceDb
    {
        static string connectionString =
              @"Data Source=localhost;Integrated Security=True";
        EnrollStudentResponse EnrollStudent(EnrollStudentRequest request);
        PromoteResponse PromoteStudent(PromoteRequest request);
        Student GetStudent(string indexNumber);
        IEnumerable<Student> GetStudents();
        public bool AddStudent(AddStudentRequest request);
        public bool DeleteStudent(string indexNumber);
        public bool UpdateStudent(UpdateStudentRequest request);
        public IEnumerable<Models.Enrollment> GetSemester(string indexNumber);
        public LoginResponse Login(LoginRequest loginRequest);
        public void SaveToken(string login, string token);
        public TokenResponse CheckToken(string token);
    }
}
