<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="function"%>
<!-- 导入标签库，指定mytag前缀的标签 -->
<%@ taglib uri="lanxuewei" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main</title>

<!-- 引入css样式 -->
<link rel="stylesheet" href="css/main_page.css" type="text/css">
</head>
<body>
	
	<!-- main-header(第一块面板) -->
	<!-- 多个页面第一块相同 -->
	<jsp:include page="head_content.jsp"></jsp:include>
	
	<!-- content-warp(第二块面板) -->
	<div class="content-warp">
		<!-- 主体部分 -->
		<div class="main-body">
			<!-- 文章主体部分 -->
			<div class="articles-body">
				<!-- 推荐阅读 -->
				<c:if test="${sorts == '推荐阅读'}" var="recommend_reading">
					<div class="suggested-reading">
						<h2>推荐阅读</h2>
						<h5>共 ${articleCount} 篇文章</h5>
					</div>
				</c:if>
				<!-- 所有文章 -->
				<c:if test="${!recommend_reading}">
					<div class="suggested-reading">
						<h2>所有文章</h2>
						<h5>共 ${articleCount} 篇文章</h5>
					</div>
				</c:if>
				
				<!-- 显示文章概要 -->
				<jsp:include page="articles_all_show.jsp"></jsp:include>
				
				<!-- 分页导航 -->
					<div class="paging">
						<c:if test="${currentPage != null}">
						<a href="Main?sorts=${requestScope.sorts}&pageNum=1">首页</a> 
						<a href="Main?sorts=${requestScope.sorts}&pageNum=${(requestScope.currentPage == 1)?1:(requestScope.currentPage-1)}">上一页</a> 
						<a href="Main?sorts=${requestScope.sorts}&pageNum=${(requestScope.currentPage == requestScope.pageCount)?requestScope.pageCount:(requestScope.currentPage+1)}">下一页</a>
						<a href="Main?sorts=${requestScope.sorts}&pageNum=${requestScope.pageCount}">尾页</a>
						</c:if>
					</div>
			</div>
			<!-- 作者信息以及文章分类 -->
			<jsp:include page="author_info_sorts.jsp"></jsp:include>
		</div>
	</div>
	
		
	<!-- 引入main-footer(第三块面板) -->
	<jsp:include page="main_footer.jsp"></jsp:include>
</body>
</html>