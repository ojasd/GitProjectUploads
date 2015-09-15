<%@ Page Title="Edit Slot" Language="C#" MasterPageFile="~/Employee.Master" AutoEventWireup="true" CodeBehind="EditSlot.aspx.cs" Inherits="Planet_Fitness.EditSlot.EditSlot" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
     <h2 style="color: rgb(221, 194, 167);"><%: Title %></h2>
    <hr />
    <p style ="color:green;font-size:large;padding-left:40%">
        <asp:Literal runat="server" ID="SuccessMessage" />
     </p>
    <h4 style="color: rgb(221, 194, 167);">Select a Slot:</h4>
    <div style="padding-left: 10%;">
    <asp:GridView ID="GridView1" CssClass ="CSSTableGenerator" Width ="94%" runat="server" OnRowEditing ="GridView1_RowEditing" AutoGenerateEditButton ="true">
    </asp:GridView>
        </div>
</asp:Content>
