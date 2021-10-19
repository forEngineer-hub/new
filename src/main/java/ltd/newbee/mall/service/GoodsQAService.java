package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.QuestionAndAnswer;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface GoodsQAService 
{
	PageResult getQuestionAndAnswer(PageQueryUtil pageUtil);

	Long getMaxGoodsId(Long goodsId);

    /* insert page */
    Long insertGoodsQA(QuestionAndAnswer qa);
    
}
