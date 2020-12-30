using Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_03_API.Database
{
    interface IDatabase
    {
        public IEnumerable<Student> GetStudents();
    }
}
