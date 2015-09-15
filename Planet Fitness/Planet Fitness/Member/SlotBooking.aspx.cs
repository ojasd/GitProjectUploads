using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.SlotBooking which contains SlotBooking Class
 */
namespace Planet_Fitness.SlotBooking
{
    public partial class SlotBooking : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             If not postback Call then preload the Activity list from Activity Table
             */
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                    var _query = (from activity in _dataContext.Activities select activity);
                    DropDownList1.DataSource = _query;
                    DropDownList1.DataTextField = "Activity_vc";
                    DropDownList1.DataValueField = "ActivityID_in";
                    DropDownList1.DataBind();
                    DropDownList1.Items.Insert(0, new ListItem("--Select--", ""));
                }
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
        /*
         On Change Event for the Activity List
         */
        protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
        {
            /*
             Check if the selected value is valid
             */
            PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
            if (DropDownList1.SelectedValue != "")
            {
                /*
                Depending on the Activity selected populate the Grid with slot values and make it visible
                 */
                var _activityId = DropDownList1.SelectedValue;
                var _query = (from slot in _dataContext.Slots
                              where slot.FK_Activity_Slot_in.Equals(_activityId)
                              select new
                              {
                                  SlotId = slot.SlotID_in,
                                  Activity = (from activity in _dataContext.Activities
                                              where activity.ActivityID_in.Equals(_activityId)
                                              select activity).FirstOrDefault().Activity_vc,
                                  StartTime = slot.StartTime_vc,
                                  EndTime = slot.EndTime_vc,
                                  Trainer = (from userAccount in _dataContext.UserAccounts
                                             join trainer in _dataContext.Trainers on
                                             userAccount.UserAccountID_in equals trainer.FK_UserAccount_Trainer_in
                                             where trainer.TrainerID_in.Equals(slot.FK_Trainer_Slot_in)
                                             select new { userAccount.UserName_vc }).FirstOrDefault().UserName_vc
                              });
                GridView1.Visible = true;
                GridView1.DataSource = _query;
                GridView1.DataBind();
            }
            else
            {
                GridView1.Visible = false;
            }
        }
        /*
         On selecting a Radio Button we Book the Slot 
         */
        protected void RowSelector_CheckedChanged(object sender, EventArgs e)
        {
            for (int i = 0; i < GridView1.Rows.Count; i++)
            {
                RadioButton rb = (RadioButton)(GridView1.Rows[i].Cells[0].FindControl("radioButton1"));
                /*
                 We Check among all the Grid Rows which is checked
                 */
                if (rb.Checked == true)
                {
                    /*
                     We fetch the values from the Grid and insert in the SlotInformation Table and update slot Table
                     */
                    var _slotId = GridView1.Rows[i].Cells[1].Text;
                    var _trainerName = GridView1.Rows[i].Cells[5].Text.Trim();
                    var _acivityName = GridView1.Rows[i].Cells[2].Text.Trim();
                    rb.Checked = false;
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                    UserAccount _userAccount = new UserAccount();
                    SlotInformation slotInformation = new SlotInformation();
                    Trainer trainer = new Trainer();
                    int _userID = Convert.ToInt32(Session["UserId"]);
                    var _member = (from member in _dataContext.Members where member.FK_UserAccount_Member_in.Equals(_userID) select member).FirstOrDefault();
                    var _query = (from user in _dataContext.UserAccounts where user.UserName_vc.Equals(_trainerName) select user).FirstOrDefault().UserAccountID_in;
                    var _queryActivity = (from activity in _dataContext.Activities where activity.Activity_vc.Equals(_acivityName) select activity).FirstOrDefault().ActivityID_in;
                    var _querySlot = (from slot in _dataContext.Slots where slot.SlotID_in.Equals(_slotId) select slot).FirstOrDefault();
                    _querySlot.AvailableSlots_in = _querySlot.AvailableSlots_in - 1;
                    _member.BillAmount_de = _member.BillAmount_de + Decimal.Parse("20.55");
                    slotInformation.FK_Slot_SlotInformation_in = Convert.ToInt32(_slotId);
                    slotInformation.FK_Member_SlotInformation_in = _member.MemberID_in;
                    slotInformation.FK_Activity_SlotInformation_in = _queryActivity;
                    try
                    {
                        _dataContext.SlotInformations.InsertOnSubmit(slotInformation);
                        _dataContext.SubmitChanges();
                        Response.Redirect("/Member/BookingInformation?bookingStatus=true");
                    }
                    catch (Exception exception)
                    {
                        ErrorMessage.Text = "Something went wrong..Please try again.";
                        Console.WriteLine(exception.InnerException);
                    }
                }
            }
        }
    }
}