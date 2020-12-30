﻿using APBD_Tutorial_06_API_Middleware_Logging.Models;
using APBD_Tutorial_06_API_Middleware_Logging.Services;
using Microsoft.AspNetCore.Http;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace APBD_Tutorial_06_API_Middleware_Logging.Logging
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
                using (StreamReader reader = new StreamReader(httpContext.Request.Body))
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




