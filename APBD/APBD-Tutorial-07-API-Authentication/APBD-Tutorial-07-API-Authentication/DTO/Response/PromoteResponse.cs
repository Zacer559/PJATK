using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_07_API_Authentication.DTO.Response
{
    public class PromoteResponse
    {
        public int IdEnrollment { get; set; }
        public int IdStudy { get; set; }
        public string Semester { get; set; }
        public DateTime StartDate { get; set; }

    }
}
