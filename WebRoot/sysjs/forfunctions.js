
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

/**
 * 获取登陆用户名
 */
function getUserName(id) {
	$.post("/hicode/UserInfo/getUserName.jsp", function(a) {
		$("#" + id).children("#user_out").remove();
		if (a) {
			if (a.ok == "okk") {
				$("#find_user").html("请登陆");
				$("#find_user").click(function() {
					window.location = "/hicode/welcome.html";
				});
			}

			if (a.name) {
				$("#find_user").html("欢迎 " + a.type + " : " + a.name);
				$("#find_user").click(function() {});
				if(a.type == '超级管理员'){
					var str = "<button id='user_out' class='for_button_vip'>退出</button>";
				}else{
					var str = "<button id='user_out' class='for_button'>退出</button>";
				}
				
				$("#" + id).append(str);
				$("#user_out").click(userOut);
			}
		}

	}, "json");

}
/**
 * 退出登录
 */
function userOut() {
	$.post("/hicode/UserInfo/logOut.spc", function(a) {
		if (a.ok == "okk") {
			alert("谢谢使用，再见。。。。。");
			window.location = "/hicode/welcome.html";
		}
	}, "json");
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
/*前台写死数据结果遍历，f:所遍历数组;optionName:选中列表     从1开始*/
function for_sel02(id, f, optionName) {
	$("#" + id).children("option").remove();
	for (var k = 0; k < f.length; k++) {
		if ($(f[k]).html() == optionName) {
			var str = "<option selected='selected' value='" + (k + 1) + "' >" + $(f[k]).html() + "</option>";
		} else {
			var str = "<option value='" + (k + 1) + "'>" + $(f[k]).html() + "</option>";
		}

		$("#" + id).append(str);
	}

}

/*前台写死数据结果遍历，f:所遍历数组;optionName:选中列表    从0开始*/
function for_sel03(id, f, optionName) {
	$("#" + id).children("option").remove();
	for (var k = 0; k < f.length; k++) {
		if ($(f[k]).html() == optionName) {
			var str = "<option selected='selected' value='" + k  + "' >" + $(f[k]).html() + "</option>";
		} else {
			var str = "<option value='" + k + "'>" + $(f[k]).html() + "</option>";
		}

		$("#" + id).append(str);
	}

}

/*通过ajax后台访问所得结果遍历,仅针对于课程顾问，筛选掉非顾问：自主上门。。。*/
function for_adviser_sel(id, f, optionName) {
	$("#" + id).children("option").remove();
	for (var i = 0; i < f.length; i++) {
		if (f[i].name == "自然上门" || f[i].name == "口碑介绍" || f[i].name == "活动上门"||f[i].name == optionName) {
			/*var str = "<option selected='selected' value=" + f[i].id + ">" + f[i].name + "</option>";*/
		} else {
			var str = "<option value=" + f[i].id + ">" + f[i].name + "</option>";
		}

		$("#" + id).append(str);
	}
}

/** ==============================================锁定按钮============================================== */
function for_btnSD_usr() {
	var bts = $("button");
	var revise = new Array();
	if (bts.length > 0) {
		for (var i = 0, j = 0; i < bts.length; i++) {
			if ($(bts[i]).html() == "锁定") {
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
					//确认锁定？
					if (!window.confirm("是否确定要锁定该用户的账号？？？？")) {
						return;
					}
					var data = {"id":$("[name='userName']:eq(" + t + ")").attr("usid")}
					//传递userid
					$.post("/hicode/UserInfo/closeUserInfoState.spc",data,function(a){
						if(a){
							alert(a.ok);
							//刷新当前页
							start_post_usr(for_btnSD_usr,for_btnXX_usr);
						}
					},"json");
				});

			})();

		}
	}

}

/** ==============================================下线按钮============================================== */
function for_btnXX_usr() {
	var bts = $("button");
	var revise = new Array();
	if (bts.length > 0) {
		for (var i = 0, j = 0; i < bts.length; i++) {
			if ($(bts[i]).html() == "下线") {
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
					//确认锁定？
					if (!window.confirm("是否确定要令该用户下线？？？？")) {
						return;
					}
					var data = {"userName":$("[name='userName']:eq(" + t + ")").html()}
					//传递userid
					$.post("/hicode/UserInfo/logOut.spc",data,function(a){
						if(a){
							alert(a.ok);
							start_post_usr(for_btnSD_usr,for_btnXX_usr);
						}
					},"json");
				});

			})();

		}
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
					$("#phone").val($("[name='phone']:eq(" + t + ")").html());

					var optionName = $("[name='update_selclass']:eq(" + t + ")").html();

					var sel_class = $("#update_selclass option");

					for_sel02("update_selclass", sel_class, optionName);
					
					$.post("/hicode/school/showSchool.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='update_school']:eq(" + t + ")").html();
							for_sel("update_school", c, name);
						}
					}, "json");

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
							var name2 = $("[name='update_seladvs2']:eq(" + t + ")").html();
							for_sel("update_seladvs2", f, name2);
						}
					}, "json");
					
					$("#remarks").val($("[name='remarks']:eq(" + t + ")").attr("myfont"));
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/* 顾问 >>>修改按钮 */
function for_btn_adv() {
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
				//对于自然上门,口碑介绍,活动上门   三项内容不允许修改
				if ($("[name='userName']:eq(" + t + ")").html() == "自然上门") {
					$(revise[t]).click(function() {
						alert('权限不足,请联系管理员。。。。');
					});
					return;
				}
				if ($("[name='userName']:eq(" + t + ")").html() == "口碑介绍") {
					$(revise[t]).click(function() {
						alert('权限不足,请联系管理员。。。。');
					});
					return;
				}
				if ($("[name='userName']:eq(" + t + ")").html() == "活动上门") {
					$(revise[t]).click(function() {
						alert('权限不足,请联系管理员。。。。');
					});
					return;
				}

				$(revise[t]).click(function() {
					$("#hidd_mask").hide().show(300);
					$("#dv_update").hide().show(300);
					$("#dv_title").html("修改市场部门信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					$("#userName").val($("[name='userName']:eq(" + t + ")").html());

					var optionName = $("[name='update_sel']:eq(" + t + ")").html();
					var sel_class = $("#update_sel option");
					for_sel02("update_sel", sel_class, optionName);

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
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/* 讲师>>>> 修改按钮 */
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
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/* 科目>>>修改按钮 */
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
					$("#up_sub").html("提交");
				});
			})();
		}
	}

}

