/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
   var cookieHost = getCookie("Clhost");
   var cookiePort = getCookie("ClPort");
   if(cookieHost!==undefined && cookieHost!==null && cookieHost.trim()!=""){
       cookieHost = cookieHost.replace(/"/g,"");
       cookieHost = " Host Address: " + cookieHost;
   }
   
   if(cookiePort!==undefined && cookiePort!==null && cookiePort.trim()!=""){
       cookiePort = " Port Number: " + cookiePort;
   }
   
   var originalText = $(".wrapper_footer").html();
   $(".wrapper_footer").html(originalText+cookieHost+cookiePort);
});

function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length === 2) return parts.pop().split(";").shift();
}

function chooseFile() {
    $("#NewStudyImage").click();
}

function highlightAbout(){
    $("#aboutdiv").css("color","blue");
    $("#aboutlink").css("color","blue");
    $("#aboutlink").css("font-style","italic");
}

function highlightHowItWorks(){
    $("#howitworkdiv").css("color","blue");
    $("#howitworklink").css("color","blue");
    $("#howitworklink").css("font-style","italic");
}

function setUserHeaderValues(){
	var firstName = theUserJSON.name.trim().split(" ")[0];
	$("#login_user_name").text("Hello, "+firstName.charAt(0).toUpperCase()+firstName.slice(1));
}

function setUserSideBarValues(){
	$("#coinsV").text(theUserJSON.coins);
	$("#partpantV").text(theUserJSON.participants);
	$("#parttionV").text(theUserJSON.participations);	
}

function doStartStopEdit(status,studyCode){
	if(status==undefined){
		$("#startStopEditAction").val("edit");
	}
	else if(status == "Start"){
		$("#startStopEditAction").val("Stop");
	}
	else if(status=="Stop"){
		$("#startStopEditAction").val("Start");
	}
	if($("#startStopEditAction").val()!=""){
		$("#selectedStudyCode").val(studyCode);
		$("#studiesForm").submit();
	}
}

function participateInStudy(studyCode){
	$("#selectedParticipationStudyCode").val(studyCode);
	$("#participateStudiesForm").submit();
}
