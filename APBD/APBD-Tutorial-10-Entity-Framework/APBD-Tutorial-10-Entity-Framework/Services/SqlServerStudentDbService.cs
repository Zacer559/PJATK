
using APBD_Tutorial_10_Entity_Framework.DTO;
using APBD_Tutorial_10_Entity_Framework.DTO.Request;
using APBD_Tutorial_10_Entity_Framework.DTO.Response;
using APBD_Tutorial_10_Entity_Framework.Entities;
using APBD_Tutorial_10_Entity_Framework.Models;
using APBD_Tutorial_10_Entity_Framework.Services;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using Student = APBD_Tutorial_10_Entity_Framework.Models.Student;

namespace APBD_Tutorial_10_Entity_Framework.Services
{
    public class SqlServerStudentDbService : IStudentServiceDb, PasswordGenerator

    {

        private readonly StudentContext context;
        public SqlServerStudentDbService(StudentContext context)
        {
            this.context = context;
        }
        ////////////////////////////////
        ///           TASK 2         ///
        ////////////////////////////////

        public IEnumerable<Student> GetStudents()
        {
            //executing query
            var result = context.Student
                .Include(s => s.IdEnrollmentNavigation)
                .ThenInclude(s => s.IdStudyNavigation)
                .Select(st => new Models.Student
                {
                    IndexNumber = st.IndexNumber,
                    FirstName = st.FirstName,
                    LastName = st.LastName,
                    Birthdate = st.BirthDate,
                    Semester = st.IdEnrollmentNavigation.Semester,
                    Studies = st.IdEnrollmentNavigation.IdStudyNavigation.Name
                }).ToList();

            return result;
        }



        public bool AddStudent(AddStudentRequest request)
        {
            //check that student with that id already exist

            if (isStudentExisting(request.IndexNumber))
                return false;
            //check if salt exists if yes remove it
            if (isSaltExisting(request.IndexNumber))
                deleteSalt(request.IndexNumber);
            //generate salt
            var salt = PasswordGenerator.GenerateSalt();
            //save salt to DB
            context.Salts.Add(new Salts
            {
                IdSalt = request.IndexNumber,
                Value = salt
            });
            //encrypt password
            var encryptedPassword = PasswordGenerator.Generate(request.Password, salt);
            //add student to DB
            context.Student.Add(new Entities.Student
            {
                IndexNumber = request.IndexNumber,
                FirstName = request.FirstName,
                LastName = request.LastName,
                Passwordd = encryptedPassword,
                BirthDate = request.Birthdate,
                IdEnrollment = request.EnrollmentId

            });
            //Comment if you dont want to save changes
            context.SaveChanges();
            return true;
        }


        public bool DeleteStudent(string indexNumber)
        {
            //checking if student exist
            if (!isStudentExisting(indexNumber))
                return false;
            //deleting salt if exist
            if (isSaltExisting(indexNumber))
                deleteSalt(indexNumber);
            //deleting student
            var toDelete = context.Student.FirstOrDefault(s => s.IndexNumber == indexNumber);
            context.Student.Remove(toDelete);
            //Comment if you dont want to save changes
            context.SaveChanges();
            return true;
        }


        public bool UpdateStudent(UpdateStudentRequest request)
        {
            //checking if student exist
            if (!isStudentExisting(request.IndexNumber))
                return false;
            //update salt
            if (isSaltExisting(request.IndexNumber))
                deleteSalt(request.IndexNumber);
            var salt = PasswordGenerator.GenerateSalt();
            //save salt to DB
            context.Salts.Add(new Salts
            {
                IdSalt = request.IndexNumber,
                Value = salt
            });
            //encrypt password
            var encryptedPassword = PasswordGenerator.Generate(request.Password, salt);
            // updating student
            var toChange = context.Student.FirstOrDefault(s => s.IndexNumber == request.IndexNumber);
            toChange.FirstName = request.FirstName;
            toChange.LastName = request.LastName;
            toChange.Passwordd = encryptedPassword;
            toChange.IdEnrollment = request.EnrollmentId;
            //Comment if you dont want to save changes
            context.SaveChanges();
            return true;
        }
        /// Some help methods
        private bool isSaltExisting(string id)
        {
            return context.Salts.Where(s => s.IdSalt == id).Any();
        }
        private void deleteSalt(string id)
        {
            var n = context.Salts.FirstOrDefault(s => s.IdSalt == id);
            context.Salts.Remove(n);
            //Comment if you dont want to save changes
            context.SaveChanges();
        }
        private bool isStudentExisting(string id)
        {
            return context.Student.Where(s => s.IndexNumber == id).Any();
        }







        ////////////////////////////////
        ///           TASK 3         ///
        ////////////////////////////////
        public EnrollStudentResponse EnrollStudent(EnrollStudentRequest request)
        {


            //get studies ID
            var idOfStudies = context.Studies.FirstOrDefault(studies => studies.Name == request.Study);
            //if null return
            if (idOfStudies == null)
                return null;

            //get enrollmentId
            int? enrollmentId = context.Enrollment.FirstOrDefault(en => en.Semester == 1 && en.IdStudy == idOfStudies.IdStudy).IdStudy;
            //if enrollment dont exist
            if (enrollmentId == null)
            {   //get max id from enrollments
                int? max = context.Enrollment.Max(en => en.IdEnrollment);
                //if there is no enrollment in db
                if (max == null)
                    return null;
                //adding new enrollment
                context.Enrollment.Add(new Entities.Enrollment
                {
                    IdEnrollment = (int)max,
                    Semester = 1,
                    IdStudy = idOfStudies.IdStudy,
                    StartDate = DateTime.Now
                });

            }


            //chekcing if student exists
            if (!isStudentExisting(request.IndexNumber))
            {
                //if not existing add him
                context.Student.Add(new Entities.Student
                {
                    IndexNumber = request.IndexNumber,
                    FirstName = request.Name,
                    LastName = request.Surname,
                    Passwordd = "CHANGE ME",
                    BirthDate = request.Birthdate,
                    IdEnrollment = (int)enrollmentId
                });
            }

            //checking that the student was inserted

            var student = context.Student.FirstOrDefault(s => s.IndexNumber == request.IndexNumber);
            if (student != null)
            {
                return new EnrollStudentResponse
                {
                    IndexNumber = student.IndexNumber,
                    FirstName = student.FirstName,
                    LastName = student.LastName,
                    Birthdate = student.BirthDate,
                    IdEnrollment = student.IdEnrollment

                };
            }

            return null;


        }



