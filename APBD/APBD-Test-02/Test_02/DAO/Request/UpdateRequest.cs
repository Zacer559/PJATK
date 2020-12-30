using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Test_02.DAO.Request
{
    public class UpdateRequest
    {
        public int idArtist { get; set; }
        public int idEvent { get; set; }
        public DateTime performanceDate { get; set; }
       
    }
}