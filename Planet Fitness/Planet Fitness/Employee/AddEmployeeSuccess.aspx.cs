using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 An Employee Success Page which is used to display a success message on Successfully adding an Employee
 */
namespace Planet_Fitness.AddEmployeeSuccess
{
    public partial class AddEmployeeSuccess : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null){

            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
    }
}