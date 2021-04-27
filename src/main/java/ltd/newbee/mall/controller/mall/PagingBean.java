package ltd.newbee.mall.controller.mall;

public class PagingBean {
	 private int page;
	 
	 @Override
	public String toString() {
		return "PagingBean [page=" + page + ", orderBy=" + orderBy + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	private String orderBy;
}
