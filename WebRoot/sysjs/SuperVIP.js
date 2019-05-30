
/** ==============================================修改按钮============================================== */
/* VIP_TMK>>>修改按钮 */
function for_btn_VIP_TMK() {
	/*return alert("对不起，超级管理员不能参与修改。。。。。");*/
}

/* VIP_修改负责人>>>修改按钮 */
function for_btn_VIP_sig() {
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
					$("#choise_adv").hide().show(300);
					//将该信息的id，赋值给提交按钮，的属性name
					$("#choise_yes").attr("name", $(this).attr("id"));
					
					var stu_name = $("[name='userName']:eq(" + t + ")").html();
					$("#kehu_name").html(stu_name);
					
					var name = $("[name='nowPeople']:eq(" + t + ")").html();
					$("#now_fuzeren").html(name);
					
					$.post("/hicode/adviser/showAdviser.spc", function(f) {
						if (f.length > 0) {
							var name = $("[name='nowPeople']:eq(" + t + ")").html();
							for_adviser_sel("new_fuzeren_sel", f,name);
						}
					}, "json");
					
				});

			})();

		}
	}

}

/** ==============================================提交按钮============================================== */

/*跟单详情_修改顾问》》》提交按钮*/
function up_sub_VIP_sig() {

	var data = {
		"id":$("#choise_yes").attr("name"),
		"adviser_sel" : $("#new_fuzeren_sel").val(),
		"adviser_name" : $("#new_fuzeren_sel option:selected").html()
	};
	console.log(data);

	var content = $(this).html();
	if (!window.confirm("是否确定要修改的内容？？？？")) {
		return;
	}
	
	$.post("/hicode/svipSigning/do_updateAdviserForSigning.spc", data, function(e) {
		$("#hidd_mask").hide().hide(300);
		$("#choise_adv").show().hide(300);
		if (e.list_advs == 'ok') {
			alert("修改成功");
			$("#tbl_body").children("tr").remove();
			var pagedata = {"page":1};
			start_post_VIP_sig(for_btn_VIP_sig,pagedata);
		}else if (e.list_advs == 'ok1') {
			alert("对不起,权限不足。。。。");
		} else {
			alert("修改失败,请联系管理员。。。。");
		}

	}, "json");

}

/** ==============================================初始化数据============================================== */


/* VIP_TMK》》》初始化数据 */
function start_post_VIP_TMK(backFunction,pagedata) {
	$.post("/hicode/svipPhones/showPhonesByInfo.spc", pagedata, function(a) {
		if (a) {
			creat_tb_VIP_TMK(a.list_advs, "#tbl_body_vip");
			/* 添加页码 */
			if (a.all_num) {
				
				$("#dv_but_vip").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#996633",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_VIP_TMK(this,pagedata);
					});

					$("#dv_but_vip").append(btn);
				}
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl_vip").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
		}
	}, "json");

}

/* VIP_市场跟单》》》初始化数据 */
function start_post_VIP_sig(backFunction,pagedata) {
	$.post("/hicode/svipSigning/showSigningByInfo.spc", pagedata, function(a) {
		if (a) {
			creat_tb_VIP_sig(a.list_advs, "#tbl_body_vip");
			/* 添加页码 */
			if (a.all_num) {
				$("#dv_but_vip").children("button").remove();
				for (var k = 0; k < a.all_num; k++) {
					var btn = document.createElement("button");
					$(btn).html(k + 1);
					$(btn).attr("mypage", (k + 1));
					if (k == 0) {
						$(btn).css({
							"backgroundColor" : "#996633",
							"color" : "#fff"
						});
					}
					$(btn).click(function() {
						change_page_VIP_sig(this,pagedata);
					});

					$("#dv_but_vip").append(btn);
				}
				/*$("#bt_end").attr("mypage", a.all_num);*/
			}
			//修改按钮赋单击事件
			backFunction();
		}
		var hei = $("#tea_tbl_vip").css("height");
		hei = hei.substr(0, hei.length - 2);
		if (hei > 650) {
			$("#dv_table").css("height", "700px");
		}

	}, "json");

}

