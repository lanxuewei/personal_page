<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="function"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<link rel="stylesheet" href="css/article_info_sorts.css" type="text/css">
<body>
	<!-- 作者信息以及文章分类 -->
	<div class="author-info">
		<!-- 根据文章title模糊查找 -->
		<form class="fuzzy_search" action="fuzzy" method="get">
			<input class="search" name="keyword" type="text" >
			<input class="search_btn" type="submit" value="Search">
		</form>
		<!-- 作者信息 -->
		<div class="author">
			<div class="author-main">
				<!-- 背景图 -->
				<div class="author-background">
					<img alt="" src="img/card.jpg">
				</div>
				
				<!-- 头像 -->
				<div class="author-head">
					<img alt="" src="img/head.jpg">
				</div>
				
				<!-- 作者 -->
				<p class="author-name">Lan</p>
				
				<!-- 文章分类 -->
				<div class="article-sorts">
					<h4 class="sorts-title">分类</h4>
	
					<!-- 文章分类 -->
					<ul class="sorts">
						<li><a href="Main?sorts=推荐阅读">推荐阅读&nbsp<sup>${counts[0]}</sup></a></li>
						<li><a href="Main?sorts=心情">心情&nbsp<sup>${counts[1]}</sup></a></li>
						<li><a href="Main?sorts=日记">日记&nbsp<sup>${counts[2]}</sup></a></li>
						<li><a href="Main?sorts=感悟">感悟&nbsp<sup>${counts[3]}</sup></a></li>
						<li><a href="Main?sorts=诗词">诗词&nbsp<sup>${counts[4]}</sup></a></li>
						<li><a href="Main?sorts=Java">Java&nbsp<sup>${counts[5]}</sup></a></li>
						<li><a href="Main?sorts=算法">算法&nbsp<sup>${counts[6]}</sup></a></li>
					</ul>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>