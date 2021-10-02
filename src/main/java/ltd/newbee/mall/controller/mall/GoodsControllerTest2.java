package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.service.NewBeeMallGoodsService;



@SpringBootTest
public class GoodsControllerTest2{
@Resource
NewBeeMallGoodsService newBeeMallGoodsService;

@Autowired
private NewBeeMallGoodsMapper goodsMapper;

@Test
public void testGoodsDescService(){
	
	
	GoodsDescEntity goodsDesc = newBeeMallGoodsService.getGoodsDesc(10230L);
	String color = goodsDesc.getColor();
	assertEquals("chilli",color);
	
	//List<Student> list = goodsMapper.findStudentByName("田");
	//test size
	//assertEquals(1,list.size());
	//
//	Student s = list.get(0);
//	
//	assertEquals("田中",s.getName());
//	assertEquals("男",s.getGender());
//	assertEquals(15,s.getAge());
	}
}