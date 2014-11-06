package com.meer07.qiitasearch;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchFragment extends ListFragment {
	protected String path;
	protected ImageButton seachButton;
	protected EditText searchEditText;
	protected ListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		path = "https://qiita.com/api/v1/search?q=";
	}
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setClickListener();
		startSearch();
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
	}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.search_fragment, container, false);
		seachButton = (ImageButton)v.findViewById(R.id.searchbutton);
		searchEditText = (EditText)v.findViewById(R.id.searchbar);
		return v;
	}
	
	// 検索を開始する。
	protected void startSearch(){
		seachButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				path = "https://qiita.com/api/v1/search?q="+searchEditText.getText().toString()+"&sort=stock&per_page=100";
				adapter = new ListAdapter(getActivity());
				JsonParse parse = new JsonParse(adapter);
				parse.execute(path);
				setListAdapter(adapter);
			}
		}); 
	}
	
	// リストの要素をクリックしたとき
	protected void setClickListener(){
		ListView listView = getListView();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position, long id){
				Item item = adapter.getItem(position);
				String urlString = item.url;
				String title = item.title;
				Intent intent = new Intent(getActivity(),DetailActivity.class);
				intent.putExtra("URL", urlString);
				intent.putExtra("title", title);
				startActivity(intent);
			}
		});
	}
}
