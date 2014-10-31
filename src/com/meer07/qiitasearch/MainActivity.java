package com.meer07.qiitasearch;

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
		actionBar.addTab(getActionBar().newTab().setText("Qiita 新着投稿").setTabListener(this));
		actionBar.addTab(getActionBar().newTab().setText("Qiita キーワード検索").setTabListener(this));
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
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/
}
