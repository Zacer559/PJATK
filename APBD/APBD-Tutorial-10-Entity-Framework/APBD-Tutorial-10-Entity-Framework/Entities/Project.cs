using System;
using System.Collections.Generic;

namespace APBD_Tutorial_10_Entity_Framework.Entities
{
    public partial class Project
    {
        public Project()
        {
            Task = new HashSet<Task>();
        }

        public int IdProject { get; set; }
        public string Name { get; set; }
        public DateTime Deadline { get; set; }

        public virtual ICollection<Task> Task { get; set; }
    }
}
