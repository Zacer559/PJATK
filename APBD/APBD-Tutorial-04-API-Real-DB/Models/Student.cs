using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_04_API_Real_DB.Models
{
    public class Student
    {
        public string IndexNumber { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Birthdate { get; set; }
        public string IdEnroll { get; set; }
    }
}
