package com.meer07.qiitasearch;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

public class FragmentPagerManager extends FragmentPagerAdapter {
	private static int page_count;
	
	public FragmentPagerManager(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	// タブ管理
	@Override
	public ListFragment getItem(int position) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			TimeLineFragment t_fragment = new TimeLineFragment();
			return t_fragment;
		case 1:
			SearchFragment s_fragment = new SearchFragment();
			return s_fragment;
		default:
			BookMarkFragment b_fragment = new BookMarkFragment();
			return b_fragment;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return page_count;
	}

	
	public void setPageCount(int tab_count) {
		page_count = tab_count;
	}
}