/* 报名学员>>>修改按钮 */
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
							for_sel("sub_sel", c, sbu_name);
						}

					}, "json");

					$("#period").val($("[name='period']:eq(" + t + ")").html());
					$("#money").val($("[name='money']:eq(" + t + ")").html());
					$("#giveClass").val($("[name='giveClass']:eq(" + t + ")").html());
					
					$.post("/hicode/teacher/showTeacher.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='the_teacher']:eq(" + t + ")").html();
							for_sel("the_teacher", c, name);
						}
					}, "json");

					$("#first_time").val($("[name='first_time']:eq(" + t + ")").html());

					$.post("/hicode/adviser/showAdviser.spc", function(f) {
						if (f.length > 0) {
							var name = $("[name='adviser_sel']:eq(" + t + ")").html();
							for_sel("adviser_sel", f, name);
						}
					}, "json");
					
					$("#remarks").val($("[name='remarks']:eq(" + t + ")").attr("myfont"));
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}
/* 报名学员_已完成当前课程的学员>>>修改按钮 */
function for_btn_cus_done() {

}

/* 试听课修改按钮 */
function for_btn_dep() {
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
					$("#dv_title").html("修改学员订金信息");
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
					
					$("#pay_time").val($("[name='pay_time']:eq(" + t + ")").html());
					
					$("#money").val($("[name='money']:eq(" + t + ")").html());
					$("#phone").val($("[name='phone']:eq(" + t + ")").html());
					$.post("/hicode/adviser/showAdviser.spc", function(f) {
						if (f.length > 0) {
							var name = $("[name='update_seladvs']:eq(" + t + ")").html();
							for_sel("update_seladvs", f, name);
							var name2 = $("[name='update_seladvs2']:eq(" + t + ")").html();
							for_sel("update_seladvs2", f, name2);
						}
					}, "json");
					$("#refundmoney").val($("[name='refundmoney']:eq(" + t + ")").html());
					$("#refund_time").val($("[name='refund_time']:eq(" + t + ")").html());
					$("#remarks").val($("[name='remarks']:eq(" + t + ")").attr("myfont"));
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/* 寒假班>>>>修改按钮 */
function for_btn_wv() {
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
							for_sel("sub_sel", c, sbu_name);
						}

					}, "json");

					$.post("/hicode/teacher/showTeacher.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='the_teacher']:eq(" + t + ")").html();
							for_sel("the_teacher", c, name);
						}
					}, "json");
					
					$("#start_time").val($("[name='start_time']:eq(" + t + ")").html());
					
					$("#clock_num").val($("[name='clock_num']:eq(" + t + ")").html());
					
					$("#giveClass").val($("[name='giveClass']:eq(" + t + ")").html());
					
					$("#remarks").val($("[name='remarks']:eq(" + t + ")").attr("myfont"));
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/* 暑假班>>>>修改按钮 */
function for_btn_sv() {
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
							for_sel("sub_sel", c, sbu_name);
						}

					}, "json");

					$.post("/hicode/teacher/showTeacher.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='the_teacher']:eq(" + t + ")").html();
							for_sel("the_teacher", c, name);
						}
					}, "json");
					
					$("#start_time").val($("[name='start_time']:eq(" + t + ")").html());
					
					$("#clock_num").val($("[name='clock_num']:eq(" + t + ")").html());
					
					$("#giveClass").val($("[name='giveClass']:eq(" + t + ")").html());
					
					$("#remarks").val($("[name='remarks']:eq(" + t + ")").attr("myfont"));
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/* TMK>>>修改按钮 */
function for_btn_TMK() {
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
					$("#dv_title").html("修改学员信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					
					$("#userName").val($("[name='userName']:eq(" + t + ")").html());
					
					$("#userAge").val($("[name='userAge']:eq(" + t + ")").html());
					
					$("#phone").val($("[name='phone']:eq(" + t + ")").html());

					var optionName = $("[name='update_selyixiang']:eq(" + t + ")").html();
					
					var sel_class = $("#update_selyixiang option");

					for_sel03("update_selyixiang", sel_class, optionName);
					
					$.post("/hicode/school/showSchool.spc", function(c) {
						if (c.length > 0) {
							var name = $("[name='update_school']:eq(" + t + ")").html();
							for_sel("update_school", c, name);
						}
					}, "json");

					$("#gaikuang").val($("[name='gaikuang']:eq(" + t + ")").attr("myfont"));
					
					$("#remarks").val($("[name='remarks']:eq(" + t + ")").attr("myfont"));
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}



/* 跟踪详情>>>修改按钮 */
function for_btn_sig() {
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
					$("#dv_title").html("修改学员跟踪信息");
					$("#tea_list").val($(this).attr("name"));
					$("#tea_list").attr("name", $(this).attr("id"));
					
					$.post("/hicode/auditions/showAuditions.spc", function(c) {
						if (c.length > 0) {
							var stu_name = $("[name='userName']:eq(" + t + ")").html();
							for_sel("student_sel", c, stu_name);
							$("#student_name").val(stu_name);
						}
						window.setTimeout(function() {
							$('.chosen-select').chosen();
							$('.chosen-container')[0].style.width = "250px";
							$(".chosen-single span").html(stu_name);
						}, 500);
					}, "json");
					
					$("#stname_li01").css("display","none");
					$("#stname_li02").css("display","block");
					
					
					var optionName01 = $("[name='fenlei']:eq(" + t + ")").html();
					var sel_leibie = $("#leibie_sel option");
					for_sel02("leibie_sel", sel_leibie, optionName01);
					
					var optionName02 = $("[name='if_signup']:eq(" + t + ")").attr("value");
					var sel_qiandan = $("#qiandan_sel option");
					for_sel03("qiandan_sel", sel_qiandan, optionName02);
					
					$.post("/hicode/adviser/showAdviser.spc", function(f) {
						if (f.length > 0) {
							var name = $("[name='firstPeople']:eq(" + t + ")").html();
							for_sel("adviser_sel", f,name);
							$("#adviser_sel").attr("disabled","disabled");
						}
					}, "json");
					
					$("#dang_tian").val($("[name='firstRemark']:eq(" + t + ")").attr("manzi01"));
					
					$("#first_time").val($("[name='firstRemark']:eq(" + t + ")").attr("myfont01"));
					$("#second_time").val($("[name='firstRemark']:eq(" + t + ")").attr("myfont02"));
					$("#third_time").val($("[name='firstRemark']:eq(" + t + ")").attr("myfont03"));
					
					$("#up_sub").html("提交");
				});

			})();

		}
	}

}

