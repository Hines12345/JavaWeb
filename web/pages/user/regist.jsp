<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//这是用jQery来验证信息是否合法
		$(function(){

			/*-----------------------------ajax操作-------------------------------------------*/
			$("#username").blur(function () {
				var username = this.value;
				$.getJSON("http://localhost:8088/BookShopProject_2.0/userServlet","action=ajaxUsername&username=" + username,function (data) {
					if(data.existUsername)
					{
						$("span.errorMsg").text("用户名已存在！");
					}
					else
					{
						$("span.errorMsg").text("用户名可用")
					}
				})
			})





			$("#img_code").click(function () {
				this.src = "${basePath}Kaptcha.jpg?d=" + new Date();
				// alert("1");

			})

            $("#sub_btn").click(function(){
            	//用户名合法判断
				var username = $("#username").val();
				var testusername = /^\w{5,12}$/;
				if(!testusername.test(username)){
                    $("span.errorMsg").text("用户名错误！")
                    return false;
                }

				//密码合法判断
                var password = $("#password").val();
				var testpassword = /^\w{5,12}$/;
				if(!testpassword.test(password)){
                    $("span.errorMsg").text("密码错误！");
                    return false;
                }

                //确认密码和密码判断是否匹配
                var repassword = $("#repwd").val();
                var password = $("#password").val();
                if(repassword!= password){
                    $("span.errorMsg").text("两次密码不一致！");
                    return false;
                }

                //邮箱验证
                var email = $("email").val();
                var testemail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if(!testemail.test(email)){
                    $("span.errorMsg").text("邮箱格式不正确！");
                    return false;
                }

                //验证码（还没有学服务器内容）只能做出判断验证码的效果
                var code = $("#code").val();
                if(code == null || code == ""){
                	$("span.errorMsg").text("请输入验证码！");
                	return false;
                }

                 //如果加载太慢，用户名错误还是会显示在页面上，所以再重写这个值为空
                $("span.errorMsg").text("")
            })
		})
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
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
<%--									<%=request.getAttribute("msg")==null?"请输入注册信息":request.getAttribute("msg")%>--%>
										${empty requestScope.msg ? "请输入注册信息": requestScope.msg}
								</span>
							</div>
							<div class="form">
<!--								下面表单标签中的action属性是提交地址-->
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
									value=${empty requestScope.username ? "" : requestScope.username}>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
										   value=${empty requestScope.email ? "" : requestScope.email}>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
									<img alt="" id="img_code" src="Kaptcha.jpg" style="float: right; margin-right: 40px;width: 100px;height: 28px">
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