package com.meer07.qiitasearch;

import java.util.List;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// Androidでは非同期通信以外はNG
public class JsonParse extends AsyncTask<String, Integer, List<NewPost>>{
	private ListAdapter adapter;
	
	public JsonParse(ListAdapter adapter) {
		// TODO Auto-generated constructor stub
		super();
		this.adapter = adapter;
	}
	
	// Gsonを利用してNewPostクラスにparse
	public List<NewPost> jsonParse(String path){
		HttpRequest request = new HttpRequest();
		String jsonString = request.Request(path);		
		Gson gson = new Gson();
		List<NewPost> timeline = gson.fromJson(jsonString,new TypeToken<List<NewPost>>(){}.getType());
		
		return timeline;
	}

	// バックグラウンドで通信と第一段階のパース(リストにする)を行う
	@Override
	protected List<NewPost> doInBackground(String... params) {
		// TODO Auto-generated method stub
		String path = params[0];
		Log.d("myapp", path);
		List<NewPost> posts = jsonParse(path);
		return posts;
	}
	
	// 第二段階のパースを行った後、アダプタにデータを追加する 
	@Override
    protected void onPostExecute(List<NewPost> posts){
		for (int i = 0; i < posts.size(); i++) {
			NewPost post = posts.get(i);
			String title = new String(post.title);
			String url = new String(post.url);
			String[] list = {title,url};
			adapter.add(list);
		}
	}
}
