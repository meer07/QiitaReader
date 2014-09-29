package com.meer07.qiitasearch;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimeLineFragment extends ListFragment {
	private ListAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		adapter = new ListAdapter(getActivity());
		JsonParse parse = new JsonParse(adapter);
		parse.execute("/items");
		setListAdapter(adapter);
	}
	
	public void onStart(){
		super.onStart();
		
	}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.timeline_fragment, container, false);
	}
}
