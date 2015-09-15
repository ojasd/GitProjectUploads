<%@ Page Title="Edit Employee Profile" Language="C#" MasterPageFile="~/Employee.Master" AutoEventWireup="true" CodeBehind="EditEmployeeProfile.aspx.cs" Inherits="Planet_Fitness.EditEmployeeProfile.EditEmployeeProfile" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 style="color: rgb(221, 194, 167);"><%: Title %></h2>
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>
    <div class="form-horizontal" style="padding-left: 18%;padding-right:10%;">
        <div class ="roundedDiv">
            <h4>Edit Employee</h4>
            <hr />
            <asp:ValidationSummary runat="server" CssClass="text-danger" />
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="FirstName" CssClass="col-md-2 control-label">First Name</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="FirstName" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="FirstName"
                        CssClass="text-danger" ErrorMessage="The First Name is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="LastName" CssClass="col-md-2 control-label">Last Name</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="LastName" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="LastName"
                        CssClass="text-danger" ErrorMessage="The Last Name is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Address" CssClass="col-md-2 control-label">Address</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="Address" CssClass="form-control" TextMode="MultiLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Address"
                        CssClass="text-danger" ErrorMessage="The Address is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="PhoneNumber" CssClass="col-md-2 control-label">Phone Number</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="PhoneNumber" CssClass="form-control" TextMode="SingleLine" MaxLength="14" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="PhoneNumber"
                        CssClass="text-danger" ErrorMessage="The Phone Number is required." />
                    <asp:RegularExpressionValidator Display="Dynamic" ControlToValidate="PhoneNumber"
                        CssClass="text-danger" ID="RegularExpressionValidator1" ValidationExpression="\(?\d{3}\)?-? *\d{3}-? *-?\d{4}"
                        runat="server" ErrorMessage="Maximum 10 characters allowed."></asp:RegularExpressionValidator>
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Email" CssClass="col-md-2 control-label">Email</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="Email" CssClass="form-control" TextMode="Email" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Email"
                        CssClass="text-danger" ErrorMessage="The Email field is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="DateOfBirthOne" CssClass="col-md-2 control-label">Date Of Birth</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="DateOfBirthOne" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="DateOfBirthOne"
                        CssClass="text-danger" ErrorMessage="The Date Of Birth is required." />
                    <asp:CompareValidator runat="server" Operator="DataTypeCheck" Type="Date"
                        ControlToValidate="DateOfBirthOne" CssClass="text-danger" ErrorMessage="The Date Should be in mm/dd/YYYY" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <asp:Button runat="server" OnClick="EditEmployeeProfile_Click" Text="Update Profile" CssClass="btn btn-default" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
