using Planet_Fitness;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 NameSpace Planet_Fitness.EditEmployeeProfile which holds Class EditEmployeeProfile
 */
namespace Planet_Fitness.EditEmployeeProfile
{
    /*
     Code Behind Class for EditEmployeePage to Edit Single Trainer Details
     */
    public partial class EditEmployeePage : System.Web.UI.Page
    {
        /*
         This Page is redirected from EditEmployee Page
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Only if it is not PostBack request we do the following
             */
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    /*
                     We fetch the Email Id from the query string 
                     */
                    if (Request.QueryString["emailId"] != null)
                    {
                        String _emailId = Request.QueryString["emailId"].ToString().Trim();
                        PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                        /*
                         Fetch the user details to be edited by fetching and prepopulating the user detiails
                         */
                        var _query = (from userAccount in _dataContext.UserAccounts
                                      where userAccount.EmailId_vc.Equals(_emailId)
                                      select userAccount).FirstOrDefault();
                        UserName.Text = _query.UserName_vc;
                        FirstName.Text = _query.FirstName_vc;
                        LastName.Text = _query.LastName_vc;
                        Address.Text = _query.Address_vc;
                        PhoneNumber.Text = _query.PhoneNumber_vc;
                        Email.Text = _query.EmailId_vc;
                        Ssn.Text = _query.SSN_vc.ToString();
                        Password.Text = Encryption.Decrypt(_query.Password_vc);
                        DateTime _dateTime = Convert.ToDateTime(_query.DOB_date);
                        DateOfBirthOne.Text = _dateTime.Date.ToString("d");
                        var _activities = (from activity in _dataContext.Activities select activity);
                        Activity.DataSource = _activities;
                        Activity.DataTextField = "Activity_vc";
                        Activity.DataValueField = "ActivityID_in";
                        Activity.DataBind();
                        Activity.Items.Insert(0, new ListItem("--Select--", ""));
                        var _activityId = (from trainer in _dataContext.Trainers
                                           where trainer.FK_UserAccount_Trainer_in.Equals(_query.UserAccountID_in)
                                           select trainer).FirstOrDefault().FK_Activity_Trainer_in.ToString();
                        Activity.Items.FindByValue(_activityId).Selected = true;
                        /*
                         Maintain the details about the user being edited as he is different from User who is Logged in
                         */
                        Session["EditId"] = _query.UserAccountID_in;
                    }
                }
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
        /*
         On Click Event for Edit Employee Button Click
         */
        protected void EditEmployee_Click(object sender, EventArgs e)
        {
            /*
             Only if the form is valid then perform the following actions.
             */
            if (IsValid)
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                /*Depending on Edit Id Stored in the Session we update the details of the Trainer*/
                String _userAccountId = Session["EditId"].ToString();
                var _userAccount = (from userAccountData in _dataContext.UserAccounts
                                   where userAccountData.UserAccountID_in.Equals(_userAccountId)
                                   select userAccountData).FirstOrDefault();
                _userAccount.UserName_vc = UserName.Text;
                _userAccount.FirstName_vc = FirstName.Text;
                _userAccount.LastName_vc = LastName.Text;
                _userAccount.Address_vc = Address.Text;
                _userAccount.EmailId_vc = Email.Text;
                _userAccount.Password_vc = Encryption.Encrypt(Password.Text);
                _userAccount.SSN_vc = Ssn.Text;
                _userAccount.DOB_date = Convert.ToDateTime(DateOfBirthOne.Text);
                _userAccount.PhoneNumber_vc = PhoneNumber.Text;
                var _activityId = (from trainer in _dataContext.Trainers
                                  where trainer.FK_UserAccount_Trainer_in.Equals(_userAccountId)
                                  select trainer).FirstOrDefault().FK_Activity_Trainer_in.ToString();
                if (_activityId.Equals(Activity.SelectedValue))
                {
                    try
                    {
                       _dataContext.SubmitChanges();
                        Response.Redirect("/Employee/EditEmployeeSuccess");
                    }
                    catch (Exception exception)
                    {
                        ErrorMessage.Text = "Something went wrong..Please try again.";
                        Console.WriteLine(exception);
                    }
                }
                /*
                 If the slot trained by the Trainer is booked by someone 
                 * Then we cannot change the Trainer Activity
                 */
                else
                {
                    var _slots = (from slot in _dataContext.Slots
                                 where slot.FK_Trainer_Slot_in.Equals(
                                 (from trainer in _dataContext.Trainers
                                  where trainer.FK_UserAccount_Trainer_in.Equals(_userAccountId)
                                  select trainer).FirstOrDefault().TrainerID_in)
                                  select slot);
                    if (_slots.Count() != 0)
                    {
                        ErrorMessage.Text = "Trainer has slots with that activity...So cannot be changed";
                    }
                    else
                    {
                        _slots.FirstOrDefault().FK_Trainer_Slot_in = Convert.ToInt32(Activity.SelectedValue);
                        _dataContext.SubmitChanges();
                        Response.Redirect("/Employee/EditEmployeeSuccess");
                    }
                }
            }
        }
    }
}