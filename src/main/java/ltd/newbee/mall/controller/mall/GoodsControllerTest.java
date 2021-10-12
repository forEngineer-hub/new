package ltd.newbee.mall.controller.mall;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.service.NewBeeMallCarouselService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

@SpringBootTest
public class GoodsControllerTest {

	// このアノテーションでインスタンス化する
	// 注入
	// DI dependency injection
	@Resource
	NewBeeMallGoodsService newBeeMallGoodsService;

	@Resource
	NewBeeMallCarouselService newBeeMallCarouselService;
	
	@Test
	public void testGoodsService() {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("page", "1");
		params.put("limit", "3");
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		PageResult rs = newBeeMallGoodsService.getNewBeeMallGoodsPage(pageUtil);
		List<NewBeeMallGoods> goodsList = (List<NewBeeMallGoods>) rs.getList();

		// 1: size = limit
		int size = 0;
		if (goodsList != null || !goodsList.isEmpty()) {
			size = goodsList.size();

		}
		assertEquals(3, size);


	}

	@Test
	public void testEquals() {
		//dev2_brach 
		//dev2_brach agin
		GoodsImage img1 = new GoodsImage();
		img1.setId(1l);
		img1.setPath("xxx");

		List<GoodsImage> list1 = new ArrayList<>();
		list1.add(img1);

		GoodsImage img2 = new GoodsImage();
		img2.setId(1l);
		img2.setPath("xxx");

		List<GoodsImage> list2 = new ArrayList<>();
		list2.add(img2);

		List<String> strList1 = new ArrayList<String>();
		strList1.add("hixxx");

		List<String> strList2 = new ArrayList<String>();
		strList2.add("hixxx");

		assertEquals(true, strList1.equals(strList2));
	}

	@Test
	public void testEquals2() {
		Carousel carousel = new Carousel();
		carousel.setCreateUser(11);
		String rs = newBeeMallCarouselService.saveCarousel(carousel);
		assertEquals(ServiceResultEnum.SUCCESS.getResult(), rs);
	}
	
	@Test
	public void testInsertStudent() {
//		Long id = mappper.getStudentId();
//		Student s = new Student();
//		s.setId(id);
//		s.setName("xiaozhang");
//		s.setAge(23);
//		int count = mappper.insertStudent(s);
//		assertEqual(1,count);
//		
//		Student s2 = new Student();
//		s2 = mapper.selectByName("xiao");
//		assertEquals("xiaozhang",s.getName);
//		assertEquals(23,s.getAge);
	
	}
}
