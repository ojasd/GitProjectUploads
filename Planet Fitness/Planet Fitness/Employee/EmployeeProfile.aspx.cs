using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

/*
 Namespace Planet_Fitness.EmployeeProfile which contains EmployeeProfile Class
 */
namespace Planet_Fitness.EmployeeProfile
{
    /*
     Code Behind class for EmployeeProfie.aspx
     */
    public partial class EmployeeProfile : System.Web.UI.Page
    {
        /*
         Depending on the userID in the Session we populate the grid with the User Account values
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null)
            {
                int _userID = Convert.ToInt32(Session["UserId"]);
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                /*
                 If we have a query String then we display a Success Message
                 */
                if (Request.QueryString["editProfile"] != null)
                {
                    var _bookingStatus = Request.QueryString["editProfile"].ToString().Trim();
                    if (_bookingStatus == "true")
                    {
                        SuccessMessage.Text = "Profile Updated Successfully";
                    }
                }
                /*
                 We Fetch the User Details from the UserAccount Table
                 */
                var _row = (from userAccount in _dataContext.UserAccounts
                            where
                                userAccount.UserAccountID_in.Equals(_userID)
                            select userAccount);
                if (_row.Count() == 1)
                {
                    DataTable _dataTable = new DataTable();
                    _dataTable.Columns.Add("Attribute", typeof(string));
                    _dataTable.Columns.Add("Value", typeof(string));
                    _dataTable.Rows.Add("First Name", _row.FirstOrDefault().FirstName_vc);
                    _dataTable.Rows.Add("Last Name", _row.FirstOrDefault().LastName_vc);
                    _dataTable.Rows.Add("User Name", _row.FirstOrDefault().UserName_vc);
                    _dataTable.Rows.Add("Address", _row.FirstOrDefault().Address_vc);
                    _dataTable.Rows.Add("Phone Number", _row.FirstOrDefault().PhoneNumber_vc);
                    _dataTable.Rows.Add("Email Id", _row.FirstOrDefault().EmailId_vc);
                    DateTime _dateTime = Convert.ToDateTime(_row.FirstOrDefault().DOB_date);
                    _dataTable.Rows.Add("Date Of Birth", _dateTime.Date.ToString("d"));
                    gridView1.DataSource = _dataTable;
                    gridView1.DataBind();
                }
            }
            else
            {
                Response.Redirect("/Account/InvalidLogin");
            }
        }
        /*
         OnClick Event for Edit Employee button
         */
        protected void editEmployee_Click(object sender, EventArgs e)
        {
            Response.Redirect("/Employee/EditEmployeeProfile.aspx");
        }
    }
}