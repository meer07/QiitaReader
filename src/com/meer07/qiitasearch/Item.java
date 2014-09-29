package com.meer07.qiitasearch;
import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{
	public String tag; // タグになっているがここではタイトル
	public String url; // リンクのURL

	public Item(String[] list){
		this.tag = list[0];
		this.url = list[1];
	}
	
	public Item(Parcel in){
		this.tag = in.readString();
		this.url = in.readString();
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(tag);
		dest.writeString(url);
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