/** ==============================================添加按钮============================================== */

/* 试听课>>>添加按钮 */
function add_aud() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
	$("#up_sub").html("添加");
	$("#phone").val("");
	$("#time_creatDate").val("");
	$("#remarks").val("");
	
	$.post("/hicode/school/showSchool.spc", function(c) {
		if (c.length > 0) {
			for_sel("update_school", c);
		}
	}, "json");

	$.post("/hicode/teacher/showTeacher.spc", function(c) {
		if (c.length > 0) {
			for_sel("update_selteas", c);
		}
	}, "json");


	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		if (f.length > 0) {
			for_sel("update_seladvs", f);
			for_sel("update_seladvs2", f);
		}
	}, "json");

}

/**
 * 顾问>>>添加按钮
 */
function add_adv() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#dv_title").html("添加市场部门信息");
	$("#up_sub").html("添加");
	$("#userName").val("");
	$("#time_creatDate").val("");
	$("#time_endDate").val("");
	$("#title_updatetime").val("");
}

/**
 * 讲师>>>添加按钮
 */
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

/**
 * 科目>>>添加按钮
 */
function add_sub() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	$("#dv_title").html("添加课程信息");
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
}

/**
 * 报名学员>>>添加按钮
 */
function add_cus() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#dv_title").html("添加签单信息");
	$("#up_sub").html("添加");
	$("#userName").val("");
	$("#period").val("");
	$("#money").val("");
	$("#giveClass").val("");
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

/* 订金页面>>>添加按钮 */
function add_dep() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
	$("#up_sub").html("添加");
	$("#pay_time").val("");
	$("#money").val("");
	$("#phone").val("");
	$("#refundmoney").val("");
	$("#refund_time").val("");
	$("#remarks").val("");
	
	$.post("/hicode/auditions/showAuditions.spc", function(c) {
		if (c.length > 0) {
			for_sel("student_sel", c);
		}
		window.setTimeout(function() {
			$('.chosen-select').chosen();
			$('.chosen-container')[0].style.width = "250px";
		}, 1000);
	}, "json");
	
	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		if (f.length > 0) {
			for_sel("update_seladvs", f);
			for_sel("update_seladvs2", f);
		}
	}, "json");

}

/**
 * 寒假班学员>>>添加按钮
 */
function add_wv() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#dv_title").html("添加寒假班信息");
	$("#up_sub").html("添加");
	$("#userName").val("");
	
	$.post("/hicode/auditions/showAuditions.spc", function(c) {
		if (c.length > 0) {
			for_sel("student_sel", c);
		}
		window.setTimeout(function() {
			$('.chosen-select').chosen();
			$('.chosen-container')[0].style.width = "250px";
		}, 1000);
	}, "json");
	
	$.post("/hicode/subject/showSubject.spc", function(c) {
		if (c.length > 0) {
			for_sel("sub_sel", c);
		}
	}, "json");

	$.post("/hicode/teacher/showTeacher.spc", function(c) {
		if (c.length > 0) {
			for_sel("the_teacher", c);
		}
	}, "json");
	
	$("#start_time").val("");
	$("#clock_num").val("");
	$("#giveClass").val("");
	$("#remarks").val("");
}

/**
 * 暑假班学员>>>添加按钮
 */
function add_sv() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#dv_title").html("添加暑假班信息");
	$("#up_sub").html("添加");
	$("#userName").val("");
	
	$.post("/hicode/auditions/showAuditions.spc", function(c) {
		if (c.length > 0) {
			for_sel("student_sel", c);
		}
		window.setTimeout(function() {
			$('.chosen-select').chosen();
			$('.chosen-container')[0].style.width = "250px";
		}, 1000);
	}, "json");
	
	$.post("/hicode/subject/showSubject.spc", function(c) {
		if (c.length > 0) {
			for_sel("sub_sel", c);
		}
	}, "json");

	$.post("/hicode/teacher/showTeacher.spc", function(c) {
		if (c.length > 0) {
			for_sel("the_teacher", c);
		}
	}, "json");
	
	$("#start_time").val("");
	$("#clock_num").val("");
	$("#giveClass").val("");
	$("#remarks").val("");
}

/* TMK>>>添加按钮 */
function add_TMK() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#userName").val("");
	$("#up_sub").html("添加");
	$("#userAge").val("");
	$("#phone").val("");
	$("#gaikuang").val("");
	$("#remarks").val("");
	
	$.post("/hicode/school/showSchool.spc", function(c) {
		if (c.length > 0) {
			for_sel("update_school", c);
		}
	}, "json");

}

/**
 * 跟踪细则>>>添加按钮
 */
function add_sig() {
	$("#hidd_mask").hide().show(300);
	$("#dv_update").hide().show(300);
	var len = $("#tea_tbl tbody tr").length;
	$("#tea_list").val(len + 1);
	$("#dv_title").html("添加跟踪信息");
	$("#up_sub").html("添加");
	$("#userName").val("");
	$("#dang_tian").val("");
	$("#first_time").val("");
	$("#second_time").val("");
	$("#third_time").val("");

	var sel_leibie = $("#leibie_sel option");
	for_sel02("leibie_sel", sel_leibie, "两周内可签单");
	var sel_qiandan = $("#qiandan_sel option");
	for_sel03("qiandan_sel", sel_qiandan, "未报名");

	$("#stname_li02").css("display","none");
	$("#stname_li01").css("display","block");
	$.post("/hicode/auditions/showAuditions.spc", function(c) {
		if (c.length > 0) {
			for_sel("student_sel", c);
		}
		
		window.setTimeout(function() {
			$('.chosen-select').chosen();
			$('.chosen-container')[0].style.width = "250px";
		}, 1000);
	}, "json");
	
	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		if (f.length > 0) {
			for_sel("adviser_sel", f);
			$("#adviser_sel").removeAttr("disabled");
		}
	}, "json");

}

