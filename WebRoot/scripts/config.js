var baseUrl =  window.location.protocol + "//" + window.location.host + "/QiupAdServer/";


$("#addConfig").click(function(){
	var d_addConfig = $("#d_addConfig");
	if(d_addConfig.css("display") == "none")
	{
		d_addConfig.css("display","");
		$("#div_table").hide();
		$("#d_updateConfig").hide();
	}
	else
	{
		d_addConfig.css("display","none");
	}
});

var initTime = function(type)
{
	var sel_date = $("#sel_date");
	var sel_hours = $("#sel_hours");
	var sel_minute = $("#sel_minute");
	var sel_hours_end = $("#sel_hours_end");
	var sel_minute_end = $("#sel_minute_end");
	
	if(type == 1)
	{
		$("#tr_sel_date").show();
		$("#tr_sel_day").hide();
	}
	else
	{
		$("#update_tr_sel_date").show();
		$("#update_tr_sel_day").hide();
		
		sel_date = $("#update_sel_date");
		sel_hours = $("#update_sel_hours");
		sel_minute = $("#update_sel_minute");
		sel_hours_end = $("#update_sel_hours_end");
		sel_minute_end = $("#update_sel_minute_end");
	}
	
	var myDate = new Date();
	var year = myDate.getFullYear();   
	var month = myDate.getMonth() + 1;       
	var date = myDate.getDate(); 
	var d = new Date(year,month,0).getDate();
	month = month < 10 ? "0"+month : month;
	var str = year+"-"+month+"-";	
	for(var i = date;i <= d;i++)
	{
		var t = i;
		if(i < 10)
			t = "0"+t;
		var s = "<option value='" + str+t + "' selected>" +str+t + "</option>";
		sel_date.append(s);	
	}
	sel_date.val("");
	
	for(var i = 0;i <= 23;i++)
	{
		var t = i;
		if(i < 10)
			t = "0"+t;
		var s = "<option value='" + t + "' selected>" + t + "</option>";
		sel_hours.append(s);	
		sel_hours_end.append(s);
	}
	sel_hours.val("");
	sel_hours_end.val("");
	
	for(var i = 0;i <= 59;i++)
	{
		var t = i;
		if(i < 10)
			t = "0"+t;
		var s = "<option value='" + t + "' selected>" + t + "</option>";
		sel_minute.append(s);	
		sel_minute_end.append(s);	
	}
	sel_minute.val("");
	sel_minute_end.val("");
};

var initTime2 = function(type)
{
	
	var sel_day = $("#sel_day");
	var sel_hours = $("#sel_hours2");
	var sel_minute = $("#sel_minute2");
	var sel_hours_end = $("#sel_hours_end2");
	var sel_minute_end = $("#sel_minute_end2");
	
	if(type == 1)
	{
		$("#tr_sel_date").hide();
		$("#tr_sel_day").show();
	}
	else
	{
		$("#update_tr_sel_date").hide();
		$("#update_tr_sel_day").show();
		
		sel_day = $("#update_sel_day");
		sel_hours = $("#update_sel_hours2");
		sel_minute = $("#update_sel_minute2");
		sel_hours_end = $("#update_sel_hours_end2");
		sel_minute_end = $("#update_sel_minute_end2");
	}
	
	var arr = ["星期一","星期二","星期三","星期四","星期五","星期六","星期日"];
	for(var i = 0;i < 7;i++)
	{
		var s = "<option value='" + arr[i] + "' selected>" + arr[i] + "</option>";
		sel_day.append(s);	
	}
	sel_day.val("");
	
	for(var i = 0;i <= 23;i++)
	{
		var t = i;
		if(i < 10)
			t = "0"+t;
		var s = "<option value='" + t + "' selected>" + t + "</option>";
		sel_hours.append(s);	
		sel_hours_end.append(s);
	}
	sel_hours.val("");
	sel_hours_end.val("");
	
	for(var i = 0;i <= 59;i++)
	{
		var t = i;
		if(i < 10)
			t = "0"+t;
		var s = "<option value='" + t + "' selected>" + t + "</option>";
		sel_minute.append(s);	
		sel_minute_end.append(s);	
	}
	sel_minute.val("");
	sel_minute_end.val("");
};

