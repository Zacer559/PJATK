

using APBD_Test_01.Models;
using APBD_Test_01.Request;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;


namespace APBD_Test_01.Services
{
    public class SqlServerTestDbService : ITestServiceDb
    {

        public TeamMember GetTasksCreator(string indexNumber)
        {
            List<Taskk> AssignedList = new List<Taskk>();
            using var con = new SqlConnection(ITestServiceDb.connectionString);
            using var cmd = new SqlCommand

            {
                Connection = con,
                CommandText = @"SELECT s.IdTeamMember,
                                       s.FirstName,
                                       s.LastName,
                                       s.Email 
                                FROM TeamMember s
                                WHERE s.IdTeamMember = @index ;"
            };

            cmd.Parameters.AddWithValue("index", indexNumber);

            con.Open();
            using var dr = cmd.ExecuteReader();
            if (dr.Read())
            {
                using var con2 = new SqlConnection(ITestServiceDb.connectionString);
                using var cmd2 = new SqlCommand

                {
                    Connection = con2,
                    CommandText = @"SELECT s.IdTask,
                                       s.Name,
                                       s.Description,
                                       s.Deadline,
                                       s.IdProject,
                                       s.IdTaskType,
                                       s.IdAssignedTo,
                                       s.IdCreator,
                                       c.Name AS name2,
                                       p.Name AS name3
                                FROM Task s
                                INNER JOIN TaskType c
                                ON c.IdTaskType = s.IdTaskType
                                INNER JOIN Project p
                                ON p.IdProject = s.IdProject
                                WHERE s.IdCreator = @indexx
                                ORDER BY s.Deadline DESC;"
                };

                cmd2.Parameters.AddWithValue("indexx", indexNumber);

                con2.Open();
                using var dr2 = cmd2.ExecuteReader();


                if (dr2.Read())
                {
                    var task = new Taskk
                    {
                        IdTask = (int)dr2["IdTask"],
                        Name = dr2["Name"].ToString(),
                        Description = dr2["Description"].ToString(),
                        Deadline = DateTime.Parse(dr2["Deadline"].ToString()),
                        IdProject = (int)dr2["IdProject"],
                        IdTaskType = (int)dr2["IdTaskType"],
                        IdAssignedTo = (int)dr2["IdAssignedTo"],
                        IdCreator = (int)dr2["IdCreator"],
                        Project = dr2["name2"].ToString(),
                        TaskType = dr2["name3"].ToString()
                    };
                    AssignedList.Add(task);
                }
                return new TeamMember
                {
                    IndexNumber = dr["IdTeamMember"].ToString(),
                    FirstName = dr["FirstName"].ToString(),
                    LastName = dr["LastName"].ToString(),
                    Email = dr["Email"].ToString(),
                    TaskList = AssignedList

                };
            }
            else
                return null;
        }
        public TeamMember GetTasksAssigned(string indexNumber)
        {
            List<Taskk> AssignedList = new List<Taskk>();
            using var con = new SqlConnection(ITestServiceDb.connectionString);
            using var cmd = new SqlCommand

            {
                Connection = con,
                CommandText = @"SELECT s.IdTeamMember,
                                       s.FirstName,
                                       s.LastName,
                                       s.Email 
                                FROM TeamMember s
                                WHERE s.IdTeamMember = @index ;"
            };

            cmd.Parameters.AddWithValue("index", indexNumber);

            con.Open();
            using var dr = cmd.ExecuteReader();
            if (dr.Read())
            {
                using var con2 = new SqlConnection(ITestServiceDb.connectionString);
                using var cmd2 = new SqlCommand

                {
                    Connection = con2,
                    CommandText = @"SELECT s.IdTask,
                                       s.Name,
                                       s.Description,
                                       s.Deadline,
                                       s.IdProject,
                                       s.IdTaskType,
                                       s.IdAssignedTo,
                                       s.IdCreator,
                                       c.Name AS name2,
                                       p.Name AS name3
                                FROM Task s
                                INNER JOIN TaskType c
                                ON c.IdTaskType = s.IdTaskType
                                INNER JOIN Project p
                                ON p.IdProject = s.IdProject
                                WHERE s.IdAssignedTo = @indexx
                                ORDER BY s.Deadline DESC;"
                };

                cmd2.Parameters.AddWithValue("indexx", indexNumber);

                con2.Open();
                using var dr2 = cmd2.ExecuteReader();


                if (dr2.Read())
                {
                    var task = new Taskk
                    {
                        IdTask = (int)dr2["IdTask"],
                        Name = dr2["Name"].ToString(),
                        Description = dr2["Description"].ToString(),
                        Deadline = DateTime.Parse(dr2["Deadline"].ToString()),
                        IdProject = (int)dr2["IdProject"],
                        IdTaskType = (int)dr2["IdTaskType"],
                        IdAssignedTo = (int)dr2["IdAssignedTo"],
                        IdCreator = (int)dr2["IdCreator"],
                        Project = dr2["name2"].ToString(),
                        TaskType = dr2["name3"].ToString()




                    };
                    AssignedList.Add(task);
                }
                return new TeamMember
                {
                    IndexNumber = dr["IdTeamMember"].ToString(),
                    FirstName = dr["FirstName"].ToString(),
                    LastName = dr["LastName"].ToString(),
                    Email = dr["Email"].ToString(),
                    TaskList = AssignedList

                };
            }
            else
                return null;
        }











