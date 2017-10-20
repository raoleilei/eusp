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
			"eid" : {
				required : true
				//remote : {
//				url : "check.jsp", // 后台处理程序
//				type : "post", // 数据发送方式
//				dataType : "html", // 接受数据格式
//				data : { // 要传递的数据
//					code : function() {
//						return $("#code").val();
//					}
//				},
//				dataFilter : function(data, type) {
//					if (data.trim() == "true")
//						return true;
//					else
//						return false;
//				}
//}
			} ,
			"password" : {
				required : true
			},
			"ename" : {
				required : true
			} ,
			"phone" : {
				required : true 
			},
			"lid" : {
				required : true ,
			},
			"did" : {
				required : true 
			},
			"salary" : {
				required : true ,
				number : true ,
				remote : {
					url : "pages/back/admin/emp/LevelBackAction!validateSalary.action", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "text", // 接受数据格式
					data : { // 要传递的数据
						"lid":function() {
				            return $("#lid").val();
				        },
						"sal":function() {
				            return $("#salary").val();
				        }
					},
					dataFilter : function(data, type) {
						if (data.trim() == "true")
							return true;
						else
							return false;
					}
				} 				
			},
			"pic" : {
				accept : ["jpg","png","gif","bmp"]
			},
			"note" : {
				required : true
			}
		}
	});
	
	$("#lid").on("change",function(){
		lid = $("#lid").val() ;
		if(lid != ""){
			$.post("pages/back/admin/emp/LevelBackAction!get.action" ,{"lid":lid},function(data){
				level = data.level ;
				$("#salary").val("") ;
				$("#salary").attr("placeholder","工资范围在" + level.losal + "到" + level.hisal + "之间") ;
			},"json") ;
		}else{
			$("#salary").val("") ;
			$("#salary").attr("placeholder","请输入雇员基本工资") ;
		}
	})
	
})