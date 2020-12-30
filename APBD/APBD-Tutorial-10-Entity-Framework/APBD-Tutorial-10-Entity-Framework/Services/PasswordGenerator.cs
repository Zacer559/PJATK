using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace APBD_Tutorial_10_Entity_Framework.Services
{
    public interface PasswordGenerator
    {
        public static string GenerateSalt()
        {
            byte[] randomBytes = new byte[256 / 8];
            using (RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider())
            {
                rng.GetBytes(randomBytes);
                return Convert.ToBase64String(randomBytes);
            }
        }

        public static string Generate(string value, string salt)

        {
            var valueBytes = KeyDerivation.Pbkdf2(

                password: value,
                salt: Encoding.UTF8.GetBytes(salt),
                prf: KeyDerivationPrf.HMACSHA512,
                iterationCount: 10000,
                numBytesRequested: 256 / 8);

            return Convert.ToBase64String(valueBytes);

        }


      
    }
}
