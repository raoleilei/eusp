$(function(){
	$("#addBtn").on("click",function(){
		$("#userInfo").modal("toggle") ;	// 显示模态窗口
	}) ;
	$("button[id^=remove-]").each(function(){
		sid = $("#sid").text() ;
		$(this).on("click",function(){
			var eid = "" ;
			temp = this.id.split("-") ;
			for(i = 1 ; i < temp.length ; i++){
				eid += temp[i] + "-" ;
			}
			eid = eid.substring(0,eid.length-1) ;
			$.post("pages/back/admin/schedule/ScheduleBackAction!empDelete.action",{"sid":sid,"eid":eid},function(data){
				if(data == "true"){
					$("#travel-" + eid).remove() ;
					operateAlert(true,"删除调度人员成功！","") ;
				}else{
					operateAlert(false,"","删除调度人员失败！") ;
				}
			},"text") ;
		}) ;
	}) ;
	$("#did").on("change",function(){
		$("#allEmp").empty() ;
		did = $(this).val() ;
		sid = $("#sid").text() ;
		$.post("pages/back/admin/schedule/ScheduleBackAction!getEmpByDid.action",{"did":did,"sid":sid},function(data){
			allEmp = data.allEmp ;
			title = data.title ;
			for(i = 0 ; i < allEmp.length ; i ++){
				tr ="	<tr id='travel-" + allEmp[i].eid + "'> " +
					"		<td class='text-center'> " +
					"			<img src='upload/emp/" + allEmp[i].photo + "' style='width:20px;'/> " + 
					"		</td> " + 
					"		<td class='text-center'>" + allEmp[i].eid + "</td> " + 
					"		<td class='text-center'>" + allEmp[i].ename + "</td> " +
					"		<td class='text-center'>" + title[allEmp[i].eid] + "</td> " +
					"		<td class='text-center'> " +
					"			<button class='btn btn-danger btn-xs' id='addEmp-" + allEmp[i].eid + "'> " +
					"				<span class='glyphicon glyphicon-plus-sign'></span>&nbsp;增加</button> " +
					"		</td> " +
					"	</tr> "  ;
				$("#allEmp").append(tr) ;
				$("#addEmp-" + allEmp[i].eid).on("click",function(){
					allEmp = data.allEmp ;
					title = data.title ;
					dname = data.dname ;
					var eid = "" ;
					temp = this.id.split("-") ;
					for(i = 1 ; i < temp.length ; i++){
						eid += temp[i] + "-" ;
					}
					eid = eid.substring(0,eid.length-1) ;
					$("#travel-" + eid).remove() ;
					
					$.post("pages/back/admin/schedule/ScheduleBackAction!addScheduleEmp.action",{"sid":sid,"eid":eid},function(data){
						if(data == "true"){
							operateAlert(true,"增加调度人员成功！","") ;
						}else{
							operateAlert(false,"","增加调度人员失败！") ;
						}
					},"text") ;
					
					for(j = 0 ; j < allEmp.length ; j ++){
						if(allEmp[j].eid == eid){
							addTr = "<tr id='travel-" + allEmp[j].eid + "'> " + 
									"	<td class='text-center'> " + 
									"		<img src='upload/emp/" + allEmp[j].photo + "' style='width:20px;'/> " + 
									"	</td> " +
									"	<td class='text-center'>" + allEmp[j].eid + "</td> " +
									"	<td class='text-center'>" + allEmp[j].ename + "</td> " + 
									"	<td class='text-center'>" + dname[allEmp[j].eid]+ "</td> " +
									"	<td class='text-center'>" + title[allEmp[j].eid] + "</td> " +
									"	<td class='text-center'>" +
									"		<button id='remove-" + allEmp[j].eid + "' class='btn btn-danger btn-xs'> " +
									"			<span class='glyphicon glyphicon-remove'></span>&nbsp;移除</button> " +
									"	</td> " +
									"</tr> " ;
							$("#schedule-table").append(addTr) ;
							$("#remove-" + allEmp[j].eid).on("click",function(){
								var eid = "" ;
								temp = this.id.split("-") ;
								for(i = 1 ; i < temp.length ; i++){
									eid += temp[i] + "-" ;
								}
								eid = eid.substring(0,eid.length-1) ;
								$.post("pages/back/admin/schedule/ScheduleBackAction!empDelete.action",{"sid":sid,"eid":eid},function(data){
									if(data == "true"){
										$("#travel-" + eid).remove() ;
										operateAlert(true,"删除调度人员成功！","") ;
									}else{
										operateAlert(false,"","删除调度人员失败！") ;
									}
								},"text") ;
							}) ;
						}
					}
				}) ;
			}
		},"json") ;
	}) ;
	
})


