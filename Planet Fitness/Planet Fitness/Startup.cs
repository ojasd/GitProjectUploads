using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Planet_Fitness.Startup))]
namespace Planet_Fitness
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
