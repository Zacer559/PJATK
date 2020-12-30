
using APBD_Test_01.Models;
using APBD_Test_01.Request;
using APBD_Test_01.Services;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text.RegularExpressions;

namespace APBD_Test_01.Controllers
{
    //Student controller from previous task. Nothing changed.
    [ApiController]
    [Route("api/team-members")]
    public class TestController : ControllerBase
    {
        private readonly ITestServiceDb _dbService;

        public TestController(ITestServiceDb dbService)
        {
            _dbService = dbService;
        }


        [HttpGet("3/tasks")]
        public IActionResult GetStudent(string indexNumber,string type)
        {
            if (Regex.IsMatch(indexNumber, @"^\d+$"))
            {
                if (type.Equals("creator"))
                {
                    var student = _dbService.GetTasksCreator(indexNumber);
                    if (student == null)
                        return NotFound($"No team member with provided index number ({indexNumber})");
                    else
                        return Ok(student);
                }else if (type.Equals("assigned"))
                {
                        var student = _dbService.GetTasksAssigned(indexNumber);
                        if (student == null)
                            return NotFound($"No team member with provided index number ({indexNumber})");
                        else
                            return Ok(student);
                }
                else {
                    return BadRequest($"Provided type ({type}) is not a assigned or creator");


                }
            }
            else {
                return BadRequest($"Provided team member id ({indexNumber}) is not a number  ");
            }
        }

        [HttpPut("2/tasks/{taskId}")]
        public IActionResult UpdateTask2(string taskId,UpdateTaskRequest request)
        {
            if (Regex.IsMatch(taskId, @"^\d+$"))
            {
                string result = _dbService.UpdateTask(taskId, request);
                return Ok(result);
            }
            else
                return BadRequest();
           
           

        }



    }
}
