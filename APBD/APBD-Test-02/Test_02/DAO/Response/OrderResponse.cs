using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Routing.Constraints;
using Test_02.Models;

namespace Test_02.DAO
{
    public class ArtistResponse
    {
        public int IdArtist { get; set; }
        public string Nickname { get; set; }
        public List<Event> Events { get; set; }

    }
}
