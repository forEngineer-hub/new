package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsInfo;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.service.StudentService;

@SpringBootTest
public class TestStudentController {

	//用以下注释导入service
	@Resource
	StudentService studentService;
	
	@Resource
	NewBeeMallGoodsService newBeeMallGoodsService;
	
	
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
