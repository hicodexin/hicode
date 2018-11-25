
// 格式化时间格式
function timestampToTime(timestamp) {
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
	m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
	s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
	return Y + M + D;
}
//格式化时间格式
function timestampToTime_hms(timestamp) {
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
	m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
	s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
	return Y + M + D + h + m + s;
}

/*通过ajax后台访问所得结果遍历*/
function for_sel(id, f, optionName) {
	$("#" + id).children("option").remove();
	for (var i = 0; i < f.length; i++) {
		if (f[i].name == optionName) {
			var str = "<option selected='selected' value=" + f[i].id + ">" + f[i].name + "</option>";
		} else {
			var str = "<option value=" + f[i].id + ">" + f[i].name + "</option>";
		}

		$("#" + id).append(str);
	}

}
/*前台写死数据结果遍历，f:所遍历数组;optionName:选中列表*/
function for_sel02(id, f, optionName) {
	$("#" + id).children("option").remove();
	for (var k = 0; k < f.length; k++) {
		if ($(f[k]).html() == optionName) {
			var str = "<option selected='selected' value='"+(k+1)+"' >" + $(f[k]).html() + "</option>";
		} else {
			var str = "<option value='"+(k+1)+"'>" + $(f[k]).html() + "</option>";
		}

		$("#" + id).append(str);
	}

}

/** ==============================================修改按钮============================================== */

/* 试听课修改按钮 */
function for_btn_aud() {
	var bts = $("button");
	var revise = new Array();
	if (bts.length > 0) {
		for (var i = 0, j = 0; i < bts.length; i++) {
			if ($(bts[i]).html() == "修改") {
				revise[j] = bts[i];
				j++;
			}
		}
	}
	if (revise.length > 0) {
		for (var k = 0; k < revise.length; k++) {
			(function() {
				var t = k;
				$(revise[t]).click(function() {
					$("#hidd_mask").hide().show(300);
					$("#dv_update").hide().show(300);
					$("#dv_title").html("修改试听学员信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					$("#time_creatDate").val($("[name='creatDate']:eq(" + t + ")").html());
					$("#userName").val($("[name='userName']:eq(" + t + ")").html());

					var optionName = $("[name='update_selclass']:eq(" + t + ")").html();

					var sel_class = $("#update_selclass option");

					for_sel02("update_selclass", sel_class, optionName);

					$.post("/hicode/teacher/showTeacher.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='update_selteas']:eq(" + t + ")").html();
							for_sel("update_selteas", c, name);
						}
					}, "json");

					$.post("/hicode/adviser/showAdviser.spc", function(f) {
						if (f.length > 0) {
							var name = $("[name='update_seladvs']:eq(" + t + ")").html();
							for_sel("update_seladvs", f, name);
						}
					}, "json");

					$("#up_sub").html("修改");
				});

			})();

		}
	}

}

