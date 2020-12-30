
using APBD_Tutorial_10_Entity_Framework.Entities;
using APBD_Tutorial_10_Entity_Framework.Logging;
using APBD_Tutorial_10_Entity_Framework.Services;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using System;
using System.Text;

namespace APBD_Tutorial_10_Entity_Framework
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddAuthorization();
            services.AddDbContext<StudentContext>(options => { options.UseSqlServer(Configuration["ConnectionStrings:DefaultConnectionString"]);  } );
            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
            {
                options.TokenValidationParameters = new TokenValidationParameters
                {

                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidIssuer = "Dominik",
                    ValidAudience = "employee",
                    ClockSkew = TimeSpan.Zero,

                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Configuration["Key"]))

                };


            });


            services.AddTransient<IStudentServiceDb, SqlServerStudentDbService>();
            services.AddControllers();
          
            services.AddSwaggerGen(c =>
            {

                c.SwaggerDoc("v1", new OpenApiInfo { Title = "APBD 7 API", Version = "v1" });

            });
        }
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, IStudentServiceDb service)
        {
            app.UseSwagger();

            app.UseSwaggerUI(c =>
            {

                c.SwaggerEndpoint("/swagger/v1/swagger.json", "APBD 7 API");
            });

            app.UseMiddleware<Logger>();
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseWhen(context => context.Request.Path.ToString().Contains("secured"), app =>
            {
                app.Use(async (context, next) =>
                {
                    if (!context.Request.Headers.ContainsKey("Index"))
                    {
                        context.Response.StatusCode = StatusCodes.Status401Unauthorized;
                        await context.Response.WriteAsync("Index number missing");
                        return;
                    }
                    var index = context.Request.Headers["Index"].ToString();
                    var stud = service.GetStudent(index);
                    if (stud == null)
                    {
                        context.Response.StatusCode = StatusCodes.Status401Unauthorized;
                        await context.Response.WriteAsync($"User ({index}) not found");
                        return;
                    }
                    await next();
                });
            });

            app.UseHttpsRedirection();
            

            app.UseRouting();
            app.UseAuthentication();
            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
