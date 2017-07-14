package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.ArticleEntity;

public class ArticleDao {

	//新增article
	public void createArticle(ArticleEntity articleEntity){
		Statement state = null;
		Connection conn = null;
		//测试
		System.out.println("articleEntity: " + articleEntity);
		
		//向article表中插入ArticleEntity的title和mainBody
		try {
			//获取Connection
			conn = ConnectionBuilder.getConnection();
			//获取Statement
			state = conn.createStatement();
			//state = StatementFactory.openStatement();
			
			String sql = "insert into article(id,title,main_body,introduction,sorts,img) values(null,'" + articleEntity.getTitle()
			+ "','" + articleEntity.getMainBody() + "','" + articleEntity.getIntroduction() + "','" + articleEntity.getSorts() + "'," + (articleEntity.getImg()==null?null:("'"+articleEntity.getImg()+"'"))+");";
				
			//测试sql语句
			System.out.println("sql = " + sql);
			
			
			state.execute(sql);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			//关闭state
			if(state!=null){
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭Connection
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//根据sorts查询article(id、title、date、introduction、sorts、img属性)并返回分类的ArticleEntity集合
	public List<ArticleEntity> queryAllArticle(Integer pageNum, Integer pageSize, String sorts){
		
		//ArticleEntity实体集合
		List<ArticleEntity> articleEntityList = new ArrayList<ArticleEntity>();
		
		Statement state = null;
		Connection conn = null;
		//查询结果集
		ResultSet rs = null;
		String sql = null;
	
		//定义sql语句(主页显示，按时间排序(降序，时间最新为先)
		//只需要introduction不需要main_body，降低数据库负载)
		Integer index = (pageNum - 1) * pageSize;
		
		//如果推荐阅读则查询所有(如果需要给推荐阅读增加分页,在此sql添加limit即可)
		if(sorts == null){
			sql = "select `id`,`title`,`date`,`introduction`,`sorts`,`img` from `article` where sorts='推荐阅读' "
					+ "order by `date` desc;";
		}
		//如果sorts=all表示(文章Articles)除推荐阅读外的所有文章
		else if(sorts.equals("all")){
			sql = "select `id`,`title`,`date`,`introduction`,`sorts`,`img` from `article` "
				+ "where `sorts`!='推荐阅读' order by `date` desc limit " + index + "," + pageSize + ";";
		}else{
			//否则按照sorts查找
			sql = "select `id`,`title`,`date`,`introduction`,`sorts`,`img` from `article` "
					+ "where `sorts`='" + sorts + "' order by `date` desc limit " + index + "," + pageSize + ";";
		}
		//测试sql
		System.out.println("queryAllArticle:sql = " + sql);
		
		try {
			conn = ConnectionBuilder.getConnection();
			//获取Statement
			//state = StatementFactory.openStatement();
			state = conn.createStatement();
			//执行sql
			rs = state.executeQuery(sql);
			//将结果集封装成ArticleEntity实体集合
			while(rs.next()){
				//只查询id、title、date、introduction、sorts,即main_body=null
				ArticleEntity articleEntity = new ArticleEntity(Integer.valueOf(rs.getString("id")), rs.getString("title"), 
						null, rs.getString("date").substring(0, 10), rs.getString("introduction"), rs.getString("sorts"), rs.getString("img"));
				
				//将ArticleEntity实体放入集合
				articleEntityList.add(articleEntity);
				//测试
				System.out.println("ArticleDao:" + articleEntityList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//关闭结果集
			if(rs != null){				
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//关闭state
			if(state!=null){
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭conn
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return articleEntityList;
	}
	
	//使用id查询单个article(id,title,main_body,sorts,date,img)返回一个ArticleEntity
	public ArticleEntity simpleArticle(int id){
		
		ArticleEntity articleEntity = null;
		
		Statement state = null;
		Connection conn = null;
		//查询结果集
		ResultSet rs = null;
		//定义sql语句
		String sql = "select `id`,`title`,`main_body`,`date`,`sorts`,`img` from `article` where `id`='" + id + "';";
		//测试sql
		System.out.println("sql = " + sql);
		
		try {
			conn = ConnectionBuilder.getConnection();
			//获取Statement
			//state = StatementFactory.openStatement();
			state = conn.createStatement();
			//执行sql
			rs = state.executeQuery(sql);
			//处理结果集
			while(rs.next()){
				//实例一个ArticleEntity用于返回查询结果(id,title,main_body,sorts,date,),即introduction=null
				articleEntity = new ArticleEntity(Integer.valueOf(rs.getString("id")), rs.getString("title"), 
						rs.getString("main_body"), rs.getString("date").substring(0, 10), null, rs.getString("sorts"), rs.getString("img"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭结果集
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭state
			if(state != null){
				//关闭state
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		//测试articleEntity
		System.out.println("articleEntity = " + articleEntity);
		
		return articleEntity;
	}
	
	/*根据当前文章id和sorts(sorts=all表示所有文章)查出该文章上一篇(vector=-1)或者下一篇(vector=+1)
	 * 查出文章的id、title和sorts，其他属性为null,如果不存在即将空对象返回*/
	public ArticleEntity simpleArticlePreOrNext(Integer id, String sorts, Integer vector){
		
		ArticleEntity articleEntity = null;
		Statement state = null;
		Connection conn = null;
		//结果集
		ResultSet rs = null;
		String sql = null;

		//查找上一篇文章sql
		if(vector == -1){
			//所有文章sql
			if(sorts.equals("all")){
				sql = "select `id`,`title`,`sorts` from article where `id`>" + id + " limit 1;";
				//测试sql
				System.out.println("ArticleDao:simpleArticlePre:sql = " + sql);
			}
			//分类查询sql
			sql = "select `id`,`title`,`sorts` from article where `sorts`='" + sorts +"' and`id`>"+ id + " limit 1;";
			//测试sql
			System.out.println("ArticleDao:simpleArticlePre:sql = " + sql);
		}
		//查找下一篇文章的sql
		if(vector == 1){
			//所有文章sql
			if(sorts.equals("all")){
				sql = "select `id`,`title`,`sorts` from article where `id`<" + id + " order by `date` desc limit 1;";
				//测试sql
				System.out.println("ArticleDao:simpleArticlePre:sql = " + sql);
			}
			//分类查询sql
			sql = "select `id`,`title`,`sorts` from article where `sorts`='" + sorts +"'and`id`<"+ id + " order by `date` desc limit 1;";
			//测试sql
			System.out.println("ArticleDao:simpleArticlePre:sql = " + sql);
		}
		try {
			conn = ConnectionBuilder.getConnection();
			//获取state
			//state = StatementFactory.openStatement();
			state = conn.createStatement();
			//执行sql
			rs = state.executeQuery(sql);
			//数据不为空
			if(rs.next()){
				//只获取id、title和sorts
				articleEntity = new ArticleEntity(Integer.valueOf(rs.getString("id")), rs.getString("title"), 
						null, null, null, rs.getString("sorts"), null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭结果集
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭state
			if(state != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//测试articleEntity
		System.out.println("ArticleDao:simpleArticlePre:articleEntity = " + articleEntity);
		
		return articleEntity;
	}
	
	//根据文章标题关键字查找文章(有可能多篇文章)
	public List<ArticleEntity> fuzzySearch(String keyword){
		
		List<ArticleEntity> articleEntities = new ArrayList<ArticleEntity>();
		Statement state = null;
		Connection conn = null;
		
		//结果集
		ResultSet rs = null;
		//sql
		String sql = "select `id`,`title`,`date`,`introduction`,`sorts`,`img` from article where `title` like '%" + keyword +  "%' order by `date` desc;";
		//测试sql
		System.out.println("ArticleDao:fuzzySearch:sql = " + sql);
		
		try {
			conn = ConnectionBuilder.getConnection();
			//关闭state
			//state = StatementFactory.openStatement();
			state = conn.createStatement();
			//执行sql
			rs = state.executeQuery(sql);
			//查询结果不为空,为空的话返回空集合
			while(rs.next()){
				ArticleEntity articleEntity = new ArticleEntity(Integer.valueOf(rs.getString("id")), 
						rs.getString("title"), null, rs.getString("date").substring(0, 10), rs.getString("introduction"), rs.getString("sorts"), rs.getString("img"));
				articleEntities.add(articleEntity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭结果集
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭state
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//测试articleEntity
		System.out.println("ArticleDao:fuzzySearch:articleEntitys = " + articleEntities);
		
		return articleEntities;
	}
	
	//根据sorts查询article分类后的总页数(all表示查询所有文章)(其实该函数功能就是根据sorts查找文章总数)
	public Integer countPage(String sorts){
		//总页数
		Integer pageCount = null;
		
		Statement state = null;
		Connection conn = null;
		//查询结果集
		ResultSet rs = null;
		
		//计算分类article总数据条数
		String sql = null;
		//sorts为空表示主页，即推荐阅读
		if(sorts == null){
			sql = "select count(*) from `article` where sorts='推荐阅读';";
		}else if(sorts.equals("all")){
			sql = "select count(*) from `article` where sorts!='推荐阅读';";
		}else {
			sql = "select count(*) from `article` where sorts='" + sorts + "';";
		}
		//测试sql
		System.out.println("ArticleDao:countPage:sql = " + sql);
		
		try {
			conn = ConnectionBuilder.getConnection();
			//获取state
			//state = StatementFactory.openStatement();
			state = conn.createStatement();
			//执行sql
			rs = state.executeQuery(sql);
			rs.next();
			//pageCount = rs.getInt(1);
			pageCount = Integer.parseInt(rs.getString("count(*)"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			//关闭结果集
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//关闭state
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//测试pageCount
		System.out.println("ArticleDao: pageCount = " + pageCount);

		return pageCount;
	}
	
}
