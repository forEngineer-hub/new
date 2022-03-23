package ltd.newbee.mall.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsInfo;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.service.StudentService;

@SpringBootTest
public class TestStudentController {
	
	private static final Logger logger = LogManager.getLogger(TestStudentController.class);
	//用以下注释导入service
	@Resource
	StudentService studentService;
	
	@Resource
	NewBeeMallGoodsService newBeeMallGoodsService;
	
	@Test
	
	public void testArrayList() {

		ArrayList<Student> aLs1 = new ArrayList<Student>();
		Student s1 = new Student();
		s1.setStudentId(100);
		aLs1.add(s1);
		
		Student s2 = new Student();
		s2.setStudentId(101);
		
		aLs1.add(s2);
		
		ArrayList<Student> aLs2 = new ArrayList<Student>();
		Student s3 = new Student();
		s3.setStudentId(100);
		
		aLs2.add(s3);
		
		Student s4 = new Student();
		s4.setStudentId(101);
		
		aLs2.add(s4);
		
		//Ls2.stream().forEach((c) -> System.out.println(c));
		
		for( int i = 0; i < aLs1.size(); i ++) {
			Student st1 = aLs1.get(i);
			Student st2 = aLs2.get(i);
			logger.debug("Hello from Log4j 2 - num : {}", st2);
//			assertEquals(st1,st2);
			assertThat(st1, samePropertyValuesAs(st2));
		}
		
	}
	@Test
	public void testGoodsInfo() {
		GoodsInfo info = newBeeMallGoodsService.getGoodsInfoByPk(10700l);
		assertEquals("iphone11",info.getName());
		assertEquals("40cm",info.getSize());
	}
	
	@Test
	public void testGoodsImage() {
		ArrayList<GoodsImage> list = newBeeMallGoodsService.getGoodsImage(10700l);

		if (!CollectionUtils.isEmpty(list)) {
			assertEquals(1,list.size());
		}
		list.size();
	}
	@Test
	public void testGoodsPage() {
		ArrayList<NewBeeMallGoods> list = newBeeMallGoodsService.getGoodsPage("iphone", 1);
		if (!CollectionUtils.isEmpty(list)) {
			assertEquals(3,list.size());
			//10278在list当中
			assertTrue(checkGoodsIdInList(list, 10278l));
		}
	}
	
	public boolean checkGoodsIdInList(ArrayList<NewBeeMallGoods> list, long goodsId) {
		return true;
	}
}
