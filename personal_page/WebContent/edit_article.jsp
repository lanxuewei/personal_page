<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>edit_article</title>

<!-- 引入样式 -->
<link rel="stylesheet" href="css/edit_article.css" type="text/css">

</head>
<body>
	<h1>编辑博客</h1>
	<form action="edit_article" method="post" enctype="multipart/form-data">
		
		<!-- 文章标题及分类 -->
		<div class="article-title-sorts">
			<!-- 标题 -->
			<label>输入文章标题：</label>
			<input name="title" type="text" />
			<!-- 选择分类 -->
			<select name="sorts">
				<option value="推荐阅读">推荐阅读</option>
				<option value="心情">心情</option>
				<option value="日记">日记</option>
				<option value="感悟">感悟</option>
				<option value="诗词">诗词</option>
				<option value="Java">Java</option>
				<option value="算法">算法</option>
			</select>
		</div>
		<!-- 添加配图 -->
		<div class="img">
			<label>选择配图</label>
			<input name="img" type="file"/>
		</div>
		
		<!-- 引言 -->
		<div class="introduction">
			<label class="introduction-title">输入文章引言(200字以内)：</label>
			<textarea name="introduction_main_body" type="text"></textarea><br/><br/>
		</div>

		<!-- 加载编译器的容器 -->
		<script id="container" name="main_body" type="text/plain">
			初始化内容
		</script>
		<br/><br/>
		<!-- 提交  -->
		<input type="submit" value="submit"/>
	</form>
	<!-- 配置文件 -->
	<script type="text/javascript" src="utf8-jsp/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="utf8-jsp/ueditor.all.js"></script>
	<script type="text/javascript">
		var editor = UE.getEditor('container');
	</script>
</body>
</html>