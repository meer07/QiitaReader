package com.meer07.qiitasearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class BookMarkAdapter extends ArrayAdapter<BookMarkItem> {
	private final int resource = R.layout.bookmarklist;
	
	public BookMarkAdapter(Context context) {
		super(context,0);
		// TODO Auto-generated constructor stub
	}

	// Viewをセット
	@Override
	public View getView(final int position,View convertView,ViewGroup parent){
		View view;
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(resource, parent,false);
		}else {
			view = convertView;
		}
		
		// 要素をViewにセットする
		BookMarkItem item = getItem(position);
		TextView title = (TextView)view.findViewById(R.id.title);
		title.setText(item.getTitle());
		
		ImageButton imageButton = (ImageButton)view.findViewById(R.id.delete);
		imageButton.setTag(position);
		imageButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BookMarkItem item = getItem(position);
				BookMarkModel model = new BookMarkModel(getContext().getApplicationContext());
				remove(item);
            	notifyDataSetChanged();
            	
            	// データベースからデータを消去
            	model.openDatabase();
            	model.deleteList(item.getId());
            	model.closeDatabase();
			}
		});
	
		return view;
	}
}
