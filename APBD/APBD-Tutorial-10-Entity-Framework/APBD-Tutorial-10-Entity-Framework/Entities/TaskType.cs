using System;
using System.Collections.Generic;

namespace APBD_Tutorial_10_Entity_Framework.Entities
{
    public partial class TaskType
    {
        public TaskType()
        {
            Task = new HashSet<Task>();
        }

        public int IdTaskType { get; set; }
        public string Name { get; set; }

        public virtual ICollection<Task> Task { get; set; }
    }
}
