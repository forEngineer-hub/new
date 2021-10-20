package ltd.newbee.mall.controller.mall;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.Review;
import ltd.newbee.mall.service.ReviewPageService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class TestReviewPageController 
{
	@Resource
	ReviewPageService reviewPageService;
	

	@RequestMapping(value = "/reviewPageService",method = RequestMethod.GET)
    @ResponseBody
	public Result list(@RequestParam Map<String, Object> params)
	{
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")))
        {
            return ResultGenerator.genErrorResult(300,"Error");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(reviewPageService.getReview(pageUtil));
	}
	
}
