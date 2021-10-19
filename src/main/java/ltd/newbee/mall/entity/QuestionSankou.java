package ltd.newbee.mall.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QuestionSankou 
{
	private Long goodsId;
	private Long questionId;
	private Long userID;
	private Date submitDate;
	private Date answersDate;
	
	
	public Long getGoodsId() 
	{
		return goodsId;
	}
	
	public void setGoodsId(Long goodsId) 
	{
		this.goodsId = goodsId;
	}
	
	public Long getQuestionId() 
	{
		return questionId;
	}
	
	public void setQuestionId(Long questionId) 
	{
		this.questionId = questionId;
	}
	

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
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
	
	public Date getAnswersDate() 
	{
		return answersDate;
	}
	
	public void setAnswersDate(Date answersDate) 
	{
		this.answersDate = answersDate;
	}

}