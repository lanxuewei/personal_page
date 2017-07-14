package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.ArticleDao;
import entities.ArticleEntity;
import vo.ArticleVO;

public class ArticleService {
	private ArticleDao articleDao = new ArticleDao();
	
	/*两个Entity和VO相互转换方法*/
	//1.VO->Entity
	public ArticleEntity voChangeEntity(ArticleVO vo){
		//属性赋值
		ArticleEntity articleEntity = new ArticleEntity(vo.getId(), vo.getTitle(), 
				vo.getMainBody(), vo.getDate(), vo.getIntroduction(), vo.getSorts(), vo.getImg());
		/*articleEntity.setId(vo.getId());
		articleEntity.setTitle(vo.getTitle());
		articleEntity.setSorts(vo.getSorts());
		articleEntity.setMainBody(vo.getMainBody());
		articleEntity.setDate(vo.getDate());
		articleEntity.setIntroduction(vo.getIntroduction());*/
		return articleEntity;
	}
	//2.Entity->VO
	public ArticleVO entityChangeVo(ArticleEntity articleEntity){
		ArticleVO articleVO = null;
		//属性赋值
		if(articleEntity != null){
			//参数不为空
			articleVO = new ArticleVO(articleEntity.getId(), articleEntity.getTitle(), 
					articleEntity.getMainBody(), articleEntity.getDate(), articleEntity.getIntroduction(), articleEntity.getSorts(), articleEntity.getImg());
		}else{
			//否则articleVO属性也为空(属性为空不代表对象为空，不同概念)
			articleVO = new ArticleVO();
		}
		/*articleVO.setId(articleEntity.getId());
		articleVO.setTitle(articleEntity.getTitle());
		articleVO.setSorts(articleEntity.getSorts());
		articleVO.setMainBody(articleEntity.getMainBody());
		articleVO.setDate(articleEntity.getDate());
		articleVO.setIntroduction(articleEntity.getIntroduction());
		return articleVO;*/
		return articleVO;
	}
	
	//新增article
	public void createArticle(ArticleVO vo){
		//将VO转换成Entity，再调用dao新增article
		articleDao.createArticle(voChangeEntity(vo));
	}
	
	//根据sorts查找所有分类article函数(文章梗概，不查找mainBody属性)
	//将Entity实体集转化成VO实体集
	public List<ArticleVO> queryAllArticle(Integer pageNum, Integer pageSize, String sorts){
		//调用dao查询所有article
		List<ArticleEntity> articleEntityList = articleDao.queryAllArticle(pageNum, pageSize, sorts);
		//测试
		System.out.println("ArticleServlet(articleEntityList):" + articleEntityList);
		
		//存储ArticleVO实体的集合
		List<ArticleVO> articleVOList = new ArrayList<ArticleVO>();
		//遍历Entity集合将其转化成VO
		Iterator<ArticleEntity> it = articleEntityList.iterator();
		while(it.hasNext()){
			articleVOList.add(entityChangeVo(it.next()));
		}
		//测试
		System.out.println("ArticleServlet(articleVOList): " + articleVOList);
		return articleVOList;
	}
	
	//查询单个article(将ArticleEntity转化成ArticleVO)
/*	public ArticleVO simpleArticle(int id){
		//articleVO作为返回对象
		ArticleVO articleVO = new ArticleVO();
		//调用dao层函数得到ArticleEntity，并将其转化成ArticleVO
		articleVO = entityChangeVo(articleDao.simpleArticle(id));
		//测试articleVO
		System.out.println("articleVO = " + articleVO);
		
		return articleVO;
	}*/
	
