using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Models
{
    public class Student
    {
        public int IdStudent { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string IndexNumber { get; set; }
        override
        public string ToString() {
           
            return "{\n   Student id: " + IdStudent + "\n   Student name: " + FirstName + "\n   Student surname: " + LastName + "\n   Student index number: " + IndexNumber + "\n}\n\n";
        }
    }
}
