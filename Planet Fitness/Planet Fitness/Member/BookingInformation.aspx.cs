using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.SlotBooking which contains BookingInformation Class 
 */
namespace Planet_Fitness.SlotBooking
{
    /*
     Code Behind Class for BookingInformation.aspx
     */
    public partial class BookingInformation : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Depending on the value in the Session we fetch the slot that he has reserved
             */
            if (Session["UserId"] != null)
            {
                var _userId = Convert.ToInt32(Session["UserId"]);
                /*
                 If the Query String Contains a value then we display a success message
                 */
                if (Request.QueryString["bookingStatus"] != null)
                {
                    var _bookingStatus = Request.QueryString["bookingStatus"].ToString().Trim();
                    if (_bookingStatus == "true")
                    {
                        SuccessMessage.Text = "Slot Booking Successful";
                    }
                    else if (_bookingStatus == "false")
                    {
                        SuccessMessage.Text = "Slot Deleted Successful";
                    }
                }
                /*
                 We fetch the values form the USerAccount, Activity and Slot Information Tables
                 */
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                var _member = (from member in _dataContext.Members where member.FK_UserAccount_Member_in.Equals(_userId) select member).FirstOrDefault();
                var _query = (from slotInformation in _dataContext.SlotInformations
                              join slot in _dataContext.Slots
                              on slotInformation.FK_Slot_SlotInformation_in
                              equals slot.SlotID_in
                              where slotInformation.FK_Member_SlotInformation_in.Equals(_member.MemberID_in)
                              select new
                              {
                                  UserName = (from userAccount in _dataContext.UserAccounts
                                              where userAccount.UserAccountID_in.Equals(slotInformation.FK_Member_SlotInformation_in)
                                              select userAccount).FirstOrDefault().UserName_vc,
                                  Acivity = (from activity in _dataContext.Activities
                                             where activity.ActivityID_in.Equals(slotInformation.FK_Activity_SlotInformation_in)
                                             select activity).FirstOrDefault().Activity_vc,
                                  StartTime = slot.StartTime_vc,
                                  EndTime = slot.EndTime_vc,
                                  Trainer = (from userAccount in _dataContext.UserAccounts
                                             join trainer in _dataContext.Trainers on
                                             userAccount.UserAccountID_in equals trainer.FK_UserAccount_Trainer_in
                                             where trainer.TrainerID_in.Equals(slot.FK_Trainer_Slot_in)
                                             select new { userAccount.UserName_vc }).FirstOrDefault().UserName_vc,
                                  SlotId = slot.SlotID_in,
                                  SlotInformationId = slotInformation.SlotInformationID_in
                              });

                GridView1.DataSource = _query;
                GridView1.DataBind();
            }
            else
            {
                Response.Redirect("/Account/InvalidLogin");
            }
        }
        /* 
         OnClick Event to handle Delete Button in Grid 
         */
        protected void GridView1_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            /*
             Depending on the value in the session we delete the particular Slot and update the Bill Amount
             */
            var _userId = Convert.ToInt32(Session["UserId"]);
            PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
            var _slotInformationId = Convert.ToInt32(GridView1.Rows[e.RowIndex].Cells[7].Text.Trim());
            var _slotId = Convert.ToInt32(GridView1.Rows[e.RowIndex].Cells[6].Text.Trim());
            var _member = (from member in _dataContext.Members where member.FK_UserAccount_Member_in.Equals(_userId) select member).FirstOrDefault();
            var _deleteQuery = (from slotInformation in _dataContext.SlotInformations where slotInformation.SlotInformationID_in.Equals(_slotInformationId) select slotInformation).FirstOrDefault();
            var _updateQuery = (from slot in _dataContext.Slots where slot.SlotID_in.Equals(_slotId) select slot).FirstOrDefault();
            _updateQuery.AvailableSlots_in = _updateQuery.AvailableSlots_in + 1;
            _member.BillAmount_de = _member.BillAmount_de - Decimal.Parse("20.55");
            try
            {
                _dataContext.SlotInformations.DeleteOnSubmit(_deleteQuery);
                _dataContext.SubmitChanges();
                Response.Redirect("/Member/BookingInformation?bookingStatus=false");
            }
            catch (Exception exception)
            {
                ErrorMessage.Text = "Something went wrong..Please try again.";
                Console.WriteLine(exception);
            }
        }

        protected void GridView1_RowCreated(object sender, GridViewRowEventArgs e)
        {
            e.Row.Cells[6].Visible = false;
            e.Row.Cells[7].Visible = false;
        }
    }
}