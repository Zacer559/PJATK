using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_06_API_Middleware_Logging.Models
{
    public class Log
    {
        public DateTime Time { get; set; }
       public string Path { get; set; }
       public string Method { get; set; }
        public string Query { get; set; }
       public  string Body { get; set; }

    }
}
