package ltd.newbee.mall.entity;

public class PagingParamBean {
	private int start;
	private int limit;
	private int curPageNo;
	private String orderByCol;
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getCurPageNo() {
		return curPageNo;
	}
	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}
	public String getOrderByCol() {
		return orderByCol;
	}
	public void setOrderByCol(String orderByCol) {
		this.orderByCol = orderByCol;
	}
}