        public PromoteResponse PromoteStudent(PromoteRequest request)
        {
            using (SqlConnection connection = new SqlConnection(IStudentServiceDb.connectionString))
            using (SqlCommand command = new SqlCommand())
            {
                int nextEnrollmentId2;
                //check that studies and semester exists
                var idStudy = context.Studies.FirstOrDefault(studies => studies.Name == request.Study);
                var sem = context.Enrollment.FirstOrDefault(enrollment => enrollment.Semester == request.Semester);
                if (idStudy == null || sem == null)
                    return null;

                //checking that exist next semester (when we are on semester 2 then if semester 3 exists)
                var nextEnrollment = context.Enrollment.FirstOrDefault(en => en.IdStudy == idStudy.IdStudy && en.Semester == (request.Semester + 1));
                //if not create it
                if (nextEnrollment == null)
                {
                    nextEnrollmentId2 = (context.Enrollment.Max(e => e.IdEnrollment)) + 1;

                    context.Enrollment.Add(new Entities.Enrollment
                    {
                        IdEnrollment = nextEnrollmentId2,
                        Semester = (request.Semester + 1),
                        IdStudy = idStudy.IdStudy,
                        StartDate = DateTime.Now
                    });

                }
                else
                {
                    // else the id is equal to founded ealier
                    nextEnrollmentId2 = nextEnrollment.IdEnrollment;
                }
                //geting current id
                int currentEnrollmentId = context.Enrollment.FirstOrDefault(e => e.IdStudy == idStudy.IdStudy && e.Semester == request.Semester).IdEnrollment;
                //geting list of students to update their idEnrollment
                var StudentsToUpdate = context.Student.Where(s => s.IdEnrollment == currentEnrollmentId).ToList();
                // Updating students
                foreach (Entities.Student student in StudentsToUpdate)
                {
                    student.IdEnrollment = nextEnrollmentId2;
                }
                //cheking 
                var respObject = context.Enrollment.FirstOrDefault(e => e.IdEnrollment == nextEnrollmentId2);
                if (respObject == null)
                    return null;

                //Comment if you dont want to save changes
                context.SaveChanges();


                //returning response (info about new Enrollment)
                return new PromoteResponse
                {
                    IdEnrollment = respObject.IdEnrollment,
                    IdStudy = respObject.IdStudy,
                    Semester = respObject.Semester,
                    StartDate = respObject.StartDate
                };
            }
        }





        ////////////////////////////////
        ///         OLD TASKS        ///
        ///   OF COURSE REWRITED TO  ///
        ///     ENTITY FRAMEWORK     ///
        ////////////////////////////////




        public Student GetStudent(string indexNumber)
        {

            var student = context.Student.FirstOrDefault(s => s.IndexNumber == indexNumber);
            if (student == null)
                return null;

            return new Models.Student
            {
                IndexNumber = student.IndexNumber,
                FirstName = student.FirstName,
                LastName = student.LastName,
                Birthdate = student.BirthDate

            };

        }
        public LoginResponse Login(LoginRequest loginRequest)
        {

            //Getting salt
            var saltObject = context.Salts.FirstOrDefault(s => s.IdSalt == loginRequest.Login);
            if (saltObject == null)
                return null;

            //salting password
            loginRequest.Password = PasswordGenerator.Generate(loginRequest.Password, saltObject.Value);

            var User = context.Student.FirstOrDefault(s => s.IndexNumber == loginRequest.Login && s.Passwordd == loginRequest.Password);
            if (User == null)
                return null;

            //if exists generate response
            return new LoginResponse
            {
                Login = User.IndexNumber,
                Name = User.LastName
            };
        }


        public void SaveToken(string login, string token)
        {
            //adding token to DB
            context.Tokens.Add(new Tokens
            {
                Login = login,
                Token = token
            });
        }




        public TokenResponse CheckToken(string token)
        {
            var TokenObject = context.Tokens.FirstOrDefault(t => t.Token == token);
            //if token exist
            if (TokenObject == null)
                return null;
            // create response with refresh token
            TokenResponse response = new TokenResponse();
            response.Login = TokenObject.Login;
            // remove old refresh token
            context.Remove(TokenObject);

            return response;



        }
        public IEnumerable<Models.Enrollment> GetSemester(string indexNumber)
        {
            return context.Student.Include(s => s.IdEnrollmentNavigation).Where(s => s.IndexNumber == indexNumber).Select(st => new Models.Enrollment
            {

                IdEnrollment = st.IdEnrollment,
                Semester = st.IdEnrollmentNavigation.Semester,
                IdStudy = st.IdEnrollmentNavigation.IdStudy,
                StartDate = st.IdEnrollmentNavigation.StartDate
            });
        }
    }


}






