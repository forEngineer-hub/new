package ltd.newbee.mall.controller.mall;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@RestController
public class SearchGoodsController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

   
    //get hit goods
//    @RequestMapping(value = "/goods/search", method = RequestMethod.GET)
//    //public GoodsQa getHitGoodsList(@RequestBody String goodsName) {
//    //getの場合、RequestBodyは使えない、postのみです。
//    //getは@RequestParam
//    public Result getHitGoodsList(@RequestParam String goodsName) {
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("keyword", goodsName);
//    	params.put("page", 1);
//    	params.put("limit", 9);
//        //params.put("start", 0);
//        PageQueryUtil pageUtil = new PageQueryUtil(params);
//        return ResultGenerator.genSuccessResult(newBeeMallGoodsService.searchNewBeeMallGoods(pageUtil));
//    }
    @RequestMapping(value = "/goods/search", method = RequestMethod.GET)
    public Result getHitGoodsList() {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("keyword", "iphone");
    	params.put("page", 1);
    	params.put("limit", 9);
        //params.put("start", 0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallGoodsService.searchNewBeeMallGoods(pageUtil));
    }
}
