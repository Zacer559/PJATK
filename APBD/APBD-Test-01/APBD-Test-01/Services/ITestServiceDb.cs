

using APBD_Test_01.Models;
using APBD_Test_01.Request;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace APBD_Test_01.Services
{
    public interface ITestServiceDb
    {
        static string connectionString =
              @"Data Source=localhost;Integrated Security=True";

        TeamMember GetTasksCreator(string indexNumber);
        TeamMember GetTasksAssigned(string indexNumber);

        string UpdateTask(string id, UpdateTaskRequest request);

    }
}
