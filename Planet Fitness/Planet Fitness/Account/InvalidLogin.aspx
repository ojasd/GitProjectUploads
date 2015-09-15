<%@ Page Title="Invalid Login" Language="C#" MasterPageFile="~/PlanetFitness.Master" AutoEventWireup="true" CodeBehind="InvalidLogin.aspx.cs" Inherits="Planet_Fitness.Account.InvalidLogin" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <hgroup>
       <h2 class="text-danger" style="padding-left: 23%;">Invalid Login Attempt, please <a runat="server" href="~/Account/Login">Login</a> again</h2>
    </hgroup>
</asp:Content>
