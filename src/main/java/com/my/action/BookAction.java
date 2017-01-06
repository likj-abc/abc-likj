package com.my.action;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.bean.Book;
import com.my.service.BookService;

@Controller
public class BookAction {

	@Autowired
	 BookService bookService;
	
	
	public BookAction() {
		super();
		
	}
	public  void pp() {  
        Runnable runnable = new Runnable() {  
            public void run() {  
                // task to run goes here  
                System.out.println("Hello !!");  
                
            }  
        };  
        ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);  
    }
	@RequestMapping("queryAll")
	@ResponseBody
	public  ModelAndView queryAll(String cur_page,HttpServletRequest request,HttpServletResponse response){
		if(cur_page == null){
			cur_page = "1";
		}
		int curPage = Integer.parseInt(cur_page);
		int limit = 3;
//		int start = (curPage - 1)*limit+1;
//		int end = curPage*limit;
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
//		wherMap.put("start", start);
//		wherMap.put("end", end);
		wherMap.put("orderbysort", " publishdate desc");
		PageHelper.startPage(curPage, limit);
		List<HashMap<String,Object>> books = bookService.queryBooks(wherMap);
		PageInfo<HashMap<String, Object>> page = new PageInfo<HashMap<String, Object>>(books);
		
		ModelAndView mode = new ModelAndView("views");
		mode.addObject("pager", page);
		return mode;
	}
	
	@RequestMapping("queryAlls")
	@ResponseBody
	public  HashMap<String, Object> queryAlls(String page,String rows,HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		
		HashMap<String,Object>  rt= new HashMap<String,Object>();
		int curPage = Integer.parseInt(page);
		int limit = Integer.parseInt(rows);
		int start = (curPage - 1)*limit+1;
		int end = curPage*limit;
		wherMap.put("start", start);
		wherMap.put("end", end);
		wherMap.put("orderbysort", " publishdate desc");
		List<HashMap<String,Object>> books = bookService.queryAll(wherMap);
		int result_count = bookService.queryAll_count(wherMap);
		System.out.println("=========================="+rows);
		rt.put("rows", books);
		rt.put("total", result_count);
		return rt;
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
	public  HashMap<String,Object> save(Book book,HttpServletRequest request,HttpServletResponse response){
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
	
	@RequestMapping("update")
	@ResponseBody
	public  HashMap<String,Object> update(Book book,HttpServletRequest request,HttpServletResponse response){
		HashMap<String,Object>  wherMap= new HashMap<String,Object>();
		
		HashMap<String,Object>  map= new HashMap<String,Object>();
		try {
			String country_no = request.getParameter("country");
			String city_no = request.getParameter("city");
			String area_no = request.getParameter("area");
			String id_no = request.getParameter("id") !=null ? request.getParameter("id"):request.getParameter("ID");
			System.out.println("========================="+id_no);
			String add = country_no+" "+city_no+" "+area_no;
			int id = Integer.parseInt(id_no);
			book.setID(id);
			book.setADDRESS(add);
			int id_a = bookService.updateByPrimaryKeySelective(book);
			System.out.println("========================="+id_a);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", true);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("dele")
	@ResponseBody
	public  HashMap<String,Object> dele(HttpServletRequest request,HttpServletResponse response){
		
		HashMap<String,Object>  map= new HashMap<String,Object>();
		try {
			String id_no = request.getParameter("id");
			int id = Integer.parseInt(id_no);
			int id_a = bookService.deleteByPrimaryKey(id);
			System.out.println("========"+id_a);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", true);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}
	@RequestMapping("detail")
	@ResponseBody
	public  String detail(HttpServletRequest request,HttpServletResponse response){
		String index = request.getParameter("index");
		String str = "<form method='post'>"+
	"<table class='dv-table' style='width:100%;background:#fafafa;padding:5px;margin-top:5px;'>"+
		"<tr>"+
			"<td>NAME</td>"+
			"<td><input name='NAME' class='easyui-validatebox' required='true'></input></td>"+
			"<td>AUTHOR</td>"+
			"<td><input name='AUTHOR' class='easyui-validatebox' required='true'></input></td>"+
		"</tr>"+
		"<tr>"+
			"<td>price</td>"+
			"<td><input name='PRICE'></input></td>"+
			"<td>Email</td>"+
			"<td><input valueField='TYPE' textField='TYPE_NAME' name='TYPE' class='easyui-combobox' editable='false' url='queryType'></input></td>"+
		"</tr>"+
	"</table>"+
	"<div style='padding:5px 0;text-align:right;padding-right:30px'>"+
		"<a href='#' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='saveItem("+index+")'>Save</a>"+
		"<a href='#' class='easyui-linkbutton' iconCls='icon-cancel' plain='true' onclick='cancelItem("+index+")'>Cancel</a>"+
	"</div>"+
"</form>";
		return str;
	}
	
}