/* 修改按钮 */
function for_btn_tea() {
	var bts = $("button");
	var revise = new Array();
	if (bts.length > 0) {
		for (var i = 0, j = 0; i < bts.length; i++) {
			if ($(bts[i]).html() == "修改") {
				revise[j] = bts[i];
				j++;
			}
		}
	}
	console.log("==========tea========");

	if (revise.length > 0) {
		for (var k = 0; k < revise.length; k++) {
			(function() {
				var t = k;
				$(revise[t]).click(function() {
					$("#hidd_mask").hide().show(300);
					$("#dv_update").hide().show(300);
					$("#dv_title").html("修改讲师信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					$("#userName").val($("[name='userName']:eq(" + t + ")").html());
					$("#time_creatDate").val($("[name='creatDate']:eq(" + t + ")").html());
					$("#time_endDate").val($("[name='endDate']:eq(" + t + ")").html());
					if ($("#time_endDate").val() == '' || $("#time_endDate").val() == null) {
						$("#time_endDate").removeAttr("disabled");
						$("#title_updatetime").removeAttr("disabled");
					} else {
						$("#time_endDate").attr("disabled", "disabled");
						$("#title_updatetime").attr("disabled", "disabled");
					}
					$("#title_updatetime").val($("[name='updatetime']:eq(" + t + ")").html());
					$("#up_sub").html("修改");
				});

			})();

		}
	}

}

/* 修改按钮 */
function for_btn_sub() {
	var bts = $("button");
	var revise = new Array();
	if (bts.length > 0) {
		for (var i = 0, j = 0; i < bts.length; i++) {
			if ($(bts[i]).html() == "修改") {
				revise[j] = bts[i];
				j++;
			}
		}
	}
	console.log("==========sub========");
	if (revise.length > 0) {
		for (var k = 0; k < revise.length; k++) {
			(function() {
				var t = k;
				$(revise[t]).click(function() {
					$("#hidd_mask").hide().show(300);
					$("#dv_update").hide().show(300);
					$("#dv_title").html("修改课程信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					$("#userName").val($("[name='userName']:eq(" + t + ")").html());
					$("#up_sub").html("修改");
				});

			})();

		}
	}

}

/* 试听课修改按钮 */
function for_btn_cus() {
	var bts = $("button");
	var revise = new Array();
	if (bts.length > 0) {
		for (var i = 0, j = 0; i < bts.length; i++) {
			if ($(bts[i]).html() == "修改") {
				revise[j] = bts[i];
				j++;
			}
		}
	}

	console.log("=============cus==================")
	console.log(revise)

	if (revise.length > 0) {
		for (var k = 0; k < revise.length; k++) {
			(function() {
				var t = k;
				$(revise[t]).click(function() {
					$("#hidd_mask").hide().show(300);
					$("#dv_update").hide().show(300);
					$("#dv_title").html("修改学员信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					
					$.post("/hicode/auditions/showAuditions.spc", function(c) {
						if (c.length > 0) {
							var stu_name = $("[name='userName']:eq(" + t + ")").html();
							for_sel("student_sel", c, stu_name);
						}
						window.setTimeout(function() {
							$('.chosen-select').chosen();
							$('.chosen-container')[0].style.width = "250px";
							$(".chosen-single span").html(stu_name);
						}, 500);
					}, "json");
					
					$.post("/hicode/subject/showSubject.spc", function(c) {
						if (c.length > 0) {
							var sbu_name = $("[name='sub_sel']:eq(" + t + ")").html();
							for_sel("sub_sel", c,sbu_name);
						}
						
					}, "json");
					
					var optionName = $("[name='period']:eq(" + t + ")").html();
					var sel_class = $("#period option");
					for_sel02("period", sel_class, optionName);
					

					$.post("/hicode/teacher/showTeacher.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='the_teacher']:eq(" + t + ")").html();
							for_sel("the_teacher", c, name);
						}
					}, "json");
					
					$("#first_time").val($("[name='first_time']:eq(" + t + ")").html());
					
					$("#phone").val($("[name='phone']:eq(" + t + ")").html());

					$.post("/hicode/adviser/showAdviser.spc", function(f) {
						if (f.length > 0) {
							var name = $("[name='adviser_sel']:eq(" + t + ")").html();
							for_sel("adviser_sel", f, name);
						}
					}, "json");

					$("#up_sub").html("修改");
				});

			})();

		}
	}

}

/** ==============================================添加按钮============================================== */

/* 试听课添加按钮 */
function add_aud() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
	$("#up_sub").html("添加");
	$("#time_creatDate").val("");
	$("#remarks").val("");

	$.post("/hicode/teacher/showTeacher.spc", function(c) {
		if (c.length > 0) {
			for_sel("update_selteas", c);
		}
	}, "json");

	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		if (f.length > 0) {
			for_sel("update_seladvs", f);
		}
	}, "json");

}

function add_adv() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
	$("#time_creatDate").val("");
	$("#time_endDate").val("");
	$("#title_updatetime").val("");

	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		if (f.length > 0) {
			for_sel("update_sel", f);
		}

	}, "json");

}

function add_tea() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	$("#dv_title").html("添加讲师信息");
	$("#up_sub").html("添加");
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
	$("#time_creatDate").val("");
	$("#time_endDate").val("");
	$("#title_updatetime").val("");
	$("#time_creatDate").removeAttr("disabled");
}

function add_sub() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	$("#dv_title").html("添加课程信息");
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
}

function add_cus() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#dv_title").html("添加签单信息");
	$("#up_sub").html("添加");
	$("#userName").val("");
	$("#first_time").val("");
	$("#remarks").val("");

	$.post("/hicode/subject/showSubject.spc", function(c) {
		if (c.length > 0) {
			for_sel("sub_sel", c);
		}
	}, "json");

	$.post("/hicode/auditions/showAuditions.spc", function(c) {
		if (c.length > 0) {
			for_sel("student_sel", c);
		}
		window.setTimeout(function() {
			$('.chosen-select').chosen();
			$('.chosen-container')[0].style.width = "250px";
		}, 1000);
	}, "json");

	$.post("/hicode/teacher/showTeacher.spc", function(c) {
		if (c.length > 0) {
			for_sel("the_teacher", c);
		}

	}, "json");

	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		if (f.length > 0) {
			for_sel("adviser_sel", f);
		}

	}, "json");

}

/** ==============================================提交按钮============================================== */

