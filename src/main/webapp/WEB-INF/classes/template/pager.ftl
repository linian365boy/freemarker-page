<#macro pager url totalPage curPage=1 class="" showPageNum=10>
	<#local halfPage=(showPageNum/2)?int/>
	<#if (halfPage>=curPage)>
		<@showPage start=1 end=curPage curPage=curPage url=url class=class/>
		<#if (curPage+halfPage>totalPage)>
			<#local endPage=totalPage>
		<#else>
			<#local endPage=curPage+halfPage>
		</#if>
		<#if ((curPage+1)<=endPage)>
			<@showPage start=curPage+1 end=endPage curPage=curPage url=url class=class/>
		</#if>
	<#else>
		<@showPage start=curPage-halfPage end=curPage curPage=curPage url=url class=class/>
		<#if (curPage+halfPage>totalPage)>
			<#local endPage=totalPage>
		<#else>
			<#local endPage=curPage+halfPage>
		</#if>
		<#if ((curPage+1)<=endPage)>
			<@showPage start=curPage+1 end=endPage curPage=curPage url=url class=class/>
		</#if>
	</#if>
</#macro>

<#macro showPage start end curPage url class>
	<#list start..end as page>
		<#if curPage==page>
			[${curPage}]
		<#else>
			<a href="/html/news/${page}.html" class="${class}">${page}</a>
		</#if>
	</#list>
</#macro>