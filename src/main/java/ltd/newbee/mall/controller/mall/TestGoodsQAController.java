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
import ltd.newbee.mall.entity.QuestionAndAnswer;
import ltd.newbee.mall.service.GoodsQAService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class TestGoodsQAController 
{
	@Resource
	GoodsQAService goodsQAService;
	

	@RequestMapping(value = "/goodsQAService",method = RequestMethod.GET)
    @ResponseBody
	public Result list(@RequestParam Map<String, Object> params)
	{
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")))
        {
            return ResultGenerator.genErrorResult(300,"Error");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(goodsQAService.getQuestionAndAnswer(pageUtil));
	}
	
	@ResponseBody
	public Result insertQA(@RequestBody QuestionAndAnswer qa) {

		long count = goodsQAService.insertGoodsQA(qa);

		Long qaId = goodsQAService.getMaxGoodsId(qa.getGoodsId());
    	qa.setGoodsId(qaId);

		Date submitDate = new Date();
		Date answersDate = new Date();
		qa.setSubmitDate(submitDate);
		qa.setAnswersDate(answersDate);

		if (count <= 0) {
			return ResultGenerator.genErrorResult(Constants.FETCH_ERROR, Constants.STUDENT_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult("挿入成功");
		}

	}
}
