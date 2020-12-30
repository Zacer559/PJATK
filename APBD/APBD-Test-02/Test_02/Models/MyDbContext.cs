using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace Test_02.Models
{
    public class MyDbContext : DbContext
    {
        public DbSet<Artist> Artist { get; set; }
        public DbSet<Organiser> Organiser { get; set; }
        public DbSet<Event> Event { get; set; }
        public DbSet<Event_Organiser> Event_Organiser { get; set; }
        public DbSet<Artist_Event> Artist_Event { get; set; }

        public MyDbContext(DbContextOptions<MyDbContext> options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Artist>(entity =>
            {
                entity.HasKey(e => e.IdArtist).HasName("Artist_PK");
                entity.Property(e => e.Nickname).HasMaxLength(30).IsRequired();
            });


            modelBuilder.Entity<Organiser>(entity =>
            {
                entity.HasKey(e => e.IdOrganiser).HasName("Organiser_PK");
                entity.Property(e => e.Name).HasMaxLength(30).IsRequired();
            });


            modelBuilder.Entity<Event>(entity =>
            {
                entity.HasKey(e => e.IdEvent).HasName("Event_PK");
                entity.Property(e => e.Name).HasMaxLength(100).IsRequired();
                entity.Property(e => e.StartDate).HasDefaultValueSql("GETDATE()").IsRequired();
                entity.Property(e => e.EndDate).IsRequired();
            });


            modelBuilder.Entity<Artist_Event>(entity =>
            {
                entity.HasKey(e => new {e.IdEvent, e.IdArtist}).HasName("Artist_Event_PK");
                entity.Property(e => e.PerformanceDate).HasDefaultValueSql("GETDATE()").IsRequired();
                entity.HasOne(e => e.Event)
                    .WithMany(d => d.Artists_Event)
                    .HasForeignKey(p => p.IdEvent)
                    .OnDelete(DeleteBehavior.Restrict)
                    .HasConstraintName("Artist_Event_Event_FK");
                entity.HasOne(e => e.Artist)
                    .WithMany(d => d.Artists_Event)
                    .HasForeignKey(p => p.IdArtist)
                    .OnDelete(DeleteBehavior.Restrict)
                    .HasConstraintName("Artist_Event_Artist_FK");
            });

            modelBuilder.Entity<Event_Organiser>(entity =>
            {
                entity.HasKey(e => new {e.IdEvent, e.IdOrganiser}).HasName("Event_Organiser_PK");
                entity.HasOne(e => e.Organiser)
                    .WithMany(d => d.Event_Organiser)
                    .HasForeignKey(p => p.IdOrganiser)
                    .OnDelete(DeleteBehavior.Restrict)
                    .HasConstraintName("Event_Organiser_Organiser_FK");
                entity.HasOne(e => e.Event)
                    .WithMany(d => d.Event_Organiser)
                    .HasForeignKey(p => p.IdEvent)
                    .OnDelete(DeleteBehavior.Restrict)
                    .HasConstraintName("Event_Organiser_Event_FK");
            });
            //Seeding data
            modelBuilder.Entity<Organiser>().HasData(
                new Organiser
                {
                    IdOrganiser = 1,
                    Name = "Franek"
                }
            );
            modelBuilder.Entity<Artist>().HasData(
                new Artist
                {
                    IdArtist = 1,
                    Nickname = "Janko muzykant"
                }
            );
            modelBuilder.Entity<Event>().HasData(
                new Event
                {
                    IdEvent = 1,
                    Name = "Fajny Event",
                    StartDate = DateTime.Now,
                    EndDate = DateTime.Now.AddDays(1),
                }
            );
            modelBuilder.Entity<Artist_Event>().HasData(
                new Artist_Event
                {
                    IdEvent = 1,
                    IdArtist = 1,
                    PerformanceDate = DateTime.Now,
                }
            );

            modelBuilder.Entity<Event_Organiser>().HasData(
                new Event_Organiser
                {
                    IdEvent = 1,
                    IdOrganiser = 1,
                }
            );
        }
    }
}