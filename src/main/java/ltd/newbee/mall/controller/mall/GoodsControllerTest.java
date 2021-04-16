package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.Review;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.service.NewBeeMallGoodsTestService;

@SpringBootTest
public class GoodsControllerTest extends GoodsController {

	@Resource
    private NewBeeMallGoodsTestService newBeeMallGoodsTestService;
	
	@Resource
	private NewBeeMallGoodsService newBeeMallGoodsService; 
	@Test
	public void testSearchPage() {
		//assertEquals("test1", newBeeMallGoodsTestService.getTestResult());
		
		Integer i = Integer.valueOf(1);
//		List<GoodsImage> list = newBeeMallGoodsService.getImageList(i);
//		GoodsImage image = list.get(0);
//		String path = image.getPath();
//		assertEquals("xxxxx",path);
		
		List<Review> list = newBeeMallGoodsService.getReviewList("1");
		Review r = list.get(0);
		String customerId = r.getCustomer_id();
		assertEquals("3",customerId);
	}

}
