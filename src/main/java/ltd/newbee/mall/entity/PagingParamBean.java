package ltd.newbee.mall.entity;

public class  PagingParamBean
{
	private int start;
	private int limit;
	private int currentPageNo;
	private String orderByColomn;
	
	public int getStart() 
	{
		return start;
	}
	
	public void setStart(int start) 
	{
		this.start = start;
	}
	
	public int getLimit() 
	{
		return limit;
	}
	
	public void setLimit(int limit) 
	{
		this.limit = limit;
	}
	
	public int getCurrentPageNo() 
	{
		return currentPageNo;
	}
	
	public void setCurrentPageNo(int currentPageNo) 
	{
		this.currentPageNo = currentPageNo;
	}
	
	public String getOrderByColomn() 
	{
		return orderByColomn;
	}
	
	public void setOrderByColomn(String orderByColomn) 
	{
		this.orderByColomn = orderByColomn;
	}
}
