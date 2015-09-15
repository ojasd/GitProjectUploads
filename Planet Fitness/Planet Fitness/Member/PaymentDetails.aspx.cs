using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

/*
 Namespace Planet_Fitness.PaymentDetails which contains PaymentDetails Class
 */
namespace Planet_Fitness.PaymentDetails
{
    public partial class PaymentDetails : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
             Depeding on the userId in the session we fetch the Card Details
             */
            if (Session["UserId"] != null)
            {
                if (!IsPostBack)
                {
                    PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                    var _userId = Convert.ToInt32(Session["UserId"]);
                    var _query = (from cardDetails in _dataContext.CardDetails
                                  where cardDetails.FK_Member_CardDetails_in.Equals((from member in _dataContext.Members
                                                                                     where member.FK_UserAccount_Member_in.
                                                                                     Equals(Session["UserId"].ToString())
                                                                                     select member).FirstOrDefault().MemberID_in)
                                  select cardDetails);
                    /*
                     Populate the list with the values of months
                     */
                    var _month = Enumerable.Range(1, 12)
                                .Select(i => new { Text = i.ToString(), Value = i.ToString() });
                    ExpiryDate.DataSource = _month;
                    ExpiryDate.DataTextField = "Text";
                    ExpiryDate.DataValueField = "Value";
                    ExpiryDate.DataBind();
                    ExpiryDate.Items.Insert(0, new ListItem("--Month--", ""));
                    /*
                     Populate the list with the values of years
                     */
                    var _year = Enumerable.Range(2010, 32)
                                   .Select(i => new { Text = i.ToString(), Value = i.ToString() });
                    ExpiryDateYear.DataSource = _year;
                    ExpiryDateYear.DataTextField = "Text";
                    ExpiryDateYear.DataValueField = "Value";
                    ExpiryDateYear.DataBind();
                    ExpiryDateYear.Items.Insert(0, new ListItem("--Year--", ""));
                    /*
                     If there are carddetails in the table for this particular user then prepopulate them
                     */
                    if (_query.Count() == 1)
                    {
                        CardNumber.Text = _query.FirstOrDefault().CardNo_in.ToString();
                        var _date = _query.FirstOrDefault().ExpDate_vc.Split('/')[0];
                        var _monthOf = "20" + _query.FirstOrDefault().ExpDate_vc.Split('/')[1];
                        if (_date.ElementAt(0).Equals('0'))
                            _date = _date.Substring(1, 1);
                        ExpiryDate.SelectedValue = _date;
                        ExpiryDateYear.SelectedValue = _monthOf;
                        SaveCard.Visible = false;
                        SaveCardText.Visible = false;
                        CardHolderName.Text = _query.FirstOrDefault().CardAliasName_vc;
                    }
                    /*
                     Populate the BillAmount
                     */
                    var _result = (from member in _dataContext.Members where member.FK_UserAccount_Member_in.Equals(_userId) select member);
                    if (_result.Count() == 1)
                        Amount.Text = _result.FirstOrDefault().BillAmount_de.ToString();
                    else
                        Amount.Text = "0.00";
                }
            }
            else
                Response.Redirect("/Account/InvalidLogin");
        }
        /*
         On Check Event to make the hidden fields visible
         */
        protected void SaveCard_CheckedChanged(object sender, EventArgs e)
        {
            if (SaveCard.Checked)
            {
                CardName.Visible = true;
                CardNameLabel.Visible = true;
                CardNameValidator.Enabled = true;
            }
            else
            {
                CardName.Visible = false;
                CardNameLabel.Visible = false;
                CardNameValidator.Enabled = false;
            }
        }
        /*
         On Click event to Make Payment Button Click
         */
        protected void MakePayment_Click(object sender, EventArgs e)
        {
            /*
             Only Perform some action if form is validated and is valid
             */
            if (IsValid)
            {
                var _expDate = "";
                var _expYear = "";
                decimal _amount = 0;
                /*
                 Check for validity of the Year and month Values
                 */
                if (ExpiryDate.Text != "")
                {
                    if (ExpiryDate.Text.Length == 1)
                        _expDate = '0' + ExpiryDate.Text;
                    else
                        _expDate = ExpiryDate.Text;
                }
                if (ExpiryDateYear.Text != "")
                {
                    _expYear = ExpiryDateYear.Text.Substring(2, 2);
                }
                /*
                 Check for validity of Bill Amount
                 */
                try
                {
                    _amount = Decimal.Parse(Amount.Text);
                }
                catch (Exception exception)
                {
                    ErrorMessage.Text = "Should be a decimal value";
                    Console.WriteLine(exception);
                }
                PlanetFitnessDataContext _dataContext = new PlanetFitnessDataContext();
                var _userId = Convert.ToInt32(Session["UserId"]);
                var _validAmount = (from member in _dataContext.Members where member.FK_UserAccount_Member_in.Equals(_userId) select member).FirstOrDefault().BillAmount_de;
                /*
                Check whether Bill Amount is valid that is less than or equal to Actual Bill Amount 
                */
                var _compare = Decimal.Compare(_amount, _validAmount);
                label1.Text = _compare.ToString();
                if (_compare > 0)
                {
                    ErrorMessage.Text = "Amount should be less than amount to be paid";
                }
                else
                {
                    var _query = (from cardDetails in _dataContext.CardDetails
                                  where cardDetails.CardNo_in.Equals(CardNumber.Text)
                                  && cardDetails.ExpDate_vc.Equals(_expDate + "/" + _expYear)
                                  && cardDetails.FK_Member_CardDetails_in.Equals((from member in _dataContext.Members
                                                                                  where member.FK_UserAccount_Member_in.
                                                                                  Equals(Session["UserId"].ToString())
                                                                                  select member).FirstOrDefault().MemberID_in)
                                  select cardDetails);
                    var _memberDetails = (from member in _dataContext.Members
                                          where member.FK_UserAccount_Member_in.
                                          Equals(Session["UserId"].ToString())
                                          select member).FirstOrDefault();
                    if (_query.Count() != 0)
                    {
                        _memberDetails.BillAmount_de = Math.Abs(_validAmount - _amount);
                        _dataContext.SubmitChanges();
                        Response.Redirect("/Member/PaymentSuccessful");
                    }
                    else
                    {
                        ErrorMessage.Text = "Card Details Should be Valid";
                    }
                }
            }
        }
    }
}