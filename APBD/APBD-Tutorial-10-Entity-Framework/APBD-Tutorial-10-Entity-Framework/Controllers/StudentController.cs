

using APBD_Tutorial_10_Entity_Framework.DTO.Request;
using APBD_Tutorial_10_Entity_Framework.Models;
using APBD_Tutorial_10_Entity_Framework.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore.Infrastructure;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;

namespace APBD_Tutorial_10_Entity_Framework.Controllers
{
    //Student controller from previous task. Nothing changed.
    [ApiController]
    [Route("api/student")]
    public class StudentController : ControllerBase
    {
        private readonly IStudentServiceDb _dbService;


        public StudentController(IStudentServiceDb dbService)
        {
            _dbService = dbService;
        }

        [HttpGet]
        public IActionResult GetStudents()
        {
            var result = _dbService.GetStudents();
            if (!result.Any())
                return Ok("There are no students!");
            else
                return Ok(result);

        }

        [HttpPut("add")]
        public IActionResult addStudent(AddStudentRequest request)
        {
            var result = _dbService.AddStudent(request);
            if (result)
                return Ok("Student added successfully");
            else
                return BadRequest("Student with following ID already exist");

        }
        [HttpDelete("delete/{indexNumber}")]
        public IActionResult addStudent(string indexNumber)
        {
            var result = _dbService.DeleteStudent(indexNumber);
            if (result)
                return Ok("Student removed successfully");
            else
                return BadRequest("No student with following id");

        }
        [HttpPut("update")]
        public IActionResult updateStudent(UpdateStudentRequest request)
        {
            var result = _dbService.UpdateStudent(request);
            if (result)
                return Ok("Student updated successfully");
            else
                return BadRequest("No student with following id");

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
        public IActionResult GetSemester(string id)
        {
            var sem = _dbService.GetSemester(id);
            if (sem.Any())
                return Ok(sem);
            else
                return BadRequest("Student dont have any semesters");
        }

    }
}


