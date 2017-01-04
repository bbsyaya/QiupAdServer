var baseUrl =  window.location.protocol + "//" + window.location.host+ "/QiupAdServer/";

var updateTable = function(from,to,adPositionType)
{	
	var doubleSta = 0;
	if($("#doubleSta").attr("checked"))
		doubleSta = 1;
	var media = $("#media_sel").val();
	
	var data = $.ajax({
		  type: 'POST',
		  url: baseUrl+"/adPositionStatistics_list2",
		  data: {"from" : from,"to":to,"adPositionType":adPositionType,
			  "doubleSta":doubleSta,"media":media},
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
	var date = new Date();
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	var adPositionType = $("#adPosition_sel").val();
	$("#from_date").val(from.split(" ")[0]);
	$("#to_date").val(to.split(" ")[0]);
	updateTable(from,to,adPositionType);
});

$("#oneWeek").click(function(){
	var date = new Date();
	date.setDate(date.getDate() - 7);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 7);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	var adPositionType = $("#adPosition_sel").val();
	$("#from_date").val(from.split(" ")[0]);
	$("#to_date").val(to.split(" ")[0]);
	updateTable(from,to,adPositionType);
});

$("#oneMonth").click(function(){
	var date = new Date();
	date.setDate(date.getDate() - 30);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 30);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	var adPositionType = $("#adPosition_sel").val();
	$("#from_date").val(from.split(" ")[0]);
	$("#to_date").val(to.split(" ")[0]);
	updateTable(from,to,adPositionType);
});

$("#find").click(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	var adPositionType = $("#adPosition_sel").val();
	updateTable(from,to,adPositionType);
});

$("#adPosition_sel").change(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	var adPositionType = $(this).val();
	updateTable(from,to,adPositionType);
});

$("#doubleSta").click(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	var adPositionType = $("#adPosition_sel").val();
	updateTable(from,to,adPositionType);
});

$("#media_sel").change(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	var adPositionType = $("#adPosition_sel").val();	
	updateTable(from,to,adPositionType);
});


var resf = function()
{

var date = new Date();
var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1);
$("#from_date").val(from);
$("#to_date").val(to);
$("#doubleSta").attr("checked", "checked");
};

resf();