/* 试听课提交按钮*/
function up_sub_aud() {
	if ($("#time_creatDate").val().length < 10) {
		$("#time_creatDate").css("borderColor", "#f00");
		return;
	} else {
		$("#time_creatDate").css("borderColor", "#336699");
	}
	if ($("#userName").val().trim().length < 2) {
		$("#userName").css("borderColor", "#f00");
		return;
	} else {
		$("#userName").css("borderColor", "#336699");
	}

	var data = {
		"time_creatDate" : $("#time_creatDate").val(),
		"userName" : $("#userName").val().trim(),
		"t_sex" : $('input:radio[name="t_sex"]:checked').val(),
		"update_selclass" : $("#update_selclass").val(),
		"update_selteas" : $("#update_selteas").val(),
		"update_seladvs" : $("#update_seladvs").val(),
		"remarks" : $("#remarks").val()
	};
	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/auditions/do_insertAuditions.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_aud();
				for_btn_aud();
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	}

	if (content == "修改") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		$.post("/hicode/auditions/do_updateAuditions.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_aud();
				for_btn_aud();
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");


	}

}

function up_sub_adv() {
	if ($("#userName").val().trim().length < 2) {
		$("#userName").css("borderColor", "#f00");
		return;
	} else {
		$("#userName").css("borderColor", "#336699");
	}

	if ($("#time_creatDate").val().length < 10) {
		$("#time_creatDate").css("borderColor", "#f00");
		return;
	} else {
		$("#time_creatDate").css("borderColor", "#336699");
	}

	var data = {
		"userName" : $("#userName").val().trim(),
		"sex" : $('input:radio[name="t_sex"]:checked').val(),
		"title" : $("#update_sel").val(),
		"if_Onthejob" : $('input:radio[name="if_Onthejob"]:checked').val(),
		"time_creatDate" : $("#time_creatDate").val(),
		"time_endDate" : $("#time_endDate").val(),
		"title_updatetime" : $("#title_updatetime").val()
	};
	$.post("/hicode/adviser/do_insertAdviser.spc", data, function(e) {
		$("#hidd_mask").hide().hide(300);
		$("#dv_update").show().hide(300);
		if (e.list_advs == 'ok') {
			alert("添加成功");
			$("#tbl_body").children("tr").remove();
			start_post_adv();
		} else {
			alert("添加失败,请联系管理员。。。。");
		}

	}, "json");

}

function up_sub_tea() {
	if ($("#userName").val().trim().length < 2) {
		$("#userName").css("borderColor", "#f00");
		return;
	} else {
		$("#userName").css("borderColor", "#336699");
	}

	if ($("#time_creatDate").val().length < 10) {
		$("#time_creatDate").css("borderColor", "#f00");
		return;
	} else {
		$("#time_creatDate").css("borderColor", "#336699");
	}
	var data = {};

	data = {
		"userName" : $("#userName").val().trim(),
		"sex" : $('input:radio[name="t_sex"]:checked').val(),
		"title" : $("#update_sel").val(),
		"if_Onthejob" : $('input:radio[name="if_Onthejob"]:checked').val(),
		"time_creatDate" : $("#time_creatDate").val(),
		"time_endDate" : $("#time_endDate").val(),
		"title_updatetime" : $("#title_updatetime").val()
	};

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/teacher/do_insertTeacher.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_tea();
				for_btn_tea();
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
	}
	if (content == "修改") {
		if ($("#time_endDate").val().length > 3) {
			$("#time_endDate").css("borderColor", "#f00");
			if (!window.confirm("此人确定离职？？？？")) {
				return;
			}
		}

		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");

		$.post("/hicode/teacher/do_updateTeacher.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_tea();
				for_btn_tea();
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");


	}
}