/** ==============================================提交按钮============================================== */

/* 试听课提交按钮*/
function up_sub_aud() {
	if ($("#phone").val().trim().length != 11) {
		$("#phone").css("borderColor", "#f00");
		return;
	} else {
		$("#phone").css("borderColor", "#336699");
	}
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
		"phone": $("#phone").val().trim(),
		"update_selclass" : $("#update_selclass").val(),
		"update_school":$("#update_school").val(),
		"update_selteas" : $("#update_selteas").val(),
		"update_seladvs" : $("#update_seladvs").val(),
		"update_seladvs2" : $("#update_seladvs2").val(),
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
				var pagedata = {"page":1};
				start_post_aud(for_btn_aud,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			}else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
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
				var pagedata = {"page":1};
				start_post_aud(for_btn_aud,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");


	}

}

/*
 * 顾问》》》提交
 */
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

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/adviser/do_insertAdviser.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_adv(for_btn_adv);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
	} else if (content == "提交") {
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
		$.post("/hicode/adviser/do_updateAdviser.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_adv(for_btn_adv);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}

}

/*讲师》》》提交*/
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
				start_post_tea(for_btn_tea);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
	} else if (content == "提交") {
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
				start_post_tea(for_btn_tea);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");


	}
}

/*课程》》》提交按钮*/
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
				start_post_sub(for_btn_sub);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");
	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		$.post("/hicode/subject/do_updateSubject.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {

				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_sub(for_btn_sub);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}


}

/*报名学员》》》提交按钮*/
function up_sub_cus() {
	if ($("#money").val().trim().length < 2) {
		$("#money").css("borderColor", "#f00");
		return;
	} else {
		$("#money").css("borderColor", "#336699");
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
			ss = $(yy[i]).val();
			break;
		}
	}

	var data = {
		"userName" : ss,
		"subject" : $('#sub_sel').val(),
		"period" : $('#period').val(),
		"money" : $("#money").val().trim(),
		"giveClass" : $("#giveClass").val().trim(),
		"if_renewal" : $('input:radio[name="if_renewal"]:checked').val(),
		"the_teacher" : $("#the_teacher").val(),
		"first_time" : $("#first_time").val(),
		"adviser_sel" : $('#adviser_sel').val(),
		"remarks" : $("#remarks").val()
	};

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/customer/do_insertCustomer.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_cus(for_btn_cus);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		data.if_done = $('input:radio[name="if_done"]:checked').val();
		data.if_refund = $('input:radio[name="if_refund"]:checked').val();
		
		$.post("/hicode/customer/do_updateCustomer.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				var pagedata = {"page":1};
				start_post_cus(for_btn_cus,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}

}

