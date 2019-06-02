/**login.js 封装登录和注册处理**/
//log_in.html主处理
$(function(){//页面载入完毕
	//给登录按钮绑定单击处理
	$("#login").click(checkLogin);
	//给注册按钮绑定单击处理
	$("#regist_button").click(registUser);
});
//注册处理
function registUser(){
	//获取参数
	var name=$("#regist_username").val().trim();
	var password = $("#regist_password").val().trim();
	var final_password = $("#final_password").val().trim();
	var nick = $("#nickname").val().trim();
	//alert(name+","+nick+","+password+","+final_password);
	//检查数据格式
	//检查用户
	var ok=true;//表示参数状态
	if(name==""){
		ok=false;
		$("#warning_1 span").html("用户名不能为空");
		$("#warning_1").show();
	}
	//检测密码数据：非空  不能小于6位
	if(password==""){
		ok=false;
		$("#warning_2 span").html("密码不能为空");
		$("#warning_2").show();
	}else if(password.length>0&&password.length<6){
		ok=false;
		$("#warning_2 span").html("密码不能小于6位");
		$("#warning_2").show();
	}
	//检测确认密码：非空 是否与密码一致
	if(final_password!=password){
		ok=false;
		$("#warning_3 span").html("密码输入不一致");
		$("#warning_3").show();
	}
	if(ok){
		$.ajax({//数据校验通过
			url:path+"/user/add.do",
			type:"post",
			data:{"name":name,
					"nick":nick,
					"password":password},
			dataType:"json",
			success:function(result){
				//注册成功
				if(result.status==0) {
					alert(result.msg);
					//返回到登录页面
					$("#back").click();
				}else if (result.status==1) {
					//alert(result.msg);//用户名被占用
					$("#warning_1 span").html(result.msg);
					$("#warning_1").show();
				}
			},
			error:function(){
				alert("注册失败")
			}
		});
	}
};
//登录处理
function checkLogin(){
	//测试绑定事件
	//alert("赌哪了？");
	//获取参数
	var name=$("#count").val().trim();
	var password=$("#password").val().trim();
	//alert(name+","+password);
	$("#count_span").html("");
	$("#password_span").html("");
	//格式检测
	var ok=true;
	if (name=="") {
		$("#count_span").html("用户名不能为空");
		ok=false;
	}
	if (password=="") {
		$("#password_span").html("密码不能为空");
		ok=false;
	}
	
	if (ok) {  //检测格式通过
		//发送ajax请求
		$.ajax({
			url:path+"/user/login.do",
			type:"post",
			data:{"name":name,"password":password},
			dataType:"json",
			success:function(result){
				//result是服务器返回的json结果
				if(result.status==0){
					//将用户信息保存到Cookie
					var userId=result.data.cn_user_id;
					addCookie("userId",userId,2);
					window.location.href="edit.html";
				}else if (result.status==1) {
					$("#count_span").html(result.msg);
				}else if (result.status==2) {
					$("#password_span").html(result.msg);
				}
			},
			error:function(){
				alert("登录失败！");
			}
		});
	}
};