using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_10_Entity_Framework.DTO.Response
{
    public class PromoteResponse
    {
        public int IdEnrollment { get; set; }
        public int IdStudy { get; set; }
        public int Semester { get; set; }
        public DateTime StartDate { get; set; }

    }
}
