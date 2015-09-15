using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Code Behind Page for displaying the success message 
 */
namespace Planet_Fitness.PaymentSuccessful
{
    public partial class PaymentSuccessful : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null)
            {
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
    }
}