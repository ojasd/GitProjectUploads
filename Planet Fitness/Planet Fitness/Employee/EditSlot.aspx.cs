using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.EditSlot which contains an Edit Slot Class
 */
namespace Planet_Fitness.EditSlot
{
    /*
     Code Behind Class for EditSlot.aspx
     */
    public partial class EditSlot : System.Web.UI.Page
    {
        /*
         If we get a Query String then we display a success message indicating successful updation
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserId"] != null)
            {
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                if (Request.QueryString["editSlot"] != null)
                {
                    var _bookingStatus = Request.QueryString["editSlot"].ToString().Trim();
                    if (_bookingStatus == "true")
                    {
                        SuccessMessage.Text = "Slot Edited Successful";
                    }
                }
                /*
                 We Populate the Grid with the Slots from the Slots Table
                 */
                var _query = (from slot in _dataContext.Slots
                              join
                                  trainer in _dataContext.Trainers
                                  on slot.FK_Trainer_Slot_in equals trainer.TrainerID_in
                              join userAccount in _dataContext.UserAccounts
                              on trainer.FK_UserAccount_Trainer_in equals userAccount.UserAccountID_in
                              select new
                              {
                                  SlotId = slot.SlotID_in,
                                  ActivityName = (from activity in _dataContext.Activities
                                                  where activity.ActivityID_in.Equals(slot.FK_Activity_Slot_in)
                                                  select activity).FirstOrDefault().Activity_vc,
                                  StartTime = slot.StartTime_vc,
                                  EndTime = slot.EndTime_vc,
                                  NumberOfSlots = slot.NumberOfSlots_in,
                                  AvailableSlots = slot.AvailableSlots_in,
                                  UserName = userAccount.UserName_vc
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
         OnClick Event for Edit Button on the Grid Row
         */
        protected void GridView1_RowEditing(object sender, GridViewEditEventArgs e)
        {
            String _slotId = GridView1.Rows[e.NewEditIndex].Cells[1].Text.ToString().Trim();
            Response.Redirect("/Employee/EditSlotPage.aspx?slotId=" + _slotId);
        }
    }
}