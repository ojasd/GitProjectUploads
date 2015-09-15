using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

/*
 Namespace Planet_Fitness.MemberProfile which contains MemberProfile Class
 */
namespace Planet_Fitness.MemberProfile
{
    public partial class MemberProfile : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             If the Query String contains the WCF USer that is the page is being redirected from HMS
             */
            if(Request.QueryString["WCFUser"]!= null)
            {
                Session["UserId"] = Request.QueryString["WCFUser"].ToString();
            }
            if (Session["UserId"] != null)
            {
                int _userID = Convert.ToInt32(Session["UserId"]);

                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                /*
                 If we get the Query String we display an success message for successful updation of the profile.
                 */
                if (Request.QueryString["editProfile"] != null)
                {
                    var _bookingStatus = Request.QueryString["editProfile"].ToString().Trim();
                        if (_bookingStatus == "true")
                        {
                            SuccessMessage.Text = "Profile Updated Successfully";
                        }
                }
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
                Response.Redirect("/Account/InvalidLogin");
        }
        /*
         On Click Event for redirecting to MemberProfilePage for updating the profile
         */
        protected void editMember_Click(object sender, EventArgs e)
        {
            Response.Redirect("/Member/EditMemberProfile.aspx");
        }
    }
}