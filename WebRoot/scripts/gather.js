var div = document.getElementById("my_div");
var b_0 = document.getElementById("a_0");
var b_1 = document.getElementById("a_1");
var b_2 = document.getElementById("a_2");
var b_3 = document.getElementById("a_3");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 20)-1;
var index = location.href.split("=")[1];
if(!index || index > maxIndex)
index = 0;
if(index == 0)
{
	b_1.style.display = "none";
	b_0.style.dispaly = "none";
}
else
{
	b_1.style.display = "";
	b_0.style.dispaly = "";
}
if(index >= maxIndex)
{
	b_2.style.display = "none";
	b_3.style.dispaly = "none";
}
else
{
	b_2.style.display = "";
	b_3.style.dispaly = "";
}
b_0.href = "gather_list?index=" + 0;
b_1.href = "gather_list?index=" + (parseInt(index)-1);
b_2.href = "gather_list?index=" + (parseInt(index)+1);
b_3.href = "gather_list?index=" + maxIndex;
}






var rdiv = document.getElementById("my_div2");
var a_0 = document.getElementById("r_0");
var a_1 = document.getElementById("r_1");
var a_2 = document.getElementById("r_2");
var a_3 = document.getElementById("r_3");

var resfs = function()
{
var maxNum = rdiv.title;
var maxIndex = Math.ceil(maxNum / 20)-1;
var index = location.href.split("=")[1];

if(!index || index > maxIndex)
index = 0;

if(index == 0)
{
	a_1.style.display = "none";
	a_0.style.dispaly = "none";
}
else
{
	a_1.style.display = "";
	a_0.style.dispaly = "";
}
if(index >= maxIndex)
{
	a_2.style.display = "none";
	a_3.style.dispaly = "none";
}
else
{
	a_2.style.display = "";
	a_3.style.dispaly = "";
}

a_0.href = "gather_list2?index=" + 0;
a_1.href = "gather_list2?index=" + (parseInt(index)-1);
a_2.href = "gather_list2?index=" + (parseInt(index)+1);	
a_3.href = "gather_list2?index=" + maxIndex;
}

//显示和隐藏相应信息
var flag = false;
$("#see_runinfo").click(function(){
	if(flag==false){
		$("#h3_text").text("app运行信息");
		$("#appinfo").hide();
		$("#runinfo").show();
		$("#see_runinfo").text("查看app上传信息");
		location.href="gather_list2";
		flag=true;
	}else{
		$("#h3_text").text("app上传信息");
		$("#appinfo").show();
		$("#runinfo").hide();
		$("#see_runinfo").text("查看app运行信息");
		location.href="gather_list";
		flag = false;	
	}
});

//app上传信息 操作按钮
$(".thUpdate").click(function(){
	var x = $(this).offset().top; 
	var y = $(this).offset().left ; 
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
		if(e.pageX <= left+w && e.pageX >= left &&	 e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div.hide();
		}
	}
});
//app上传信息 删除按钮
$("#deleteinfo").click(function(){
	var id = $("#div_update").attr("title");
	var hf = document.location.href;
	var urll = hf.substring(0,hf.lastIndexOf("/"))+"/gather_deleteAppInfo?id="+id;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.reload();
});



//app运行信息 操作按钮
$(".runUpdate").click(function(){
	var x = $(this).offset().top; 
	var y = $(this).offset().left-50 ; 
	var div = $("#r_update");
	div.css("left",y + "px"); 
	div.css("top",x + "px");
	var preall = $(this).prevAll();
	var id = preall[preall.length-1].innerHTML;
	div.attr("title",id);
	div.show();
});
$("html").mousedown(function(e){
	var div = $("#r_update");
	if(div.css('display') != "none")
	{
		var w = div.width();
		var h = div.height();
		var left =  div.offset().left;
		var top = div.offset().top;
		if(e.pageX <= left+w && e.pageX >= left &&	 e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div.hide();
		}
	}
});
//app运行信息 删除按钮
$("#deleteruninfo").click(function(){
	var id = $("#r_update").attr("title");
	var hf = document.location.href;
	var urll = hf.substring(0,hf.lastIndexOf("/"))+"/gather_deleteRunInfo?id="+id;
	$.ajax({url:urll,async:false});
	$("#r_update").hide();
	location.href="gather_list2";
	/* location.href="http://localhost/gather_list#"; */
});
if(document.location.href.indexOf("list2")>-1){
	$("#h3_text").text("app运行信息");
	$("#appinfo").hide();
	$("#runinfo").show();
	$("#see_runinfo").text("查看app上传信息");
	flag=true;
}



if($("#my_div").is(":visible")){
	resf();
}else{
	resfs();
}

