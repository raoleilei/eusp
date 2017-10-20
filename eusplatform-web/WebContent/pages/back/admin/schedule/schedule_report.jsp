<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/audit/audit_schedule_apply.js"></script>
<body class="hold-transition skin-blue sidebar-mini"> 
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="4"/>
			<jsp:param name="msi" value="41"/>
		</jsp:include>
		<div class="content-wrapper text-left">
					<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-check"></span>&nbsp;查看任务报告</strong>
				</div>
				<div class="panel-body">
					<div>
						<table class="table table-striped table-bordered table-hover">
							<tr> 
								<td style="width:150px;"><strong>申请标题：</strong></td>
								<td>${schedule.title }</td>
							</tr>	
							<tr>
								<td><strong>任务类型：</strong></td>
								<td>${itemTitle }</td>
							</tr>
							<tr>
								<td><strong>总人数：</strong></td>
								<td>${schedule.ecount }人</td>
							</tr>
							<tr>
								<td><strong>任务日期：</strong></td>
								<td>${schedule.sdate }</td>
							</tr>
							<tr>
								<td><strong>任务描述：</strong></td>
								<td>${schedule.note }</td>
							</tr>
							<tr>
								<td><strong>任务组织者报告：</strong></td>
								<td>${seidReport }</td>
							</tr>
						</table>
					</div>
					<div class="panel-group" id="news">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title"> 
									<a data-toggle="collapse" data-parent="news" href="#contentOne">
										<strong><span class="glyphicon glyphicon-user"></span>&nbsp;任务报告（总人数：${schedule.ecount }人、已提交报告：${count }人）</strong>
									</a>
								</h4>
							</div>
							<div id="contentOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<table class="table table-condensed">
										<thead>
											<tr>
												<th style="width:5%;" class="text-center"><strong>照片</strong></th>
												<th style="width:10%;" class="text-center"><strong>姓名</strong></th>
												<th style="width:10%;" class="text-center"><strong>电话</strong></th>
												<th style="width:10%;" class="text-center"><strong>级别</strong></th>
												<th style="width:10%;" class="text-center"><strong>部门</strong></th>
												<th style="width:10%;" class="text-center"><strong>报告日期</strong></th>
												<th style="width:45%;" class="text-center"><strong>内容</strong></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${allEmp }" var="emp">
												<tr id="travel-${emp.eid }">
													<td class="text-center">
														<img src="upload/emp/${emp.photo }" style="width:20px;"/> 
													</td>
													<td class="text-center"><span id="eid-${emp.eid }" style="cursor:pointer;">${emp.ename }</span></td>
													<td class="text-center">${emp.phone }</td>
													<td class="text-center">${levelTitle[emp.eid] }</td>
													<td class="text-center">${dname[emp.eid] }</td>
													<td class="text-center">${empReport[emp.eid].subdate }</td>
													<td class="text-center">${empReport[emp.eid].note }</td>
												</tr> 
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer" style="height:80px;">
					<jsp:include page="/pages/plugins/include_alert.jsp"/>
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/back/info/emp_info_modal.jsp"/>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
