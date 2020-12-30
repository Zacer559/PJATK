using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_07_API_Authentication.DTO.Response
{
    public class FinalTokenResponse
    {
        public Guid RefreshToken { get; set; }
        public string LoginToken { get; set; }
    }
}
