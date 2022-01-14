package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.PagingParamBean;

public interface PagingIF {
	//PagingParamBean getPagingParam(int currPageNo, int limit, String OrderByCol);
	
	void setPagingParam(PagingParamBean bean);
}
