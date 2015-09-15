using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Code Behind Class for Success Page which displays a success message on successful updation
 */
namespace Planet_Fitness.EditEmployeeSuccess
{
    public partial class EditEmployeeSuccess : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null)
            {    
            }
            else
            {
                Response.Redirect("/Account/InvalidLogin");
            }

        }
    }
}