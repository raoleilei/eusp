$(function(){
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
	
	if($("#audit").text() == 5 || $("#audit").text() == 6){
		$("#taskReport").show() ;
	}else{
		$("#taskReport").hide() ;
	}
})