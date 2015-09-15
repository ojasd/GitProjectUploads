<%@ Page Title="Edit Employee" Language="C#" MasterPageFile="~/Employee.Master" AutoEventWireup="true" CodeBehind="EditEmployee.aspx.cs" Inherits="Planet_Fitness.EditEmployee.EditEmployee" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 style="color: rgb(221, 194, 167);"><%: Title %></h2>
    <hr />
    <h4 style="color: rgb(221, 194, 167);">Select an Employee:</h4>
    <div style="padding-left: 30%">
        <asp:GridView ID="GridView1" CssClass="CSSTableGenerator" runat="server" OnRowEditing="GridView1_RowEditing" AutoGenerateEditButton="true">
        </asp:GridView>
    </div>
</asp:Content>
