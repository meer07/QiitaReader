package com.meer07.qiitamagazine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdMobFragment extends Fragment {
	private AdView mAdView;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
	    return inflater.inflate(R.layout.admob_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle bundle) {
	    super.onActivityCreated(bundle);
	    mAdView = (AdView) getView().findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    mAdView.loadAd(adRequest);
	}

	 @Override
     public void onPause() {
         if (mAdView != null) {
             mAdView.pause();
         }
         super.onPause();
     }

     @Override
     public void onResume() {
         super.onResume();
         if (mAdView != null) {
             mAdView.resume();
         }
     }

     @Override
     public void onDestroy() {
         if (mAdView != null) {
             mAdView.destroy();
         }
         super.onDestroy();
     }
}
