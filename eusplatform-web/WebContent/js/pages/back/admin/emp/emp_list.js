$(function(){
	$(selall).on("click",function(){
		flag = this.checked ;
		$("input[id^=eid-]").each(function(){
			$(this).prop("checked",flag) ;
		}) ;
	}) ;
})