package ltd.newbee.mall.controller.mall;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;


import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.service.NewBeeMallGoodsService;

@SpringBootTest
class GoodsControllerTest2 extends GoodsController{
	@Resource
	NewBeeMallGoodsService newBeeMallGoodsService;

	 @Test
	public void testGoodsDescService() {
	  long goodsId = 10339L;
	  GoodsDescEntity goodsDesc = newBeeMallGoodsService.getGoodsDesc(goodsId);
	  String color = goodsDesc.getColor();
	  assertEquals("yellow",color);
	 }

}