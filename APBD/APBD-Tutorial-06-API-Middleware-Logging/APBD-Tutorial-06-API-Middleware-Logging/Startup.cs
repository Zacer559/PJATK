using APBD_Tutorial_06_API_Middleware_Logging.Logging;
using APBD_Tutorial_06_API_Middleware_Logging.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
namespace APBD_Tutorial_06_API_Middleware_Logging
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
            services.AddTransient<IStudentServiceDb, SqlServerStudentDbService>();
            services.AddControllers();
            services.AddSwaggerGen(c =>
            {

                c.SwaggerDoc("v1", new OpenApiInfo { Title = "APBD 6 API", Version = "v1" });

            });
        }
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, IStudentServiceDb service)
        {
            app.UseSwagger();

            app.UseSwaggerUI(c =>
            {

                c.SwaggerEndpoint("/swagger/v1/swagger.json", "APBD 6 API");
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

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
