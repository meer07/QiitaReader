package com.meer07.qiitasearch;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class DetailFragment extends Fragment {

	private String url;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		DetailActivity detailActivity = (DetailActivity)getActivity();
		url = detailActivity.url;
		Log.d("DetailView", url);
		setHasOptionsMenu(true);
	}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.detail_fragment, container, false);
		WebView webView = (WebView)v.findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
		return v;
	}
}
	
