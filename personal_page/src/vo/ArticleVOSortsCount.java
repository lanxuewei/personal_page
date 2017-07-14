package vo;

public class ArticleVOSortsCount {
	
	private String sorts = null;
	private Integer count = null;
	
	//无参构造器
	public ArticleVOSortsCount(){
		
	}
	//赋值构造器
	public ArticleVOSortsCount(String sorts, Integer count){
		this.sorts = sorts;
		this.count = count;
	}
	
	public String getSorts() {
		return sorts;
	}
	public void setSorts(String sorts) {
		this.sorts = sorts;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
