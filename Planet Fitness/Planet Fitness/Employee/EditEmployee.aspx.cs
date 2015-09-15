using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 NameSpace Planet_Fitness.EditEmployee to write EditEmployee Class
 */
namespace Planet_Fitness.EditEmployee
{
    /*
     Code Behind Class for EditEmployee for EditEmployee.aspx
     * This Class is used to Employees Who are Trainers
     */
    public partial class EditEmployee : System.Web.UI.Page
    {
        /*
         We are to fetch values of all employees who are Trainers
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null)
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                var _query = (from userAccount in _dataContext.UserAccounts
                             join userTypes in _dataContext.UserTypes on
                             userAccount.FK_UserType_UserAccount_in equals userTypes.UserTypeID_in
                             where userTypes.UserType_vc.Equals("Trainer")
                             select new {FirstName = userAccount.FirstName_vc,
                                        LastName = userAccount.LastName_vc, 
                                        EmailId = userAccount.EmailId_vc});
                GridView1.DataSource = _query;
                GridView1.DataBind();
            }
            else
            {
                Response.Redirect("/Account/InvalidLogin");
            }
        }
        /*
         On Clicking Edit Button We reditect to Another Page with the Email of the 
         * Employee to be edited in Query String
         */
        protected void GridView1_RowEditing(object sender, GridViewEditEventArgs e)
        {
            String _emailId = GridView1.Rows[e.NewEditIndex].Cells[3].Text.ToString().Trim();
            Response.Redirect("/Employee/EditEmployeePage.aspx?emailId="+_emailId);
        }
    }
}