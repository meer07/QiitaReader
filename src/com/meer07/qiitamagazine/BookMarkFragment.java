package com.meer07.qiitamagazine;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.meer07.qiitamagazine.R;

public class BookMarkFragment extends ListFragment {
	private BookMarkAdapter bookMarkAdapter;
	private BookMarkModel model;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		bookMarkAdapter = new BookMarkAdapter(getActivity());
		model = new BookMarkModel(getActivity());
		setListAdapter(bookMarkAdapter);
		setHasOptionsMenu(true);
	}

	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setClickListener();
	}

	// リストを再描画
	@Override
	public void onStart(){
		super.onStart();

		bookMarkAdapter.clear();
		openDB();
		bookMarkAdapter.notifyDataSetChanged();
	}

	@Override
	public void onResume() {
	   super.onResume();
	}

	@Override
	public void onPause(){
		super.onPause();

	}

	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.bookmark_fragment, container, false);
	}

	private void setClickListener(){
		ListView listView = getListView();

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),DetailActivity.class);
				BookMarkItem item = bookMarkAdapter.getItem(position);
				String urlString = item.getURL();
				String title = item.getTitle();
				intent.putExtra("URL", urlString);
				intent.putExtra("title", title);
				startActivity(intent);
			}
		});
	}

	// データベースからリストを生成
	private void openDB(){
		model.openDatabase();
		Cursor cursor = model.getAllList();
		if(cursor.moveToFirst()){
			do {
				BookMarkItem item = new BookMarkItem(cursor.getInt(cursor.getColumnIndex("_id")),
						cursor.getString(cursor.getColumnIndex("title")),
						cursor.getString(cursor.getColumnIndex("link_url")));
				bookMarkAdapter.add(item);
			} while (cursor.moveToNext());
		}
		model.close();
	}
}
