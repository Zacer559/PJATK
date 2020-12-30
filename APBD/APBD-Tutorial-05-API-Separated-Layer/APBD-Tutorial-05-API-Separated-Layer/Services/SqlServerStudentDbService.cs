
using APBD_Tutorial_05_API_Separated_Layer.DTO;
using APBD_Tutorial_05_API_Separated_Layer.DTO.Request;
using APBD_Tutorial_05_API_Separated_Layer.DTO.Response;
using System;
using System.Data.SqlClient;


namespace APBD_Tutorial_05_API_Separated_Layer.Services
{
    public class SqlServerStudentDbService : IStudentServiceDb
    {
        public EnrollStudentResponse EnrollStudent(EnrollStudentRequest request)
        {
            using (SqlConnection connection = new SqlConnection(IStudentServiceDb.connectionString))
            {
                int idStudy;
                int enrollmentId;
                using (SqlCommand command = new SqlCommand())
                {
                    //find Id of study on which student want be enrollment
                    command.CommandText = "SELECT IdStudy FROM Studies WHERE Name=@name";
                    command.Parameters.AddWithValue("name", request.Study);
                    command.Connection = connection;
                    connection.Open();
                    var query = command.ExecuteReader();
                    if (!query.Read())
                    {
                        return null;
                    }
                    idStudy = (int)query["IdStudy"];
                    query.Close();
                }

                using (SqlCommand command = new SqlCommand())
                {
                    // Finding for enrollment on which student should be enrolled
                    command.CommandText = @"SELECT IdEnrollment FROM Enrollment WHERE Semester=1 and IdStudy=@id";
                    command.Parameters.AddWithValue("id", idStudy);
                    command.Connection = connection;
                    var IdEnroll = command.ExecuteReader();

                    if (IdEnroll.Read())
                    {
                        enrollmentId = (int)IdEnroll["IdEnrollment"];
                        IdEnroll.Close();
                    }
                    else
                    {
                        //If enrollment not found creating new enrollment
                        IdEnroll.Close();
                        using (SqlCommand enrollCommand = new SqlCommand())
                        {   //Finding max enrollment ID
                            enrollCommand.CommandText = @"SELECT MAX(IdEnrollment) AS maxx FROM Enrollment";
                            enrollCommand.Connection = connection;
                            var max = enrollCommand.ExecuteReader();
                            if (max.Read())
                            {
                                enrollmentId = (int)max["maxx"] + 1;
                            }
                            else
                            {
                                enrollmentId = 1;
                            }
                            max.Close();

                        }
                        //adding new enrollment
                        using (SqlCommand sqlCommand = new SqlCommand())
                        {
                            sqlCommand.CommandText = @"INSERT INTO Enrollment VALUES(@id,@semester,@IdStudy,@startDate)";
                            sqlCommand.Parameters.AddWithValue("Id", enrollmentId);
                            sqlCommand.Parameters.AddWithValue("semester", 1);
                            sqlCommand.Parameters.AddWithValue("IdStudy", idStudy);
                            sqlCommand.Parameters.AddWithValue("startdate", DateTime.Now);
                            sqlCommand.Connection = connection;
                            sqlCommand.ExecuteNonQuery();

                        }

                    }

                }

                using (SqlCommand command = new SqlCommand())
                {
                    //Checking if student with that index already exist
                    command.CommandText = @"SELECT * FROM Student WHERE IndexNumber=@index";
                    command.Parameters.AddWithValue("index", request.IndexNumber);
                    command.Connection = connection;
                    var index = command.ExecuteReader();
                    if (index.Read())
                    {
                        return null;
                    }

                    index.Close();
                }

                using (SqlCommand commandInsert = new SqlCommand())
                {
                    //if not exist then add him.
                    commandInsert.CommandText = @"INSERT INTO Student VALUES(@indexnumber,@firstname,@lastname,@birthdate,@idenrollment)";
                    commandInsert.Connection = connection;
                    commandInsert.Parameters.AddWithValue("indexnumber", request.IndexNumber);
                    commandInsert.Parameters.AddWithValue("firstname", request.Name);
                    commandInsert.Parameters.AddWithValue("lastname", request.Surname);
                    commandInsert.Parameters.AddWithValue("birthdate", request.Birthdate);
                    commandInsert.Parameters.AddWithValue("idenrollment", enrollmentId);
                    commandInsert.ExecuteNonQuery();

                }

                using (SqlCommand checkCommand = new SqlCommand())
                {   //checking that the student was inserted
                    checkCommand.CommandText = @"SELECT * FROM Student WHERE IndexNumber=@indexnumber";
                    checkCommand.Connection = connection;
                    checkCommand.Parameters.AddWithValue("indexnumber", request.IndexNumber);
                    var buff = checkCommand.ExecuteReader();
                    if (buff.Read())
                    {
                        var id = buff["IndexNumber"].ToString();
                        var Firstname = buff["FirstName"].ToString();
                        var Lastname = buff["LastName"].ToString();
                        var Birthdate = (DateTime)buff["Birthdate"];
                        var IdEnrollment = (int)buff["IdEnrollment"];
                        buff.Close();
                        return new EnrollStudentResponse
                        {
                            IndexNumber = id,
                            FirstName = Firstname,
                            LastName = Lastname,
                            Birthdate = Birthdate,
                            IdEnrollment = IdEnrollment

                        };
                    }
                return null;


            }


            }

          
        }



        public PromoteResponse PromoteStudent(PromoteRequest request)
        {
            using (SqlConnection connection = new SqlConnection(IStudentServiceDb.connectionString))
            using (SqlCommand command = new SqlCommand())
            {
                //executing procedure
                command.CommandText = "PromoteStudent";
                command.CommandType = System.Data.CommandType.StoredProcedure;
                command.Connection = connection;
                command.Parameters.AddWithValue("@studyName", request.Study);
                command.Parameters.AddWithValue("@semester", request.Semester);

                connection.Open();
                try
                {
                    var reader = command.ExecuteReader();
                    if (reader.Read())
                    {
                        int id = (int)reader["IdEnrollment"];
                        var sem = reader["semester"].ToString();
                        var idStudy = (int)reader["IdStudy"];
                        var startDate = (DateTime)reader["StartDate"];

                        return new PromoteResponse
                        {
                            IdEnrollment = id,
                            IdStudy = idStudy,
                            Semester = sem,
                            StartDate = startDate
                        };
                    }
                  
                        return null;
                  
                }
                catch (SqlException e)
                {
                    return null;
                }

            }
        }
    }
}
