package com.my.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.BookService;
import com.my.util.Page;

@Controller
public class BookAction {

	@Autowired
	 BookService bookService;
	
	@RequestMapping("queryAll")
	@ResponseBody
	public  ModelAndView queryAll(String cur_page,HttpServletRequest request,HttpServletResponse response){
		if(cur_page == null){
			cur_page = "1";
		}
		int curPage = Integer.parseInt(cur_page);
		int limit = 3;
		int start = (curPage - 1)*limit+1;
		int end = curPage*limit;
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		wherMap.put("start", start);
		wherMap.put("end", end);
		wherMap.put("orderbysort", " publishdate desc");
		List<HashMap<String,Object>> books = bookService.queryAll(wherMap);
		int result_count = bookService.queryAll_count(wherMap);
		Page page = new Page(curPage, result_count, limit, books);
		
		ModelAndView mode = new ModelAndView("views");
		mode.addObject("pager", page);
		return mode;
	}
}
