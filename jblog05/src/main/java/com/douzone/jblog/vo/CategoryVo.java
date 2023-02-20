package com.douzone.jblog.vo;

public class CategoryVo {
	
	private Long no;
	private String name;
	private String context;
	private String id;
	private Long countPost;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public Long getCountPost() {
		return countPost;
	}
	public void setCountPost(Long countPost) {
		this.countPost = countPost;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", id=" + id + ", countPost=" + countPost + ", context="
				+ context + "]";
	}	
}
