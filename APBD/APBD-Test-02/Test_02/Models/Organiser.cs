using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Test_02.Models
{
    public class Organiser
    {
        public int IdOrganiser { get; set; }
        public string Name { get; set; }
        public Organiser()
        {
            Event_Organiser = new HashSet<Event_Organiser>();
        }
        public virtual ICollection<Event_Organiser> Event_Organiser { get; set; }
    }
}
