using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Tutorial_11_Code_First_Approche.Models
{
    public class MyDbContext : DbContext
    {
        public DbSet<Patient> Patient { get; set; }
        public DbSet<Prescription> Prescription { get; set; }
        public DbSet<PrescriptionMedicament> PrescriptionMedicaments { get; set; }
        public MyDbContext(DbContextOptions<MyDbContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Patient>(entity =>
            {
                entity.HasKey(e => e.IdPatient).HasName("Patient_PK");
                entity.Property(e => e.FirstName).HasMaxLength(100).IsRequired();
                entity.Property(e => e.LastName).HasMaxLength(100).IsRequired();
                entity.Property(e => e.BirthDate).IsRequired();
            });

            modelBuilder.Entity<Prescription>(entity =>
            {
                entity.HasKey(e => e.IdPrescription).HasName("Prescription_PK");
                entity.Property(e => e.IdPrescription).ValueGeneratedNever();
                entity.Property(e => e.Date).HasDefaultValueSql("GETDATE()").IsRequired();
                entity.Property(e => e.DueDate).IsRequired();

                entity.HasOne(e => e.Patient)
                                .WithMany(d => d.Prescriptions)
                                .HasForeignKey(p => p.IdPatient)
                                .OnDelete(DeleteBehavior.Restrict)
                                .HasConstraintName("Prescription_Patient_FK");
            });

            modelBuilder.Entity<PrescriptionMedicament>(entity =>
            {
                entity.ToTable("Prescription_Medicaments");
                entity.HasKey(e => new { e.IdPrescription, e.IdMedicament });


            });
        }
    }
}
