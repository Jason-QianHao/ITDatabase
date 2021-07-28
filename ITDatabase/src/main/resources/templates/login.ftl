
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<link rel="stylesheet" href="css/common.css">
<!-- 自适应样式单 -->
<link rel="stylesheet" href="css/adaptive.css">
<link rel="stylesheet" href="css/login.css">
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>

	<!--

	<div class="login-form">
			<div class="top-login">
				<span><img src="imgs/logo.jpg" alt=""/></span>
			</div>
	</div>
	--!>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>ITDatabase登录</h3>
						<form action="login"  id="myform"  method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="用户名/手机号/邮箱">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="密码">
							</div>
							<div class="ng-form-area show-place" id="form-area">
								<input class="button orange" type="submit" id="message_LOGIN_IMMEDIATELY" value="立即登录">
								<span id="custom_display_128"> <a href="regist" class="button" id="message_REGISTER">注册帐号</a>
								</span> 
								<span id="custom_display_8">
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
		<div style="text-align:center;">
		<p>版本号@XiaoQian v2.0</p>
	</div>
	</body>
</html>