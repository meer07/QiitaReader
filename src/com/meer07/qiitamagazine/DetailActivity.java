package com.meer07.qiitamagazine;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class DetailActivity extends Activity {
	public String url,title;
	private BookMarkModel db;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		url = intent.getStringExtra("URL");
		title = intent.getStringExtra("title");
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		db = new BookMarkModel(getApplicationContext());
		setContentView(R.layout.datail_activity);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.share: // シェアする
			Share();
			break;
		case R.id.bookmark: // ブックマークする
			BookMark();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	// シェア
	private void Share() {
		try {
		    Intent intent = new Intent();
		    intent.setAction(Intent.ACTION_SEND);
		    intent.setType("text/plain");
		    intent.putExtra(Intent.EXTRA_TEXT, url);
		    startActivity(Intent.createChooser(intent, "共有する"));
		} catch (Exception e) {
		}
	}

	// ブックマーク
	private void BookMark(){
		db.openDatabase();
		db.saveBookMark(title, url);
		db.closeDatabase();

		Toast.makeText(this, getString(R.string.bookmarked), Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // アクションバー内に使用するメニューアイテムを注入します。
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_items, menu);
	    return super.onCreateOptionsMenu(menu);
	}
}
