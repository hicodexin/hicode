
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
			var str = "<option selected='selected' >" + $(f[k]).html() + "</option>";
		} else {
			var str = "<option >" + $(f[k]).html() + "</option>";
		}

		$("#" + id).append(str);
	}

}

/* 初始化数据 */
function start_post_aud() {
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
						change_page(this);
					});

					$("#dv_but").append(btn);
				}
				$("#bt_end").attr("mypage", a.all_num);
			}
		}
		var hei = $("#tea_tbl").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "800px");
		}
	}, "json");

}

/* 试听课修改按钮 */
function for_btn_aud() {
	window.setTimeout(function() {
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

	}, 500);
}
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
		console.log(c);
		if (c.length > 0) {
			for_sel("update_selteas", c);
		}
	}, "json");

	$.post("/hicode/adviser/showAdviser.spc", function(f) {
		console.log(f);
		if (f.length > 0) {
			for_sel("update_seladvs", f);
		}
	}, "json");

}
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
		str += "<td>" + back_all[i].name + "</td>";
		str += "<td>" + back_all[i].kemu + "</td>";

		if (back_all[i].period == 1) {
			str += "<td> 一个季度 </td>";
		} else if (back_all[i].period == 2) {
			str += "<td> 两个季度 </td>";
		} else if (back_all[i].period == 3) {
			str += "<td> 一个年度 </td>";
		} else if (back_all[i].period == 4) {
			str += "<td> 两个年度 </td>";
		}

		str += "<td>" + back_all[i].tea_name + "</td>";

		var firsttime = timestampToTime_hms(back_all[i].firsttime.time);

		str += "<td>" + firsttime + "</td>";

		str += "<td>" + back_all[i].tel + "</td>";

		str += "<td>" + back_all[i].qianyue + "</td>";

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
		str += "<td><button id = '" + btid + "' name='" + (i + 1) + "'onclick=alert('权限不足，请联系管理员')>修改</button></td>";
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

/*切换页面*/
function change_page(this_dom) {
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