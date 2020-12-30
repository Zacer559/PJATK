
using Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_03_API.Database
{
    public class StudentDatabase : IDatabase
    {

        private static IEnumerable<Student> _students;

        static StudentDatabase()
        {


            _students = new List<Student> {

            new Student{IdStudent=1,FirstName="Andrzej",LastName="Duda",IndexNumber="s1"},
            new Student{IdStudent=2,FirstName="Mateusz",LastName="Morawiecki",IndexNumber="s2"},
            new Student{IdStudent=3,FirstName="Jarosław",LastName="Kaczyński",IndexNumber="s3"}

            };
        }
        public IEnumerable<Student> GetStudents()
        {
            return _students;
        }


        
        override
        public string ToString() {
            string answer="Students: \n\n";
            foreach (Student s in _students) {
                answer = answer + s;
                
            }
            return answer;
        }
    }
}