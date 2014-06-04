	var exportToCSV = function(url){
		art.dialog.confirm("导出可能需要花费您几分钟，是否确定导出？",function(){
			window.location.href = url;
		});
	};
	
	var dialog = function(id,icon,cont){
		art.dialog({
			id:id,
			content:cont,
			icon:icon,
			time:2.5
		});
	};