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


import ltd.newbee.mall.dao.ReviewPageMapper;
import ltd.newbee.mall.entity.Review;
import ltd.newbee.mall.service.ReviewPageService;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

@Service
public class ReviewPageServiceImpl implements ReviewPageService
{
	@Autowired
    private ReviewPageMapper reviewPageMapper;

	@Override
	public PageResult getReview(PageQueryUtil pageUtil)
	{
		List<Review> review = reviewPageMapper.findReviewPageList(pageUtil);
		int total = reviewPageMapper.getPageResult(pageUtil);
		PageResult pageResult = new PageResult(review,total,pageUtil.getLimit(),pageUtil.getPage());
		return pageResult;
	}

}