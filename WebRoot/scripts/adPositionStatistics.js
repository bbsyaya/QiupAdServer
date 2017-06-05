var baseUrl =  window.location.protocol + "//" + window.location.host+ "/QiupAdServer/";

var updateTable = function(from,to,adPositionType)
{	
	var media = $("#media_sel").val();
	var channel = $("#channel_sel").val();
	
	var data = $.ajax({
		  type: 'POST',
		  url: baseUrl+"/adPositionStatistics_list2",
		  data: {"from" : from,"to":to,"adPositionType":adPositionType,
			  "media":media,"channel":channel},
		  async:false
		});
	data = data.responseText;
	data = eval("("+data+")");
	
	var body = $("#tbody");
	var str = "";
	for(var i=0;i<data.length;i++)
	{
		var s = "<tr>";		
		s+="<td>" + data[i].requestNum + "</td>";
		s+="<td>" + data[i].showNum + "</td>";
		s+="<td>" + data[i].clickNum + "</td>";
		s+="<td>" + data[i].downloadNum + "</td>";
		s+="<td>" + data[i].downloadSuccessNum + "</td>";
		s+="<td>" + data[i].installNum + "</td>";
		s+="<td>" + data[i].activateNum + "</td>";
		s+="<td>" + data[i].income + "</td>";
		s+="<td>" + data[i].newAddUserNum + "</td>";
		s+="<td>" + data[i].activeUserNum + "</td>";
		s+="<td>" + data[i].adActiveUserNum + "</td>";
		s+= "</tr>";
			
		str += s;	
	}
	body.html(str);
};

$("#today").click(function(){
	var time = $("#today").attr("data-time");
	var date = new Date();
	date.setTime(time);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 23:59:59";
	var adPositionType = $("#adPosition_sel").val();
	$("#from_date").val(from);
	$("#to_date").val(to);
	updateTable(from,to,adPositionType);
});

$("#oneWeek").click(function(){
	var date = new Date();
	var time = $("#today").attr("data-time");
	date.setTime(time);
	date.setDate(date.getDate() - 7);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 7);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 23:59:59";
	var adPositionType = $("#adPosition_sel").val();
	$("#from_date").val(from);
	$("#to_date").val(to);
	updateTable(from,to,adPositionType);
});

$("#oneMonth").click(function(){
	var date = new Date();
	var time = $("#today").attr("data-time");
	date.setTime(time);
	date.setDate(date.getDate() - 30);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 30);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 23:59:59";
	var adPositionType = $("#adPosition_sel").val();
	$("#from_date").val(from);
	$("#to_date").val(to);
	updateTable(from,to,adPositionType);
});

$("#find").click(function(){
	var from = $("#from_date").val();
	var to = $("#to_date").val();
	var adPositionType = $("#adPosition_sel").val();
	updateTable(from,to,adPositionType);
});

$("#adPosition_sel").change(function(){
	var from = $("#from_date").val();
	var to = $("#to_date").val();
	var adPositionType = $(this).val();
	updateTable(from,to,adPositionType);
});


$("#media_sel").change(function(){
	var from = $("#from_date").val();
	var to = $("#to_date").val();
	var adPositionType = $("#adPosition_sel").val();	
	updateTable(from,to,adPositionType);
});


var resf = function()
{

var time = $("#today").attr("data-time");
var date = new Date();
date.setTime(time);
var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+ " 00:00:00";
var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+ " 23:59:59";
$("#from_date").val(from);
$("#to_date").val(to);
};

resf();
