package com.meer07.qiitasearch;

import java.util.ArrayList;
import java.util.List;

// 新しい
public class NewPost {
	private String title,url,created_at_in_words;
	private List<Tags> tags;
	
	public NewPost() {
		// TODO Auto-generated constructor stub
	}
	
	public NewPost(String title,String url,String created_at_in_words){
		this.title = title;
		this.url = url;
		this.created_at_in_words = created_at_in_words;
		this.tags = new ArrayList<Tags>();
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
}
