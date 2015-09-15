<%@ Page Title="Slot Information" Language="C#" MasterPageFile="~/Trainer.Master" AutoEventWireup="true" CodeBehind="SlotInformation.aspx.cs" Inherits="Planet_Fitness.SlotInformationTrainer.SlotInformation" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 style="color: white;"><%: Title %></h2>
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>
    <div style="padding-left: 26%;">
        <asp:GridView ID="GridView1" runat="server" CssClass="CSSTableGenerator">
        </asp:GridView>
    </div>
</asp:Content>
