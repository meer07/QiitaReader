package com.meer07.qiitasearch;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
public class TabListener<T extends ListFragment> implements ActionBar.TabListener{
	private ListFragment mFragment;
	private final Activity mActivity;
	private final String mTag;
	private final Class<T> mClass;
	
	public TabListener(Activity activity,String tag,Class<T> clz){
		mActivity = activity;
		mTag = tag;
		mClass = clz;
		mFragment = (ListFragment) mActivity.getFragmentManager().findFragmentByTag(mTag);
	}
	
	// タブが選択されてたとき
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager fm = mActivity.getFragmentManager();
		// TODO Auto-generated method stub
		if(mFragment == null){
			mFragment = (ListFragment) Fragment.instantiate(mActivity, mClass.getName());
			fm.beginTransaction().add(R.id.container,mFragment,mTag).commit();
		}else{
			if(mFragment.isDetached()){
				fm.beginTransaction().attach(mFragment).commit();
			}
		}
	}

	// 選択されたタブの選択が解除されたとき
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (mFragment != null) {
			FragmentManager fm = mActivity.getFragmentManager();
			fm.beginTransaction().detach(mFragment).commit();
		}
	}

	// 選択されたタブが選択されたとき
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
}
