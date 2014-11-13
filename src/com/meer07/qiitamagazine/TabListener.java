package com.meer07.qiitamagazine;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
@SuppressWarnings("deprecation")

public class TabListener<T extends ListFragment> implements ActionBar.TabListener{
	private ViewPager pager;
	
	public TabListener(Activity activity,String tag,Class<T> clz,ViewPager pager){
		if (this.pager == null) {
			this.pager = pager;
		}
	}
	
	// タブが選択されてたとき
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (pager != null) {
			pager.setCurrentItem(tab.getPosition());
		}
	}

	// 選択されたタブの選択が解除されたとき
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}

	// 選択されたタブが選択されたとき
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
}