/*押金>>>提交按钮*/
function up_sub_dep() {
	
	if ($("#pay_time").val().length < 10) {
		$("#pay_time").css("borderColor", "#f00");
		return;
	} else {
		$("#pay_time").css("borderColor", "#336699");
	}
	
	if($("#money").val().trim().length<1){
		$("#jiao_money").css("borderColor", "#f00");
		return;
	}else {
		$("#jiao_money").css("borderColor", "#336699");
	}
	
	if (isNaN($("#money").val())) { 
		$("#jiao_money").css("borderColor", "#f00");
		return;
　　	}else {
		$("#jiao_money").css("borderColor", "#336699");
	}
	
	if ($("#phone").val().trim().length != 11) {
		$("#phone").css("borderColor", "#f00");
		return;
	} else {
		$("#phone").css("borderColor", "#336699");
	}
	
	if($("#refundmoney").val().length>0){
		if (isNaN($("#refundmoney").val())) { 
			$("#tui_money").css("borderColor", "#f00");
			return;
	　　	}else {
			$("#tui_money").css("borderColor", "#336699");
		}
		if ($("#refund_time").val().length < 10) {
			$("#refund_time").css("borderColor", "#f00");
			return;
		} else {
			$("#refund_time").css("borderColor", "#336699");
		}
	}
	
	var ss = $(".chosen-single span").html();
	var yy = $("#student_sel option");
	for (var i = 0; i < yy.length; i++) {
		if ($(yy[i]).html() == ss) {
			ss = $(yy[i]).val();
			break;
		}
	}

	var data = {
		"userName" : ss,
		"pay_time" : $('#pay_time').val(),
		"money" : $('#money').val().trim(),
		"phone" : $("#phone").val().trim(),
		"adv_id":$('#update_seladvs').val(),
		"adv_id2":$('#update_seladvs2').val(),
		"ifsignup":$('input:radio[name="ifsignup"]:checked').val(),
		"refundmoney":$('#refundmoney').val(),
		"refund_time" : $('#refund_time').val(),
		"remarks" : $("#remarks").val()
	};

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/deposit/do_insertDeposit.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_dep(for_btn_dep);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		$.post("/hicode/deposit/do_updateDeposit.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_dep(for_btn_dep);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}

}

/**
 * 寒假班>>>提交按钮
 */
function up_sub_wv() {
	if ($("#start_time").val().length < 10) {
		$("#start_time").css("borderColor", "#f00");
		return;
	} else {
		$("#start_time").css("borderColor", "#336699");
	}
	
	if ($("#clock_num").val().trim().length < 1) {
		$("#clock_num").css("borderColor", "#f00");
		return;
	} else {
		$("#clock_num").css("borderColor", "#336699");
	}
	
	if ($("#giveClass").val().trim().length < 1) {
		$("#giveClass").css("borderColor", "#f00");
		return;
	} else {
		$("#giveClass").css("borderColor", "#336699");
	}

	var ss = $(".chosen-single span").html();
	var yy = $("#student_sel option");
	for (var i = 0; i < yy.length; i++) {
		if ($(yy[i]).html() == ss) {
			ss = $(yy[i]).val();
			break;
		}
	}

	var data = {
		"userName" : ss,
		"subject" : $('#sub_sel').val(),
		"the_teacher" : $("#the_teacher").val(),
		"start_time" : $("#start_time").val(),
		"clock_num" : $('#clock_num').val(),
		"giveClass" : $("#giveClass").val().trim(),
		"if_signup" : $('input:radio[name="if_signup"]:checked').val(),
		"remarks" : $("#remarks").val()
	};

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/winterVacation/do_insertWinterVacation.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_wv(for_btn_wv);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		
		$.post("/hicode/winterVacation/do_updateWinterVacation.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_wv(for_btn_wv);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}

}

/**
 * 暑假班>>>提交按钮
 */
function up_sub_sv() {
	if ($("#start_time").val().length < 10) {
		$("#start_time").css("borderColor", "#f00");
		return;
	} else {
		$("#start_time").css("borderColor", "#336699");
	}
	
	if ($("#clock_num").val().trim().length < 1) {
		$("#clock_num").css("borderColor", "#f00");
		return;
	} else {
		$("#clock_num").css("borderColor", "#336699");
	}
	
	if ($("#giveClass").val().trim().length < 1) {
		$("#giveClass").css("borderColor", "#f00");
		return;
	} else {
		$("#giveClass").css("borderColor", "#336699");
	}

	var ss = $(".chosen-single span").html();
	var yy = $("#student_sel option");
	for (var i = 0; i < yy.length; i++) {
		if ($(yy[i]).html() == ss) {
			ss = $(yy[i]).val();
			break;
		}
	}

	var data = {
		"userName" : ss,
		"subject" : $('#sub_sel').val(),
		"the_teacher" : $("#the_teacher").val(),
		"start_time" : $("#start_time").val(),
		"clock_num" : $('#clock_num').val(),
		"giveClass" : $("#giveClass").val().trim(),
		"if_signup" : $('input:radio[name="if_signup"]:checked').val(),
		"remarks" : $("#remarks").val()
	};

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/summerVacation/do_insertSummerVacation.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				start_post_sv(for_btn_sv);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		
		$.post("/hicode/summerVacation/do_updateSummerVacation.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				start_post_sv(for_btn_sv);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}

}

/* TMK>>>提交按钮*/
function up_sub_TMK() {
	
	if ($("#phone").val().trim().length != 11) {
		$("#phone").css("borderColor", "#f00");
		return;
	} else {
		$("#phone").css("borderColor", "#336699");
	}
	
	if ($("#userName").val().trim().length < 2) {
		$("#userName").css("borderColor", "#f00");
		return;
	} else {
		$("#userName").css("borderColor", "#336699");
	}
	
	if ($("#userAge").val().trim().length < 1) {
		$("#userAge").css("borderColor", "#f00");
		return;
	} else {
		$("#userAge").css("borderColor", "#336699");
	}

	var data = {
		"userName" : $("#userName").val().trim(),
		"userAge" : $("#userAge").val(),
		"t_sex" : $('input:radio[name="t_sex"]:checked').val(),
		"phone": $("#phone").val().trim(),
		"update_school":$("#update_school").val(),
		"update_selyixiang" : $("#update_selyixiang").val(),
		"gaikuang" : $("#gaikuang").val(),
		"if_renewal" : $('input:radio[name="if_renewal"]:checked').val(),
		"remarks" : $("#remarks").val()
	};
	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/phones/do_insertPhones.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				var pagedata = {"page":1};
				start_post_TMK(for_btn_TMK,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			}else if (e.list_advs == 'no') {
				$("#phone").css("borderColor", "#f00");
				alert("该手机号已存在。。。");
			}else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		$.post("/hicode/phones/do_updatePhones.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				var pagedata = {"page":1};
				start_post_TMK(for_btn_TMK,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			}else if (e.list_advs == 'no') {
				$("#phone").css("borderColor", "#f00");
				alert("该手机号已存在。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");


	}

}

/*跟单详情》》》提交按钮*/
function up_sub_sig() {
	if ($("#dang_tian").val().length > 200) {
		$("#dang_tian").css("borderColor", "#f00");
		return;
	} else {
		$("#dang_tian").css("borderColor", "#336699");
	}

	var ss = $(".chosen-single span").html();
	var yy = $("#student_sel option");
	for (var i = 0; i < yy.length; i++) {
		if ($(yy[i]).html() == ss) {
			ss = $(yy[i]).val();
			break;
		}
	}

	var data = {
		"userName" : ss,
		"leibie_sel" : $('#leibie_sel').val(),
		"qiandan_sel" : $('#qiandan_sel').val(),
		"adviser_sel" : $("#adviser_sel").val(),
		"dang_tian" : $("#dang_tian").val(),
		"first_time" : $("#first_time").val(),
		"second_time" : $("#second_time").val(),
		"third_time" : $("#third_time").val(),
	};

	var content = $(this).html();
	if (content == "添加") {
		$.post("/hicode/signing/do_insertSigning.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("添加成功");
				$("#tbl_body").children("tr").remove();
				var pagedata = {"page":1,"if_signup":0};
				start_post_sig(for_btn_sig,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			}else if (e.list_advs == 'ok2') {
				alert("对不起,该学员信息已被分派。。。。");
			} else {
				alert("添加失败,请联系管理员。。。。");
			}

		}, "json");

	} else if (content == "提交") {
		if (!window.confirm("是否确定要修改的内容？？？？")) {
			return;
		}
		data.id = $("#tea_list").attr("name");
		$.post("/hicode/signing/do_updateSigning.spc", data, function(e) {
			$("#hidd_mask").hide().hide(300);
			$("#dv_update").show().hide(300);
			if (e.list_advs == 'ok') {
				alert("修改成功");
				$("#tbl_body").children("tr").remove();
				var pagedata = {"page":1,"if_signup":0};
				start_post_sig(for_btn_sig,pagedata);
			}else if (e.list_advs == 'ok1') {
				alert("对不起,权限不足。。。。");
			} else {
				alert("修改失败,请联系管理员。。。。");
			}

		}, "json");
	}

}


/** ==============================================初始化数据============================================== */

/* 初始化数据 */
function start_post_aud(backFunction,pagedata) {
	$.post("/hicode/auditions/showAuditionsByInfo.spc", pagedata, function(a) {
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
						change_page_aud(this,pagedata);
					});

					$("#dv_but").append(btn);
				}
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
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
			$("#dv_table").css("height", "700px");
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
			$("#dv_table").css("height", "700px");
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
			$("#dv_table").css("height", "700px");
		}
	}, "json");

}

