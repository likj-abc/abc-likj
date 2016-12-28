package com.my.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Page {
	private int cur_page = 1;//当前页
	private int result_count;//总条数
	private int page_size;//每页条数
	private int total_page;//总页数
	private int pre_page;//上一页
	private int next_page;//下一页
	private boolean hasPre;//是否有上一页
	private boolean hasNext;//是否有下一页
	private boolean hasFirst;//是否是第一页
	private boolean hasLast;//是否是最后一页
	private List<HashMap<String,Object>> resultMap;//每页的数
	private int first_page = 1;//第一页
	private int last_page;//最后一页

	public Page() {
		super();
	}

	public Page(int cur_page,int result_count,int page_size,List<HashMap<String,Object>> resultMap){
		this.cur_page = cur_page;
		this.result_count = result_count;
		this.page_size = page_size;
		this.resultMap = resultMap;
		this.total_page = (result_count - 1 ) / page_size + 1;
	}
	
	public int getCur_page() {
		return cur_page;
	}

	public void setCur_page(int cur_page) {
		this.cur_page = cur_page;
	}

	public int getResult_count() {
		return result_count;
	}

	public void setResult_count(int result_count) {
		this.result_count = result_count;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getPre_page() {
		if(isHasPre()){
			return this.next_page = getCur_page() - 1;
		}else{
			return this.next_page = getCur_page();
		}
	}

	public void setPre_page(int pre_page) {
		this.pre_page = pre_page;
	}

	public int getNext_page() {
		if(isHasNext()){
			return this.pre_page = getCur_page() + 1;
		}else{
			return this.pre_page = getCur_page();
		}
	}

	public void setNext_page(int next_page) {
		this.next_page = next_page;
	}

	public boolean isHasPre() {
		if(getCur_page()>1){
			return this.hasPre = true;
		}else{
			return this.hasPre = false;
		}
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public boolean isHasNext() {
		if(getCur_page()<getTotal_page()){
			return this.hasNext = true;
		}else{
			return this.hasNext = false;
		}
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasFirst() {
		if(getCur_page() == 1){
			return this.hasFirst = true;
		}else{
			return this.hasFirst = false;
		}
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}

	public boolean isHasLast() {
		if(getCur_page() == getTotal_page()){
			return this.hasLast = true;
		}else{
			return this.hasLast = false;
		}
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

	public void setResultMap(List<HashMap<String, Object>> resultMap) {
		this.resultMap = resultMap;
	}
	
	public List<HashMap<String,Object>> getResultMap(){
		return this.resultMap;
	}

	public int getFirst_page() {
		return first_page = 1;
	}

	public void setFirst_page(int first_page) {
		this.first_page = first_page;
	}

	public int getLast_page() {
		return this.total_page;
	}

	public void setLast_page(int last_page) {
		this.last_page = last_page;
	}
	
	public List getPages(){
		List<String> list = new ArrayList<String>();
		if(total_page<6){
			for(int i = 1; i <= total_page;i++){
				list.add(String.valueOf(i));
			}
		}else{
			if(cur_page-1<=2){
				for(int i = 1; i <= 5;i++){
					list.add(String.valueOf(i));
				}
			}else{
				if(total_page-cur_page>2){
					for(int i = cur_page - 2; i <= cur_page + 2;i++){
						list.add(String.valueOf(i));
					}
				}else{
					for(int i = total_page - 4; i <= total_page ;i++){
						list.add(String.valueOf(i));
					}
				}
			}
		}
		return list;
	}
}