        public string UpdateTask(string id, UpdateTaskRequest request)
        {
            using (SqlConnection connection = new SqlConnection(ITestServiceDb.connectionString))
            {
                int idStudy;
                int enrollmentId;
                using (SqlCommand command = new SqlCommand())
                {
                    command.CommandText = "SELECT * FROM Tasks WHERE IdTask=@id ";
                    command.Parameters.AddWithValue("id", id);
                    command.Connection = connection;
                    connection.Open();
                    var query = command.ExecuteReader();
                    if (!query.Read())
                    {
                        return "TASK NOT EXIST";
                    }
                    query.Close();
                }

                using (SqlCommand command = new SqlCommand())
                {
                    // Finding for enrollment on which student should be enrolled
                    command.CommandText = @"SELECT * FROM TaskType WHERE IdTaskType = @idd";
                    command.Parameters.AddWithValue("idd", request.TaskType.IdTaskType);
                    command.Connection = connection;
                    var IdEnroll = command.ExecuteReader();

                    if (!IdEnroll.Read())
                    {
                        using (SqlCommand command2 = new SqlCommand())
                        {
                            // Finding for enrollment on which student should be enrolled
                            command2.CommandText = @"Insert INTO TaskType VALUES(@iddd,@namee);";
                            command2.Parameters.AddWithValue("iddd", request.TaskType.IdTaskType);
                            command2.Parameters.AddWithValue("namee", request.TaskType.Name);
                            command2.Connection = connection;
                            command2.ExecuteNonQuery();
                        }

                        {
                            //If enrollment not found creating new enrollment
                            IdEnroll.Close();



                            using (SqlCommand enrollCommand = new SqlCommand())
                            {   //Finding max enrollment ID
                                enrollCommand.CommandText = @"Update Task SET
                                                            Name=@Nameee,
                                                            Description=@Description,
                                                            Deadline=@Deadline,
                                                            IdProject=@IdProject,
                                                            IdTaskType=@IdTaskType,
                                                            IdCreator=@IdCreator,
                                                            IdAssignedTo=@IdAssignedTo
                                                            WHERE IdTask=@id3;";
                                command.Parameters.AddWithValue("id3", id);
                                command.Parameters.AddWithValue("Nameee", request.Name);
                                command.Parameters.AddWithValue("Description", request.Description);
                                command.Parameters.AddWithValue("Deadline", request.Deadline);
                                command.Parameters.AddWithValue("IdProject", request.IdProject);
                                command.Parameters.AddWithValue("IdTaskType", request.TaskType.IdTaskType);
                                command.Parameters.AddWithValue("IdCreator", request.IdCreator);
                                command.Parameters.AddWithValue("IdCreator", request.IdAssignedTo);
                                enrollCommand.Connection = connection;
                                enrollCommand.ExecuteNonQuery();

                            }


                        }
                    }
                }
            }
            return "Updated";
        }

    }
}

