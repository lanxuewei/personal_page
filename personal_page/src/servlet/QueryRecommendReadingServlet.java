/*package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ArticleService;
import vo.ArticleVO;

public class QueryRecommendReadingServlet extends HttpServlet{
	
	public ArticleService articleService = new ArticleService();
	
	@Override
	public void service(final HttpServletRequest req, final HttpServletResponse res){
		// TODO Auto-generated method stub
		List<ArticleVO> articleVOList = articleService.QueryRecommendReading();
		//文章总数
		Integer countArticle = articleVOList.size();
		//栏目标题
		String title = "推荐阅读";
		//测试
		System.out.println("QueryRecommendReadingServlet:articleVOList = " + articleVOList);
		//放入request
		req.setAttribute("articleVOList", articleVOList);
		req.setAttribute("countArticle", countArticle);
		req.setAttribute("title", title);
		try {
			req.getRequestDispatcher("WEB-INF/main_page.jsp").forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
*/