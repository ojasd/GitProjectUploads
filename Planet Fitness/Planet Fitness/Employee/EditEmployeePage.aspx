<%@ Page Title="Edit Employee Details" Language="C#" MasterPageFile="~/Employee.Master" AutoEventWireup="true" CodeBehind="EditEmployeePage.aspx.cs" Inherits="Planet_Fitness.EditEmployeeProfile.EditEmployeePage" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 style="color: rgb(221, 194, 167);"><%: Title %></h2>
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>
    <div class="form-horizontal" style="padding-left: 20%;padding-right:15%;">
        <div class ="roundedDiv">
            <h4>Edit Employee</h4>
            <hr />
            <asp:ValidationSummary runat="server" CssClass="text-danger" />
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="UserName" CssClass="col-md-2 control-label">User Name</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="UserName" CssClass="form-control" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="UserName"
                        CssClass="text-danger" ErrorMessage="The User Name is required." />
                </div>
            </div>
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
                <asp:Label runat="server" AssociatedControlID="Ssn" CssClass="col-md-2 control-label">SSN</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="Ssn" CssClass="form-control" MaxLength="12" TextMode="SingleLine" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Ssn"
                        CssClass="text-danger" ErrorMessage="The SSN is required." />
                    <asp:RegularExpressionValidator Display="Dynamic" ControlToValidate="Ssn"
                        CssClass="text-danger" ID="RegularExpressionValidator2" ValidationExpression="\(?\d{3}\)?-? *\d{3}-? *-?\d{4}" runat="server"
                        ErrorMessage="Maximum 10 characters allowed."></asp:RegularExpressionValidator>
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Password" CssClass="col-md-2 control-label">Password</asp:Label>
                <div class="col-md-10">
                    <asp:TextBox runat="server" ID="Password" TextMode="SingleLine" CssClass="form-control" />
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Password"
                        CssClass="text-danger" ErrorMessage="The password field is required." />
                </div>
            </div>
            <div class="form-group">
                <asp:Label runat="server" AssociatedControlID="Activity" CssClass="col-md-2 control-label">Activity</asp:Label>
                <div class="col-md-10">
                    <asp:DropDownList ID="Activity" runat="server" CssClass="ddl" AutoPostBack="true" AppendDataBoundItems="true"></asp:DropDownList>
                    <asp:RequiredFieldValidator runat="server" ControlToValidate="Activity"
                        CssClass="text-danger" ErrorMessage="The Activity is required." />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <asp:Button runat="server" OnClick="EditEmployee_Click" Text="Edit Employee" CssClass="btn btn-default" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
