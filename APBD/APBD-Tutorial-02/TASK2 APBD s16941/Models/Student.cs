using System;
using System.Xml.Serialization;

namespace TASK2_APBD_s16941.Models
{
    public class Student
    {
        [XmlAttribute]
        [Newtonsoft.Json.JsonProperty]

        public String indexNumber { get; set; }
        [Newtonsoft.Json.JsonProperty("fname")]
       
        public String firstName { get; set; }
        [Newtonsoft.Json.JsonProperty("lname")]
      
        public String lastName { get; set; }
        [Newtonsoft.Json.JsonProperty("birthdate")]
     
        public String birthDate { get; set; }
        public String email { get; set; }
        public String mothersName { get; set; }
        public String fathersName { get; set; }
        public Studies studies { get; set; }


        public override bool Equals(object obj)
        {
            Student fooItem = obj as Student;

            if (fooItem == null)
            {
                return false;
            }

            return fooItem.indexNumber == this.indexNumber && fooItem.firstName.Equals(this.firstName) && fooItem.lastName.Equals(this.lastName);
        }
        public override int GetHashCode()
        {
            var hash = indexNumber + firstName + lastName;
            return hash.GetHashCode();
        }
    }
}
