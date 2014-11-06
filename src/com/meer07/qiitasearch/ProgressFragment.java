package com.meer07.qiitasearch;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressFragment extends DialogFragment {
	private static ProgressFragment instance;
	private ProgressDialog progressDialog;
	
	public static ProgressFragment newInstance(String title,String message){
		instance = new ProgressFragment();
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		bundle.putString("message", message);
		
		instance.setArguments(bundle);
		
		return instance;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		if(progressDialog != null){
			return progressDialog;
		}
		
		String title = getArguments().getString("title");
		String message = getArguments().getString("message");
		
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		setCancelable(false);
		
		return progressDialog;
	}
	
	@Override
	public Dialog getDialog(){
		return progressDialog;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		progressDialog = null;
	}
}
