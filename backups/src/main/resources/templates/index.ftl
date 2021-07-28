<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>资源详情</title>
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
			<h1>ITDatabase资源一览表</h1>
			<a href="add">添加资源(共享平台接口)</a>
		</center>
		<div class="dvcontent">

			<div>
				<!--tab start-->
				<div class="tabs" style="margin: 30px;">
					<div class="hd">
						<ul>
							<li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;">资源列表记录</li>
						</ul>
					</div>
					<div class="bd">
						<ul style="display: block;padding: 20px;">
							<li>
								<!--分页显示角色信息 start-->
								<div id="dv1">
									<table class="table" id="tbRecord">
										<thead>
											<tr>
												<th>编号</th>
												<th>资源名称</th>
												<th>资源类型</th>
												<th>上传者</th>
												<th>上架时间</th>
												<th>更新时间</th>
												<th>下载资源 </th>
												<th>删除资源 </th>
												<th>修改资源 </th>
											</tr>
										</thead>
										<tbody>
											<#if listResource??>
												<#list listResource as p>
													<tr style="font-size: 18px">
														<td>${p_index+1}</td>
														<td>${p.messageName}</td>
														<td>${p.typeName}</td>
														<td>${p.messageAuthor}</td>
														<td>${p.created}</td>
														<td>${p.updated}</td>
														<td><a href="${p.messageUrl}"
															style='text-decoration: none;'>提取码 ${p.messagePwd}</a></td>
														<td>
															<a href="deleteMessage?url=${p.messageUrl}" class="am-btn am-btn-success">刪除资源</a>
														</td>
														<td>
															<a href="updateMessage?url=${p.messageUrl}" class="am-btn am-btn-success">修改资源</a>
														</td>													
													</tr>
												</#list>
											</#if>																															
										</tbody>
										<#if error??>
												<div>
													<span id="message_LOGIN_TOO_MUCH" style="color: red">
														${error}</span>
												</div>
										</#if>

									</table>
									<center>
										<div>
													<span >
														<a href="splitPage?pageNo=1">首页</a>
													</span>
													<#if pageNumList??>
														<#list pageNumList as i>
															<span>
																<a href="splitPage?pageNo=${i}">${i}</td>
															</span>
														</#list>
													</#if>							
													<span >
														<a href="splitPage?pageNo=${pageInfo.pages}">末页</a>
													</span>
									    </div>
									 </center>
								</div>
								<!--分页显示角色信息 end-->
							</li>
						</ul>
						
						<ul class="theme-popbod dform" style="display: none;">
								<div class="am-cf admin-main" style="padding-top: 0px;">
						<!-- content start -->
			
						<div class="am-cf admin-main" style="padding-top: 0px;">
							<!-- content start -->
							
							<!-- content end -->
						</div>
							<!--添加 end-->
							<!--end-->
						</ul>
					</div>
				</div>
				<!--tab end-->

			</div>

		</div>
	</body>

</html>