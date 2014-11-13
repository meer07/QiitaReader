package com.meer07.qiitamagazine;

public class Tags {
	private String name;
	private String url_name;
	private String icon_url;
	
	public Tags(String name,String url_name,String icon_url){
		this.name = name;
		this.url_name = url_name;
		this.icon_url = icon_url;
	}
	
	public String getName() {
		return name;
	}
	
	public String getURL_Name() {
		return url_name;
	}
	
	public String getIcon_Name() {
		return icon_url;
	}
}
