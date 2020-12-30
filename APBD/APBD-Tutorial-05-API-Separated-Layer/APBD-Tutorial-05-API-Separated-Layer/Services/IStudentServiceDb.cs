
using APBD_Tutorial_05_API_Separated_Layer.DTO;
using APBD_Tutorial_05_API_Separated_Layer.DTO.Request;
using APBD_Tutorial_05_API_Separated_Layer.DTO.Response;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
namespace APBD_Tutorial_05_API_Separated_Layer.Services
{
    public interface IStudentServiceDb
    {
        static string connectionString =
              @"Data Source=DESKTOP-6AJN4SI;Initial Catalog=school;Integrated Security=True";
        EnrollStudentResponse EnrollStudent(EnrollStudentRequest request);
        PromoteResponse PromoteStudent(PromoteRequest request);
    }
}
