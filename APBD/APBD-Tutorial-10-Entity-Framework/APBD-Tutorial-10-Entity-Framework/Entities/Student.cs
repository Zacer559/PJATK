using System;
using System.Collections.Generic;

namespace APBD_Tutorial_10_Entity_Framework.Entities
{
    public partial class Student
    {
        public string IndexNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Passwordd { get; set; }
        public DateTime BirthDate { get; set; }
        public int IdEnrollment { get; set; }

        public virtual Enrollment IdEnrollmentNavigation { get; set; }
        public virtual Salts Salts { get; set; }
    }
}
