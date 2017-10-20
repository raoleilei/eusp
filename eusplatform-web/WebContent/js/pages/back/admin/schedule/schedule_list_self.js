$(function(){
	$("span[id^=sp]").each(function(){
		temp = this.id.split("-") ;
		auditUtil(parseInt(temp[1]),parseInt(temp[2])) ;
	}) ;
})