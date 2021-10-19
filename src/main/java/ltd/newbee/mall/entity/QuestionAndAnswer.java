package ltd.newbee.mall.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QuestionAndAnswer 
{
	private Long goodsId;
	private Long questionId;
	private String question;
	private String answer;
	private Date submitDate;
	private Date answersDate;
	private String helpNum;
	
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
	
	public String getQuestion() 
	{
		return question;
	}
	
	public void setQuestion(String question) 
	{
		this.question = question;
	}
	
	public String getAnswer() 
	{
		return answer;
	}
	
	public void setAnswer(String answer) 
	{
		this.answer = answer;
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

	public String getHelpNum() 
	{
		return helpNum;
	}

	public void setHelpNum(String helpNum) 
	{
		this.helpNum = helpNum;
	}
	
	
}