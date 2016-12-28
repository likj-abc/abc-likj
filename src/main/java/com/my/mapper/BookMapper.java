package com.my.mapper;

import com.my.bean.Book;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(BigDecimal ID);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(BigDecimal ID);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    
    List<HashMap<String,Object>> queryAll(HashMap<String,Object>  wherMap);
    
    int queryAll_count(HashMap<String,Object>  wherMap);
}