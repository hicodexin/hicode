
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

/*前台写死数据结果遍历，f:所遍历数组;optionName:选中列表*/
function for_sel_sf02(id, f, optionName) {
	$("#" + id).children("option").remove();
	var str = "<option selected='selected' disabled='disabled'>请选择</option>";
	$("#" + id).append(str);
	//删除含有"请选择"的选项
	for(var i = 0; i<f.length;i++){
		(function(){
			var t = i;
			if($(f[t]).html() == '请选择'){
				f.splice(t,1);
			}
		})();
	}
	for (var k = 0; k < f.length; k++) {
		if ($(f[k]).html() == optionName) {
			var str = "<option selected='selected' value='" + (k + 1) + "' >" + $(f[k]).html() + "</option>";
		} else {
			var str = "<option value='" + (k + 1) + "'>" + $(f[k]).html() + "</option>";
		}
		$("#" + id).append(str);
	}

}

function for_sel_Age(id, f) {
	$("#" + id).children("option").remove();
	var str = "<option selected='selected' disabled='disabled'>请选择</option>";
	$("#" + id).append(str);
	for (var k = 0; k < f; k++) {
		var str = "<option value='" + k + "'>" + k + "</option>";
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

	/** ==============================================条件查询--展开============================================== */

	//试听课》》》展开
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

		$.post("/hicode/adviser/showAdviser.spc", function(f) {
			if (f.length > 0) {
				for_sel_sf("yao_gu", f);
				for_sel_sf("qian_gu", f);
			}
		}, "json");

		start = -650;
		sit = setInterval(for_open, 10);
	});

	//已报名》》》展开
	$("#bt_open_cus").click(function() {

		$("#hidd_mask").hide().show(300);

		$("#stu_name").val("");
		$("#stu_subject").val("");

		$.post("/hicode/subject/showSubject.spc", function(c) {
			if (c.length > 0) {
				console.log(c);
				for_sel_sf("stu_subject", c);
			}
		}, "json");

		$("#stu_period").val("");
		$("#stu_teacher").val("");
		$.post("/hicode/teacher/showTeacher.spc", function(c) {
			if (c.length > 0) {
				for_sel_sf("stu_teacher", c);
			}
		}, "json");

		$("#kai_time").val("");
		$("#ting_time").val("");

		$.post("/hicode/adviser/showAdviser.spc", function(f) {
			if (f.length > 0) {
				for_sel_sf("qian_gu", f);
			}
		}, "json");
		$("#if_xufei").val("-1");
		start = -650;
		sit = setInterval(for_open, 10);
	});

	//TMK》》》展开
	$("#bt_open_TMK").click(function() {

		$("#hidd_mask").hide().show(300);

		$("#stu_name").val("");
		$("#stu_phone").val("");

		$.post("/hicode/school/showSchool.spc", function(c) {
			if (c.length > 0) {
				for_sel_sf("stu_school", c);
			}
		}, "json");

		$("#sel_yixiang").val("请选择");
		for_sel_Age("kai_age",21);
		for_sel_Age("ting_age",21);
		
		$("#if_arrival").val("请选择");

		start = -650;
		sit = setInterval(for_open, 10);
	});
	
	//顾问签单成功》》》展开
	$("#bt_open_advSuccess").click(function() {

		$("#hidd_mask").hide().show(300);

		$("#stu_name").val("");
		$.post("/hicode/adviser/showAdviser.spc", function(f) {
			if (f.length > 0) {
				for_sel_sf("qian_gu", f);
			}
		}, "json");
		start = -650;
		sit = setInterval(for_open, 10);
	});
	
	//顾问跟单》》》展开
	$("#bt_open_sig").click(function() {

		$("#hidd_mask").hide().show(300);

		$("#stu_name").val("");
		$("#stu_phone").val("");

		$.post("/hicode/school/showSchool.spc", function(c) {
			if (c.length > 0) {
				for_sel_sf("stu_school", c);
			}
		}, "json");

		start = -650;
		sit = setInterval(for_open, 10);
	});	
	
	//VIP_顾问跟单》》》展开
	$("#bt_open_VIP_ADV").click(function() {

		$("#hidd_mask").hide().show(300);

		$("#stu_name").val("");
		$("#stu_phone").val("");
		start = -650;
		sit = setInterval(for_open, 10);
	});
	
	//异业兑换名单》》》展开
	$("#bt_open_kua_phone").click(function() {
	
		$("#hidd_mask").hide().show(300);
		$("#kai_time").val("");
		$("#ting_time").val("");
		$("#begin_num").val("");
		$("#end_num").val("");
		
		var tt = $("#stu_class option");
		for_sel_sf02("stu_class", tt);
		
		$.post("/hicode/kuajie/showKuaJie.spc", function(f) {
			if (f.length > 0) {
				for_sel_sf("stu_name", f,name);
			}
		}, "json");
		
		$.post("/hicode/adviser/showAdviser.spc", function(f) {
			if (f.length > 0) {
				for_sel_sf("yao_gu", f);
			}
		}, "json");

		start = -650;
		sit = setInterval(for_open, 10);
	});

	/** ==============================================条件查询data============================================== */

	//试听课条件查询
	$("#sel_sub_aud").click(function() {
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"stu_class" : $('#stu_class').val(),
			"stu_school" : $("#stu_school").val(),
			"stu_phone" : $('#stu_phone').val().trim(),
			"stu_teacher" : $("#stu_teacher").val(),
			"kai_time" : $("#kai_time").val().trim(),
			"ting_time" : $("#ting_time").val(),
			"yao_gu" : $("#yao_gu").val(),
			"qian_gu" : $('#qian_gu').val()
		};

		/*console.log(data);*/
		start_post_aud(for_btn_aud, data);
		click_close();
	});
	
	//报名学员>>>条件查询
	$("#sel_sub_cus").click(function() {
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"stu_subject" : $('#stu_subject').val(),
			"stu_period" : $("#stu_period").val(),
			"stu_teacher" : $("#stu_teacher").val(),
			"kai_time" : $("#kai_time").val().trim(),
			"ting_time" : $("#ting_time").val(),
			"if_xufei":$("#if_xufei").val(),
			"qian_gu" : $('#qian_gu').val()
		};

		start_post_cus(for_btn_cus, data);
		click_close();
	});

	//TMK条件查询
	$("#sel_sub_TMK").click(function() {
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"ph_school" : $("#stu_school").val(),
			"phone_num" : $('#stu_phone').val().trim(),
			"ph_intention" : $("#sel_yixiang").val(),
			"if_arrival" : $("#if_arrival").val(),
			"kai_age" : $("#kai_age").val(),
			"ting_age" : $("#ting_age").val()
		};
		start_post_TMK(for_btn_TMK, data);
		click_close();
	});
	
	//VIP_TMK条件查询
	$("#sel_sub_VIP_TMK").click(function() {
		
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"ph_school" : $("#stu_school").val(),
			"phone_num" : $('#stu_phone').val().trim(),
			"ph_intention" : $("#sel_yixiang").val(),
			"if_arrival" : $("#if_arrival").val(),
			"kai_age" : $("#kai_age").val(),
			"ting_age" : $("#ting_age").val()
		};
//		console.log(data);
		start_post_VIP_TMK(for_btn_VIP_TMK, data);
		click_close();
	});
	
	//市场签单成功_条件查询
	$("#sel_sub_advSuccess").click(function() {
		
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"qian_gu" : $('#qian_gu').val(),
			"if_signup":1
			
		};
//		console.log(data);
		start_post_sig(for_btn_sig,data);
		click_close();
	});
	
	//市场跟单_条件查询
	$("#sel_sub_sig").click(function() {
		
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"phone" : $('#stu_phone').val(),
			"stu_school" : $('#stu_school').val()
		};
//		console.log(data);
		start_post_sig(for_btn_sig,data);
		click_close();
	});
	
	
	//VIP_市场跟单_条件查询
	$("#sel_sub_VIP_ADV").click(function() {
		
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val().trim(),
			"phone" : $('#stu_phone').val()
		};
		console.log(data);
		start_post_VIP_sig(for_btn_VIP_sig,data);
		click_close();
	});
	
	//异业兑换名单 条件查询
	$("#sel_sub_kua_phone").click(function() {
		var data = {
			"page" : 1,
			"stu_name" : $("#stu_name").val(),
			"stu_class" : $('#stu_class option:selected').text() == "请选择"? "":$('#stu_class option:selected').text(),
			"yao_gu" : $('#yao_gu option:selected').text() == "请选择"? "":$('#yao_gu option:selected').text(),
			"kai_time" : $("#kai_time").val().trim(),
			"ting_time" : $("#ting_time").val(),
			"begin_num" : $("#begin_num").val().trim(),
			"end_num" : $("#end_num").val()
		};

//		console.log(data);
		start_post_kua_phone(for_btn_kua_Phone,data);
		click_close();
	});
		
	/** ==============================================关闭============================================== */

	//关闭
	$("#sel_res").click(click_close);

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