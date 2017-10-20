$(function(){
	$("span[id^=sp]").each(function(){
		audit = parseInt(this.id.split("-")[1]) ;
		sid = parseInt(this.id.split("-")[2]) ;
		auditUtil(audit,sid) ;
	}) ;
}) ;