package ltd.newbee.mall.service;

import java.util.ArrayList;

import ltd.newbee.mall.entity.Student;

public interface StudentService 
{
	ArrayList<Student> getStudentListByName(String name);
	
	/* insert student */
	Long insertStudent(Student s);
	
	/* update student */
	String updateStudent(Student sUpdate);

	/* delete student */
	Boolean deleteStudent(Long id);
	
}
