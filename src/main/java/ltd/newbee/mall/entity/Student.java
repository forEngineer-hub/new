package ltd.newbee.mall.entity;

public class Student extends PagingParamBean
{
	private Long studentId;
	private String studentName;
	private Short studentOld;
	private String sexual;
	private String address;
	private String hometown;
	private Long telephoneNumber;
	private Short totalScore;
	private String studentClass;
	private Short scoreRank;
	
	private String blacklist;
	private Short scoreOfPhysics;
	private Short scoreOfChemistry;
	private Short scoreOfBiology;
	

	public Long getStudentId()
	{
		return studentId;
	}
	
	public void setStudentId(Long studentId)
	{
		this.studentId = studentId;
	}
	

	public String getStudentName()
	{
		return studentName;
	}
	
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	

	public Short getStudentOld()
	{
		return studentOld;
	}
	
	public void setStudentOld(Short studentOld)
	{
		this.studentOld = studentOld;
	}
	

	public String getSexual()
	{
		return sexual;
	}
	
	public void setSexual(String sexual)
	{
		this.sexual = sexual;
	}
	

	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	
	public String getHometown()
	{
		return hometown;
	}
	
	public void setHometown(String hometown)
	{
		this.hometown = hometown;
	}
	
	
	public Long getTelephoneNumber()
	{
		return telephoneNumber;
	}
	
	public void setTelephoneNumber(Long telephoneNumber)
	{
		this.telephoneNumber = telephoneNumber;
	}
	
	
	public Short getTotalScore()
	{
		return totalScore;
	}
	
	public void setTotalScore(Short totalScore)
	{
		this.totalScore = totalScore;
	}
	
	
	public String getStudentClass()
	{
		return studentClass;
	}
	
	public void setStudentClass(String studentClass)
	{
		this.studentClass = studentClass;
	}
	
	
	public Short getScoreRank()
	{
		return scoreRank;
	}
	
	public void setScoreRank(Short scoreRank)
	{
		this.scoreRank = scoreRank;
	}
	
	
	
	public String getBlacklist() 
	{
		return blacklist;
	}
	
	public void setBlacklist(String blacklist)
	{
		this.blacklist = blacklist;
	}
	
	
	public Short getScoreOfPhysics()
	{
		return scoreOfPhysics;
	}
	
	public void setScoreOfphysics(Short scoreOfPhysics)
	{
		this.scoreOfPhysics = scoreOfPhysics;
	}
	
	
	public Short getScoreOfChemistry()
	{
		return scoreOfChemistry;
	}
	
	public void setScoreOfChemistry(Short scoreOfChemistry)
	{
		this.scoreOfChemistry = scoreOfChemistry;
	}
	
	
	public Short getScoreOfBiology() 
	{
		return scoreOfBiology;
	}
	
	public void setScoreOfBiology(Short scoreOfBiology)
	{
		this.scoreOfBiology = scoreOfBiology;
	}
}
