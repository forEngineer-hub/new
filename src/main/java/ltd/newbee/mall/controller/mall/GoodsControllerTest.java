package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.service.NewBeeMallGoodsTestService;

@SpringBootTest
public class GoodsControllerTest extends GoodsController {

	@Resource
    private NewBeeMallGoodsTestService newBeeMallGoodsTestService;
	
	@Test
	public void testSearchPage() {
		assertEquals("test1", newBeeMallGoodsTestService.getTestResult());
	}

}
