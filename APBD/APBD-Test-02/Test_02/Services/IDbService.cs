using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Test_02.DAO;
using Test_02.DAO.Request;
using Test_02.Models;

namespace Test_02.Services
{
   public interface IDbService
    {
         public IEnumerable<ArtistResponse> GetArtist(int ArtistId);
         public string UpdatePerformanceDate(UpdateRequest request);

    }
}