/* 报名学员》》》初始化数据 */
function start_post_cus(backFunction,pagedata) {
	$.post("/hicode/customer/showCustomerByInfo.spc", pagedata, function(a) {
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
						change_page_cus(this,pagedata);
					});

					$("#dv_but").append(btn);
				}
				/*$("#bt_end").attr("mypage", a.all_num);*/
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
		}

	}, "json");

}

/* 报名学员（已经结束当前学期的）》》》初始化数据 */
function start_post_cus_done(backFunction,pagedata) {
	$.post("/hicode/customer/showCustomerByInfo_done.spc", pagedata, function(a) {
		if (a) {
			creat_tb_cus_done(a.list_advs, "#tbl_body");
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
						change_page_cus_done(this,pagedata);
					});

					$("#dv_but").append(btn);
				}
				/*$("#bt_end").attr("mypage", a.all_num);*/
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
		}

	}, "json");

}


/* 初始化数据 */
function start_post_usr(backFunction,backFunction2) {
	$.post("/hicode/UserInfo/showUserOnline.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tb_usr(a.list_advs, "#tbl_body");
		}
		//锁定账号按钮赋单击事件
		backFunction();
		//勒令下线按钮赋单击事件
		backFunction2();
	}, "json");

}

/* 初始化数据 */
function start_post_dep(backFunction) {
	$.post("/hicode/deposit/showDepositByInfo.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tb_dep(a.list_advs, "#tbl_body");
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
						change_page_dep(this);
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
			$("#dv_table").css("height", "700px");
		}
	}, "json");

}

/* 寒假班》》》初始化数据 */
function start_post_wv(backFunction) {
	$.post("/hicode/winterVacation/showWinterVacationByInfo.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tb_wv(a.list_advs, "#tbl_body");
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
						change_page_wv(this);
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
			$("#dv_table").css("height", "700px");
		}

	}, "json");

}


/* 暑假班》》》初始化数据 */
function start_post_sv(backFunction) {
	$.post("/hicode/summerVacation/showSummerVacationByInfo.spc", {
		"page" : 1
	}, function(a) {
		if (a) {
			creat_tb_sv(a.list_advs, "#tbl_body");
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
						change_page_sv(this);
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
			$("#dv_table").css("height", "700px");
		}

	}, "json");

}

/* TMK》》》初始化数据 */
function start_post_TMK(backFunction,pagedata) {
	$.post("/hicode/phones/showPhonesByInfo.spc", pagedata, function(a) {
		if (a) {
			creat_tb_TMK(a.list_advs, "#tbl_body");
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
						change_page_TMK(this,pagedata);
					});

					$("#dv_but").append(btn);
				}
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
		}
	}, "json");

}


/* 市场跟单》》》初始化数据 */
function start_post_sig(backFunction,pagedata) {
	
	$.post("/hicode/signing/showSigningByInfo.spc", pagedata, function(a) {
		if (a) {
			creat_tb_sig(a.list_advs, "#tbl_body");
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
						change_page_sig(this,pagedata);
					});

					$("#dv_but").append(btn);
				}
				/*$("#bt_end").attr("mypage", a.all_num);*/
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
		}

	}, "json");

}

/** ==============================================待便利的值============================================== */

/* back_all: 待便利的值 ====市场顾问 */
function creat_tb(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name = 'userName'>" + back_all[i].name + "</td>";

		if (back_all[i].sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.png'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.png'/></td>";
		}
		str += "<td name='update_sel'>" + back_all[i].title + "</td>";

		if (back_all[i].onthejob == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
		}

		var creatDate = timestampToTime(back_all[i].opentime.time);
		str += "<td name='creatDate'>" + creatDate + "</td>";

		if (back_all[i].endtime) {
			var endDate = timestampToTime(back_all[i].endtime.time);
			str += "<td name='time_endDate'>" + endDate + "</td>";
			$(tr).css("color", "#b0b0b0");
		} else {
			str += "<td name='time_endDate'></td>";
		}

		if (back_all[i].title_updatetime) {
			var updatetime = timestampToTime(back_all[i].title_updatetime.time);
			str += "<td name = 'title_updatetime'>" + updatetime + "</td>";
		} else {
			str += "<td name = 'title_updatetime'></td>";
		}

		var btid = back_all[i].id;
		str += "<td><button id = '" + btid + "'name='" + (i + 1) + "'>修改</button></td>";
		str += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);

	}
}

/* back_all: 待便利的值 */
function creat_tb_aud(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		var st_time = timestampToTime_hms(back_all[i].time.time);
		str += "<td name='creatDate'>" + st_time + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";

		if (back_all[i].sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.png'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.png'/></td>";
		}

		str += "<td name='update_selclass'>" + back_all[i].classinfo + "</td>";
		
		str += "<td name='update_school'>" + back_all[i].school + "</td>";
		
		if(back_all[i].phone){
			str += "<td name='phone'>" + back_all[i].phone + "</td>";
		}else{
			str += "<td name='phone'></td>";
		}
		str += "<td name='update_selteas'>" + back_all[i].tea_name + "</td>";
		str += "<td name='update_seladvs'>" + back_all[i].adv_name + "</td>";
		str += "<td name='update_seladvs2'>" + back_all[i].adv_name2 + "</td>";
		
		$(tr).append(str);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
			
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);

	}
}


/* 报名学员》》待便利的值 */
function creat_tb_cus(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='sub_sel'>" + back_all[i].kemu + "</td>";
		str += "<td name='period'>"+back_all[i].period+"</td>";
		str += "<td name='money'>"+back_all[i].money+"</td>";
		str += "<td name='giveClass'>"+back_all[i].giveClass+"</td>";
		
		str += "<td name='the_teacher'>" + back_all[i].tea_name + "</td>";

		var firsttime = timestampToTime_hms(back_all[i].firsttime.time);

		str += "<td name='first_time'>" + firsttime + "</td>";

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
		$(tr).append(str);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
			
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);

	}

}

