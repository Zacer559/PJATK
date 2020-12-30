
using APBD_Tutorial_05_API_Separated_Layer.Models;
using APBD_Tutorial_05_API_Separated_Layer.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace APBD_Tutorial_05_API_Separated_Layer.Controllers
{
    //Student controller from previous task. Nothing changed.
    [ApiController]
    [Route("api/student")]
    public class StudentController : ControllerBase
    {
        private string connectionString = IStudentServiceDb.connectionString;
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
                    student.FirstName = reader["FirstName"].ToString();
                    student.LastName = reader["LastName"].ToString();
                    student.Birthdate = (DateTime)reader["BirthDate"];
                    student.IndexNumber = reader["IndexNumber"].ToString();

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
                    e.IdEnrollment= (int)reader["IdEnrollment"];
                    e.Semester = (int)reader["Semester"];
                    e.IdStudy = (int)reader["IdStudy"];
                    e.StartDate = (DateTime) reader["StartDate"];
                    result.Add(e);
                }
            }

            return Ok(result);
        }

    }
}