/** ==============================================待便利的值============================================== */

/* VIP_TMK >>> back_all: 待便利的值 */
function creat_tb_VIP_TMK(back_all, p_dom) {
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
		/*var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";*/
		var strr = "<td></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);

	}
}

/* VIP_市场跟单》》back_all: 待便利的值 */
function creat_tb_VIP_sig(back_all, p_dom) {
	$(p_dom).children("tr").remove();
	for (var i = 0; i < back_all.length; i++) {
		var tr = document.createElement("tr");
		var str = "<td>" + (i + 1) + "</td>";
		str += "<td name='userName'>" + back_all[i].name + "</td>";
		str += "<td name='school'>" + back_all[i].school + "</td>";
		str += "<td name='nianji'>"+back_all[i].nianji+"</td>";
		str += "<td name='phone'>"+back_all[i].phone+"</td>";
		
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
		
		str += "<td name='firstPeople'>"+back_all[i].firstPeople+"</td>";
		str += "<td name='nowPeople'>"+back_all[i].nowPeople+"</td>";
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
		
		var td_2 = document.createElement("td");
		var img_2 = document.createElement("img");
		if(back_all[i].history != null && back_all[i].history != ""){
			$(img_2).attr("src","/hicode/sysimg/beizhu/for_yes.png");
		}else{
			$(img_2).attr("src","/hicode/sysimg/beizhu/for_no.png");
		}
		
		$(img_2).attr("name","history");
		$(img_2).attr("userName",back_all[i].name);
		
		var str = back_all[i].history;
		/*console.log(str);*/
		
		while(str.indexOf('&&&') >= 0){
			str = str.replace('&&&','<br/>');
		}
		
		$(img_2).attr("myfont",str);
		
		img_2.onclick = function(){
			var ss = $(this).attr("userName");
			var ff = $(this).attr("myfont");
			if(ff != null && ff != ""){
				create_remarks(ss,ff);
			}else{
				create_remarks(ss,"暂无。。。。。");
			}
		};
		
		td_2.appendChild(img_2);
		tr.appendChild(td_2);
		
		var btid = back_all[i].id;
		var strr = "<td><button id = '" + btid + "' name='" + (i + 1) + "'>修改</button></td>";
		strr += "<td><input type='checkbox' value='" + btid + "' /></td>";
		$(tr).append(strr);
		$(p_dom).append(tr);

	}

}

/** ==============================================切换页面============================================== */
/*TMK_切换页面*/
function change_page_VIP_TMK(this_dom,pagedata) {
	$("#dv_but_vip button").css({
		"backgroundColor" : "#fff",
		"color" : "#996633"
	});
	$(this_dom).css({
		"backgroundColor" : "#996633",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");
	
	$.post("/hicode/svipPhones/showPhonesByInfo.spc",pagedata, function(a_s) {
		$("#tbl_body_vip").html(' ');

		var js_arry = eval('(' + a_s + ')');

		creat_tb_VIP_TMK(js_arry.list_advs, "#tbl_body_vip");
		//修改按钮
		for_btn_VIP_TMK();
	});
}
/*报名学员》》》切换页面*/
function change_page_VIP_sig(this_dom,pagedata) {
	$("#dv_but_vip button").css({
		"backgroundColor" : "#fff",
		"color" : "#996633"
	});
	$(this_dom).css({
		"backgroundColor" : "#996633",
		"color" : "#fff"
	});
	
	pagedata.page = $(this_dom).attr("mypage");

	$.post("/hicode/svipSigning/showSigningByInfo.spc",pagedata, function(a_s) {
		$("#tbl_body_vip").html(' ');
		var js_arry = eval('(' + a_s + ')');
		creat_tb_VIP_sig(js_arry.list_advs, "#tbl_body_vip");
		for_btn_VIP_sig();
	});
}