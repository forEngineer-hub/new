package ltd.newbee.mall.controller.mall;

import java.util.ArrayList;
import static org.junit.Assert.*;
import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.StudentService;

@SpringBootTest
public class TestStudentController 
{
	@Resource
	StudentService studentService;
	

	String name = "三";
	String name1 = "张三丰";
	String name2 = "李三连";
	String name3 = "王三全";
	
	
	@Test
	public void testLength() 
	{
		ArrayList<Student> list = studentService.getStudentListByName(name);
		if(list !=null && !list.isEmpty()) 
		{
			assertEquals(3,list.size());
		}
	}

	@Test
	public void matchName() 
	{
		ArrayList<Student> list = studentService.getStudentListByName(name);
		for(Student s :list) 
		{
			assertTrue(name1.equals(s.getStudentName()) || name2.equals(s.getStudentName()) || name3.equals(s.getStudentName()));
		}
	}
}
