using System;
using System.Collections.Generic;
using System.Diagnostics.Eventing.Reader;
using System.Linq;
using System.Threading.Tasks;
using Test_02.DAO;
using Test_02.DAO.Request;
using Test_02.Models;

namespace Test_02.Services
{
    public class DbService : IDbService
    {
        private readonly MyDbContext dbContext;

        public DbService(MyDbContext studentContext)
        {
            dbContext = studentContext;
        }

        public IEnumerable<ArtistResponse> GetArtist(int ArtistId)
        {
            //creating proper response
            return dbContext.Artist.Where(p => p.IdArtist == ArtistId).Select(e => new ArtistResponse
                {
                    IdArtist = e.IdArtist,
                    Nickname = e.Nickname,
                    Events = dbContext.Event
                        .Where(d => d.IdEvent == e.Artists_Event.Select(z => z.IdEvent).FirstOrDefault())
                        .OrderBy(ev => ev.StartDate).ToList()
                }
            );
        }

        public string UpdatePerformanceDate(UpdateRequest request)
        {
            //using transaction
            using (var dbContextTransaction = dbContext.Database.BeginTransaction())
            {
                //check artist exist
                var ArtistWithGivenID = dbContext.Artist.Where(c => c.IdArtist == request.idArtist);
                if (ArtistWithGivenID.ToList().Count() == 0)
                {
                    return "Artist with specified ID does not exist";
                }

                //check event exist
                var EventWithGivenID = dbContext.Event.Where(c => c.IdEvent == request.idEvent);
                if (EventWithGivenID.ToList().Count() == 0)
                {
                    return "Event with specified ID does not exist";
                }

                //checking tgat event has not started
                if (dbContext.Event.Where(e => e.IdEvent == request.idEvent).Select(e => e.StartDate).FirstOrDefault() >
                    DateTime.Now)
                {
                    // checking that new permormance date is in Event time
                    if (request.performanceDate > dbContext.Event.Where(e => e.IdEvent == request.idEvent)
                        .Select(e => e.StartDate).FirstOrDefault() && request.performanceDate < dbContext.Event
                        .Where(e => e.IdEvent == request.idEvent)
                        .Select(e => e.EndDate).FirstOrDefault())
                    {
                        var result = dbContext.Artist_Event.Where(b => b.IdArtist == request.idArtist)
                            .Where(d => d.IdEvent == request.idEvent).FirstOrDefault();
                        if (result != null)
                        {
                            result.PerformanceDate = request.performanceDate;
                        }
                        else
                        {
                            return "Event Actions with those id not exists";
                        }
                    }
                    else
                    {
                        return "New date not in Event time";
                    }
                }
                else
                {
                    return "Event already started";
                }

                dbContext.SaveChanges();
                //Change to dbContextTransaction.Commit() to save changes in DB
                dbContextTransaction.Rollback();
                return "ok";
            }
        }
    }
}