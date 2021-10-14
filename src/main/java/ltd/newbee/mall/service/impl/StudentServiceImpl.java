/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ltd.newbee.mall.common.ServiceResultEnum;


import ltd.newbee.mall.dao.StudentMapper;
import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{

    @Resource
    private StudentMapper studentMapper;
   
	
	@Override
	public ArrayList<Student> getStudentListByName(String name) 
	{
		// TODO Auto-generated method stub
		return studentMapper.getStudentListByName(name);
	}
	
	@Override
	public Long insertStudent(Student s)
	{
		Long id = studentMapper.getMaxStudentID();
		s.setStudentId(id + 1);
		return studentMapper.insertStudent(s);
	}

	
	/* Update Student */
	@Override
	public String updateStudent(Student sUpdate)
	{
		Student temp = studentMapper.checkByStudentId(sUpdate.getStudentId());
		
		if(temp == null)
		{
			return ServiceResultEnum.DATA_NOT_EXIST.getResult();
		}
		
		temp.setStudentName(sUpdate.getStudentName());
		temp.setStudentOld(sUpdate.getStudentOld());
		temp.setSexual(sUpdate.getSexual());
		temp.setAddress(sUpdate.getAddress());
		temp.setHometown(sUpdate.getHometown());
		temp.setTelephoneNumber(sUpdate.getTelephoneNumber());
		temp.setTotalScore(sUpdate.getTotalScore());
		temp.setStudentClass(sUpdate.getStudentClass());
		temp.setScoreRank(sUpdate.getScoreRank());
		temp.setBlacklist(sUpdate.getBlacklist());
		temp.setScoreOfphysics(sUpdate.getScoreOfPhysics());
		temp.setScoreOfChemistry(sUpdate.getScoreOfChemistry());
		temp.setScoreOfBiology(sUpdate.getScoreOfBiology());
		
		
		if(studentMapper.updateByStudentIdCheckOut(temp) > 0)
		{
			return ServiceResultEnum.SUCCESS.getResult();
		}
		
		return ServiceResultEnum.DB_ERROR.getResult();
	}

	

	@Override
	public Boolean deleteStudent(Long id) 
	{
		// TODO Auto-generated method stub
		return studentMapper.deleteStudent(id)>0;
	}

}