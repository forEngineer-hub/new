/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.PagingIF;
import ltd.newbee.mall.service.StudentService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;

@Controller
public class TestStudentServiceRestController {

	@Resource
	private StudentService studentService;

	@Resource
	private PagingIF pagingIF;

	@GetMapping("/testgoods/detail/{goodsId}")
	public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {

		return "mall/detail";
	}

	@GetMapping({ "/testsearch", "/search.html" })
	public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {

		return "mall/search";
	}

	// @CrossOrigin(origins = "http://localhost:3000")
//	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@GetMapping({"/students/{name1}","/students"})
	@ResponseBody
	public Result searchStudents(@RequestParam(required=false) String name,
			@PathVariable("name1") String name1){

		List<Student> list = studentService.getStudentsListByName(name);
		if (CollectionUtils.isEmpty(list)) {
			return ResultGenerator.genErrorResult(Constants.FETCH_ERROR, Constants.STUDENT_FETCH_ERROR_MESSAGE);
		} else {
			return ResultGenerator.genSuccessResult(list);
		}
	}
	
//	@GetMapping("/students")
//	@ResponseBody
//	public Result searchStudents2(@RequestParam String name) {
//
//		List<Student> list = studentService.getStudentsListByName(name);
//		if (CollectionUtils.isEmpty(list)) {
//			return ResultGenerator.genErrorResult(Constants.FETCH_ERROR, Constants.STUDENT_FETCH_ERROR_MESSAGE);
//		} else {
//			return ResultGenerator.genSuccessResult(list);
//		}
//	}
	
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	@ResponseBody
//	public Result insertStudent(@RequestBody Student s,HttpSession httpSession) {
	public Result insertStudent(@RequestBody Map<String,Object> s,HttpSession httpSession) {		
		NewBeeMallUserVO user = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
    	
		return ResultGenerator.genSuccessResult(s);
    	
//		pagingIF.setPagingParam(s);
//		if (s.getStudentName() == null | s.getStudentName().isEmpty()) {
//			return ResultGenerator.genErrorResult(301, "name invalid");
//		}
//		int count = studentService.insertStudent(s);
//		if (count <= 0) {
//			return ResultGenerator.genErrorResult(Constants.FETCH_ERROR, Constants.STUDENT_FETCH_ERROR_MESSAGE);
//		} else {
//			return ResultGenerator.genSuccessResult("挿入できました。");
//		}
	}

	@RequestMapping(value = "/csvInsertCampaign", method = RequestMethod.POST)
	@ResponseBody
	public Result csvInsertCampaign(@RequestParam String filePath) {
		BufferedReader br = null;
		// String file_name = "csvdata.txt"; // 入力ファイル
		try {
//			File file = new File(filePath);
//			br = new BufferedReader(new FileReader(file));
//			
			FileInputStream fis = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fis,"utf-8" );
		     br = new BufferedReader(isr);
		    		   
		    		   
			// readLineで一行ずつ読み込む
			String line; // 読み込み行
			String[] data; // 分割後のデータを保持する配列
			while ((line = br.readLine()) != null) {
				// lineをカンマで分割し、配列dataに設定
				data = line.split(",");
				// 1行分の読み込みデータを表示（データ間にスペース）
				Student s = new Student();
				s.setStudentId(Integer.parseInt(data[0]));
				int count = studentService.insertStudent(s);
				
				for (int i = 0; i < data.length; i++) {
					System.out.print(data[i] + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// return ResultGenerator.genSuccessResult("挿入できました。");
		}
		return ResultGenerator.genSuccessResult("挿入できました。");
	}
}
