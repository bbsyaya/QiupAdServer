var div = document.getElementById("my_div");
var b_0 = document.getElementById("a_0");
var b_1 = document.getElementById("a_1");
var b_2 = document.getElementById("a_2");
var b_3 = document.getElementById("a_3");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 100)-1;
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
var maxIndex = Math.ceil(maxNum / 100)-1;
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



//----------------------------浏览器兼容问题---------------------
var idTmr;
function  getExplorer() {
var explorer = window.navigator.userAgent ;
//ie 
if (explorer.indexOf("MSIE") >= 0) {
	return 'ie';
}
//firefox 
else if (explorer.indexOf("Firefox") >= 0) {
	return 'Firefox';
}
//Chrome       
else if(explorer.indexOf("Chrome") >= 0){
	return 'Chrome';
}
//Opera
else if(explorer.indexOf("Opera") >= 0){
	return 'Opera';
}
//Safari
else if(explorer.indexOf("Safari") >= 0){
	return 'Safari';
}}
$("#out").click(function(){
if(getExplorer()=='ie')
{
	var curTbl = document.getElementById("tableList");
	var oXL = new ActiveXObject("Excel.Application");
	//创建AX对象excel 
	var oWB = oXL.Workbooks.Add();
	//获取workbook对象 
	var xlsheet = oWB.Worksheets(1);
	//激活当前sheet 
	var sel = document.body.createTextRange();
	sel.moveToElementText(curTbl);
	//把表格中的内容移到TextRange中 
	sel.select();
	//全选TextRange中内容 
	sel.execCommand("Copy");
	//复制TextRange中内容  
	xlsheet.Paste();
	//粘贴到活动的EXCEL中       
	oXL.Visible = true;
	//设置excel可见属性
	try {
		var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
	} catch (e) {
		print("Nested catch caught " + e);
	} finally {
		oWB.SaveAs(fname);
		oWB.Close(savechanges = false);
		oXL.Quit();
		oXL = null;
		//结束excel进程，退出完成
		idTmr = window.setInterval("Cleanup();", 1);
		}
	}else
	{
		tableToExcel("tableList");
	}
});
function Cleanup() {
 window.clearInterval(idTmr);
 CollectGarbage();
}

var tableToExcel = (function(){
	var uri = 'data:application/vnd.ms-excel;base64,',
	template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
	base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
	format = function(s, c) {
	return s.replace(/{(\w+)}/g,
	function(m, p) { return c[p]; }) }
	return function(table, name) {
	if (!table.nodeType) table = document.getElementById(table);
	var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	window.location.href = uri + base64(format(template, ctx))
	}
})()

