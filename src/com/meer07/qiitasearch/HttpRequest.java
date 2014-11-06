package com.meer07.qiitasearch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpRequest {
	public String Request(String path){
		try {
			String line;
			StringBuilder builder = new StringBuilder();
			String urlString = path.replace(" ", "%20");
		
			HttpClient client = new DefaultHttpClient();
			HttpGet method = new HttpGet(URI.create(urlString));
			HttpResponse response = client.execute(method);
			InputStream is = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			
			return builder.toString();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
}
