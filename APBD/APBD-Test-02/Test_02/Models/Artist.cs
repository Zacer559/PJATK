using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Test_02.Models
{
    public class Artist
    {
        public int IdArtist { get; set; }
        public string Nickname { get; set; }

        public Artist()
        {
            Artists_Event = new HashSet<Artist_Event>();
        }
        public virtual ICollection<Artist_Event> Artists_Event { get; set; }
    }
}