function up_sub_sub() {
	if ($("#userName").val().trim().length < 2) {
		$("#userName").css("borderColor", "#f00");
		return;
	} else {
		$("#userName").css("borderColor", "#336699");
	}
	var data = {
			"userName" : $("#userName").val().trim(),
			"if_Onthejob" : $('input:radio[name="if_Onthejob"]:checked').val()
		};
	var content = $(this).html();
	if (content == "添加") {
		
		$.post("/hicode/subject/do_insertSubject.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_sub();
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
	} else if (content == "修改") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		console.log(data);
		$.post("/hicode/subject/do_updateSubject.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {

				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_sub();
				for_btn_sub();
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}


}

function up_sub_cus() {
	if ($("#phone").val().trim().length != 11) {
		$("#phone").css("borderColor", "#f00");
		return;
	} else {
		$("#phone").css("borderColor", "#336699");
	}
	if ($("#first_time").val().length < 10) {
		$("#first_time").css("borderColor", "#f00");
		return;
	} else {
		$("#first_time").css("borderColor", "#336699");
	}

	var ss = $(".chosen-single span").html();
	var yy = $("#student_sel option");
	for (var i = 0; i < yy.length; i++) {
		if ($(yy[i]).html() == ss) {
			console.log($(yy[i]).val());
			ss = $(yy[i]).val();
			break;
		}
	}

	var data = {
		"userName" : ss,
		"subject" : $('#sub_sel').val(),
		"period" : $('#period').val(),
		"if_renewal" : $('input:radio[name="if_renewal"]:checked').val(),
		"the_teacher" : $("#the_teacher").val(),
		"first_time" : $("#first_time").val(),
		"phone" : $("#phone").val().trim(),
		"adviser_sel" : $('#adviser_sel').val(),
		"remarks" : $("#remarks").val()
	};
	console.log(data);
	
	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/customer/do_insertCustomer.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_cus(for_btn_cus);
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
		
	}
	else if(content == "修改"){
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		$.post("/hicode/customer/do_updateCustomer.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_cus(for_btn_cus);
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
	}

}

/** ==============================================初始化数据============================================== */

/* 初始化数据 */
function start_post_aud(backFunction) {
	$.post("/hicode/auditions/showAuditionsByInfo.spc", {
		"page" : 1
	}, function(a) {
		console.log(a.list_advs);
		if (a) {
			creat_tb_aud(a.list_advs, "#tbl_body");
			/* 添加页码 */
			if (a.all_num) {
				$("#dv_but").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#336699",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_aud(this);
					});

					$("#dv_but").append(btn);
				}
				$("#bt_end").attr("mypage", a.all_num);
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "800px");
		}
	}, "json");

}

/* 初始化数据 */
function start_post_adv(backFunction) {
	$.post("/hicode/adviser/showAdviserByInfo.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tb(a.list_advs, "#tbl_body");
			/* 添加页码 */
			if (a.all_num) {
				$("#dv_but").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#336699",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_adv(this);
					});

					$("#dv_but").append(btn);
				}
				$("#bt_end").attr("mypage", a.all_num);
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "800px");
		}
	}, "json");

}

function start_post_tea(backFunction) {
	$.post("/hicode/teacher/showTeacherByInfo.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tbTea(a.list_tea, "#tbl_body");
			/* 添加页码 */
			if (a.all_num) {
				$("#dv_but").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#336699",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_tea(this);
					});

					$("#dv_but").append(btn);
				}
				$("#bt_end").attr("mypage", a.all_num);
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "800px");
		}

	}, "json");
}

/* 初始化数据 */
function start_post_sub(backFunction) {
	$.post("/hicode/subject/showSubjectByInfo.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tb_sub(a.list_advs, "#tbl_body");
			/* 添加页码 */
			if (a.all_num) {
				$("#dv_but").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#336699",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_sub(this);
					});

					$("#dv_but").append(btn);
				}
				$("#bt_end").attr("mypage", a.all_num);
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "800px");
		}
	}, "json");

}

/* 初始化数据 */
function start_post_cus(backFunction) {
	$.post("/hicode/customer/showCustomerByInfo.spc", {
		"page" : 1
	}, function(a) {
		console.log(a.list_advs);
		if (a) {
			creat_tb_cus(a.list_advs, "#tbl_body");
			/* 添加页码 */
			if (a.all_num) {
				$("#dv_but").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#336699",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_cus(this);
					});

					$("#dv_but").append(btn);
				}
				$("#bt_end").attr("mypage", a.all_num);
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "800px");
		}

	}, "json");

}

/** ==============================================待便利的值============================================== */

/* back_all: 待便利的值 */
function creat_tb(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td>" + back_all[i].name + "</td>";

		if (back_all[i].sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.jpg'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.jpg'/></td>";
		}
		str += "<td>" + back_all[i].title + "</td>";

		if (back_all[i].onthejob == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
		}

		var creatDate = timestampToTime(back_all[i].opentime.time);
		str += "<td>" + creatDate + "</td>";

		if (back_all[i].endtime) {
			var endDate = timestampToTime(back_all[i].endtime.time);
			str += "<td>" + endDate + "</td>";
			$(tr).css("color", "#b0b0b0");
		} else {
			str += "<td>   </td>";
		}

		if (back_all[i].title_updatetime) {
			var updatetime = timestampToTime(back_all[i].title_updatetime.time);
			str += "<td>" + updatetime + "</td>";
		} else {
			str += "<td>   </td>";
		}

		var btid = back_all[i].id;
		str += "<td><button id = '" + btid + "'name='" + (i + 1) + "'  onclick=alert('权限不足，请联系管理员')>修改</button></td>";
		str += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);

	}
}

