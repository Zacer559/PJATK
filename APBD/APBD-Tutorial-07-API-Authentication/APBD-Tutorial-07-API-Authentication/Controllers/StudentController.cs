
using APBD_Tutorial_07_API_Authentication.Models;
using APBD_Tutorial_07_API_Authentication.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace APBD_Tutorial_07_API_Authentication.Controllers
{
    //Student controller from previous task. Nothing changed.
    [ApiController]
    [Route("api/student")]
    public class StudentController : ControllerBase { 
    private readonly IStudentServiceDb _dbService;

    public StudentController(IStudentServiceDb dbService)
    {
        _dbService = dbService;
    }

    [HttpGet]
    public IActionResult GetStudents()
    {
        return Ok(_dbService.GetStudents());
    }

    [HttpGet("secured/{indexNumber}")]
    public IActionResult GetStudent(string indexNumber)
    {
        var student = _dbService.GetStudent(indexNumber);
        if (student == null)
            return NotFound($"No students with provided index number ({indexNumber})");
        else
            return Ok(student);
    }

    [HttpGet("{id}")]
        public IActionResult GetSemester(string id) {
            var result = new List<Enrollment>();

            using (SqlConnection connection = new SqlConnection(IStudentServiceDb.connectionString))
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
