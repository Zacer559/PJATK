﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Test_02.Models;

namespace Test_02.Migrations
{
    [DbContext(typeof(MyDbContext))]
    [Migration("20200618142340_Initial")]
    partial class Initial
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "3.1.4")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("Test_02.Models.Artist", b =>
                {
                    b.Property<int>("IdArtist")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Nickname")
                        .IsRequired()
                        .HasColumnType("nvarchar(30)")
                        .HasMaxLength(30);

                    b.HasKey("IdArtist")
                        .HasName("Artist_PK");

                    b.ToTable("Artist");

                    b.HasData(
                        new
                        {
                            IdArtist = 1,
                            Nickname = "Janko muzykant"
                        });
                });

            modelBuilder.Entity("Test_02.Models.Artist_Event", b =>
                {
                    b.Property<int>("IdEvent")
                        .HasColumnType("int");

                    b.Property<int>("IdArtist")
                        .HasColumnType("int");

                    b.Property<DateTime>("PerformanceDate")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("datetime2")
                        .HasDefaultValueSql("GETDATE()");

                    b.HasKey("IdEvent", "IdArtist")
                        .HasName("Artist_Event_PK");

                    b.HasIndex("IdArtist");

                    b.ToTable("Artist_Event");

                    b.HasData(
                        new
                        {
                            IdEvent = 1,
                            IdArtist = 1,
                            PerformanceDate = new DateTime(2020, 6, 18, 16, 23, 40, 351, DateTimeKind.Local).AddTicks(3132)
                        });
                });

            modelBuilder.Entity("Test_02.Models.Event", b =>
                {
                    b.Property<int>("IdEvent")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<DateTime>("EndDate")
                        .HasColumnType("datetime2");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasColumnType("nvarchar(100)")
                        .HasMaxLength(100);

                    b.Property<DateTime>("StartDate")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("datetime2")
                        .HasDefaultValueSql("GETDATE()");

                    b.HasKey("IdEvent")
                        .HasName("Event_PK");

                    b.ToTable("Event");

                    b.HasData(
                        new
                        {
                            IdEvent = 1,
                            EndDate = new DateTime(2020, 6, 19, 16, 23, 40, 351, DateTimeKind.Local).AddTicks(1575),
                            Name = "Fajny Event",
                            StartDate = new DateTime(2020, 6, 18, 16, 23, 40, 349, DateTimeKind.Local).AddTicks(3646)
                        });
                });

            modelBuilder.Entity("Test_02.Models.Event_Organiser", b =>
                {
                    b.Property<int>("IdEvent")
                        .HasColumnType("int");

                    b.Property<int>("IdOrganiser")
                        .HasColumnType("int");

                    b.HasKey("IdEvent", "IdOrganiser")
                        .HasName("Event_Organiser_PK");

                    b.HasIndex("IdOrganiser");

                    b.ToTable("Event_Organiser");

                    b.HasData(
                        new
                        {
                            IdEvent = 1,
                            IdOrganiser = 1
                        });
                });

            modelBuilder.Entity("Test_02.Models.Organiser", b =>
                {
                    b.Property<int>("IdOrganiser")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasColumnType("nvarchar(30)")
                        .HasMaxLength(30);

                    b.HasKey("IdOrganiser")
                        .HasName("Organiser_PK");

                    b.ToTable("Organiser");

                    b.HasData(
                        new
                        {
                            IdOrganiser = 1,
                            Name = "Franek"
                        });
                });

            modelBuilder.Entity("Test_02.Models.Artist_Event", b =>
                {
                    b.HasOne("Test_02.Models.Artist", "Artist")
                        .WithMany("Artists_Event")
                        .HasForeignKey("IdArtist")
                        .HasConstraintName("Artist_Event_Artist_FK")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();

                    b.HasOne("Test_02.Models.Event", "Event")
                        .WithMany("Artists_Event")
                        .HasForeignKey("IdEvent")
                        .HasConstraintName("Artist_Event_Event_FK")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();
                });

            modelBuilder.Entity("Test_02.Models.Event_Organiser", b =>
                {
                    b.HasOne("Test_02.Models.Event", "Event")
                        .WithMany("Event_Organiser")
                        .HasForeignKey("IdEvent")
                        .HasConstraintName("Event_Organiser_Event_FK")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();

                    b.HasOne("Test_02.Models.Organiser", "Organiser")
                        .WithMany("Event_Organiser")
                        .HasForeignKey("IdOrganiser")
                        .HasConstraintName("Event_Organiser_Organiser_FK")
                        .OnDelete(DeleteBehavior.Restrict)
                        .IsRequired();
                });
#pragma warning restore 612, 618
        }
    }
}
