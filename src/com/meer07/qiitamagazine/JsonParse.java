package com.meer07.qiitamagazine;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

// Androidでは非同期通信以外はNG
public class JsonParse extends AsyncTask<String, Integer, List<NewPost>>{
	private ListAdapter adapter;
	
	public JsonParse(ListAdapter adapter) {
		// TODO Auto-generated constructor stub
		super();
		this.adapter = adapter;
	}
	
	// Gsonを利用してNewPostクラスにparse
	public List<NewPost> json_Parse(String path) throws NetworkErrorException {
        try {
            HttpRequest request = new HttpRequest();
            String jsonString = request.Request(path);
            Log.d("JSON",jsonString);
            Gson gson = new Gson();
            List<NewPost> timeline = gson.fromJson(jsonString,new TypeToken<List<NewPost>>(){}.getType());
            return timeline;

        }
        catch (NetworkErrorException e)
        {
            Log.e("NetworkErrorException",e.getMessage());
//            return null;
            throw new NetworkErrorException(e);
        }

	}

	// バックグラウンドで通信と第一段階のパース(リストにする)を行う
	@Override
	protected List<NewPost> doInBackground(String... params) {
		// TODO Auto-generated method stub
        try {		String path = params[0];
            Log.d("myapp", path);
            List<NewPost> posts = json_Parse(path);
            return posts;
        } catch (NetworkErrorException e)
        {
            return null;
        }
    }
	
	// 第二段階のパースを行った後、アダプタにデータを追加する 
	@Override
    protected void onPostExecute(List<NewPost> posts){
		if(posts != null){
			for (int i = 0; i < posts.size(); i++) {
                String tag = "";
                NewPost post = posts.get(i);
				String title = new String(post.getTitle());
				String url = new String(post.getURL());
				String time = new String(post.getCreateTimes());
				String stock = new String(post.getStock());
				
				for (Tags tags : post.getTags()) {
                    if(tag!="")
                        tag+=" , ";
					tag += tags.getName();
				}
			
				String[] list = {title,url,tag,time,stock};
				adapter.add(list);
			}
		}
        else
        {
            String title = "通信エラー";
            String[] list = {title,"","","",""};
            adapter.add(list);

        }
	}
}
