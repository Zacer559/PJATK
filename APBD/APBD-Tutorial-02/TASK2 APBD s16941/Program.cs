using Newtonsoft.Json;
using System;
using System.IO;
using System.Text.Json;
using System.Xml;
using System.Xml.Serialization;
using TASK2_APBD_s16941.Models;

namespace APBD2
{
    class Program
    {

        static void Main(string[] args)
        {
            var inputPath = args.Length > 0 ? args[0] : @"Files\data.csv";
            var outputPath = args.Length > 1 ? args[1] : @"Files\result";
            var outputType = args.Length > 2 ? args[2] : "xml";
            //Checking paths correctness ( 1.2 Argument Exception )
            //This is the best method which i found in whole internet.
            //If you know better method how to check that String is a valid Path please inform me. 
            try
            {
                Path.GetFullPath(inputPath);
                Path.GetFullPath(outputPath);
            }
            catch (ArgumentException e)
            {
                File.AppendAllText(@"Files\Log.txt", $"{DateTime.UtcNow} ERR specified path is incorrect: {e.Message}\n");
            }

            try
            {

                //1.2 (Checking file exists)
                if (!File.Exists(inputPath))
                    throw new FileNotFoundException("ERR", inputPath.Split("\\")[^1]);

                var university = new University
                {
                    Author = "Dominik Krawiec"
                };
                //Reading from file
                foreach (var line in File.ReadAllLines(inputPath))
                {
                    //cheking if there is 9 arguments in line
                    var splitted = line.Split(",");
                    if (splitted.Length < 9)
                    {
                        File.AppendAllText(@"Files\Log.txt", $"{DateTime.UtcNow} ERR not enough information in line {line}\n");
                        continue;
                    }
                    //checking if all from 9 lines are not empty
                    int countMissing = 0;
                    for (int i = 0; i < splitted.Length; i++)
                    {
                        if (splitted[i].Equals(""))
                        {
                            File.AppendAllText(@"Files\Log.txt", $"{DateTime.UtcNow} ERR empty information in line {line}\n");
                            countMissing++;
                        }


                    }
                    if (countMissing > 0)
                    {
                        continue;
                    }

                    var stud = new Student
                    {
                        indexNumber = splitted[4],
                        firstName = splitted[0],
                        lastName = splitted[1],
                        birthDate = splitted[5],
                        mothersName = splitted[7],
                        fathersName = splitted[8],
                        studies = new Studies
                        {
                            name = splitted[2],
                            mode = splitted[3],
                        },
                        email = splitted[6],

                    };

                    university.Students.Add(stud);

                }
                // adding studies to active studies 
                foreach (Student student in university.Students)
                {
                    var sth = student.studies;
                    var active = new activeStudies
                    {
                        name = sth.name,
                        numberOfStudents = 1,
                    };
                    if (university.getObjectReference(active) != null)
                        university.getObjectReference(active).numberOfStudents++;
                    else
                        university.activeStudies.Add(active);
                }



                //Serializing to xml
                if (outputType.Equals("xml"))
                {
                    using var writer = new FileStream($"{outputPath}.{outputType}", FileMode.Create);
                    XmlSerializerNamespaces ns = new XmlSerializerNamespaces();
                    ns.Add("", "");
                    var serializer = new XmlSerializer(typeof(University));

                    serializer.Serialize(writer, university, ns);
                }
                else if (outputType.Equals("json"))
                {
                   //Serializing to json
                    var forJson = new ForJson
                    {
                        university = university,
                    };
                    var serializer = new Newtonsoft.Json.JsonSerializer();
                    var stringWriter = new StringWriter();
                    using (var writer = new JsonTextWriter(stringWriter))
                    {
                        writer.Formatting = Newtonsoft.Json.Formatting.Indented;
                        writer.QuoteName = false;
                        serializer.Serialize(writer, forJson);
                    }
                    var jsonString = stringWriter.ToString();
                    File.WriteAllText($"{outputPath}.json", jsonString);
                }
                else
                {

                    File.AppendAllText(@"Files\Log.txt", $"{DateTime.UtcNow} Provided file type is not supported : {outputType}\n");
                }

             
            }
            catch (FileNotFoundException e)
            {
                File.AppendAllText(@"Files\Log.txt", $"{DateTime.UtcNow} {e.Message} File not found ({e.FileName})\n");
            }

        }
    }
}
