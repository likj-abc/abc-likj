package com.my.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.my.bean.Book;

public interface BookService {
    int deleteByPrimaryKey(int ID) throws Exception;

    int insert(Book record);

    int insertSelective(Book record) throws Exception;

    Book selectByPrimaryKey(BigDecimal ID);

    int updateByPrimaryKeySelective(Book record) throws Exception;

    int updateByPrimaryKey(Book record);
    
    List<HashMap<String,Object>> queryAll(HashMap<String,Object>  wherMap);
    
    int queryAll_count(HashMap<String,Object>  wherMap);
    
    List<HashMap<String,Object>> queryType(HashMap<String,Object>  wherMap);
    
    List<HashMap<String,Object>> queryCountry(HashMap<String,Object>  wherMap);
    
    List<HashMap<String,Object>> queryCity(HashMap<String,Object>  wherMap);
    
    List<HashMap<String,Object>> queryArea(HashMap<String,Object>  wherMap);
    
    int getNextSeq(); 
}
