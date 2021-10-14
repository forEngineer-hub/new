package ltd.newbee.mall.service;

import java.util.ArrayList;

import ltd.newbee.mall.entity.Student;

public interface StudentService 
{
	ArrayList<Student> getStudentListByName(String name);
	
	Long insertStudent(Student s);
	
	Boolean updateStudentName(String oldName,String newName);

	Long deleteStudent(Student d);
	
}