	//查找该文章以及上下篇
	public Map<String, ArticleVO> simpleArticlePreAndNext(Integer id){
		
		Map<String, ArticleVO> articleVOs = new HashMap<String, ArticleVO>();
		//三篇文章(当前，上下篇)
		ArticleVO currentArticleVO = null;
		ArticleVO preArticleVO = null;
		ArticleVO nextArticleVO = null;
		
		//当前文章并转化成vo加入集合
		currentArticleVO = entityChangeVo((articleDao.simpleArticle(id)));
		articleVOs.put("currentArticleVO", currentArticleVO);
		
		//上一文章
		preArticleVO = entityChangeVo(articleDao.simpleArticlePreOrNext(id, currentArticleVO.getSorts(), -1));
		//即上一篇文章不存在
		if(preArticleVO.getId() == null){
			//将当前文章id、sorts赋值给上一篇文章
			preArticleVO.setId(currentArticleVO.getId());
			preArticleVO.setSorts(currentArticleVO.getSorts());
			//提示已经是第一篇
			preArticleVO.setTitle("已经是第一篇文章");
		}
		articleVOs.put("preArticleVO", preArticleVO);
		
		//下一文章
		nextArticleVO = entityChangeVo(articleDao.simpleArticlePreOrNext(id, currentArticleVO.getSorts(), 1));
		//下一篇文章不存在
		if(nextArticleVO.getId() == null){
			nextArticleVO.setId(currentArticleVO.getId());
			nextArticleVO.setSorts(currentArticleVO.getSorts());
			//提示
			nextArticleVO.setTitle("已经是最后一篇文章");
		}
		articleVOs.put("nextArticleVO", nextArticleVO);
				
		System.out.println("ArticleService:simpleArticlePreAndNext:articleVOs: "+ articleVOs);
		return articleVOs;
	}
/*	public List<ArticleVO> simpleArticlePreOrNext(Integer id, String sorts, Integer vector){
		ArticleVO articleVO = new ArticleVO();
		
		articleVO = entityChangeVo(articleDao.simpleArticlePreOrNext(id, sorts, vector));
		
		return articleVO;
	}*/
	
	
	//根据title关键字查询文章
	public List<ArticleVO> fuzzySearch(String keyword){
		
		List<ArticleEntity> articleEntities = articleDao.fuzzySearch(keyword);
		//测试
		System.out.println("ArticleService:fuzzySearch:articleEntities" + articleEntities);
		
		//转化
		List<ArticleVO> articleVOs = new ArrayList<ArticleVO>();
		Iterator<ArticleEntity> it = articleEntities.iterator();
		while (it.hasNext()) {
			articleVOs.add(entityChangeVo(it.next()));
		}
		//测试
		System.out.println("ArticleService:fuzzySearch:articleVOs:" + articleVOs);
		return articleVOs;
	}
	
	//查询总页数
	public Integer coutPage(String sorts) {
		Integer pageCount = articleDao.countPage(sorts);
		//测试
		System.out.println("ArticleService: pageCount = " + pageCount);
		return pageCount;
	}
	
	//查出各分类文章总数
	public List<Integer> ArticleVOSortsCount(){
		
		List<Integer> counts = new ArrayList<Integer>();

		Integer count1 = articleDao.countPage("推荐阅读");
		Integer count2 = articleDao.countPage("心情");
		Integer count3 = articleDao.countPage("日记");
		Integer count4 = articleDao.countPage("感悟");
		Integer count5 = articleDao.countPage("诗词");
		Integer count6 = articleDao.countPage("Java");
		Integer count7 = articleDao.countPage("算法");

		counts.add(count1);
		counts.add(count2);
		counts.add(count3);
		counts.add(count4);
		counts.add(count5);
		counts.add(count6);
		counts.add(count7);

		//测试
		System.out.println("service:ArticleVOSortsCount:counts = " + counts);
		
		return counts;
	}
	
	//查询推荐阅读(主页)
/*	public List<ArticleVO> QueryRecommendReading(){
		//用于返回的vo集合
		List<ArticleVO> articleVOList = new ArrayList<ArticleVO>();
				
		List<ArticleEntity> articleEntityList = new ArrayList<ArticleEntity>();
		//调用Dao层函数查询
		articleEntityList = articleDao.QueryRecommendReading();
		//遍历转化成vo实体
		Iterator<ArticleEntity> it = articleEntityList.iterator();
		while(it.hasNext()){
			articleVOList.add(entityChangeVo((ArticleEntity)it.next()));
		}
		//测试
		System.out.println("ArticleService:articleVOList = " + articleVOList);
		return articleVOList;
	}*/
	
}