$("#addTimeSlot1").click(function(){initTime(1);});
$("#update_addTimeSlot1").click(function(){initTime(2);});

$("#addTimeSlot2").click(function(){initTime2(1);});
$("#update_addTimeSlot2").click(function(){initTime2(2);});


$("#tr_sel_date_add").click(function(){
	var sel_date = $("#sel_date").val();
	var sel_hours = $("#sel_hours").val();
	var sel_minute = $("#sel_minute").val();
	var sel_hours_end = $("#sel_hours_end").val();
	var sel_minute_end = $("#sel_minute_end").val();
	
	var td_timeSlot = $("#td_timeSlot");
	var timeSlot = $("#timeSlot");
	
	var str = sel_date + " " + sel_hours +":"+sel_minute + "--" +sel_hours_end +":"+sel_minute_end;	
	var s = "<label><input type='checkbox' name='timeSlot' checked='checked' value='1'/>" + str + "</label>";
	td_timeSlot.append(s);
	timeSlot.val(timeSlot.val() + str + "type=1,");
	
	//删除时重新赋值上传的数据
	td_timeSlot.children('label:last-child').click(function(){
		$(this).remove();
		var timeSlot = $("#timeSlot");
		timeSlot.val("");
		var put = $("#td_timeSlot").find('label');
		for(var i=0;i<put.length;i++)
		{
			timeSlot.val(timeSlot.val() + put[i].innerText + "type=1,");
		}
	});
});

$("#update_tr_sel_date_add").click(function(){
	var sel_date = $("#update_sel_date").val();
	var sel_hours = $("#update_sel_hours").val();
	var sel_minute = $("#update_sel_minute").val();
	var sel_hours_end = $("#update_sel_hours_end").val();
	var sel_minute_end = $("#update_sel_minute_end").val();
	
	var td_timeSlot = $("#update_td_timeSlot");
	var timeSlot = $("#update_timeSlot");
	
	var str = sel_date + " " + sel_hours +":"+sel_minute + "--" +sel_hours_end +":"+sel_minute_end;	
	var s = "<label><input type='checkbox' name='timeSlot' checked='checked' value='1' />" + str + "</label>";
	td_timeSlot.append(s);
	timeSlot.val(timeSlot.val() + str + "type=1,");
	
	//删除时重新赋值上传的数据
	td_timeSlot.children('label:last-child').click(function(){
		$(this).remove();
		var timeSlot = $("#update_timeSlot");
		timeSlot.val("");
		var put = $("#update_td_timeSlot").find('label');
		for(var i=0;i<put.length;i++)
		{
			timeSlot.val(timeSlot.val() + put[i].innerText + "type=1,");
		}
	});
});

$("#tr_sel_day_add").click(function(){
	var sel_day = $("#sel_day").val();
	var sel_hours = $("#sel_hours2").val();
	var sel_minute = $("#sel_minute2").val();
	var sel_hours_end = $("#sel_hours_end2").val();
	var sel_minute_end = $("#sel_minute_end2").val();
	
	var td_timeSlot = $("#td_timeSlot");
	var timeSlot = $("#timeSlot");
	
	var str = sel_day + " " + sel_hours +":"+sel_minute + "--" +sel_hours_end +":"+sel_minute_end;	
	var s = "<label><input type='checkbox' name='timeSlot' checked='checked' value='1' />" + str + "</label>";
	td_timeSlot.append(s);
	timeSlot.val(timeSlot.val() + str + "type=2,");
	
	//删除时重新赋值上传的数据
	td_timeSlot.children('label:last-child').click(function(){
		$(this).remove();
		var timeSlot = $("#timeSlot");
		timeSlot.val("");
		var put = $("#td_timeSlot").find('label');
		for(var i=0;i<put.length;i++)
		{
			timeSlot.val(timeSlot.val() + put[i].innerText + "type=2,");
		}
	});
});

