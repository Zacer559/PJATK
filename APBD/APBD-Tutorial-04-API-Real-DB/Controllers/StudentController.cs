using APBD_Tutorial_04_API_Real_DB.Models;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace APBD_Tutorial_04_API_Real_DB.Controllers
{
    [ApiController]
    [Route("api/student")]
    public class StudentController : ControllerBase
    {
        private string connectionString =
             @"Data Source=DESKTOP-6AJN4SI;Initial Catalog=school;Integrated Security=True";
        public StudentController()
        {

        }
     
        [HttpGet]
        public IActionResult GetStudents()
        {
            var result = new List<Student>();

            using (SqlConnection connection = new SqlConnection(connectionString))
            using (SqlCommand command = new SqlCommand())
            {
                command.Connection = connection;
                command.CommandText = "select * from student";

                connection.Open();
                SqlDataReader reader = command.ExecuteReader();

                while (reader.Read())
                {
                    var student = new Student();
                    student.Name = reader["FirstName"].ToString();
                    student.Surname = reader["LastName"].ToString();
                    student.Birthdate = reader["BirthDate"].ToString();
                    student.IndexNumber = reader["IndexNumber"].ToString();
                    student.IdEnroll = reader["IdEnrollment"].ToString();
                    result.Add(student);
                }
            }

            return Ok(result);
        }

        [HttpGet("{id}")]
        public IActionResult GetSemester(string id) {
            var result = new List<Enrollment>();

            using (SqlConnection connection = new SqlConnection(connectionString))
            using (SqlCommand command = new SqlCommand())
            {
                command.Connection = connection;
                command.CommandText = "select * from enrollment as s join student as e on s.idenrollment = e.idenrollment where e.indexnumber = @index ;";


                command.Parameters.AddWithValue("index",id);

                connection.Open();
                SqlDataReader reader = command.ExecuteReader();

                if (reader.Read())
                {
                    Enrollment e = new Enrollment();
                    e.IdEnrollment= reader["IdEnrollment"].ToString();
                    e.Semester = reader["Semester"].ToString();
                    e.IdStudy = reader["IdStudy"].ToString();
                    e.StartDate = reader["StartDate"].ToString();
                    result.Add(e);
                }
            }

            return Ok(result);
        }

    }
}
