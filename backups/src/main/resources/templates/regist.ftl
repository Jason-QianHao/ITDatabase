
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<link rel="stylesheet" href="css/common.css">
<!-- 自适应样式单 -->
<link rel="stylesheet" href="css/adaptive.css">
<link rel="stylesheet" href="css/login.css">
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>ITDatabase注册</h3>
						<form action="registForm"  method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="用户名">
							</div>
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="email" class="text" style="color: #FFFFFF !important" type="text" placeholder="邮箱">
							</div>
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="phone" class="text" style="color: #FFFFFF !important" type="text" placeholder="手机号">
							</div>
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="password" class="text" style="color: #FFFFFF !important" type="text" placeholder="password">
							</div>
							<div class="ng-form-area show-place" id="form-area">
								<input class="button orange" type="submit" id="message_LOGIN_IMMEDIATELY" value="立即注册" ">
							</div>
							<#if error??>
								<div>
									<span id="message_LOGIN_TOO_MUCH" style="color: red">
										${error}</span>
								</div>
							</#if>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>
		
		<script type="text/javascript">
		    function check(form) {
		        	var username = form.username.value;
					var email = form.email.value;
					var phone = form.phone.value;
					var password = form.password.value;
					$.ajax({
						type: "post",
						url: "http://localhost/ITDatabase/registFormJs",
						data: { username: username, email: email, phone: phone},
						success: function(data) {
							if(data == "success") {
								$.jq_Alert({
									message: "添加成功",
									btnOktext: "确认",
									dialogModal: true,
									btnOkClick: function() {
										return true;

									}
								});
							}else {
								$.jq_Alert({
									message: "添加失败",
									btnOktext: "确认",
									dialogModal: true,
									btnOkClick: function() {
										return false;

									}
							}
						}
					});
					alert(t);
		    }
		</script>

		<div style="text-align:center;">
			<p>版本号@XiaoQian v2.0</p>
		</div>
	</body>
</html>