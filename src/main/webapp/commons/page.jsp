<%@page import="java.net.URLEncoder"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/include.jsp" %>
				<c:if test="${not empty page}">
				共有${page.totalRowNum}条记录，当前是${page.currentPageIndex}/${page.totalPageNum }页
				&nbsp;&nbsp;
					<c:if test="${page.hasFirst}">
							<a href="${ctx}${param.url}?pageNo=1"> 首页 </a>
					</c:if>
					<c:if test="${page.hasPrev}">
						<a href="${ctx}${param.url}?pageNo=${page.currentPageIndex - 1}"> 上一页
						</a>
					</c:if>
					<c:forEach begin="${page.startPageIndex}" end="${page.endPageIndex}"
						var="i">
						<c:choose>
							<c:when test="${i==page.currentPageIndex}">
								<a class="number current" title="${i }">${i }</a>
		 					</c:when>
							<c:otherwise>
								<a class="number" title="${i }" href="${ctx}${param.url}?pageNo=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page.hasNext}">
						<a href="${ctx}${param.url}?pageNo=${page.currentPageIndex+1 }">
							下一页 </a>
					</c:if>
					<c:if test="${page.hasLast}">
						<a href="${ctx}${param.url}?pageNo=${page.totalPageNum}"> 尾页 </a>
					</c:if>
				</c:if>
