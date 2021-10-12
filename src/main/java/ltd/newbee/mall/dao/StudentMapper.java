/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.dao;

import java.util.ArrayList;

import ltd.newbee.mall.entity.Student;

public interface StudentMapper {
	//通过名字来查询学生的信息 参数是学生名字 name
	//返回可以是多个学生，list<Student>
	/*
	 * 
	 */
	/**
     * 学生リストを返す。
     * 学生名前で曖昧検索を行う。
     * @param name 学生名前
     * @return 学生リスト
     */
	public ArrayList<Student> getStudentListByName(String name);
	
    // get student id
    Long getStudentId();
    // xml mapper ↓
    // select max(id) + 1 from student
    
    int insertStudent(Student s);
   
}