/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.StudentService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class TestStudentServiceRestController {

	@Resource
    private StudentService studentService;
	
    //@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    @ResponseBody
    public Result categories(@RequestParam String name) {
    	
    	List<Student> list = studentService.getStudentsListByName(name);
        if (CollectionUtils.isEmpty(list)) {
        	return ResultGenerator.genErrorResult(Constants.FETCH_ERROR, Constants.STUDENT_FETCH_ERROR_MESSAGE);
        }else {
        	return ResultGenerator.genSuccessResult(list);
        }
    
    }
    
   
}
