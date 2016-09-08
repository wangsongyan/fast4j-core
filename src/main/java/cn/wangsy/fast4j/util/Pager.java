package cn.wangsy.fast4j.util;

import java.io.Serializable;

/** 
 * 说明：分页对象
 * @author wangsy
 * @date 创建时间：2016年9月7日 下午3:56:00
 */
public class Pager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_PAGE_INDEX = 1; //默认分页位置
	private static final int DEFAULT_PAGE_SIZE = 10; //默认分页数量

	private int pageIndex;
	private int pageSize;
	private int pageStart;
	private int pageEnd;
	
	public Pager(int pageIndex,int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public Pager(){
		this(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize < 1 ? 1 : pageSize;
	}

	public int getPageStart() {
		pageStart = (pageIndex - 1) * pageSize;
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		pageEnd = pageIndex * pageSize;
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	
}
