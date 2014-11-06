package com.meer07.qiitasearch;
import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{
	public String title; // タイトル　
	public String tag; // タグ
	public String url; // リンクのURL
	public String time; // 投稿からの経過時間
	public String stock;
	
	public Item(String[] list){
		this.title = list[0];
		this.url = list[1];
		this.tag = list[2];
		this.time = list[3];
		this.stock = list[4];
	}
	
	public Item(Parcel in){
		this.title = in.readString();
		this.url = in.readString();
		this.tag = in.readString();
		this.time = in.readString();
		this.stock = in.readString();
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(title);
		dest.writeString(tag);
		dest.writeString(url);
		dest.writeString(time);
		dest.writeString(stock);
	}

	public static final Creator<Item> CREATOR = new Creator<Item>(){

		@Override
		public Item createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Item(source);
		}

		@Override
		public Item[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Item[size];
		}
		
	};
}
