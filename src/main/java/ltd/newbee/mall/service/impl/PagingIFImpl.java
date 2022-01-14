package ltd.newbee.mall.service.impl;

import org.springframework.stereotype.Service;

import ltd.newbee.mall.entity.PagingParamBean;
import ltd.newbee.mall.service.PagingIF;
@Service
public class PagingIFImpl implements PagingIF {

//	@Override
//	public PagingParamBean getPagingParam(int currPageNo, int limit, String OrderByCol) {
//		PagingParamBean param = new PagingParamBean();
//		param.setLimit(limit);
//		param.setOrderByCol(OrderByCol);
//		int start = limit * (currPageNo - 1);
//		param.setStart(start);
//		return param;
//	}
	
	@Override
	public void setPagingParam(PagingParamBean bean) {
		//PagingParamBean param = new PagingParamBean();
		bean.setLimit(bean.getLimit());
		bean.setOrderByCol(bean.getOrderByCol());
		int start = bean.getLimit() * (bean.getCurPageNo() - 1);
		bean.setStart(start);
		//return bean;
	}

}
