package userdefinedtag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import vo.ArticleVO;

public class ArticleVOTag extends SimpleTagSupport{
	//标签属性，用于指定被迭代的集合
	private List<ArticleVO> articleVOList ;
	//标签属性，用于迭代集合元素，为集合元素指定名称
	private String item;
	
	//属性setter和getter()方法
	public List<ArticleVO> getArticleVOList() {
		return articleVOList;
	}
	public void setArticleVOList(List<ArticleVO> articleVOList) {
		this.articleVOList = articleVOList;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	//标签处理方法
	@Override
	public void doTag() {
		//从request中获取articleVOList集合
		/*articleVOList = (List<ArticleVO>)getJspContext()
				.getAttribute("articleVOList", PageContext.REQUEST_SCOPE);*/
		if(articleVOList==null){
			return ;
		}
		//测试
		System.out.println(articleVOList);
		
		//遍历集合
		Iterator<ArticleVO> it = articleVOList.iterator();
		while(it.hasNext()) {
			//将集合元素设置到page范围内
			getJspContext().setAttribute(item, (it.next()));
			//输出标签体
			try {
				getJspBody().invoke(null);
			} catch (JspException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
