<%@ Page Title="Employee Profile" Language="C#" MasterPageFile="~/Employee.Master" AutoEventWireup="true" CodeBehind="EmployeeProfile.aspx.cs" Inherits="Planet_Fitness.EmployeeProfile.EmployeeProfile" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
        <h2 style="color: rgb(221, 194, 167);"><%: Title %></h2>
        <p style ="color:white;font-size:large;padding-left:40%">
        <asp:Literal runat="server" ID="SuccessMessage" />
        </p>
        <div class ="CSSTableGenerator">
            <asp:GridView runat ="server" ID ="gridView1"></asp:GridView>
        </div>
        <br/>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10" style="padding-left: 23%;">
                <asp:Button runat="server" OnClick="editEmployee_Click"  Text="Edit Employee" CssClass="btn btn-default" />
            </div>
        </div>
        <br/>
</asp:Content>
