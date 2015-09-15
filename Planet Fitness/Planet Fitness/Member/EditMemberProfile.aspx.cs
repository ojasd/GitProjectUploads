using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.EditMemberProfile for EditMemberProfile Class
 */
namespace Planet_Fitness.EditMemberProfile
{
    /*
     Code Behind Class to EditMemberProfile.aspx
     */
    public partial class EditMemberProfile : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    /*
                     Depending on the UserId from the Session we populate the User Account Details
                     */
                    int _userID = Convert.ToInt32(Session["UserId"]);
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                    var _query = (from userAccount in _dataContext.UserAccounts
                                  where
                                      userAccount.UserAccountID_in.Equals(_userID)
                                  select userAccount).FirstOrDefault();
                    FirstName.Text = _query.FirstName_vc;
                    LastName.Text = _query.LastName_vc;
                    Address.Text = _query.Address_vc;
                    PhoneNumber.Text = _query.PhoneNumber_vc;
                    Email.Text = _query.EmailId_vc;
                    DateTime _dateTime = Convert.ToDateTime(_query.DOB_date);
                    DateOfBirthOne.Text = _dateTime.Date.ToString("d");
                    Session["ProfileId"] = _query.UserAccountID_in;
                }
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
        /*
         On Click Event to handle Edit Employee Button Click
         */
        protected void EditEmployeeProfile_Click(object sender, EventArgs e)
        {
            if (IsValid)
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                String _userAccountId = Session["ProfileId"].ToString();
                var _userAccount = (from userAccountData in _dataContext.UserAccounts
                                    where userAccountData.UserAccountID_in.Equals(_userAccountId)
                                    select userAccountData).FirstOrDefault();
                _userAccount.FirstName_vc = FirstName.Text;
                _userAccount.LastName_vc = LastName.Text;
                _userAccount.Address_vc = Address.Text;
                _userAccount.PhoneNumber_vc = PhoneNumber.Text;
                _userAccount.EmailId_vc = Email.Text;
                _userAccount.DOB_date = Convert.ToDateTime(DateOfBirthOne.Text);
                try
                {
                    _dataContext.SubmitChanges();
                    Response.Redirect("/Member/MemberProfile?editProfile=true");
                }
                catch (Exception exception)
                {
                    ErrorMessage.Text = "Something went wrong..Please try again.";
                    Console.WriteLine(exception);
                }
            }
        }
    }
}