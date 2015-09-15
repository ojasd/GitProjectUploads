using System;
using System.Web;
using System.Web.UI;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Owin;
using Planet_Fitness.Models;
using System.Linq;
using Planet_Fitness;

/*
    Account namespace (Package) has a class called Login which handles user login 
 */
namespace Planet_Fitness.Account
{
    /* Login Page code behing class which is used to perform user login to the system using LogIn function*/
    public partial class Login : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        /* LogIn function which is called when a Login button is clicked. This is on button Click event handler
           It takes Default parameters of eventArgs and Sender
         */
        protected void LogIn(object sender, EventArgs e)
        {
            PlanetFitnessDataContext _context = new PlanetFitnessDataContext();
            /*Only if form is validated it is allowed to perform some function*/
            if (IsValid)
            {
                /* Fetch users with the entered emailId*/
                var _query = (from user in _context.UserAccounts
                              where user.EmailId_vc.Equals(Email.Text)
                              select user);
                /* Only if there is a single user with that particular EmailId he is allowed to Login*/
                if (_query.Count() == 1)
                {
                    /* Check for the Password against Decrypted Password*/
                    if (Encryption.Decrypt(_query.FirstOrDefault().Password_vc).Equals(Password.Text))
                    {
                        var _result = (from userType in _context.UserTypes
                                       where
                                           userType.UserTypeID_in.Equals(_query.FirstOrDefault().FK_UserType_UserAccount_in)
                                       select userType).FirstOrDefault();
                        /* Depending on type of user we reditect to corresponding page.*/
                        if (_result.UserType_vc.Equals("Manager"))
                        {
                            Session["UserName"] = _query.FirstOrDefault().UserName_vc;
                            Session["UserId"] = _query.FirstOrDefault().UserAccountID_in;
                            Response.Redirect("/Employee/EmployeeProfile");
                        }
                        else if (_result.UserType_vc.Equals("Member"))
                        {
                            Session["UserName"] = _query.FirstOrDefault().UserName_vc;
                            Session["UserId"] = _query.FirstOrDefault().UserAccountID_in;
                            Response.Redirect("/Member/MemberProfile");
                        }
                        else if (_result.UserType_vc.Equals("Trainer"))
                        {
                            Session["UserName"] = _query.FirstOrDefault().UserName_vc;
                            Session["UserId"] = _query.FirstOrDefault().UserAccountID_in;
                            Response.Redirect("/Trainer/TrainerProfile");
                        }
                        else
                        {
                            FailureText.Text = "Invalid login attempt";
                            ErrorMessage.Visible = true;
                        }
                    }
                    else
                    {
                        FailureText.Text = "Invalid login attempt";
                        ErrorMessage.Visible = true;
                    }
                }
                else
                {
                    FailureText.Text = "Invalid login attempt";
                    ErrorMessage.Visible = true;
                }
            }
        }
    }
}