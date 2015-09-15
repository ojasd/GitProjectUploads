using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.SlotInformationTrainer which contains SlotInformation Class
 */
namespace Planet_Fitness.SlotInformationTrainer
{
    public partial class SlotInformation : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Depending on the UserId we populate the Slots that the Trainer has to train
             */
            if (Session["UserId"] != null)
            {
                var _userId = Convert.ToInt32(Session["UserId"]);
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                var _query = (from slot in _dataContext.Slots
                              where slot.FK_Trainer_Slot_in.Equals
                                  ((from trainer in _dataContext.Trainers
                                    where trainer.FK_UserAccount_Trainer_in.Equals(_userId)
                                    select trainer).FirstOrDefault().TrainerID_in)
                              select new
                              {
                                  SlotId = slot.SlotID_in,
                                  ActivityId = (from activity in _dataContext.Activities where activity.ActivityID_in.Equals(slot.FK_Activity_Slot_in) select activity).FirstOrDefault().Activity_vc,
                                  StartTime = slot.StartTime_vc,
                                  EndTime = slot.EndTime_vc,
                                  BookedSlots = slot.NumberOfSlots_in - slot.AvailableSlots_in
                              });
                GridView1.DataSource = _query;
                GridView1.DataBind();
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
    }
}