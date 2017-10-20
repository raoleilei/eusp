<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<%!
	public static final String SCHEDULE_REPORT_URL = "pages/back/admin/center/emp_schedule_report.jsp" ;
	public static final String SCHEDULE_SHOW_URL = "pages/back/admin/schedule/ScheduleBackAction!scheduleShow.action" ;
%>
<script type="text/javascript" src="js/pages/back/admin/schedule/schedule_list_self.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="5"/>
			<jsp:param name="msi" value="52"/>
		</jsp:include>
		<div class="content-wrapper text-left">
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-list"></span>&nbsp;个人待处理任务清单</strong>
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
								<th class="text-center"><strong>任务时间</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allSchedule }" var="schedule">
								<tr id="travel-${schedule.sid }">
									<td class="text-center"><span id="sp-${schedule.audit}-${schedule.sid}" class="text-warning"><span class="glyphicon glyphicon-flag"></span>&nbsp;进行中</span></td>
									<td class="text-center">
										<a id="showBtn-${schedule.sid }" onmouseover="this.style.cursor='hand'" href="<%=SCHEDULE_SHOW_URL%>?sid=${schedule.sid}&audit=${schedule.audit}">${schedule.title }</a>
									</td>
									<td class="text-center">${schedule.sdate }</td>
									<c:if test="${flag[schedule.sid] == 0 }">
										<td class="text-center">
											<a type="button" class="btn btn-primary btn-xs" href="<%=SCHEDULE_REPORT_URL%>?sid=${schedule.sid}">
												<span class="fa fa-pencil-square-o"></span>&nbsp;任务报告</a>
										</td>
									</c:if>
									<c:if test="${flag[schedule.sid] == 1 }">
										<td class="text-center">
											<a type="button" class="btn btn-primary btn-xs" href="<%=SCHEDULE_SHOW_URL%>?sid=${schedule.sid}&audit=${schedule.audit}">
												<span class="fa fa-pencil-square-o"></span>&nbsp;查看详情</a>
										</td>
									</c:if>
								</tr> 
							</c:forEach> 
						</tbody>
					</table>
					<div id="splitBarDiv" style="float:right">
						<jsp:include page="/pages/plugins/split_plugin_page_bar.jsp"/> 
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
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
