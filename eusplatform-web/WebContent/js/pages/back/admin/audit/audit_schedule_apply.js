$(function(){
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"flag" : {
				required : true,
			},
			"aNote" : {
				required : true
			}
		}
	});

	if($("#audit").text() != 1){
		$("#auditModel").hide() ;
	}else{
		$("#auditModel").show() ;
	}
	
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
	
})