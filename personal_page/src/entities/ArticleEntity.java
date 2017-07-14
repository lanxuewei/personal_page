package entities;

public class ArticleEntity {
	//id
	private Integer id;
	//标题
	private String title;
	//分类
	private String sorts;
	//正文内容
	private String mainBody;
	//最后修改时间
	private String date;
	//引言
	private String introduction;
	//配图路径
	private String img;
	
	//空构造器
	public ArticleEntity(){
		
	}
	
	//全属性赋值构造器
	public ArticleEntity(Integer id, String title, String main_body,
			String date, String introduction, String sorts, String img){
		this.id = id;
		this.title = title;
		this.mainBody = main_body;
		this.date = date;
		this.introduction = introduction;
		this.sorts = sorts;
		this.img = img;
	}
/*	//所有属性直接赋值
	public ArticleEntity setAllProperty(Integer id, String title, String main_body,
			String date, String introduction, String sorts){
		
		ArticleEntity articleEntity = new ArticleEntity();
		//对于所有属性都进行赋值
		articleEntity.setId(id);
		articleEntity.setTitle(title);
		articleEntity.setMainBody(main_body);
		articleEntity.setDate(introduction.substring(0, 10));
		articleEntity.setSorts(sorts);
		return articleEntity;
	}*/
	
	//setter()和getter()方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSorts() {
		return sorts;
	}
	public void setSorts(String sorts) {
		this.sorts = sorts;
	}
	public String getMainBody() {
		return mainBody;
	}
	public void setMainBody(String mainBody) {
		this.mainBody = mainBody;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id=" + id + ",title=" + this.getTitle() + ",sorts=" + this.getSorts() + ",mainbody=" + this.getMainBody() + ",date="
				+ this.getDate() + ",img=" + this.getImg() + "]";
	}
}
