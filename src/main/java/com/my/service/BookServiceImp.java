package com.my.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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
	public int deleteByPrimaryKey(BigDecimal ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Book record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Book record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Book selectByPrimaryKey(BigDecimal ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Book record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Book record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<HashMap<String, Object>> queryAll(HashMap<String, Object> wherMap) {
		List<HashMap<String,Object>> books = bookMapper.queryAll(wherMap);
		return books;
	}

	public int queryAll_count(HashMap<String, Object> wherMap) {
		// TODO Auto-generated method stub
		return bookMapper.queryAll_count(wherMap);
	}

}
