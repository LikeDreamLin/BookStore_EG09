<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

<script type="text/javascript">
	
	$(function(){
		
		//给name为username的input绑定影change事件
		$("[name=username]").change(function(){
			
			//获取用户填写的用户名
			var username = this.value;
			
			//设置一个请求地址
			var url = "${pageContext.request.contextPath}/client/UserServlet";
			//设置请求参数
			var param = {
							"method":"checkUsername",
							"username":username
						};
			//发送ajax请求
			$.post(url,param,function(data){
				
				if(data==0){
					//用户名不可用，显示一个错误消息
					$(".errorMsg").html("用户名已存在！");
					//禁用提交按钮
					$("#sub_btn").attr("disabled",true);
					
				}else{
					//用户名可用，移除错误
					$(".errorMsg").html("");
					//启用提交按钮
					$("#sub_btn").attr("disabled",false);
				}
				
			},"text");
			
			
			
		});
		
		
		//设置点击验证码刷新的功能
		//给验证码的图片绑定一个单击响应函数
		$("#code_img").click(function(){
			
			//要想刷新页面中的一个图片，就是重新设置一下图片的src属性即可
			//当img标签的src属性发生改变时，浏览器会自动重新加载图片
			this.src = "code.jpg?t="+Math.random();
			
		});
		
		
		//给注册按钮绑定一个单击响应函数
		$("#sub_btn").click(function(){
			
			//获取用户填写的用户名、密码、确认密码、电子邮件、验证码
			var username = $("[name=username]").val();
			var password = $("[name=password]").val();
			var repwd = $("[name=repwd]").val();
			var email = $("[name=email]").val();
			var code = $("[name=code]").val();
			
			//检查这些字符串是否符合格式
			//可以使用正则表达式来检查某一个字符串是否符合某种规则
			//创建一个正则表达式
			//a这个正则代表检查一个字符串是否包含a
			//var reg = /a/;
			
			//使用正则表达式
			//var flag = reg.test(username);
			
			//alert(flag);
			
			//创建一个username的正则表达式
			var nameReg = /^[a-zA-Z0-9_-]{3,16}$/;
			
			//测试用户名
			if(!nameReg.test(username)){
				//如果用户名不符合规则
				alert("请输入包括字母、数字、_、-3-16位的用户名！");
				
				//取消默认行为
				return false;
			}
			
			//测试密码
			var pwdReg = /^[a-z0-9_-]{6,18}$/;
			if(!pwdReg.test(password)){
				//密码不符合规则
				alert("请输入包括字母、数字、_、-6-18位的密码！");
				
				//取消默认行为
				return false;
			}
			
			//检查确认密码是否和密码一致
			if(password != repwd){
				
				alert("两次输入的密码不一致！");
				
				//取消默认行为
				return false;
			}
			
			//检查电子邮箱地址
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				
				alert("请输入正确的电子邮件地址！");
				
				//取消默认行为
				return false;
			}
			
			//检查验证码是否为空
			if(code == ""){
				alert("请输入验证码！");
				
				//取消默认行为
				return false;
			}
		});
		
		
	});
	

</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%-- <%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %> --%>
									${msg}
								</span>
							</div>
							<div class="form">
								<form action="client/UserServlet?method=regist" method="post">
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input name="code" class="itxt" type="text" style="width: 150px;"/>
									<img id="code_img" alt="" src="code.jpg" style="float: right; margin-right: 40px; width: 80px; height: 40px;">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>