/* back_all: 待便利的值 */
function creat_tb_aud(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		var st_time = timestampToTime_hms(back_all[i].time.time);
		str += "<td name='creatDate'>" + st_time + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";

		if (back_all[i].sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.jpg'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.jpg'/></td>";
		}

		str += "<td name='update_selclass'>" + back_all[i].classinfo + "</td>";
		str += "<td name='update_selteas'>" + back_all[i].tea_name + "</td>";
		str += "<td name='update_seladvs'>" + back_all[i].adv_name + "</td>";

		if (back_all[i].remarks) {
			str += "<td>" + back_all[i].beizhu + "</td>";
		} else {
			str += "<td>   </td>";
		}

		var btid = back_all[i].id;
		str += "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		str += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);

	}
}


/* back_all: 待便利的值 */
function creat_tb_cus(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='sub_sel'>" + back_all[i].kemu + "</td>";

		if (back_all[i].period == 1) {
			str += "<td name='period'>一个季度</td>";
		} else if (back_all[i].period == 2) {
			str += "<td name='period'>两个季度</td>";
		} else if (back_all[i].period == 3) {
			str += "<td name='period'>一个年度</td>";
		} else if (back_all[i].period == 4) {
			str += "<td name='period'>两个年度</td>";
		}

		str += "<td name='the_teacher'>" + back_all[i].tea_name + "</td>";

		var firsttime = timestampToTime_hms(back_all[i].firsttime.time);

		str += "<td name='first_time'>" +firsttime+ "</td>";

		str += "<td name='phone'>" + back_all[i].tel + "</td>";

		str += "<td name='adviser_sel'>" + back_all[i].qianyue + "</td>";

		if (back_all[i].over == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td>  </td>";
		}

		if (back_all[i].xufei == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td>  </td>";
		}

		if (back_all[i].tuifei == 1) {
			str += "<td> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
			$(tr).css("color", "#b0b0b0");
		} else {
			str += "<td>  </td>";
		}

		if (back_all[i].remarks) {
			str += "<td>" + back_all[i].beizhu + "</td>";
		} else {
			str += "<td>   </td>";
		}

		var btid = back_all[i].id;
		str += "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		str += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);

	}

}

/* back_all: 待便利的值 */
function creat_tb_sub(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name = 'userName'>" + back_all[i].name + "</td>";

		if (back_all[i].xiaxian == 0) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
		}

		var btid = back_all[i].id;
		str += "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		str += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);
	}
}


/* back_all: 待便利的值 */
function creat_tbTea(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name = 'userName'>" + back_all[i].t_name + "</td>";
		if (back_all[i].t_sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.jpg'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.jpg'/></td>";
		}
		if (back_all[i].if_Onthejob == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
		}

		var creatDate = timestampToTime(back_all[i].time_creatDate.time);

		str += "<td name='creatDate'>" + creatDate + "</td>";
		if (back_all[i].time_endDate) {
			var endDate = timestampToTime(back_all[i].time_endDate.time);
			str += "<td name='endDate'>" + endDate + "</td>";
			$(tr).css("color", "#b0b0b0");
		} else {
			str += "<td name='endDate'></td>";
		}
		str += "<td>" + back_all[i].title + "</td>";
		if (back_all[i].title_updatetime) {
			var updatetime = timestampToTime(back_all[i].title_updatetime.time);
			str += "<td name='updatetime'>" + updatetime + "</td>";
		} else {
			str += "<td name='updatetime'></td>";
		}
		var btid = back_all[i].t_id;
		str += "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		str += "<td><input type='checkbox' value='" + back_all[i].t_id + "' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);
	}

}
/** ==============================================切换页面============================================== */

/*切换页面*/
function change_page_aud(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/auditions/showAuditionsByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tb_aud(js_arry.list_advs, "#tbl_body");
		for_btn_aud();
	});
}

/* 切换页码 */
function change_page_adv(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/adviser/showAdviserByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tb(js_arry.list_advs, "#tbl_body");
	});
}

/* 切换页码 */
function change_page_tea(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/teacher/showTeacherByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tbTea(js_arry.list_tea, "#tbl_body");
		for_btn_tea();
	});
}

/* 切换页码 */
function change_page_sub(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/subject/showSubjectByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tb_sub(js_arry.list_advs, "#tbl_body");
		for_btn_sub();
	});
}

/*切换页面*/
function change_page_cus(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/customer/showCustomerByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_cus(js_arry.list_advs, "#tbl_body");
	});
}