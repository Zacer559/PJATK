using System;
using System.Collections.Generic;

namespace APBD_Tutorial_10_Entity_Framework.Entities
{
    public partial class Task
    {
        public int IdTask { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Deadline { get; set; }
        public int IdProject { get; set; }
        public int IdTaskType { get; set; }
        public int IdAssignedTo { get; set; }
        public int IdCreator { get; set; }

        public virtual TeamMember IdAssignedToNavigation { get; set; }
        public virtual TeamMember IdCreatorNavigation { get; set; }
        public virtual Project IdProjectNavigation { get; set; }
        public virtual TaskType IdTaskTypeNavigation { get; set; }
    }
}
