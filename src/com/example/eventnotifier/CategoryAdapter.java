package com.example.eventnotifier;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
	
	private ArrayList<Category> content;
	private Activity context;
	
	public CategoryAdapter(Activity context, ArrayList<Category> content) {
		this.content = content;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return content.size();
	}
	
	@Override
	public Object getItem(int position) {
		return content.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = context.getLayoutInflater();
		Category categoryContent = (Category)getItem(position);
		//if (position % 2 == 0)
			convertView = layoutInflater.inflate(R.layout.category1, parent, false);
		//else
		//	convertView = layoutInflater.inflate(R.layout.category2, parent, false);
		ImageView imageView1 = (ImageView)convertView.findViewById(R.id.imageView1);
		imageView1.setImageResource(PictureFinder.findPictureByName(categoryContent.getPicture()));
		TextView textView1 = (TextView)convertView.findViewById(R.id.textView1);
		textView1.setText(categoryContent.getName());
		TextView textView2 = (TextView)convertView.findViewById(R.id.textView2);
		textView2.setText(categoryContent.getDescription());
		TextView textView3 = (TextView)convertView.findViewById(R.id.textView3);
		textView3.setText(categoryContent.getDebut());		
		
		return convertView;
	}
}
