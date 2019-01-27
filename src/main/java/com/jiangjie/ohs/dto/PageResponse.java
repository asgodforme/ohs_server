package com.jiangjie.ohs.dto;

import java.util.List;

/**
 * 分页返回对象
 * 
 * @author Administrator
 *
 */
public class PageResponse<T> {

	private List<T> content; // 数据域

	private int number; // 页数

	private int size; // 每页条数

	private long totalElements; // 总元素个数

	private int totalPages; // 总页数

	public PageResponse() {
		super();
	}

	public PageResponse(List<T> content, int number, int size, long totalElements, int totalPages) {
		super();
		this.content = content;
		this.number = number;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
