/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsImageVO;
import ltd.newbee.mall.controller.vo.GoodsReviewVo;
import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.controller.vo.SearchPageCategoryVO;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class GoodsController {

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        if (params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")) {
            Long categoryId = Long.valueOf(params.get("goodsCategoryId") + "");
            SearchPageCategoryVO searchPageCategoryVO = newBeeMallCategoryService.getCategoriesForSearch(categoryId);
            if (searchPageCategoryVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }

        //封装参数供前端回显
        if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        String keyword = "";
        //对keyword做过滤 去掉空格
        if (params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword") + "").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", keyword);
        params.put("keyword", keyword);
        //搜索上架状态下的商品
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", newBeeMallGoodsService.searchNewBeeMallGoods(pageUtil));
        return "mall/search";
    }

    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(goodsId);
        if (goods == null) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()) {
            NewBeeMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        //added by ka 2021/04/20 add imageList
        List<GoodsImage> imageEntityList = newBeeMallGoodsService.getImageList(goodsId);
        
        //copy list
        List<GoodsImageVO> imageVoList = BeanUtil.copyList(imageEntityList, GoodsImageVO.class);
        
        NewBeeMallGoodsDetailVO goodsDetailVO = new NewBeeMallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", goodsDetailVO);
        // set imageList
        request.setAttribute("imageList", imageVoList);
        
        //added by foren 2021/04/15 詳細画面追加対応
        //newBeeMallGoodsService.getImageList("1");
        return "mall/detail";
    }

    /**
     * paging
     */
    @RequestMapping(value = "/goods/qaSort", method = RequestMethod.POST)
    @ResponseBody
    public Result qaSort(@RequestBody PagingBean page) {
    	
    	List<GoodsQa> qaList = new ArrayList<GoodsQa>();
    	GoodsQa q1 = new GoodsQa();
    	GoodsQa q2 = new GoodsQa();
    	GoodsQa q3 = new GoodsQa();
    	q1.setId(1L);
    	q1.setAnswer("①答えです。よろしくお願いいたします。");
    	q1.setQuestion("１回答よろしくお願いいたします。");
    	qaList.add(q1);
    	
    	q2.setId(2L);
    	q2.setAnswer("②答えです。よろしくお願いいたします。");
    	q2.setQuestion("２回答よろしくお願いいたします。");
    	qaList.add(q2);
    	
    	q3.setId(3L);
    	q3.setAnswer("③答えです。よろしくお願いいたします。");
    	q3.setQuestion("３回答よろしくお願いいたします。");
    	qaList.add(q3);
    	
		 PageResult pageResult = new PageResult(qaList, 10, 3, page.getPage());
	     return ResultGenerator.genSuccessResult(pageResult);
    
    }
    
    
    @RequestMapping(value = "/goods/insertQa", method = RequestMethod.POST)
    @ResponseBody
    public Result insetQa(@RequestBody GoodsQa qa) {
    	Integer count = null;
    	//generate qa id
    	Long qaId = newBeeMallGoodsService.getMaxQaId(qa.getGoodsId());
    	qa.setId(qaId);
    	//submitDate
    	Date submitDate = new Date(); 
    	//answerDate
    	Date answerDate = new Date();
    	
    	qa.setSubmitDate(submitDate);
    	qa.setAnswerDate(answerDate);
    	if(qa != null) {
    		count = newBeeMallGoodsService.insertQa(qa);
    	}
	    if(!(count > 0)) {
	    	return ResultGenerator.genFailResult("投稿失敗");
	    }
    	
    	return ResultGenerator.genSuccessResult(count);
	    
    
    }
    
    @RequestMapping(value = "/goods/showMoreRevies", method = RequestMethod.POST)
    @ResponseBody
    public Result showMoreRevies(@RequestBody Long goodsId) {
    	
    	List<GoodsReviewVo>	reviewList = newBeeMallGoodsService.getGoodsReviews(goodsId);
    	List<GoodsReviewVo>	subReviewList = reviewList.subList(3, reviewList.size()-1); 
    	return ResultGenerator.genSuccessResult(reviewList);
	    
    } 
    
    //helpNum
    @RequestMapping(value = "/goods/helpNum", method = RequestMethod.POST)
    @ResponseBody
    public Result helpNum(@RequestBody GoodsReviewHelpNum goodsReviewHelpNum, HttpSession httpSession) {
    	
    	NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
    	if(user!=null) {
    		goodsReviewHelpNum.setUserId(user.getUserId());
    	}
    	
    	boolean	addFlag = newBeeMallGoodsService.addHelpNum(goodsReviewHelpNum);
    	if(addFlag) {
    		boolean updateFlag = newBeeMallGoodsService.updateReviewNum(goodsReviewHelpNum);
    		if(updateFlag) {
    			long helpNum = newBeeMallGoodsService.getGoodsReviewHelpNum(goodsReviewHelpNum.getReviewId());
    			return ResultGenerator.genSuccessResult(helpNum);
    		}else {
    			return ResultGenerator.genFailResult("改修失敗！！");
    		}
    	}else {
    		return ResultGenerator.genFailResult("挿入失敗！！");
    	}
	    
    } 
}
