/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Review 
{
	private long goodsId;
	private long reviewId;
	private long reviewUserId;
	private short star; 
	private Date submitDate;
	private String reviewTitle;
	private String reviewDetail;
	private String imageUrl;
	private String helpNum;
	
	public long getGoodsId() 
	{
		return goodsId;
	}
	
	public void setGoodsId(long goodsId) 
	{
		this.goodsId = goodsId;
	}
	
	public long getReviewId() 
	{
		return reviewId;
	}
	
	public void setReviewId(long reviewId) 
	{
		this.reviewId = reviewId;
	}
	
	public long getReviewUserId() 
	{
		return reviewUserId;
	}
	
	public void setReviewUserId(long reviewUserId) 
	{
		this.reviewUserId = reviewUserId;
	}
	
	public short getStar() 
	{
		return star;
	}
	
	public void setStar(short star)
	{
		this.star = star;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
	public Date getSubmitDate() 
	{
		return submitDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
	public void setSubmitDate(Date submitDate) 
	{
		this.submitDate = submitDate;
	}
	
	public String getReviewTitle() 
	{
		return reviewTitle;
	}
	
	public void setReviewTitle(String reviewTitle) 
	{
		this.reviewTitle = reviewTitle;
	}
	
	public String getReviewDetail() 
	{
		return reviewDetail;
	}
	
	public void setReviewDetail(String reviewDetail)
	{
		this.reviewDetail = reviewDetail;
	}
	
	public String getImageUrl() 
	{
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getHelpNum() 
	{
		return helpNum;
	}

	public void setHelpNum(String helpNum) 
	{
		this.helpNum = helpNum;
	}
	
	
}