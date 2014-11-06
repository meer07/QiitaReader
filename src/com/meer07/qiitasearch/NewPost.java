package com.meer07.qiitasearch;

import java.util.ArrayList;
import java.util.List;

// 新規投稿アイテム
public class NewPost {
	private String title,url,created_at_in_words,stock_count;
	private List<Tags> tags;
	
	public NewPost() {
		// TODO Auto-generated constructor stub
	}
	
	public NewPost(String title,String url,String created_at_in_words,String stock_count){
		this.title = title;
		this.url = url;
		this.created_at_in_words = created_at_in_words;
		this.tags = new ArrayList<Tags>();
		this.stock_count = stock_count;
	}
	
	public String getTitle() {
		return title;
	}
	
	public  String getURL() {
		return url;
	}
	
	public String getCreateTimes() {
		return created_at_in_words;
	}
	
	public List<Tags> getTags() {
		return tags;
	}
	
	public String getStock() {
		return stock_count;
	}
}
