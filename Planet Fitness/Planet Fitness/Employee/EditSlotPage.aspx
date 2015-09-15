<%@ Page Title="Edit Slot" Language="C#" MasterPageFile="~/Employee.Master" AutoEventWireup="true" CodeBehind="EditSlotPage.aspx.cs" Inherits="Planet_Fitness.EditSlotPage.EditSlotPage" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 style="color: rgb(221, 194, 167);"><%: Title %></h2>
    <div class="form-horizontal" style="padding-left: 20%;padding-right: 20%;">
        <div class="roundedDiv">
            <h4>Edit Slot</h4>
            <hr />
            <asp:ValidationSummary runat="server" CssClass="text-danger" />
                <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="SlotId" CssClass="col-md-2 control-label">Slot Id</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" Enabled="False" ID="SlotId" CssClass="form-control" TextMode="SingleLine" MaxLength="10" />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Activity" CssClass="col-md-2 control-label">Activity</asp:Label>
                <div class="col-md-10">
                    <asp:DropDownList ID="Activity" Enabled="False" runat="server" CssClass="ddl" AutoPostBack="true" AppendDataBoundItems="true"></asp:DropDownList>
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Activity"
                        CssClass="text-danger" ErrorMessage="The Activity is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Availability" CssClass="col-md-2 control-label">Availability</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="Availability" CssClass="form-control" TextMode="SingleLine" MaxLength="10" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Availability"
                        CssClass="text-danger" ErrorMessage="The Availabilty is required." />
                    <asp:RegularExpressionValidator Display="Dynamic" ControlToValidate="Availability"
                        CssClass="text-danger" ID="RegularExpressionValidator1" ValidationExpression="^([1-9]|10)$"
                        runat="server" ErrorMessage="Between 1-10"></asp:RegularExpressionValidator>
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="StartTime" CssClass="col-md-2 control-label">Start Time</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="StartTime" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="StartTime"
                        CssClass="text-danger" ErrorMessage="The Start Time is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="EndTime" CssClass="col-md-2 control-label">End Time</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="EndTime" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="EndTime"
                        CssClass="text-danger" ErrorMessage="The End Time is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Trainer" CssClass="col-md-2 control-label">Trainer</asp:Label>
                <div class="col-md-10">
                    <asp:DropDownList ID="Trainer" runat="server" CssClass="ddl" AutoPostBack="true" AppendDataBoundItems="true"></asp:DropDownList>
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Trainer"
                        CssClass="text-danger" ErrorMessage="The Trainer is required." />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <asp:Button runat="server" OnClick="EditSlot_Click" Text="Edit Slot" CssClass="btn btn-default" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
