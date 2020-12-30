using APBD_Tutorial_10_Entity_Framework.Models;
using APBD_Tutorial_10_Entity_Framework.Services;
using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace APBD_Tutorial_10_Entity_Framework.Logging
{
    public class Logger
    {

        private readonly RequestDelegate _next;

        public Logger(RequestDelegate next)
        {
            _next = next;
        }

        public async Task InvokeAsync(HttpContext httpContext, IStudentServiceDb service)
        {
            //checking that request is not null
            if (httpContext.Request != null)
            {
                //reading body
                httpContext.Request.EnableBuffering();
                string BodyBuffer = "";

                // using (StreamReader reader = new StreamReader(httpContext.Request.Body))
                // Fix for 
                // System.ObjectDisposedException: Cannot access a disposed object.
                // Object name: 'FileBufferingReadStream'.
                // at Microsoft.AspNetCore.WebUtilities.FileBufferingReadStream.ThrowIfDisposed()
                // Last week it was working without those 4 additional parameters I have no idea what happened.
                // I was trying to fix that error about 4 hours :(

                using (StreamReader reader = new StreamReader(httpContext.Request.Body, Encoding.UTF8, true, 1024, true))
                {
                    BodyBuffer = await reader.ReadToEndAsync();
                    httpContext.Request.Body.Position = 0;

                }
                //reading rest of attribiutes and storing them in object of type Log
                var log = new Log
                {
                    Path = httpContext.Request.Path,
                    Method = httpContext.Request.Method,
                    Query = httpContext.Request.QueryString.ToString(),
                    Body = BodyBuffer,
                    Time = DateTime.UtcNow
                };
                //Logging to txt file 
                using (StreamWriter writer = File.AppendText("Log.txt"))
                {
                    string LogString = "TIME : " + log.Time + "\n" + "PATH: " + log.Path + "\n" + "METHOD: " + log.Method + "\n" + "QUERY: " + log.Query + "\n" + "BODY: " + log.Body + "\n\n\n";

                    writer.Write(LogString);

                    writer.Flush();
                    writer.Close();

                }
                //Logging to JSON file
                //seriaizing to JSON
                var serializer = new Newtonsoft.Json.JsonSerializer();
                var stringWriter = new StringWriter();
                using (var writer = new JsonTextWriter(stringWriter))
                {
                    writer.Formatting = Newtonsoft.Json.Formatting.Indented;
                    writer.QuoteName = false;
                    serializer.Serialize(writer, log);
                }
                var jsonString = stringWriter.ToString();
                File.AppendAllText($"Log.json", jsonString);

            }
            if (_next != null)
            {

                await _next(httpContext);
            }

        }


    }

}




