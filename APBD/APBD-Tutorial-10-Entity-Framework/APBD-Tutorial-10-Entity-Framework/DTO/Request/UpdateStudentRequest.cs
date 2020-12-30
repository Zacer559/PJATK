using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_10_Entity_Framework.DTO.Request
{
    public class UpdateStudentRequest
    {
        [Required]
        public string IndexNumber { get; set; }
        [Required]
        public string FirstName { get; set; }
        [Required]
        public string LastName { get; set; }
        [Required]
        public string Password { get; set; }
        [Required]
        public DateTime Birthdate { get; set; }
        [Required]
        public int EnrollmentId { get; set; }
    }
}
