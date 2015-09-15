using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
    It is inside the same package as Login and Registration whose functionalities are common to all users
 */
namespace Planet_Fitness.Account
{
    /* Code behind page class for Logout Page*/
    public partial class Logout : System.Web.UI.Page
    {
        /* Before we logout we invalidate the session which is storing the user login details*/
        protected void Page_Load(object sender, EventArgs e)
        {
            Session.Abandon();
        }
    }
}