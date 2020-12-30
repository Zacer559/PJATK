using System.Xml.Serialization;

namespace TASK2_APBD_s16941.Models
{
    public class activeStudies
    {
        [XmlAttribute(AttributeName = "studies")]
        public string name { get; set; }
        [XmlAttribute]
        public int numberOfStudents { get; set; }
      

        public override bool Equals(object obj)
        {
            activeStudies fooItem = obj as activeStudies;

            if (fooItem == null)
            {
                return false;
            }
          
            return fooItem.name == this.name;
        }
        public override int GetHashCode()
        {
            
            return name.GetHashCode();
        }


    }
   
}
