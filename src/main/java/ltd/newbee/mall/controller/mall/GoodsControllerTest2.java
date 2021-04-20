package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.service.NewBeeMallGoodsService;

@SpringBootTest
public class GoodsControllerTest2{
@Resource
NewBeeMallGoodsService newBeeMallGoodsService;

@Test
public void testGoodsDescService(){
	GoodsDescEntity goodsDesc = newBeeMallGoodsService.getGoodsDesc(10230L);
	String color = goodsDesc.getColor();
	assertEquals("chilli",color);
	}
}