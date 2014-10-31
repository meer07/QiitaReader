package com.meer07.qiitasearch;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class TimeLineFragment extends ListFragment {
	public String path = "https://qiita.com/api/v1/items";
	private ListAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		adapter = new ListAdapter(getActivity());
		JsonParse parse = new JsonParse(adapter);
		parse.execute(path);
		setListAdapter(adapter);
		setHasOptionsMenu(true);
	}
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setClickListener();
	}
	
	public void onStart(){
		super.onStart();
		
	}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.timeline_fragment, container, false);
	}
	
	private void setClickListener(){
		ListView listView = getListView();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position, long id){
				Item item = adapter.getItem(position);
				String urlString = item.url;
				Intent intent = new Intent(getActivity(),DetailActivity.class);
				intent.putExtra("URL", urlString);
				startActivity(intent);
			}
		});
	}
}
