package com.meer07.qiitamagazine;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity implements TabListener{
	private ViewPager pager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pager = (ViewPager)findViewById(R.id.Pager);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// タブをセット
		actionBar.addTab(getActionBar().newTab().setText(getString(R.string.newpost)).setTabListener(this));
		actionBar.addTab(getActionBar().newTab().setText(getString(R.string.search)).setTabListener(this));
		actionBar.addTab(getActionBar().newTab().setText(getString(R.string.bookmarks)).setTabListener(this));
		int tab_count = actionBar.getTabCount();


		FragmentManager fm = getSupportFragmentManager();
		FragmentPagerManager fpm = new FragmentPagerManager(fm);
		fpm.setPageCount(tab_count);
		pager.setAdapter(fpm);

		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position){
				actionBar.setSelectedNavigationItem(position);
			}
		});
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(pager!= null){
			pager.setCurrentItem(tab.getPosition());
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
