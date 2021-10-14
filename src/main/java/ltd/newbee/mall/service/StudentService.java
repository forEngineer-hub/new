package ltd.newbee.mall.service;

import java.util.ArrayList;

import ltd.newbee.mall.entity.Student;

public interface StudentService 
{
	ArrayList<Student> getStudentListByName(String name);
	
	Long insertStudent(Student s);
	
	Long updateStudent(Student uStudent);

	Boolean deleteStudent(Long id);
	
}
