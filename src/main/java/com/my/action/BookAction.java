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

import com.my.bean.Book;
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
	
	@RequestMapping("queryAlls")
	@ResponseBody
	public  List<HashMap<String,Object>> queryAlls(HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		wherMap.put("orderbysort", " publishdate desc");
		List<HashMap<String,Object>> books = bookService.queryAll(wherMap);
		
		return books;
	}
	
	@RequestMapping("queryType")
	@ResponseBody
	public  List<HashMap<String,Object>> queryType(HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		List<HashMap<String,Object>> types = bookService.queryType(wherMap);

		return types;
	}
	
	@RequestMapping("queryCountry")
	@ResponseBody
	public  List<HashMap<String,Object>> queryCountry(HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		List<HashMap<String,Object>> country = bookService.queryCountry(wherMap);

		return country;
	}
	
	@RequestMapping("queryCity")
	@ResponseBody
	public  List<HashMap<String,Object>> queryCity(String country_no,HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		if(null!=country_no){
			int country_id = Integer.parseInt(country_no);
			wherMap.put("country_no", country_id);
		}
		System.out.println("======================="+country_no);
		List<HashMap<String,Object>> city = bookService.queryCity(wherMap);
		return city;
	}
	
	@RequestMapping("queryArea")
	@ResponseBody
	public  List<HashMap<String,Object>> queryArea(String city_no,HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		if(null!=city_no){
			int city_id = Integer.parseInt(city_no);
			wherMap.put("city_no", city_id);
		}
		List<HashMap<String,Object>> area = bookService.queryArea(wherMap);
		return area;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public  HashMap<String,Object> queryArea(Book book,HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		
		HashMap<String,Object>  map= new HashMap<String,Object>();
		try {
			String country_no = request.getParameter("country");
			String city_no = request.getParameter("city");
			String area_no = request.getParameter("area");
			String add = country_no+" "+city_no+" "+area_no;
			int id = bookService.getNextSeq();
			book.setID(id);
			book.setADDRESS(add);
			System.out.println("========================="+book);
			int id_a = bookService.insertSelective(book);
			System.out.println("========================="+id_a);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", true);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}
}
