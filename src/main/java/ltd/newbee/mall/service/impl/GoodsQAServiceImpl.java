/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ltd.newbee.mall.dao.GoodsQAMapper;
import ltd.newbee.mall.entity.QuestionAndAnswer;
import ltd.newbee.mall.service.GoodsQAService;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

@Service
public class GoodsQAServiceImpl implements GoodsQAService
{
	@Autowired
    private GoodsQAMapper goodsQAMapper;

	@Override
	public PageResult getQuestionAndAnswer(PageQueryUtil pageUtil)
	{
		List<QuestionAndAnswer> questionAndAnswer = goodsQAMapper.findGoodsQAList(pageUtil);
		int total = goodsQAMapper.getGoodsQA(pageUtil);
		PageResult pageResult = new PageResult(questionAndAnswer,total,pageUtil.getLimit(),pageUtil.getPage());
		return pageResult;
	}
	

	@Override
	public Long getMaxGoodsId(Long goodsId)
	{
		Long maxGoodsId = goodsQAMapper.getMaxGoodsId(goodsId);
		if(maxGoodsId !=null) {
			return maxGoodsId + 1;
		}else {
			return 1L;
		}
	}
	
	@Override
	public Long insertGoodsQA(QuestionAndAnswer qa) 
	{
		return goodsQAMapper.insertGoodsQA(qa);
	}

}