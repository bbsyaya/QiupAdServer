var baseUrl =  window.location.protocol + "//" + window.location.host+ "/QiupAdServer/";


$("#addMedia").click(function(){
	var d_addMedia = $("#d_addMedia");
	if(d_addMedia.css("display") == "none")
	{
		d_addMedia.css("display","");
		$("#div_table").hide();
		$("#d_updateMedia").hide();
	}
	else
	{
		d_addMedia.css("display","none");
	}
});


$("#findMedia").click(function(){
	$("#d_addMedia").hide();
	$("#d_updateMedia").hide();
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
	
	var urll = baseUrl + "/media_findMedia?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#update_id").val(jsonobj.id);
	$("#update_name").val(jsonobj.name);
	$("#update_packageName").val(jsonobj.packageName);
	$("#update_loopTime").val(jsonobj.loopTime);

	if(jsonobj.open)
	{
		$("#open_state1").attr("checked", "checked");
		$("#open_state2").attr("checked", "");
	} else {
		$("#open_state2").attr("checked", "checked");
		$("#open_state1").attr("checked", "");
	}
	
	if(jsonobj.uploadPackage)
	{
		$("#uploadPackage_state1").attr("checked", "checked");
		$("#uploadPackage_state2").attr("checked", "");
	} else {
		$("#uploadPackage_state2").attr("checked", "checked");
		$("#uploadPackage_state1").attr("checked", "");
	}
	
	if(jsonobj.adPosition != "" && jsonobj.adPosition != null)
	{
		var arr = jsonobj.adPosition.split(",");
		for(var i=0;i<arr.length;i++)
		{
			var id = "#update_adPositionSwitch_" + arr[i];
			$(id).attr("checked", "checked");
		}
	}
	
	$("#d_addMedia").hide();
	$("#d_updateMedia").show();
	$("#div_table").hide();
	$("#div_update").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll =  baseUrl +"/media_deleteMedia?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.href = baseUrl + "/media_list";
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

a_1.href = "media_list?index=" + (parseInt(index)-1);
a_2.href = "media_list?index=" + (parseInt(index)+1);	
};

resf();