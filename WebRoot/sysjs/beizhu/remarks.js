/**
 * 出现详情
 */
function create_remarks(name, myfont) {
	$("body").children("#dv_remarks").remove();
	var bd_wid = document.documentElement.clientWidth;
	var str = "<div id='dv_remarks' style='left:"+(bd_wid-600)/2+"px'>";
	str += "<div id='dv_rmtitle' class='dv_font'>详情展示</div>";
	str += "<div id='dv_img'></div>";
	str += "<div id='dv_name' class='dv_font'><b>姓名:</b><span>" + name + "</span></div>";
	str += "<div id='dv_bei' class='dv_font'><b>备注信息:</b></div>";
	str += "<div id='dv_neirong' class='dv_font'> " + myfont + "</div>";
	str += "<div id='dv_back'><button id='bt_back'>返回</button></div>";
	str += "</div>";

	$("body").append(str);
	$("#bt_back").click(function(){
		$("body").children("#dv_remarks").remove();
	});
}

