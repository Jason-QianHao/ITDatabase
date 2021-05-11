<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>添加资源</title>
		<link rel="stylesheet" />
		<link rel="stylesheet" href="css/Site.css" />
		<link rel="stylesheet" href="css/zy.all.css" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/amazeui.min.css" />
		<link rel="stylesheet" href="css/admin.css" />
	</head>

	<body>
	<a href="ITDatabase"><img border="0" width="5%" height="5%" src="imgs/home.jpg" /></a>
		<center>
			<h1>ITDatabase添加资源</h1>
		</center>
		<div class="dvcontent">

			<div>
				<!--tab start-->
				<div class="tabs" style="margin: 30px;">
					<div class="hd">
						<ul>
							 <li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;">添加资源(共享平台接口)</li>
						</ul>
					</div>
					<div class="bd">
						<ul class="theme-popbod dform">
								<div class="am-cf admin-main" style="padding-top: 0px;">												
									<div class="am-cf admin-main" style="padding-top: 0px;">
										<!-- content start -->
										<div class="admin-content">
											<div class="admin-content-body">								
												<div class="am-g">
													<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">							
													</div>
													<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4"
														style="padding-top: 30px;">

															<form class="am-form am-form-horizontal"
																action="addMessage" method="post">
															
																<div class="am-form-group">
																	<label for="user-email" class="am-u-sm-3 am-form-label">
																	资源类型:</label>
																	<div class="am-u-sm-9">
																		<select name="messageTypeId" required onchange="change(this)">
																			<#if listType??>
																				<#list listType as p>
																					<option value="${p.id}">${p.typeName}</option>
																				</#list>
																			</#if>										
																		</select> <small>类型</small>
																	</div>
																</div>
																
																<div class="am-form-group" id="addnew" style="display:none">
																	<label for="user-email" class="am-u-sm-3 am-form-label">
																	类型名称:</label>
																	<div class="am-u-sm-9">
																		<input type="text" id="name"
																			placeholder="类型" name="messageTypeNew">
																		<small>类型</small>
																	</div>
																</div>
																
																<div class="am-form-group">
																	<label for="user-email" class="am-u-sm-3 am-form-label">
																	资源名称:</label>
																	<div class="am-u-sm-9">
																		<input type="text" id="name" required
																			placeholder="名称" name="messageName">
																		<small>名称</small>
																	</div>
																</div>
																
																<div class="am-form-group">
																	<label for="name" class="am-u-sm-3 am-form-label">
																		资源URL:</label>
																	<div class="am-u-sm-9">
																		<input type="text" id="url" required
																			placeholder="URL" name="messageUrl">
																			<small>URL</small>
																	</div>
																</div>
																
																<div class="am-form-group">
																	<label for="name" class="am-u-sm-3 am-form-label">
																		资源提取码:</label>
																	<div class="am-u-sm-9">
																		<input type="text" id="user-intro" required
																			placeholder="提取码" name="messagePwd">
																			<small>提取码</small>
																	</div>
																</div>
																
																<div class="am-form-group">
																	<label for="name" class="am-u-sm-3 am-form-label">
																		上传者:</label>
																	<div class="am-u-sm-9">
																		<input type="text" id="user-intro" required
																			placeholder="上传者" name="messageAuthor">
																			<small>上传者</small>
																	</div>
																</div>
																<#if sessionToken??>																
																	<input type="hidden" name="token" value="${sessionToken}">
																</#if>
																<div class="am-form-group">
																	<div class="am-u-sm-9 am-u-sm-push-3">
																		<input type="submit" class="am-btn am-btn-success" value="添加资源" />
																	</div>
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
												<!-- content end -->
											</div>
																	<!--添加 end-->
																	<!--end-->
									</ul>
								</div>
							</div>
							<!--tab end-->
			
						</div>

			
			 <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
              <script src="js/plugs/Jqueryplugs.js" type="text/javascript"></script>
              <script src="js/_layout.js"></script>
             <script src="js/plugs/jquery.SuperSlide.source.js"></script>
			<script>
				var num = 1;
				$(function() {

				 $(".tabs").slide({ trigger: "click" });

				});

	
				var btn_save = function() {
					var pid = $("#RawMaterialsTypePageId  option:selected").val();
					var name = $("#RawMaterialsTypeName").val();
					var desc = $("#RawMaterialsTypeDescription").val();
					var ramark = $("#Ramark").val();
					$.ajax({
						type: "post",
						url: "/RawMaterialsType/AddRawMaterialsType",
						data: { name: name, pid: pid, desc: desc, ramark: ramark },
						success: function(data) {
							if(data > 0) {
								$.jq_Alert({
									message: "添加成功",
									btnOktext: "确认",
									dialogModal: true,
									btnOkClick: function() {
										//$("#RawMaterialsTypeName").val("");
										//$("#RawMaterialsTypeDescription").val("");
										//$("#Ramark").val("");                           
										//page1();
										location.reload();

									}
								});
							}
						}
					});
					alert(t);
				}

				var btn_edit = function(id) {
					$.jq_Panel({
						url: "/RawMaterialsType/EditRawMaterialsType?id=" + id,
						title: "编辑分类",
						dialogModal: true,
						iframeWidth: 500,
						iframeHeight: 400
					});
				}
				var btn_delete = function(id) {
					$.jq_Confirm({
						message: "您确定要删除吗?",
						btnOkClick: function() {
							
						}
					});
				}
				function change(obj){
					if(obj.options[obj.selectedIndex].value == "10"){
						document.getElementById("addnew").style.display="";
					}else {
						document.getElementById("addnew").style.display="none";
					}
				}
			</script>
		</div>
	</body>
</html>