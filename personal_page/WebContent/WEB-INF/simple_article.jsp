<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${requestScope.articleVO.title}</title>

<!-- 引入css样式 -->
<link rel="stylesheet" href="css/simple_article.css" type="text/css"> 
<link rel="stylesheet" href="css/main_page.css" type="text/css">

</head>
<body>
	
	<!-- main-header(第一块面板) -->
	<!-- 多个页面第一块相同 -->
	<jsp:include page="head_content.jsp"></jsp:include>
	
	<!-- 第二块面板 -->
	<div class="content-warp">
		<!-- 主要内容 -->
		<div class="main-body">
			<!-- 文章正文部分 -->
			<div class="articles-body">
				<div class="article-body">
					<!-- 修饰条 -->
					<div class="article-modified-bar">
					</div>
					<!-- 文章标题 -->
					<h2 class="article-title">${requestScope.articleVOs["currentArticleVO"].title}</h2>
					<!-- 发表时间 -->
					<p class="article-date">发表时间 ${requestScope.articleVOs["currentArticleVO"].date}</p>
					<!-- 文章内容 -->
					<div class="article-mainBody">
						<!-- 显示正文 -->
						<div class="article-mainBody-show">
							${requestScope.articleVOs["currentArticleVO"].mainBody}
						</div>						
						<!-- 该文章的上下篇 -->
						<div class="article_pre_next">
							<!-- 上一篇 -->
							<a class="pre" href="simple-article?sorts=${requestScope.articleVOs['preArticleVO'].sorts}&id=${requestScope.articleVOs['preArticleVO'].id}">上一篇: ${requestScope.articleVOs['preArticleVO'].title}</a>
							
							<!-- 隔开 -->
							<div class="useless_block">
							</div>
							<!-- 下一篇 -->
							<a class="next" href="simple-article?sorts=${requestScope.articleVOs['nextArticleVO'].sorts}&id=${requestScope.articleVOs['nextArticleVO'].id}">下一篇: ${requestScope.articleVOs['nextArticleVO'].title}</a>							
							<!-- 文章分类 -->
							<p class="article-sorts">分类：${requestScope.articleVOs["currentArticleVO"].sorts}</p>
						</div>
					</div>
					<p class="illustrate">*如无特殊说明，文章均为原创</p>
				</div>
			</div>
			<!-- 作者信息以及文章分类 -->
			<jsp:include page="author_info_sorts.jsp"></jsp:include>				
		</div>
	</div>
	
	<!-- 第三块面板 -->
	<jsp:include page="main_footer.jsp"></jsp:include>
	

</body>
</html>