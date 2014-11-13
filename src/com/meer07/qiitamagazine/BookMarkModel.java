package com.meer07.qiitamagazine;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BookMarkModel extends SQLiteOpenHelper {
	public static BookMarkModel model;
	final static private int DB_VERSION = 1;
	private SQLiteDatabase db;
	public BookMarkModel(Context context) {
		super(context.getApplicationContext(), "mydb.db", null, DB_VERSION);
		model = this;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) { // テーブルを生成
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE bookmark_table(_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,link_url TEXT NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // 更新処理
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists bookmark_table");
		onCreate(db);
		//onUpgrade(db, oldVersion, newVersion);
	}
	
	public void openDatabase() {
		db = getWritableDatabase();
	}
	
	public void closeDatabase() {
		close();
	}
	
	// リストアイテムを削除
	public boolean deleteList(int positon) {
		return db.delete("bookmark_table","_id"+"="+positon,null) > 0;
	}
	
	// 一覧取得
	public Cursor getAllList() {
		return db.query("bookmark_table", null, null, null, null, null, null);
	}
	
	// ブックマークの保存
	public void saveBookMark(String title,String url){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title", title);
		values.put("link_url", url);
		db.insertOrThrow("bookmark_table", null, values);
	}
}