/* 报名学员_已经完成当前课程的学员》》待便利的值 */
function creat_tb_cus_done(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='sub_sel'>" + back_all[i].kemu + "</td>";
		str += "<td name='period'>"+back_all[i].period+"</td>";
		str += "<td name='money'>"+back_all[i].money+"</td>";
		str += "<td name='giveClass'>"+back_all[i].giveClass+"</td>";
		
		str += "<td name='the_teacher'>" + back_all[i].tea_name + "</td>";

		var firsttime = timestampToTime_hms(back_all[i].firsttime.time);

		str += "<td name='first_time'>" + firsttime + "</td>";

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
		$(tr).append(str);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
			
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		/*var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";*/
		var strr = "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
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
			str += "<td><img src='/hicode/sysimg/face_boy.png'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.png'/></td>";
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

/* back_all: 待便利的值 */
function creat_tb_usr(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName' usid = '"+back_all[i].userID+"'>" + back_all[i].name + "</td>";
		str += "<td>" + back_all[i].type + "</td>";
		str += "<td>" + back_all[i].ip + "</td>";
		var startTime = timestampToTime_hms(back_all[i].time.time);
		str += "<td>" + startTime + "</td>";
		str += "<td><button>锁定</button>";
		str += "<button style='margin-left:5px;'>下线</button></td>";
		
		str += "<td><input type='checkbox' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);
	}
}

/* back_all: 待便利的值 */
function creat_tb_dep(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		var st_time = timestampToTime(back_all[i].pay_time.time);
		str += "<td name='pay_time'>" + st_time + "</td>";
		str += "<td name='money'>" + back_all[i].num + "</td>";
		str += "<td name='phone'>" + back_all[i].tel + "</td>";
		str += "<td name='update_seladvs'>" + back_all[i].qianyue + "</td>";
		str += "<td name='update_seladvs2'>" + back_all[i].yaoyue + "</td>";

		if (back_all[i].if_ok == 1) {
			str += "<td><img src='/hicode/sysimg/face_smile.jpg'/> </td>";
		} else {
			str += "<td></td>";
		}
		if(back_all[i].tuimoney){
			str += "<td name='refundmoney'>" + back_all[i].tuimoney + "</td>";
			$(tr).css("color", "#b0b0b0");
		} else {
			str += "<td name='refundmoney'></td>";
		}
		
		if(back_all[i].end_time){
			var end_time = timestampToTime(back_all[i].end_time.time);
			str += "<td name='refund_time'>" + end_time + "</td>";
		} else {
			str += "<td name='refund_time'></td>";
		}
		
		$(tr).append(str);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
			
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);
	}
}


/* 寒假班>>>back_all: 待便利的值 */
function creat_tb_wv(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='sub_sel'>" + back_all[i].subject + "</td>";
		str += "<td name='the_teacher'>" + back_all[i].teacher + "</td>";
		var start_time = timestampToTime(back_all[i].start_time.time);
		str += "<td name='start_time'>" + start_time + "</td>";
		str += "<td name='clock_num'>"+back_all[i].clock_num+"</td>";
		if (back_all[i].if_signup == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td>  </td>";
		}
		str += "<td name='giveClass'>"+back_all[i].giveClass+"</td>";

		$(tr).append(str);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);
	}

}


/* 暑假班>>>back_all: 待便利的值 */
function creat_tb_sv(back_all, p_dom) {
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='sub_sel'>" + back_all[i].subject + "</td>";
		str += "<td name='the_teacher'>" + back_all[i].teacher + "</td>";
		var start_time = timestampToTime(back_all[i].start_time.time);
		str += "<td name='start_time'>" + start_time + "</td>";
		str += "<td name='clock_num'>"+back_all[i].clock_num+"</td>";
		if (back_all[i].if_signup == 1) {
			str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else {
			str += "<td>  </td>";
		}
		str += "<td name='giveClass'>"+back_all[i].giveClass+"</td>";

		$(tr).append(str);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);
	}

}

/* TMK >>> back_all: 待便利的值 */
function creat_tb_TMK(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='userAge'>" + back_all[i].age + "</td>";

		if (back_all[i].sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.png'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.png'/></td>";
		}
		str += "<td name='phone'>" + back_all[i].phone + "</td>";
		str += "<td name='update_school'>" + back_all[i].school + "</td>";
//		（0:待确定；1:错号/空号；2:近期上门；3:可邀约上门；4:意向一般；5:无意向；）
		if(back_all[i].yixiang == 0){
			str += "<td name='update_selyixiang'>待确定</td>";
		}else if(back_all[i].yixiang == 1){
			str += "<td name='update_selyixiang'>错号/空号</td>";
			$(tr).css("color", "#b0b0b0");
		}else if(back_all[i].yixiang == 2){
			str += "<td name='update_selyixiang'>近期上门</td>";
		}else if(back_all[i].yixiang == 3){
			str += "<td name='update_selyixiang'>可邀约上门</td>";
		}else if(back_all[i].yixiang == 4){
			str += "<td name='update_selyixiang'>意向一般</td>";
		}else if(back_all[i].yixiang == 5){
			str += "<td name='update_selyixiang'>无意向</td>";
		}else{
			str += "<td name='update_selyixiang'>待确定</td>";
		}
	/*	str += "<td name='gaikuang'>" + back_all[i].gaikuang + "</td>";*/
		
		if (back_all[i].if_arrival == 1) {
			str += "<td><img src='/hicode/sysimg/face_smile.jpg'/> </td>";
		} else {
			/*str += "<td><img src='/hicode/sysimg/face_grieved.jpg'/></td>";*/
			str += "<td> </td>";
		}
		
		$(tr).append(str);
		
		var td01 = document.createElement("td");
		var img01 = document.createElement("img");
		if(back_all[i].gaikuang != null && back_all[i].gaikuang != ""){
			$(img01).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img01).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		$(img01).attr("name","gaikuang");
		$(img01).attr("userName",back_all[i].name);
		$(img01).attr("myfont",back_all[i].gaikuang);
		img01.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
		};
		
		td01.appendChild(img01);
		tr.appendChild(td01);
		
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].beizhu != null && back_all[i].beizhu != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		$(img).attr("name","remarks");
		$(img).attr("userName",back_all[i].name);
		$(img).attr("myfont",back_all[i].beizhu);
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_remarks(ss,tt);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);

	}
}


