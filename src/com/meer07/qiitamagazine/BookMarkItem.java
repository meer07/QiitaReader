package com.meer07.qiitamagazine;

public class BookMarkItem {
	private int id;
	private String title;
	private String url;
	
	public BookMarkItem(int id,String title,String url) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.title = title;
		this.url = url;
	}
	
	public int getId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getURL(){
		return url;
	}
}
