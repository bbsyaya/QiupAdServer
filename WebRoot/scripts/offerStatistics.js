var baseUrl =  window.location.protocol + "//" + window.location.host+ "/QiupAdServer/";

Date.prototype.format = function(format) {
    var o = {
            "M+" : this.getMonth() + 1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S"  : this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
    for ( var k in o) if (new RegExp("(" + k + ")").test(format)) {
        format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]: ("00" + o[k]).substr(("" + o[k]).length));
    }
        return format;
}
function getDate(strDate) {
    var date = new Date(strDate.time);
    return date.format('yyyy-MM-dd');

}

var updateTable = function(from,to)
{	

	var offer = $("#offer_sel").val();
	
	var data = $.ajax({
		  type: 'POST',
		  url: baseUrl+"/offerStatistics_list2",
		  data: {"from" : from,"to":to,"offer":offer},
		  async:false
		});
	data = data.responseText;
	data = eval("("+data+")");
	
	var body = $("#tbody");
	var str = "";
	for(var i=0;i<data.length;i++)
	{
		var s = "<tr>";		
		s+="<td>" + data[i].offerName + "</td>";
		s+="<td>" + data[i].requestNum + "</td>";
		s+="<td>" + data[i].showNum + "</td>";
		s+="<td>" + data[i].clickNum + "</td>";
		s+="<td>" + data[i].downloadNum + "</td>";
		s+="<td>" + data[i].downloadSuccessNum + "</td>";
		s+="<td>" + data[i].installNum + "</td>";
		s+="<td>" + data[i].activateNum + "</td>";
		s+="<td>" + data[i].clickRate + "</td>";
		s+="<td>" + data[i].downloadRate + "</td>";
		s+="<td>" + data[i].installRate + "</td>";
		s+="<td>" + data[i].activateRate + "</td>";
		s+="<td>" + getDate(data[i].stime) + "</td>";
		s+= "</tr>";
			
		str += s;	
	}
	body.html(str);
};


$("#find").click(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	updateTable(from,to);
});

$("#offer_sel").change(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	updateTable(from,to);
});



var resf = function()
{

var date = new Date();
var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()-1);
var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate());
$("#from_date").val(from);
$("#to_date").val(to);
};

resf();