package com.my.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.bean.Book;
import com.my.mapper.BookMapper;

@Service
@Transactional
public class BookServiceImp implements BookService {
	@Autowired
	BookMapper bookMapper;
	
	public BookServiceImp() {
		System.out.println("============================================");
		p();
	}
	public  void p() {  
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
	public int deleteByPrimaryKey(int ID) throws Exception {
		try {
			int a = bookMapper.deleteByPrimaryKey(ID);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除信息失败！");
		}
	}

	public int insert(Book record) {
		return 0;
	}

	public int insertSelective(Book record) throws Exception {
		try {
			int a = bookMapper.insertSelective(record);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("插入信息失败！");
		}
		
	}

	public Book selectByPrimaryKey(BigDecimal ID) {
		return null;
	}

	public int updateByPrimaryKeySelective(Book record) throws Exception {
		try {
			int a = bookMapper.updateByPrimaryKeySelective(record);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("更新信息失败！");
		}
	}

	public int updateByPrimaryKey(Book record) {
		return 0;
	}

	public List<HashMap<String, Object>> queryAll(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> books = bookMapper.queryAll(wherMap);
		return books;
	}

	public int queryAll_count(HashMap<String, Object> wherMap) {
		return bookMapper.queryAll_count(wherMap);
	}

	@Override
	public List<HashMap<String, Object>> queryType(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> types = bookMapper.queryType(wherMap);
		return types;
	}

	@Override
	public List<HashMap<String, Object>> queryCountry(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> country = bookMapper.queryCountry(wherMap);
		return country;
	}

	@Override
	public List<HashMap<String, Object>> queryCity(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> city = bookMapper.queryCity(wherMap);
		return city;
	}

	@Override
	public List<HashMap<String, Object>> queryArea(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> area = bookMapper.queryArea(wherMap);
		return area;
	}

	@Override
	public int getNextSeq() {
		int id = bookMapper.getNextSeq();
		return id;
	}

	@Override
	public List<HashMap<String, Object>> queryBooks(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> books = bookMapper.queryBooks(wherMap);
		return books;
	}
}
