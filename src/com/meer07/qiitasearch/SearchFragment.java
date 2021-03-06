package com.meer07.qiitasearch;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchFragment extends ListFragment {
	protected String path;
	protected ImageButton seachButton;
	protected EditText searchEditText;
	protected ListAdapter adapter;
	private ProgressDialog progressDialog;
	private Thread thread;
	
	final Handler handler = new Handler(){
    	@Override
        public void handleMessage(Message msg) {
            progressDialog.dismiss();
        }
    };
    
    final Runnable onStartRunnable = new Runnable() {	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			handler.sendEmptyMessage(0);
		}	
	};
	
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
				progressDialog = new ProgressDialog(getActivity());
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressDialog.setMessage("検索中です");
				progressDialog.setCancelable(true);
				progressDialog.show();
				
				path = "https://qiita.com/api/v1/search?q="+searchEditText.getText().toString()+"&per_page=100";
				thread = new Thread(onStartRunnable);
				thread.start();
				adapter = new ListAdapter(getActivity());
				JsonParse parse = new JsonParse(adapter);
				parse.execute(path);
				setListAdapter(adapter);
				hideKeyBoard(v);
				thread = new Thread(onStartRunnable);
				thread.start();
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
	
	private void hideKeyBoard(View v) {
		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
