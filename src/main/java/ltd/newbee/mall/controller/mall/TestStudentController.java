package ltd.newbee.mall.controller.mall;

import java.util.ArrayList;
import static org.junit.Assert.*;
import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.StudentService;

@SpringBootTest
public class TestStudentController {

	//用以下注释导入service
	@Resource
	StudentService studentService;
	
	// 01 王小丽
	// 02 王小丫
	String name = "小";
	String name1 = "王小丽";
	String name2 = "王小丫";
	
	@Test
	public void testLenght() {
		ArrayList<Student> list = studentService.getStudentsListByName(name);
		if(list !=null && !list.isEmpty()) {
			assertEquals(2,list.size());
		}
	}

	@Test
	public void ifMactchName() {
		ArrayList<Student> list = studentService.getStudentsListByName(name);
		for(Student s :list) {
			assertTrue(name1.equals(s.getStudentName()) || name2.equals(s.getStudentName()) );
		}
	}
}
