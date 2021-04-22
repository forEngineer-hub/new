package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;
import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.service.impl.NewBeeMallGoodsServiceImpl;


@SpringBootTest
public class GoodsControllerTest  {
	
	//このアノテーションでインスタンス化する
	//注入
	//DI dependency injection
	@Resource
	NewBeeMallGoodsService newBeeMallGoodsService;
	
	@Test
	public void testGoodsDescService() {
		long goodsId = 10003L;
		GoodsDescEntity goodsDesc = newBeeMallGoodsService.getGoodsDesc(goodsId);
		String color = goodsDesc.getColor();
		//assertEquals("yellow", color);
		//assertEquals(10003L,goodsId);
	}
}
