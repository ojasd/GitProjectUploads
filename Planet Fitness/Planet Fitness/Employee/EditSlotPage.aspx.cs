using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.EditSlotPage which contains EditSlotPage Class
 */
namespace Planet_Fitness.EditSlotPage
{
    /*
     Code Behind Class for EditSlotPage.aspx
     */
    public partial class EditSlotPage : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Only if it is not postback call then we prepopulate the fields with the Slot Information from slot Table
             */
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    if (Request.QueryString["slotId"] != null)
                    {
                        var _slotId = Request.QueryString["slotId"].ToString().Trim();
                        PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                        var _query = (from slots in _dataContext.Slots where slots.SlotID_in.Equals(_slotId) select slots).FirstOrDefault();
                        var _activities = (from activity in _dataContext.Activities select activity);
                        /*
                         Activity List is populated and Corresponding activity is selected
                         */
                        Activity.DataSource = _activities;
                        Activity.DataTextField = "Activity_vc";
                        Activity.DataValueField = "ActivityID_in";
                        Activity.DataBind();
                        Activity.Items.Insert(0, new ListItem("--Select--", ""));
                        Activity.Items.FindByValue(_query.FK_Activity_Slot_in.ToString()).Selected = true;
                        Availability.Text = _query.NumberOfSlots_in.ToString();
                        StartTime.Text = _query.StartTime_vc.Substring(0, (_query.StartTime_vc.IndexOf(' ')));
                        EndTime.Text = _query.EndTime_vc.Substring(0, (_query.EndTime_vc.IndexOf(' ')));
                        SlotId.Text = _slotId;
                        var _trainerNames = (from userAccount in _dataContext.UserAccounts
                                             join trainer in _dataContext.Trainers on
                                             userAccount.UserAccountID_in equals trainer.FK_UserAccount_Trainer_in
                                             where trainer.FK_Activity_Trainer_in.Equals(_query.FK_Activity_Slot_in)
                                             select new
                                             {
                                                 UserName = userAccount.UserName_vc,
                                                 TrainerId = trainer.TrainerID_in
                                             });
                        /*
                         Trainer List is populated and Corresponding Trainer is selected
                         */
                        Trainer.DataSource = _trainerNames;
                        Trainer.DataTextField = "UserName";
                        Trainer.DataValueField = "TrainerId";
                        Trainer.DataBind();
                        Trainer.Items.Insert(0, new ListItem("--Select--", ""));
                        Trainer.Items.FindByValue(_query.FK_Trainer_Slot_in.ToString()).Selected = true;
                    }
                }
            }
            else
            {
                Response.Redirect("/Account/InvalidLogin");
            }
        }
        /*
         On Click Event for Edit Slot Button
         */
        protected void EditSlot_Click(object sender, EventArgs e)
        {
            /*
             Only if the form is validated and is valid
             */
            if (IsValid)
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                var _startTime = StartTime.Text;
                var _endTime = EndTime.Text;
                var _slotId = SlotId.Text;
                var _flag = false;
                var _query = (from slots in _dataContext.Slots where slots.SlotID_in.Equals(_slotId) select slots).FirstOrDefault();
                var _slotInformation = (from slotInformation in _dataContext.SlotInformations where slotInformation.FK_Slot_SlotInformation_in.Equals(_slotId) select slotInformation);
                /*
                 If slot being changed is booke dalready it cannot be changed
                 */
                if (Convert.ToInt32(_startTime) < Convert.ToInt32(_endTime))
                {
                    if (_slotInformation.Count() != 0)
                    {
                        ErrorMessage.Text = "Slot has already been booked cannot change the slot";
                    }
                    /*
                     Check if the time values entered is between 8 A.M and 8 P.M
                     */
                    else
                    {
                        if ((Convert.ToInt32(_startTime) < 8) || (Convert.ToInt32(_startTime) > 20))
                            ErrorMessage.Text = "Start Time should be between 8 A.M and 8 P.M";
                        else if ((Convert.ToInt32(_endTime) < 8) || (Convert.ToInt32(_endTime) > 20))
                            ErrorMessage.Text = "End Time should be between 8 A.M and 8 P.M";
                        else if ((Convert.ToInt32(_startTime) - Convert.ToInt32(_endTime)) >= 0)
                            ErrorMessage.Text = "End Time should be greater than Start Time";
                        /*
                         * Convert time to 12 hours format and add A.M or P.M
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
                            /*
                             If all the values are valid then update the slot 
                             */
                            if (_flag)
                            {
                                _query.AvailableSlots_in = Convert.ToInt32(Availability.Text);
                                _query.StartTime_vc = _startTime;
                                _query.EndTime_vc = _endTime;
                                _query.NumberOfSlots_in = Convert.ToInt32(Availability.Text);
                                _query.FK_Trainer_Slot_in = Convert.ToInt32(Trainer.SelectedValue);
                                try
                                {
                                    _dataContext.SubmitChanges();
                                    Response.Redirect("/Employee/EditSlot?editSlot=true");
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
                else
                {
                    ErrorMessage.Text = "Start Time should be greater than End Time";
                }
            }
        }
    }
}