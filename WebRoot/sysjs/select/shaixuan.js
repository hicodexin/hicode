
/*通过ajax后台访问所得结果遍历*/
function for_sel_sf(id, f) {
	$("#" + id).children("option").remove();
	var str = "<option selected='selected' disabled='disabled'>请选择</option>";
	$("#" + id).append(str);
	for (var i = 0; i < f.length; i++) {
		var str = "<option value=" + f[i].id + ">" + f[i].name + "</option>";
		$("#" + id).append(str);
	}
}


$().ready(function() {

	var stop,
		sit,
		sitout,
		start;
	//初始位置
	$("#dv_select").css("top", "-651px");

	//展开
	$("#bt_open_aud").click(function() {

		$("#hidd_mask").hide().show(300);

		$("#stu_name").val("");
		$("#stu_phone").val("");
		
		$.post("/hicode/school/showSchool.spc", function(c) {
			if (c.length > 0) {
				for_sel_sf("stu_school", c);
			}
		}, "json");
		
		$.post("/hicode/teacher/showTeacher.spc", function(c) {
			if (c.length > 0) {
				for_sel_sf("stu_teacher", c);
			}
		}, "json");

		$("#kai_time").val("");
		$("#ting_time").val("");
		$("#yao_gu").val("");
		$("#qian_gu").val("");

		$.post("/hicode/adviser/showAdviser.spc", function(f) {
			if (f.length > 0) {
				for_sel_sf("yao_gu", f);
				for_sel_sf("qian_gu", f);
			}
		}, "json");

		start = -650;
		sit = setInterval(for_open, 10);
	});


	//关闭
	$("#sel_res").click(click_close);


	//试听课条件查询
	$("#sel_sub_aud").click(function() {
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"stu_class" : $('#stu_class').val(),
			"stu_school":$("#stu_school").val(),
			"stu_phone" : $('#stu_phone').val().trim(),
			"stu_teacher" : $("#stu_teacher").val(),
			"kai_time" : $("#kai_time").val().trim(),
			"ting_time" : $("#ting_time").val(),
			"yao_gu" : $("#yao_gu").val(),
			"qian_gu" : $('#qian_gu').val()
		};

		console.log(data);
		start_post_aud(for_btn_aud, data);
		click_close();
	});


	function click_close() {
		$("#hidd_mask").show().hide(300);
		stop = 0;
		sitout = setInterval(for_close, 10);
	}

	function for_close() {
		stop = stop - 10;
		$("#dv_select").css("top", stop + "px");
		if (stop <= -670) {
			window.clearInterval(sitout);
			return;
		}
	}

	function for_open() {
		start = start + 10;
		$("#dv_select").css("top", start + "px");
		if (start >= 0) {
			window.clearInterval(sit);
			return;
		}
	}

});