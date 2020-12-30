using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;
using System.Xml.Serialization;

namespace TASK2_APBD_s16941.Models
{
    public class University
    {



        public University()
        {
            Students = new HashSet<Student>();
            CreationDate = DateTime.Now.ToString("yyyy-MM-dd");
            activeStudies = new HashSet<activeStudies>();
        }

        [Newtonsoft.Json.JsonProperty("CreatedAt")]
        [XmlAttribute(AttributeName = "CreatedAt")]
        public string CreationDate { get; set; }
        [XmlAttribute]
        [Newtonsoft.Json.JsonProperty]
        public string Author { get; set; }



        public HashSet<Student> Students { get; set; }
        public HashSet<activeStudies> activeStudies { get; set; }
        public activeStudies getObjectReference( activeStudies obj)
        {
            if (activeStudies.Contains(obj))
            {
                foreach (activeStudies o in activeStudies)
                {
                    if (obj.Equals(o))
                        return o;
                }
            }
            return null;
        }
        public Student getObjectReference(Student obj)
        {
            if (Students.Contains(obj))
            {
                foreach (Student o in Students)
                {
                    if (obj.Equals(o))
                        return o;
                }
            }
            return null;
        }
    }
}
