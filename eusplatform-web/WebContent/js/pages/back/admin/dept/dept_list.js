$(function(){
	$("button[id^=edit-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-")[1] ;
			dname = $("#dname-" + did).val() ;
			maxnum = parseInt($("#maxnum-" + did).val()) ;
			var currnum = 0 ;
			if($("#currnum-" + did).text() != null){
				currnum = parseInt($("#currnum-" + did).text()) ;
			}
			if (dname == "") { 
				operateAlert(false,"","部门名称不允许为空，请确认后再提交更新！") ;
			} else if(maxnum < currnum){
				operateAlert(false,"","部门最大人数不能小于当前人数，请确认后再提交更新！") ;
			}else{
				$.post("pages/back/admin/dept/DeptBackAction!edit.action",{"did":did,"dname":dname,"maxnum":maxnum},function(data){
					if(data == "true"){
						operateAlert(true,"部门信息更新成功！","") ;
					}else{
						operateAlert(false,"","部门信息更新失败！") ;
					}
				},"text")
				
			}
		}) ;
	}) ;
	$("span[id^=eid-]").each(function(){
		$(this).on("click",function(){
			var eid = "" ;
			temp = this.id.split("-") ;
			for(i = 1 ; i < temp.length ; i++){
				eid += temp[i] + "-" ;
			}
			eid = eid.substring(0,eid.length-1) ;
			$.post("pages/back/admin/emp/EmpBackAction!getDnameAndTitle.action",{"eid":eid},function(data){
				if(data.loginMemberDid == 2){
					$("#salary").text(data.emp.salary) ;
				}else{
					$("#salary").text("******") ;
				}
				$("#photo").attr("src","upload/emp/" + data.emp.photo) ;
				$("#dname").text(data.dname) ;
				$("#ename").text(data.emp.ename) ;
				$("#title").text(data.title) ;
				$("#phone").text(data.emp.phone) ;
				$("#hiredate").text(data.emp.hiredate) ;
				$("#note").text(data.emp.note) ;
				$("#userInfo").modal("toggle") ;
			},"json") ;
		}) ;
	}) ;
}) ;