var baseUrl =  window.location.protocol + "//" + window.location.host+ "/QiupAdServer/";

var updateSel_2 = function(type)
{
	var s = '<option value ="-1">选择</option>';
	if(type == "1")
	{
		s += '<option value ="0">请求</option>';
		s += '<option value ="1">展示</option>';
		s += '<option value ="2">点击</option>';
		s += '<option value ="3">下载</option>';
		s += '<option value ="4">下载成功</option>';
		s += '<option value ="5">安装</option>';
		s += '<option value ="6">激活</option>';
	}
	else if(type == "2")
	{
		var data = $.ajax({
			  type: 'POST',
			  url: baseUrl+"/statistics_findAdPosition",
			  data: {},
			  async:false
			});
		data = data.responseText;
		data = eval("("+data+")");
		
		for(var i=0;i<data.length;i++)
		{
			s += '<option value ="';
			s += data[i].type;
			s += '">';
			s += data[i].name;
			s += '</option>';
		}
	}
	
	else if(type == "3")
	{
		s += '<option value ="0">self</option>';
		s += '<option value ="1">appNext</option>';
		s += '<option value ="2">smaato</option>';
		s += '<option value ="3">MobVista</option>';
	}
	
	else if(type == "4")
	{
		var data = $.ajax({
			  type: 'POST',
			  url: baseUrl+"/statistics_findMedia",
			  data: {},
			  async:false
			});
		data = data.responseText;
		data = eval("("+data+")");
		
		for(var i=0;i<data.length;i++)
		{
			s += '<option value ="';
			s += data[i].id;
			s += '">';
			s += data[i].name;
			s += '</option>';
		}
	}
	
	else if(type == "5")
	{
		var data = $.ajax({
			  type: 'POST',
			  url: baseUrl+"/statistics_findSdk",
			  data: {},
			  async:false
			});
		data = data.responseText;
		data = eval("("+data+")");
		for(var i=0;i<data.length;i++)
		{
			s += '<option value ="';
			s += data[i].id;
			s += '">';
			s += data[i].channel;
			s += '</option>';
		}
	}
	
	$("#filed_sel_2").html(s);
}

$("#filed_sel").change(function(){
	var type = $(this).val();
	updateSel_2(type);
});

var updateTable = function(from,to,type,type2)
{	
	var data = $.ajax({
		  type: 'POST',
		  url: baseUrl+"/statistics_list2",
		  data: {"from" : from,"to":to,"type":type,
			  "type2":type2},
		  async:false
		});
	data = data.responseText;
	data = eval("("+data+")");
	
	var body = $("#tbody");
	var str = "";
	for(var i=0;i<data.length;i++)
	{
		var s = "<tr>";		
		s+="<td>" + data[i].id + "</td>";
		s+="<td>" + data[i].userId + "</td>";
		s+="<td>" + data[i].statisticsType + "</td>";
		s+="<td>" + data[i].adPosition + "</td>";
		s+="<td>" + data[i].offerId + "</td>";
		s+="<td>" + data[i].appName + "</td>";
		s+="<td>" + data[i].packageName + "</td>";
		s+="<td>" + data[i].channel + "</td>";
		s+="<td>" + data[i].uploadTime2 + "</td>";
		s+= "</tr>";
			
		str += s;	
	}
	body.html(str);
	
	$("#maxNum").html("总记录数："+data.length);
};

$("#today").click(function(){
	var date = new Date();
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	var type = $("#filed_sel").val();
	var type2 = $("#filed_sel_2").val();
	$("#from_date").val(from.split(" ")[0]);
	$("#to_date").val(to.split(" ")[0]);
	updateTable(from,to,type,type2);
});

$("#oneWeek").click(function(){
	var date = new Date();
	date.setDate(date.getDate() - 7);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 7);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	var type = $("#filed_sel").val();
	var type2 = $("#filed_sel_2").val();
	$("#from_date").val(from.split(" ")[0]);
	$("#to_date").val(to.split(" ")[0]);
	updateTable(from,to,type,type2);
});

$("#oneMonth").click(function(){
	var date = new Date();
	date.setDate(date.getDate() - 30);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 30);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	var type = $("#filed_sel").val();
	var type2 = $("#filed_sel_2").val();
	$("#from_date").val(from.split(" ")[0]);
	$("#to_date").val(to.split(" ")[0]);
	updateTable(from,to,type,type2);
});

$("#find").click(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	var type = $("#filed_sel").val();
	var type2 = $("#filed_sel_2").val();
	updateTable(from,to,type,type2);
});

$("#filed_sel_2").change(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	var type = $("#filed_sel").val();
	var type2 = $("#filed_sel_2").val();
	updateTable(from,to,type,type2);
});



$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = baseUrl + "/statistics_deleteStatistics?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.reload();
});

$(".thUpdate").click(function(){	
	var x = $(this).offset().top; 
	var y = $(this).offset().left - 100; 
	var div = $("#div_update");
	div.css("left",y + "px"); 
	div.css("top",x + "px");
	var preall = $(this).prevAll();
	var id = preall[preall.length-1].innerHTML;
	
	div.attr("title",id);
	div.show();
});

$("html").mousedown(function(e){
	var div = $("#div_update");
	
	if(div.css('display') != "none")
	{
		var w = div.width();
		var h = div.height();
		
		var left =  div.offset().left;
		var top = div.offset().top;
		if(e.pageX <= left+w && e.pageX >= left && e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div.hide();
		}
	}
});

var div = document.getElementById("my_div");
var a_1 = document.getElementById("a_1");
var a_2 = document.getElementById("a_2");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 100)-1;
var index = location.href.split("=")[1];

if(!index || index > maxIndex)
index = 0;

if(index == 0)
{
	a_1.style.display = "none";
}
else
{
	a_1.style.display = "";
}
if(index >= maxIndex )
{
	a_2.style.display = "none";
}
else
{
	a_2.style.display = "";
}

a_1.href = "statistics_list?index=" + (parseInt(index)-1);
a_2.href = "statistics_list?index=" + (parseInt(index)+1);	
};

resf();

var resf2 = function()
{

var date = new Date();
var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1);
$("#from_date").val(from);
$("#to_date").val(to);
};

resf2();