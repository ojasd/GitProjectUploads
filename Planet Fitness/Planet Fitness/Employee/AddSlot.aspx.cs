using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

/*
 Class that is inside namespace Planet_Fitness.AddSlot used to Add a Slot
 */
namespace Planet_Fitness.AddSlot
{
    /*
     Class AddSlot which is code behind class for AddSlot.aspx
     */
    public partial class AddSlot : System.Web.UI.Page
    {
        /*
         Preload the Activity values to the AddSlot Page
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Only if it is not PostBack this has to be performed
             */
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();

                    /*
                     If We Add a Slot then the Success Message is Displayed based on the Query Paramter that is Parameter in URL 
                     */
                    if (Request.QueryString["slotAdded"] != null)
                    {
                        var _bookingStatus = Request.QueryString["slotAdded"].ToString().Trim();
                        if (_bookingStatus == "true")
                        {
                            SuccessMessage.Text = "Slot Added Successful";
                        }
                    }
                    var _activities = (from activity in _dataContext.Activities select activity);
                    Activity.DataSource = _activities;
                    Activity.DataTextField = "Activity_vc";
                    Activity.DataValueField = "ActivityID_in";
                    Activity.DataBind();
                    Activity.Items.Insert(0, new ListItem("--Select--", ""));
                }
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }

        /*
         On Click Event when a Add Slot Button is Clicked
         */
        protected void AddSlot_Click(object sender, EventArgs e)
        {
            /*
             Only if the form is validated and is valid then perform some action
             */
            if (IsValid)
            {
                /*
                 Get Start and End Time with out Minutes
                 */
                var _startTime = StartTime.Text.Split(':')[0];
                var _endTime = EndTime.Text.Split(':')[0];
                var _flag = false;
                if (_startTime.IndexOf('0') == 0)
                    _startTime = _startTime.Substring(1);
                else if (_endTime.IndexOf('0') == 0)
                    _endTime = _endTime.Substring(1);
                /*
                 verify the Time is between 8 A.M and 8 P.M
                 */
                if ((Convert.ToInt32(_startTime) < 8) || (Convert.ToInt32(_startTime) > 20))
                    ErrorMessage.Text = "Start Time should be between 8 A.M and 8 P.M";
                else if ((Convert.ToInt32(_endTime) < 8) || (Convert.ToInt32(_endTime) > 20))
                    ErrorMessage.Text = "End Time should be between 8 A.M and 8 P.M";
                else if ((Convert.ToInt32(_startTime) - Convert.ToInt32(_endTime)) >= 0)
                    ErrorMessage.Text = "End Time should be greater than Start Time";
                /*
                 Convert the time to 12 hours format and flag if it is valid
                 */
                else
                {
                    if (Convert.ToInt32(_startTime) > 8 && Convert.ToInt32(_startTime) < 12)
                    {
                        _startTime = _startTime + " A.M";
                        _flag = true;
                    }
                    else if (Convert.ToInt32(_startTime) >= 12 && Convert.ToInt32(_startTime) <= 20)
                    {
                        if (Convert.ToInt32(_startTime) != 12)
                            _startTime = (Convert.ToInt32(_startTime) - 12).ToString();
                        _startTime = _startTime + " P.M";
                        _flag = true;
                    }
                    if (Convert.ToInt32(_endTime) > 8 && Convert.ToInt32(_endTime) < 12)
                    {
                        _endTime = _endTime + " A.M";
                        _flag = true;
                    }
                    else if (Convert.ToInt32(_endTime) >= 12 && Convert.ToInt32(_endTime) <= 20)
                    {
                        if (Convert.ToInt32(_endTime) != 12)
                            _endTime = (Convert.ToInt32(_endTime) - 12).ToString();
                        _endTime = _endTime + " P.M";
                        _flag = true;
                    }
                }
                /*
                  If a Valid Activity adnd Trainer are selected
                 */
                if (Activity.SelectedValue != "" && Trainer.SelectedValue != "")
                {
                    /*
                     And if time is valid Then insert the values to Database
                     */
                    if (_flag)
                    {
                        PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                        Slot _slot = new Slot();
                        _slot.NumberOfSlots_in = Convert.ToInt32(Availability.Text);
                        _slot.AvailableSlots_in = Convert.ToInt32(Availability.Text);
                        _slot.FK_Activity_Slot_in = Convert.ToInt32(Activity.SelectedValue);
                        _slot.FK_Trainer_Slot_in = (from userAccount in _dataContext.UserAccounts
                                                    join trainer in _dataContext.Trainers on
                                                    userAccount.UserAccountID_in equals trainer.FK_UserAccount_Trainer_in
                                                    where userAccount.UserAccountID_in.Equals(Trainer.SelectedValue)
                                                    select trainer).FirstOrDefault().TrainerID_in;
                        _slot.StartTime_vc = _startTime;
                        _slot.EndTime_vc = _endTime;
                        _dataContext.Slots.InsertOnSubmit(_slot);
                        _dataContext.SubmitChanges();
                        Response.Redirect("/Employee/AddSlot?slotAdded=true");
                    }
                }
                else
                {
                    ErrorMessage.Text = "Select a valid Activity and Trainer";
                }
            }
        }
        /*
         On Change Event where we populate the Trainer List on the type of Activity Selected
         */
        protected void Activity_SelectedIndexChanged(object sender, EventArgs e)
        {
            var _value = Activity.SelectedValue;
            /*
             If a Empty value is selected after valid value was present
             */
            if (_value.Equals(""))
            {
                ErrorMessage.Text = "Select a valid Activity";
            }
            /*
             If value entered is valid, then fetch the trainer corresponding to Activity
             */
            else
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                var _trainerNames = (from userAccount in _dataContext.UserAccounts
                                     join trainer in _dataContext.Trainers on
                                     userAccount.UserAccountID_in equals trainer.FK_UserAccount_Trainer_in
                                     where trainer.FK_Activity_Trainer_in.Equals(_value)
                                     select new
                                     {
                                         UserName = userAccount.UserName_vc,
                                         UserAccountId = userAccount.UserAccountID_in
                                     });
                Trainer.DataSource = _trainerNames;
                Trainer.DataTextField = "UserName";
                Trainer.DataValueField = "UserAccountId";
                Trainer.DataBind();
                Trainer.Items.Insert(0, new ListItem("--Select--", ""));
            }
        }
    }
}