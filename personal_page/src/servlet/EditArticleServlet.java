package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.ArticleService;
import vo.ArticleVO;

//编辑article servlet
public class EditArticleServlet extends HttpServlet{
	
	private ArticleService articleService = new ArticleService();

	@Override
	public void service(final HttpServletRequest req,final HttpServletResponse res) throws IOException {
/*		PrintWriter out=res.getWriter();
		out.println("**************************************");*/
		/*
		Get限制Form表单的数据集的值必须为ASCII字符；
		   而Post支持整个ISO10646字符集。默认是用ISO-8859-1编码 ，所以需要设置编码格式 */
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取请求中的title(标题)、sorts(分类)、main_body(正文)和introduction(引言)、lastPath(配图路径)
		String title = null;
		String sorts = null;
		String main_body = null;
		String introduction_main_body = null;
		String lastPath = null;
		String img = null;
		
/*		//封装成ArticleVO
		ArticleVO articleVo = new ArticleVO(null, title, mainBody, null, introduction, sorts);
		articleVo.setTitle(title);
		articleVo.setSorts(sorts);
		articleVo.setMainBody(mainBody);
		articleVo.setIntroduction(introduction);
		
		//调用service新增article
		articleService.createArticle(articleVo);    */
		
		ServletContext application = req.getServletContext();
		//获取当前路径
		String realPath = application.getRealPath(".");
		System.out.println("realPath:" + realPath);
		
		//建立文件工厂(默认临界值和系统临时文件夹)
		FileItemFactory factory = new DiskFileItemFactory();
		//FileItemFactory factory = new DiskFileItemFactory(1024*6, new File(realPath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置该次上传最大值为20M 
		upload.setSizeMax(1024*1024*20);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem item : items){
				//表单域
				if(item.isFormField()){
					//title、sorts、introduction、mainBody
					if(item.getFieldName().equals("title")){
						title = item.getString("utf-8");
					}
					if(item.getFieldName().equals("sorts")){
						sorts = item.getString("utf-8");
					}
					if(item.getFieldName().equals("introduction_main_body")){
						introduction_main_body = item.getString("utf-8");
					}
					if(item.getFieldName().equals("main_body")){
						main_body = item.getString("utf-8");
					}
					
					System.out.println(item.getFieldName()+"-->"+item.getString("utf-8"));
					
				}else{
					//System.out.println("*"+item.getName()+"*");
					if(item.getName()!=null&&!item.getName().equals("")){
						//配图路径
						lastPath = realPath + "/img/" + item.getName();
						img = item.getName();
						//写入文件
						item.write(new File(lastPath));
						//item.write(new File(realPath + "/img/" + item.getName()));
						System.out.println("lastPath = " + lastPath);
						
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//封装ArticleVO
		ArticleVO articleVO = new ArticleVO(null, title, main_body, null, introduction_main_body, sorts, img);
		System.out.println("**************"+articleVO);
/*		File file=new File(realPath+"/a.txt");
		PrintStream ps=null;
		try {
			ps=new PrintStream(file);
			ps.println(articleVO);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//System.out.println(file.exists());
			if(ps!=null){				
				ps.close();
				
			}
		}*/
		//新增文章
		articleService.createArticle(articleVO);

		/*try {
			PrintWriter out=res.getWriter();
			out.println(lastPath+new File(lastPath).exists());
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	
	}
}
