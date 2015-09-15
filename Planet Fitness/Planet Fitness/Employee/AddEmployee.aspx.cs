using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Globalization;
using Planet_Fitness;

/*
    Add Employee Class inside Namespace Planet_Fitness.AddEmployee
 */
namespace Planet_Fitness.AddEmployee
{
    /*
     Code Behind Class for Add Employee Page 
     */
    public partial class AddEmployee : System.Web.UI.Page
    {

        /*
         Before we load the page we fetch List of Activities to be populated into the Select List
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Fetch Should perform only if it is not PostBack to avoid loading the 
             * Select List with duplicate items Everytime
             */
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                    var _query = (from activity in _dataContext.Activities select activity);
                    Activity.DataSource = _query;
                    Activity.DataTextField = "Activity_vc";
                    Activity.DataValueField = "ActivityID_in";
                    Activity.DataBind();
                    Activity.Items.Insert(0, new ListItem("--Select--", ""));
                }
            }
            else
            {
                Response.Redirect("/Account/InvalidLogin");
            }

        }

        /*
         On Click Event to handle Add Employee Button Click
         */
        protected void CreateEmployee_Click(object sender, EventArgs e)
        {
            /*
             Only if the form is valid, We Perform Some Action
             */
            if (IsValid)
            {
                /*
                 If a valid Activity is selected
                 */
                if (Activity.SelectedValue != "")
                {
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                    UserAccount _userAccount = new UserAccount();
                    UserType _userType = new UserType();
                    Trainer trainer = new Trainer();
                    var query = (from user in _dataContext.UserAccounts where user.EmailId_vc.Equals(Email.Text) select user);
                    /*
                     If EmailId is Already registered we throw an error
                     */
                    if (query.Count() != 0)
                    {
                        ErrorMessage.Text = "Email Id already registered.....";
                        ErrorMessage.Visible = true;
                    }
                    else
                    {
                        ErrorMessage.Visible = false;
                        _userAccount.UserName_vc = UserName.Text;
                        _userAccount.FirstName_vc = FirstName.Text;
                        _userAccount.LastName_vc = LastName.Text;
                        _userAccount.Address_vc = Address.Text;
                        _userAccount.EmailId_vc = Email.Text;
                        _userAccount.PhoneNumber_vc = PhoneNumber.Text;
                        DateTime dt = DateTime.ParseExact(DateOfBirth.Text, "yyyy-MM-dd", CultureInfo.InvariantCulture);
                        _userAccount.DOB_date = dt;
                        _userAccount.Password_vc = Encryption.Encrypt(Password.Text);
                        _userAccount.SSN_vc = Ssn.Text;
                        var _result = (from userTypeOne in _dataContext.UserTypes where userTypeOne.UserType_vc.Equals("Trainer") select userTypeOne).FirstOrDefault();
                        _userAccount.FK_UserType_UserAccount_in = _result.UserTypeID_in;
                        /*
                         After Password is Encrypted and Date is formatted, we Insert them into tables
                         */
                        _dataContext.UserAccounts.InsertOnSubmit(_userAccount);
                        _dataContext.SubmitChanges();
                        var result = (from user in _dataContext.UserAccounts
                                      where user.EmailId_vc.Equals(Email.Text)
                                      select user).FirstOrDefault();
                        var activityId = (from activity in _dataContext.Activities
                                          where activity.ActivityID_in.Equals(Activity.SelectedValue)
                                          select activity).FirstOrDefault().ActivityID_in;
                        trainer.FK_Activity_Trainer_in = activityId;
                        trainer.FK_UserAccount_Trainer_in = result.UserAccountID_in;
                        /*
                         Data is inserted into trainer table depending on which activity is selected
                         */
                        _dataContext.Trainers.InsertOnSubmit(trainer);
                        _dataContext.SubmitChanges();
                        Response.Redirect("/Employee/AddEmployeeSuccess");
                    }
                }
                else
                {
                    ErrorMessage.Text = "Select a valid activity";
                    ErrorMessage.Visible = true;
                }
            }
        }
    }
}