$("#update_tr_sel_day_add").click(function(){
	var sel_day = $("#update_sel_day").val();
	var sel_hours = $("#update_sel_hours2").val();
	var sel_minute = $("#update_sel_minute2").val();
	var sel_hours_end = $("#update_sel_hours_end2").val();
	var sel_minute_end = $("#update_sel_minute_end2").val();
	
	var td_timeSlot = $("#update_td_timeSlot");
	var timeSlot = $("#update_timeSlot");
	
	var str = sel_day + " " + sel_hours +":"+sel_minute + "--" +sel_hours_end +":"+sel_minute_end;	
	var s = "<label><input type='checkbox' name='timeSlot' checked='checked' value='1' />" + str + "</label>";
	td_timeSlot.append(s);
	timeSlot.val(timeSlot.val() + str + "type=2,");
	
	//删除时重新赋值上传的数据
	td_timeSlot.children('label:last-child').click(function(){
		$(this).remove();
		var timeSlot = $("#update_timeSlot");
		timeSlot.val("");
		var put = $("#update_td_timeSlot").find('label');
		for(var i=0;i<put.length;i++)
		{
			timeSlot.val(timeSlot.val() + put[i].innerText + "type=2,");
		}
	});
});

$("#findConfig").click(function(){
	$("#d_addConfig").hide();
	$("#d_updateConfig").hide();
	$("#div_table").show();
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

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = baseUrl + "/config_findConfig?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	$("#update_id").val(jsonobj.id);
	$("#update_name").val(jsonobj.name);
	$("#update_whiteList").val(jsonobj.whiteList);
	$("#update_showNum").val(jsonobj.showNum);
	$("#update_showTimeInterval").val(jsonobj.showTimeInterval);
	$("#update_repeatNum").val(jsonobj.repeatNum);
	$("#update_name").val(jsonobj.name);
	$("#update_browerWhiteList").val(jsonobj.browerWhiteList);
	if (jsonobj.open) {
		$("#update_open_state1").attr("checked", "checked");
		$("#update_open_state2").attr("checked", "");
	} else {
		$("#update_open_state2").attr("checked", "checked");
		$("#update_open_state1").attr("checked", "");
	}
	
	var update_timeSlot = $("#update_timeSlot");
	var update_td_timeSlot = $("#update_td_timeSlot");
	update_td_timeSlot.children('label').remove();
	update_timeSlot.val(jsonobj.timeSlot);
	if(jsonobj.timeSlot != "" && jsonobj.timeSlot != null)
	{
		var arr = jsonobj.timeSlot.split(",");
		for(var i=0;i<arr.length;i++)
		{
			var str = arr[i].split("type")[0];
			var s = "<label><input type='checkbox' name='timeSlot' checked='checked' value='1' />" + str + "</label>";
			update_td_timeSlot.append(s);
			
			//删除时重新赋值上传的数据
			update_td_timeSlot.children('label:last-child').click(function(){
				$(this).remove();
				var timeSlot = $("#update_timeSlot");
				timeSlot.val("");
				var put = $("#update_td_timeSlot").find('label');
				for(var i=0;i<put.length;i++)
				{
					timeSlot.val(timeSlot.val() + put[i].innerText + "type=1,");
				}
			});
		}
	}
	
	
	$("#d_addConfig").hide();
	$("#d_updateConfig").show();
	$("#div_table").hide();
	$("#div_update").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll =  baseUrl +"/config_deleteConfig?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.href = baseUrl + "/config_list";
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

a_1.href = "config_list?index=" + (parseInt(index)-1);
a_2.href = "config_list?index=" + (parseInt(index)+1);	
};

resf();