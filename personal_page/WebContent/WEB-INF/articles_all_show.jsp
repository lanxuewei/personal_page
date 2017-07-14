<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- 导入标签库，指定mytag前缀的标签 -->
<%@ taglib uri="lanxuewei" prefix="mytag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/articles_all_show.css" type="text/css">

<body>
	<!-- 显示文章概要 -->
	<mytag:articleVO item="item" articleVOList="${requestScope.articleVOList}">
		<div class="articles">
			<div class="articles-border">
				<!-- 输出article的title、introduction、date、sorts属性 -->
				<!-- 文章标题 -->
				<a class="articles-title"href="simple-article?id=${pageScope.item.id}">${pageScope.item.title}</a>
				<!-- 作者、发表时间 -->
				<p class="articles-author">作者 Lan &nbsp ${pageScope.item.date}</p>
				<c:if test="${pageScope.item.img != null}">
					<!-- 配图 -->
					<div class="article-img">
						<img alt="" src="${pageContext.request.contextPath}/img/${pageScope.item.img}">
					</div>					
				</c:if>	
				
				<!-- 文章引言 -->
				<p class="articles-introduction"><a class="articles-introductions"href="simple-article?id=${pageScope.item.id}">${pageScope.item.introduction}</a></p>		
				<p class="articles-sort">分类：${pageScope.item.sorts}</p>
			</div>
		</div>
	</mytag:articleVO>
</body>