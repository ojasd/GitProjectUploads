<%@ Page Title="Log out" Language="C#" MasterPageFile="~/PlanetFitness.Master" AutoEventWireup="true" CodeBehind="Logout.aspx.cs" Inherits="Planet_Fitness.Account.Logout" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
     <hgroup>
        <h2 class="text-danger">This account has been logged out, please <a runat="server" href="~/Account/Login">Login</a> again</h2>
    </hgroup>
</asp:Content>
