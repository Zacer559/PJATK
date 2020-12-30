using APBD_Test_01.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Test_01.Request
{
    public class UpdateTaskRequest
    {
    
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Deadline { get; set; }
        public int IdProject { get; set; }
        public int IdAssignedTo { get; set; }
        public int IdCreator { get; set; }
        public TaskType TaskType { get; set; }


    }
}
