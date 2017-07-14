package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ArticleService;
import vo.ArticleVO;

//展开全文
public class SimpleArticleServlet extends HttpServlet{
	
	private ArticleService articleService = new ArticleService();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void service(final HttpServletRequest req, final HttpServletResponse res){
		
		//设置编码格式utf-8
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取article的id
		int id = Integer.valueOf(req.getParameter("id"));
		System.out.println("SimpleArticle:id = " + id);
		//id作为查询条件,查出当前文章以及上下篇文章
		Map<String, ArticleVO> articleVOs = articleService.simpleArticlePreAndNext(id);
		System.out.println("SimpleArticleServlet:articleVOs: " + articleVOs.get("currentArticleVO"));
/*		同时查出该文章的上下篇id,title,introduction,date,sorts
		String preTitle = null;
		String nextTitle = null;
		//上一篇
		ArticleVO articleVOPre = articleService.simpleArticlePreOrNext(articleVO.getId(), 
				articleVO.getSorts(), -1);
		//测试
		System.out.println("55555articleVOPre: " + articleVOPre);
		//如果上一篇为空
		if(articleVOPre.getTitle()==null){
			preTitle = "已经是第一篇了";
			//将当前文章的id,sorts拷贝给上一篇
			articleVOPre.setId(articleVO.getId());
			articleVOPre.setSorts(articleVO.getSorts());
		}else{
			//不为空
			preTitle = articleVOPre.getTitle();
		}
		//下一篇
		ArticleVO articleVONext = articleService.simpleArticlePreOrNext(articleVO.getId(), 
				articleVO.getSorts(), 1);
		//如果下一篇为空
		if(articleVONext.getTitle()==null){
			nextTitle = "已经是最后一篇了";
			//将当前文章的id,sorts拷贝给下一篇
			articleVONext.setId(articleVO.getId());
			articleVONext.setSorts(articleVO.getSorts());
		}else {
			nextTitle = articleVONext.getTitle();
		}*/
		//计算各类文章总数
		List<Integer> counts = new ArrayList<Integer>();
		counts = articleService.ArticleVOSortsCount();
		
		//将articleVO、articleVOPre和articleVONext放入request
/*		req.setAttribute("articleVO", articleVO);
		req.setAttribute("articleVOPre", articleVOPre);
		req.setAttribute("preTitle", preTitle);
		req.setAttribute("articleVONext", articleVONext);
		req.setAttribute("nextTitle", nextTitle);*/
		req.setAttribute("articleVOs", articleVOs);
		req.setAttribute("counts", counts);
		
		try {
			req.getRequestDispatcher("WEB-INF/simple_article.jsp").forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
