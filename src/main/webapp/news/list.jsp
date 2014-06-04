<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/commons/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }resources/js/jquery-1.8.3.js"></script>
<title>新闻列表</title>
<script type="text/javascript">
	function publishNews(){
		var str="";
		var href="${ctx }news_batchPublish.do";
		$("input[type='checkbox'][name='selectOne']:checked").each(function(){
			str+=$(this).val()+",";
		});
		if(str.length>0){
			str = str.substring(0,str.length-1);
		}
		$.post(href,{'ids':str},function(data){
			if(data==1){
				alert("生成静态页面成功！");
			}else{
				alert("生成静态页面失败！");
			}
		});
	}
	function publishAll(){
		var href="${ctx }news_publishAll.do";
		$.get(href,function(data){
			if(data==1){
				alert("生成静态页面成功！");
			}else{
				alert("生成静态页面失败！");
			}
		});
	}
	
	function publish(obj){
		$.get("${ctx }news_publish.do?id="+obj,function(data){
			if(data==1){
				alert("生成静态页面成功！");
			}else{
				alert("生成静态页面失败！");
			}
		});
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td width="10px"><input type="checkbox" name="selectAll" onclick="selectAll(this);"/></td>
			<td width="45px">序号</td>
			<td width="500px">标题</td>
			<td width="100px">发布日期</td>
			<td>操作</td>
		</tr>
			<c:forEach items="${page.result }" var="model" varStatus="status">
		<tr>
				<td><input type="checkbox" name="selectOne" value="${model.id }" id="n_${model.id }"/></td>
				<td>${(pageNo-1)*pageSize+status.index+1 }</td>
				<td>${model.title }</td>
				<td>${model.publishDate }</td>
				<td>
					<a href="${ctx }news_preview.do?id=${model.id}">预览</a>&nbsp;&nbsp;
					<a href="${ctx }news_delete.do?id=${model.id}">删除</a>&nbsp;&nbsp;
					<a href="javascript:void(0);" onclick="publish(${model.id})">发布</a>&nbsp;&nbsp;
				</td>
		</tr>
			</c:forEach>
		<tr>
			<td colspan="3">
				<a href="javascript:void(0);" onclick="publishNews();" >批量发布</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="publishAll();">全部发布</a>&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<c:import url="/commons/page.jsp">
                			<c:param name="url" value="news_list.do"/>
                		</c:import>
                		</td>
		</tr>
	</table>
	<script type="text/javascript">
		function selectAll(obj){
			if($(obj).attr("checked")){
				$("input[type='checkbox'][name='selectOne']").attr("checked",true);
			}else{
				$("input[type='checkbox'][name='selectOne']").attr("checked",false);
			}
		}
	</script>
</body>
</html>