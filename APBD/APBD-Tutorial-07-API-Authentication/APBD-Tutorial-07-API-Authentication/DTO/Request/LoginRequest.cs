using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_07_API_Authentication.DTO.Request
{
    public class LoginRequest
    {
        public string Login { get; set; }

        public string Password { get; set; }
    }
}

