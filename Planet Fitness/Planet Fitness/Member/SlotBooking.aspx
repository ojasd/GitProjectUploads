<%@ Page Title="Slot Booking" Language="C#" MasterPageFile="~/Member.Master" AutoEventWireup="true" CodeBehind="SlotBooking.aspx.cs" Inherits="Planet_Fitness.SlotBooking.SlotBooking" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2 style="color: black;"><%: Title %></h2>
    <hr />
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>
    <h4 style="color: rgb(221, 194, 167);">Select an activity:</h4>
    <asp:DropDownList ID="DropDownList1" runat="server" CssClass="ddl" AutoPostBack ="true" AppendDataBoundItems="true" OnSelectedIndexChanged ="DropDownList1_SelectedIndexChanged"></asp:DropDownList>
    <div style="padding-left: 22%;">
    <asp:GridView ID="GridView1" CssClass ="CSSTableGenerator" runat="server" Visible="false">
        <Columns>
            <asp:TemplateField>
                <ItemTemplate>
                   <asp:RadioButton runat ="server" ID ="radioButton1" GroupName="RowSelectore" OnCheckedChanged="RowSelector_CheckedChanged" AutoPostBack ="true"/>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
    </div>
</asp:Content>
