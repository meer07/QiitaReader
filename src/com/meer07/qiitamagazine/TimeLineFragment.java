package com.meer07.qiitamagazine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class TimeLineFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener{
	private final String path = "https://qiita.com/api/v1/items?per_page=100";
	private Thread thread;
	private ProgressDialog progressDialog;
	private ListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    final Handler handler = new Handler(){
    	@Override
        public void handleMessage(Message msg) {
            progressDialog.dismiss();
        }
    };

    final Runnable onRefreshRunnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mSwipeRefreshLayout.setEnabled(true);
			mSwipeRefreshLayout.setRefreshing(false);
		}
	};

	final Runnable onStartRunnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				refreshStart();
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
		setList();
	}

	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setClickListener();
	}

	@Override
	public void onStart(){
		super.onStart();

	}

	@Override
	public void onPause(){
		super.onPause();

	}

	@Override
	public void onResume() {
	   super.onResume();
	}

	@SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.timeline_fragment, container, false);

		mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipelayout);
		mSwipeRefreshLayout.setColorScheme(R.color.red,R.color.green,R.color.blue,R.color.orange);
		mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		return view;
	}

	private void setClickListener(){
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

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		refreshStart();
		refreshEnd();
	}

	private void refreshStart(){
		adapter = new ListAdapter(getActivity());
		JsonParse parse = new JsonParse(adapter);
		parse.execute(path);
		setListAdapter(adapter);
	}

	private void refreshEnd() {
		handler.removeCallbacks(onRefreshRunnable);
		handler.postDelayed(onRefreshRunnable, 3000);
		mSwipeRefreshLayout.setEnabled(false);
	}

	private void setList() {
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(getString(R.string.loading_newposts));
		progressDialog.setCancelable(true);
		progressDialog.show();

		thread = new Thread(onStartRunnable);
		thread.start();
	}
}
