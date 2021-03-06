package com.meer07.qiitamagazine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.meer07.qiitamagazine.R;

public class ListAdapter extends ArrayAdapter<Item> {
	private final int resource = R.layout.custom_listview_item;

	public ListAdapter(Context context) {
		super(context,0);
		// TODO Auto-generated constructor stub
	}

	// listviewのrowを作成して返す
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		View view;

		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(resource, parent,false);
		}else {
			view = convertView;
		}

		Item item = getItem(position);

		// タイトルをセット
		TextView title = (TextView)view.findViewById(R.id.title);
		title.setText(item.title);

		// タグをセット
		TextView tag = (TextView)view.findViewById(R.id.tag);
		tag.setText(item.tag);

		// ストック数をセット
		TextView stock = (TextView)view.findViewById(R.id.stock);
		stock.setText(item.stock+" "+"ストック");

		// 投稿からの経過時間をセット
		TextView time = (TextView)view.findViewById(R.id.time);
		time.setText(item.time+"前");

		return view;
	}

	// リストに追加
	public void add(String[] list){
		Item item = new Item(list);
		super.add(item);
	}
}
