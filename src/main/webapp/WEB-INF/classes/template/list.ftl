<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<#macro showNews cid titleNum>
	<#nested>
	<#--<#local news=newsList[cid]/>-->
	<#list newsPage.result as new>
		<li>
			${(newsPage.currentPageIndex-1)*newsPage.pageSize+new_index+1}
			<span><a href="/html/news/${newsPage.currentPageIndex}/${new.url}">
			<#if (new.title?length>titleNum)>
				${new.title[0..titleNum]}...
			<#else>
				${new.title}
			</#if>
			</a></span>
			<span style="margin-left:50px">
				<#if new.publishDate??>
					${new.publishDate}
					<#else>
					${.now?string("yyyy-MM-dd")}
				</#if>
			</span>
		</li>
	</#list>
</#macro>
<@showNews cid="1" titleNum=70>
	<h1>新闻大发送！</h1>
</@showNews>
<#import "pager.ftl" as my/>
<@my.pager url="#" totalPage=newsPage.totalPageNum curPage=newsPage.currentPageIndex class="pagers" showPageNum=10/>
</body>
</html>