/* 市场跟单》》back_all: 待便利的值 */
function creat_tb_sig(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='school'>" + back_all[i].school + "</td>";
		str += "<td name='nianji'>"+back_all[i].nianji+"</td>";
		
		if (back_all[i].if_signup == 1) {
			str += "<td>-*-*-*-*-</td>";
		}else{
			str += "<td name='phone'>"+back_all[i].phone+"</td>";
		}
		
		//用户分类（1.两周内可签单；2.一个月内可签单；3.需要长期跟踪；4.无意向）
		if (back_all[i].fenlei == 1) { 
			str += "<td name='fenlei'>两周内可签单</td>";
		} else if(back_all[i].fenlei == 2){
			str += "<td name='fenlei'>一个月内可签单</td>";
		}else if(back_all[i].fenlei == 3){
			str += "<td name='fenlei'>需要长期跟踪</td>";
		}else{
			str += "<td name='fenlei'>无意向</td>";
		}
		
		//是否报名（0:未报名；1:已报名；2:死单）
		if (back_all[i].if_signup == 1) {
			str += "<td name='if_signup' value='已报名'> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
		} else if(back_all[i].if_signup == 2){
			str += "<td name='if_signup' value='死单'> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
			$(tr).css("color", "#b0b0b0");
		}else{
			str += "<td name='if_signup' value='未报名'>  </td>";
		}
		
		if (back_all[i].if_signup == 1) {
			str += "<td >"+back_all[i].successPeople+"</td>";
		}else{
			str += "<td name='firstPeople'>"+back_all[i].firstPeople+"</td>";
		}
		
		/*str += "<td name='nowPeople'>"+back_all[i].nowPeople+"</td>";*/
		str += "<td name='begin_time'>"+timestampToTime(back_all[i].begin_time.time)+"</td>";
		
		$(tr).append(str);
		var td = document.createElement("td");
		var img = document.createElement("img");
		if(back_all[i].firstRemark != null && back_all[i].firstRemark != ""){
			$(img).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		
		$(img).attr("name","firstRemark");
		$(img).attr("userName",back_all[i].name);
		
		var mianzi = "&nbsp;&nbsp;&nbsp;" + back_all[i].firstRemark;
		$(img).attr("manzi",mianzi);
		$(img).attr("manzi01",back_all[i].firstRemark);
		
		var zhuizong = "&nbsp;&nbsp;&nbsp;1)" + back_all[i].zhuizong_01+
						"<br/>&nbsp;&nbsp;&nbsp;2)" + back_all[i].zhuizong_02+
						"<br/>&nbsp;&nbsp;&nbsp;3)" + back_all[i].zhuizong_03;
		
		$(img).attr("myfont",zhuizong);
		
		$(img).attr("myfont01",back_all[i].zhuizong_01);
		$(img).attr("myfont02",back_all[i].zhuizong_02);
		$(img).attr("myfont03",back_all[i].zhuizong_03);
		
		img.onclick = function(){
			var ss = $(this).attr("userName");
			var tt = $(this).attr("manzi");
			var ff = $(this).attr("myfont");
			if(tt != null && tt != ""){
				create_GenZongXiZe(ss,tt,ff);
			}else{
				create_GenZongXiZe(ss,"暂无。。。。。",ff);
			}
		};
		
		td.appendChild(img);
		tr.appendChild(td);
		
		var strr = "<td> </td>";
		
		if(back_all[i].if_signup != 1){
			var btid = back_all[i].id;
			strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		}
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);
		
	}

}

/** ==============================================切换页面============================================== */

/*切换页面*/
function change_page_aud(this_dom,pagedata) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");
	
	$.post("/hicode/auditions/showAuditionsByInfo.spc",pagedata, function(a_s) {
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
		for_btn_adv();
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

/*报名学员》》》切换页面*/
function change_page_cus(this_dom,pagedata) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");

	$.post("/hicode/customer/showCustomerByInfo.spc",pagedata, function(a_s) {
		$("#tbl_body").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_cus(js_arry.list_advs, "#tbl_body");
		for_btn_cus();
	});
}

/*报名学员_已完成当前课程的学员》》》切换页面*/
function change_page_cus_done(this_dom,pagedata) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");

	$.post("/hicode/customer/showCustomerByInfo_done.spc",pagedata, function(a_s) {
		$("#tbl_body").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_cus_done(js_arry.list_advs, "#tbl_body");
		for_btn_cus_done();
	});
}

/*切换页面*/
function change_page_dep(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/deposit/showDepositByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tb_dep(js_arry.list_advs, "#tbl_body");
		for_btn_dep();
	});
}

/*寒假班>>>切换页面*/
function change_page_wv(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/winterVacation/showWinterVacationByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_wv(js_arry.list_advs, "#tbl_body");
		for_btn_wv();
	});
}

/*暑假班>>>切换页面*/
function change_page_sv(this_dom) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});

	$.post("/hicode/summerVacation/showSummerVacationByInfo.spc", {
		"page" : $(this_dom).attr("mypage")
	}, function(a_s) {
		$("#tbl_body").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_sv(js_arry.list_advs, "#tbl_body");
		for_btn_sv();
	});
}


/*切换页面*/
function change_page_TMK(this_dom,pagedata) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");
	
	$.post("/hicode/phones/showPhonesByInfo.spc",pagedata, function(a_s) {
		$("#tbl_body").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tb_TMK(js_arry.list_advs, "#tbl_body");
		for_btn_TMK();
	});
}


/*报名学员》》》切换页面*/
function change_page_sig(this_dom,pagedata) {
	$("#dv_but button").css({
		"backgroundColor" : "#fff",
		"color" : "#336699"
	});
	$(this_dom).css({
		"backgroundColor" : "#336699",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");
	
	console.log("pagedata=====fanye");

	console.log(pagedata);

	$.post("/hicode/signing/showSigningByInfo.spc",pagedata, function(a_s) {
		$("#tbl_body").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_sig(js_arry.list_advs, "#tbl_body");
		for_btn_sig();
	});
}

