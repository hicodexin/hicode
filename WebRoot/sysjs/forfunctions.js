
// 格式化时间格式
function timestampToTime(timestamp) {
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear() + ' - ';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + ' - ';
	D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
	m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
	s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
	return Y + M + D;
}
//格式化时间格式
function timestampToTime_hms(timestamp) {
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear() + ' - ';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + ' - ';
	D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
	m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
	s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
	return Y + M + D + h + m + s;
}

function for_sel(id, f) {
	$("#" + id).children("option").remove();
	for (var i = 0; i < f.length; i++) {
		var str = "<option value=" + f[i].id + ">" + f[i].name + "</option>";
		$("#" + id).append(str);
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
		str += "<td>" + st_time + "</td>";
		str += "<td>" + back_all[i].name + "</td>";

		if (back_all[i].sex == 1) {
			str += "<td><img src='/hicode/sysimg/face_boy.jpg'/> </td>";
		} else {
			str += "<td><img src='/hicode/sysimg/face_girl.jpg'/></td>";
		}

		str += "<td>" + back_all[i].classinfo + "</td>";
		str += "<td>" + back_all[i].tea_name + "</td>";
		str += "<td>" + back_all[i].adv_name + "</td>";

		if (back_all[i].remarks) {
			str += "<td>" + back_all[i].beizhu + "</td>";
		} else {
			str += "<td>   </td>";
		}

		var btid = back_all[i].id;
		str += "<td><button id = '" + btid + "' name='" + (i + 1) + "' onclick=alert('权限不足，请联系管理员')>修改</button></td>";
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
function creat_tb_sub(back_all,p_dom){	
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
			str += "<td name = 'userName'>"+back_all[i].name+"</td>";
			
			if(back_all[i].xiaxian == 0){
				str += "<td> <img src='/hicode/sysimg/face_smile.jpg' /> </td>";
			}else{
				str += "<td> <img src='/hicode/sysimg/face_grieved.jpg' /> </td>";
			}
			
			var btid = back_all[i].id;
			str += "<td><button id = '"+btid+"' name='"+(i+1)+"'>修改</button></td>";
			str += "<td><input type='checkbox' value='"+btid+"' /></td>";
		$(tr).append(str);
		$(p_dom).append(tr);
	} 
}

