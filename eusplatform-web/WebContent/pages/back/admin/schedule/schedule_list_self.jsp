<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<%!
	public static final String SCHEDULE_EDIT_URL = "pages/back/admin/schedule/ScheduleBackAction!editPre.action" ;
	public static final String SCHEDULE_DELETE_URL = "pages/back/admin/schedule/ScheduleBackAction!delete.action" ;
	public static final String SCHEDULE_EMP_URL = "pages/back/admin/schedule/ScheduleBackAction!scheduleEmpEdit.action" ;
	public static final String SCHEDULE_SUBMIT_URL = "pages/back/admin/schedule/ScheduleBackAction!submitRequest.action" ;
	public static final String SCHEDULE_SHOW_URL = "pages/back/admin/schedule/ScheduleBackAction!scheduleShow.action" ;
	public static final String AUDIT_SCHEDULE_APPLY = "pages/back/admin/schedule/ScheduleBackAction!auditScheduleApply.action" ;
%>
<script type="text/javascript" src="js/pages/back/admin/schedule/schedule_list_self.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="3"/>
			<jsp:param name="msi" value="32"/>
		</jsp:include>
		<div class="content-wrapper text-left">
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-list"></span>&nbsp;资源协调申请信息列表</strong>
				</div>
				<div class="panel-body">
					<div>
						<jsp:include page="/pages/plugins/split_plugin_search_bar.jsp"/>
					</div>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center"><strong>状态</strong></th>
								<th class="text-center"><strong>申请标题</strong></th>
								<th class="text-center"><strong>申请时间</strong></th>
								<th class="text-center"><strong>任务时间</strong></th>
								<th class="text-center"><strong>调度员工数</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allSchedule }" var="schedule">
								<tr id="travel-${schedule.sid}">
									<td class="text-center"><span id="sp-${schedule.audit}-${schedule.sid}" class="text-warning"><span class="glyphicon glyphicon-flag"></span>&nbsp;进行中</span></td>
									<td class="text-center">
										<a id="showBtn-${schedule.sid}" onmouseover="this.style.cursor='hand'" href="<%=SCHEDULE_SHOW_URL%>?sid=${schedule.sid}&audit=${schedule.audit}">${schedule.title}</a>
									</td>
									<td class="text-center">${schedule.subdate}</td>
									<td class="text-center">${schedule.sdate}</td>
									<td class="text-center">${schedule.ecount}人</td>
									<td class="text-center">
										<c:if test="${schedule.audit == 0 }">
											<a type="button" class="btn btn-primary btn-xs" href="<%=SCHEDULE_SUBMIT_URL%>?sid=${schedule.sid}">
												<span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;提交申请</a>
										</c:if>
										<c:if test="${schedule.audit == 2 }">
											<a type="button" class="btn btn-primary btn-xs" href="<%=AUDIT_SCHEDULE_APPLY%>?sid=${schedule.sid}&audit=${schedule.audit}">
												<span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;查看详情</a>
										</c:if>
										<c:if test="${schedule.audit != 0 && schedule.audit != 2 }">
											<a type="button" class="btn btn-primary btn-xs" href="<%=SCHEDULE_SHOW_URL%>?sid=${schedule.sid}&audit=${schedule.audit}">
												<span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;查看详情</a>
										</c:if>
										<c:if test="${schedule.audit == 0 || schedule.audit == 1 }">
											<a type="button" class="btn btn-warning btn-xs" href="<%=SCHEDULE_EMP_URL%>?sid=${schedule.sid}">
												<span class="glyphicon glyphicon-user"></span>&nbsp;人员安排</a>
											<a type="button" class="btn btn-info btn-xs" href="<%=SCHEDULE_EDIT_URL%>?sid=${schedule.sid}">
												<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</a>
											<a type="button" class="btn btn-danger btn-xs" href="<%=SCHEDULE_DELETE_URL%>?sid=${schedule.sid}">
												<span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div id="splitBarDiv" style="float:right">
						<jsp:include page="/pages/plugins/split_plugin_page_bar.jsp"/> 
					</div>
				</div>
				<div class="panel-footer">
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
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
