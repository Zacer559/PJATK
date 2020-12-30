using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Test_02.Models
{
    public class Event
    {
        public int IdEvent { get; set; }
        public string Name { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }

        public Event()
        {
            Artists_Event = new HashSet<Artist_Event>();
            Event_Organiser = new HashSet<Event_Organiser>();
        }

        public virtual ICollection<Artist_Event> Artists_Event { get; set; }
        public virtual ICollection<Event_Organiser> Event_Organiser { get; set; }
    }
}