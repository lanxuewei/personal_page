<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>about</title>

<!-- 引入css样式 -->
<link rel="stylesheet" href="css/about.css" type="text/css">
<link rel="stylesheet" href="css/main_page.css" type="text/css">

</head>
<body>

	<!-- main-header(第一块面板) -->
	<!-- 多个页面第一块相同 -->
	<jsp:include page="WEB-INF/head_content.jsp"></jsp:include>

	<!-- content-warp(第二块) -->
	<div class="about-content-warp">
		<!-- 整体 -->
		<div class="about-main">
			<!-- 修饰条 -->
			<div class="about-modified-bar">
				<!-- 无内容 -->
			</div>
			<!-- 标题 -->
			<div class="about-title">
				<h2>简介 | About</h2> 
			</div>
			<!-- 主体部分 -->
			<div class="about-main-body">
				<!-- 修饰图片以及边框 -->
				<div class="about-picture-border">
					<div class="about-picture">
						<!-- 山水图 -->
						<img src="img/35741744_31.jpg">
					</div>
				</div>
				<!-- 个人介绍以及其他 -->
				<div class="about-myself">
					<h2>个人介绍</h2>
					<p>本人男，爱好女。除此之外，诸多爱好。虽文不能治国，武不能安邦，但确实是好人一枚。<br>喜欢文学，唐诗宋词略懂皮毛。笔耕不辍，记些鸡毛蒜皮，市井小事。</p>
					<p>—Where is haha point?</p>
				</div>
				<!-- 修饰图片以及边框 -->
				<div class="about-picture-border">
					<div class="about-picture">
						<!-- 泛舟图 -->
						<img src="img/123.png">
					</div>
				</div>
				<!--  -->
				<div class="about-myself">
					<p>渔舟唱晚，家里的美景总是那么醉人心。<br>如果可以的话，我想就这样牵着你的手，回到家乡，一起去泛舟江上，当夜色降临，拉着你可以随着鸟儿的歌声一起附唱着，踩着余晖，归家温粥，与你共黄昏。</p>
				</div>
				<!-- 联系方式 -->
				<div class="about-myself">
					<h2>联系方式</h2>
					<p>E-mail:547426980@qq.com</p>
				</div>
			</div>
		</div>
	</div>

	<!-- 引入main-footer(第三块) -->
	<jsp:include page="WEB-INF/main_footer.jsp"></jsp:include>
	
</body>
</html>