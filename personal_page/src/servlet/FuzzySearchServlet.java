package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ArticleService;
import vo.ArticleVO;

//用于模糊查找
public class FuzzySearchServlet extends HttpServlet{
	
	ArticleService articleService = new ArticleService();
	
	@Override
	public void service(final HttpServletRequest req, final HttpServletResponse res){
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//文章总数
		Integer articleCount = null;
		//获取文章标题关键字
		String keyword = req.getParameter("keyword");
		System.out.println("keyword = " + keyword);

		List<ArticleVO> articleVOList = null;
		if(keyword != ""){
			//调用下层查询
			articleVOList = articleService.fuzzySearch(keyword);
			articleCount = articleVOList.size();
		}else{
			//参数为空不往下执行查询
			articleCount = 0;
		}
		//查询分类对应的文章数
		List<Integer> counts = articleService.ArticleVOSortsCount();
		
		req.setAttribute("articleVOList", articleVOList);	//所有文章
		req.setAttribute("counts", counts);					//各分类文章总数
		req.setAttribute("articleCount", articleCount); 	//文章总数

		try {
			req.getRequestDispatcher("WEB-INF/main_page.jsp").forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
