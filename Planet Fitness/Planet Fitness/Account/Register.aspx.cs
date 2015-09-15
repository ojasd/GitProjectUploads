using System;
using System.Linq;
using System.Web;
using System.Web.UI;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Owin;
using Planet_Fitness.Models;
using System.Globalization;
using Planet_Fitness;

/*
 Inside Account Package which handles functionalities common to all users
 */
namespace Planet_Fitness.Account
{
    public partial class Register : Page
    {
        /*
         Button Click event for Register Button
         */
        protected void CreateUser_Click(object sender, EventArgs e)
        {
            /*
             Only after validations are done and page is valid the functionality is performed
             * Only Members are registered using Registration Page
             * Employees are Registered by Manager Using Add Employee Login
             */
            if (IsValid)
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                UserAccount _userAccount = new UserAccount();
                UserType userType = new UserType();
                Member _member = new Member();
                var _query = (from user in _dataContext.UserAccounts where user.EmailId_vc.Equals(Email.Text) select user);
                /* If there is already a user (Member) with the same email Id we throw an Error*/
                if (_query.Count() != 0)
                {
                    ErrorMessage.Text = "Email Id already registered.....";
                    ErrorMessage.Visible = true;
                }
                /* Else we register the user i.e., Member
                     */
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
                    label1.Text = dt.ToString();
                    _userAccount.DOB_date = dt;
                    _userAccount.Password_vc = Encryption.Encrypt(Password.Text);
                    _userAccount.SSN_vc = Ssn.Text;
                    var _result = (from userTypeOne in _dataContext.UserTypes where userTypeOne.UserType_vc.Equals("Member") select userTypeOne).FirstOrDefault();
                    _userAccount.FK_UserType_UserAccount_in = _result.UserTypeID_in;
                    /*
                     After Password is encrypted and Date is formatted, we Insert into 
                     * User Accounts Table with User Type fetched from User Type Table
                     */
                    _dataContext.UserAccounts.InsertOnSubmit(_userAccount);
                    _dataContext.SubmitChanges();
                    var _resultAccount = (from userAccount in _dataContext.UserAccounts where userAccount.EmailId_vc.Equals(Email.Text) select userAccount).FirstOrDefault();
                    _member.BillAmount_de = Decimal.Parse("0.00");
                    _member.FK_UserAccount_Member_in = _resultAccount.UserAccountID_in;
                    /*
                     An Entry into Member Table with the Bill Amount Set to Zero Dollars is Created
                     */
                    _dataContext.Members.InsertOnSubmit(_member);
                    _dataContext.SubmitChanges();
                    Session["UserId"] = _resultAccount.UserAccountID_in;
                    Session["UserName"] = _resultAccount.UserName_vc;
                    Response.Redirect("/Member/MemberProfile");
                }
            }

        }
    }
}