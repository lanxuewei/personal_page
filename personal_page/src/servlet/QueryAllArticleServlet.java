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

//目前废弃未用(用于查询所有文章(一个sorts作为查询条件，如果all,即查询所有除推荐阅读外的文章))

//queryAllArticle servlet展示所有article
public class QueryAllArticleServlet extends HttpServlet {
	
	private ArticleService articleService = new ArticleService();
	
	@Override
	public void service(final HttpServletRequest req, final HttpServletResponse res){
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sorts = req.getParameter("sorts"); 
		//用于标识是否为主页
		//Integer tag = 0;
		if(sorts == null){
			//tag = 1;
			sorts = "推荐阅读";
		}
		System.out.println("QueryAllArticleServlet:sorts = " + sorts);
		
		//pageNum(当前页数),pageSize(页数大小),pageCount(总页数)
		Integer pageNum;
		Integer pageCount;
		Integer pageSize = 7;
		Integer articleCount = null;
		//首次访问主页，默认为第一页
		if(req.getParameter("pageNum") == null) {
			pageNum = 1;
		} else {
			//否则获取get请求参数
			pageNum = Integer.valueOf(req.getParameter("pageNum"));
		}
		//测试pageNum和pageSize
		System.out.println("pageNum = " + pageNum + ", pageSize = " + pageSize);
		
		//查询条件为pageNum、pageSize、sorts
		List<ArticleVO> articleVOList = articleService.queryAllArticle(pageNum, pageSize, sorts);
		//查询分类对应的文章数
		List<Integer> counts = articleService.ArticleVOSortsCount();
		//获取总条数
		articleCount = articleService.coutPage(sorts);
		
		//计算尾页
		pageCount = (pageSize + articleCount - 1)/pageSize;
		System.out.println(pageCount);
		System.out.println("QueryAllArticle: " + articleVOList);
		System.out.println("QueryAllArticle: pageCount = " + pageCount);
		
		//将articleVOList、currentPage、pageCount、pageSize、title、sorts、articlesCount放入request中
		req.setAttribute("articleVOList", articleVOList);	//文章
		req.setAttribute("currentPage", pageNum);			//当前页
		req.setAttribute("pageCount", pageCount);			//总页数
		req.setAttribute("counts", counts);					//各分类文章数
		req.setAttribute("sorts", sorts); 					//分类
		req.setAttribute("articleCount", articleCount);   	//文章总数
//		req.setAttribute("tag", tag);						//主页标识
//		req.setAttribute("pageSize", pageSize);				//页数大小
		
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
