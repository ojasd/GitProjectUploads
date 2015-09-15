<%@ Page Title="Payment Details" Language="C#" MasterPageFile="~/Member.Master" AutoEventWireup="true" CodeBehind="PaymentDetails.aspx.cs" Inherits="Planet_Fitness.PaymentDetails.PaymentDetails" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <h2 style="color: black;"><%: Title %>.</h2>
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>
    <div class="form-horizontal" style="padding-left: 20%;padding-right:25%;">
        <div class="roundedDiv">
            <h4>Payment Gateway</h4>
            <hr />
            <asp:ValidationSummary runat="server" CssClass="text-danger" />
            <asp:Label runat="server" ID="label1"></asp:Label>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Amount" CssClass="col-md-2 control-label">Amount</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="Amount" CssClass="form-control" TextMode="SingleLine" />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="CardNumber" CssClass="col-md-2 control-label">Card Number</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="CardNumber" CssClass="form-control" TextMode="SingleLine" MaxLength="16" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="CardNumber"
                        CssClass="text-danger" ErrorMessage="The Card Number is required." />
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator1" ControlToValidate="CardNumber" runat="server"
                        CssClass="text-danger" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+">
                    </asp:RegularExpressionValidator>
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="ExpiryDate" CssClass="col-md-2 control-label">Expiry Date</asp:Label>
                <div class="col-md-10">
                    <asp:DropDownList ID="ExpiryDate" runat="server" CssClass="ddl" AutoPostBack="true" AppendDataBoundItems="true">
                    </asp:DropDownList>
                    /
                    <asp:DropDownList ID="ExpiryDateYear" runat="server" CssClass="ddl" AutoPostBack="true" AppendDataBoundItems="true">
                    </asp:DropDownList>
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="ExpiryDate"
                        CssClass="text-danger" ErrorMessage="The ExpiryDate is required." />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="ExpiryDateYear"
                        CssClass="text-danger" ErrorMessage="The ExpiryDate Year is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="CardHolderName" CssClass="col-md-2 control-label">CardHolder Name</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="CardHolderName" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="CardHolderName"
                        CssClass="text-danger" ErrorMessage="The CardHolder Name is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="CVV" CssClass="col-md-2 control-label">CVV</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="CVV" CssClass="form-control" TextMode="SingleLine" MaxLength="3" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="CVV"
                        CssClass="text-danger" ErrorMessage="The CVV is required." />
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator2" ControlToValidate="CVV" runat="server"
                        CssClass="text-danger" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+">
                    </asp:RegularExpressionValidator>
                </div>
            </div>
            <div class="form-group" style="padding-left: 14%; font-weight: bolder; font-family: sans-serif;">
                <asp:CheckBox ID="SaveCard" runat="server" OnCheckedChanged="SaveCard_CheckedChanged" AutoPostBack="true" />
                <asp:Label ID="SaveCardText" runat="server" Text="Do you wish to save the Card Details"></asp:Label>
            </div>
            <div class="form-group">
                <asp:Label ID="CardNameLabel" runat="server" AssociatedControlID="CardName" CssClass="col-md-2 control-label" Visible="false">Card Name</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="CardName" CssClass="form-control" TextMode="SingleLine" Visible="false" />
                    <asp:RequiredFieldValidator ID="CardNameValidator" runat="server" ControlToValidate="CardName"
                        CssClass="text-danger" ErrorMessage="The Card Name is required." Enabled="false" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <asp:Button runat="server" Text="Make Payment" OnClick="MakePayment_Click" CssClass="btn btn-default" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
