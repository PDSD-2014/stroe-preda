package com.example.eventnotifier;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class CategoriesActivity extends Activity {
	
	class Item{
	    private String name;
	    public void setName(String name){
	        this.name = name;
	    }
	    public String getName(){
	        return name;
	    }
	    public String toString(){
	        return name; 
	    }
	}

	@Override
	protected void onCreate (Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.categories_main);
		try {
			final ListView listView1 = (ListView)findViewById(R.id.listViewCat);
			CategoriesXmlParser categoriesXmlParser = new CategoriesXmlParser();
			InputStream inputstream = getAssets().open("categories.xml");
			ArrayList<Category> categories = categoriesXmlParser.parse(inputstream);
			for (int count=0; count<categories.size(); count++)
				Log.println(Log.DEBUG, "tag", categories.get(count).toString());
			CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
			listView1.setAdapter(categoryAdapter);

			listView1.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

			    	String fullText = parent.getItemAtPosition(position).toString();
			    	int end = fullText.indexOf("description:");
			    	String name = fullText.substring(6, end);
			    	Log.d("tag", "AAAAAAAAAAAAAAAAAA " + name + name.length());
			    	
			    	Intent i;
			    	if (name.equals("Sport ")) {
			    		i = new Intent(CategoriesActivity.this, SportActivity.class); 
			    		startActivity(i);
			    	}
			    	
			    	if (name.equals("Notices ")) {
			    		i = new Intent(CategoriesActivity.this, NoticesActivity.class); 
			    		startActivity(i);
			    		//Log.d("tag", "NOTICESSSSSSSSSSSS");
			    	}
			    	
			    	if (name.equals("Cinema ")) {
			    		i = new Intent(CategoriesActivity.this, CinemaActivity.class); 
			    		startActivity(i);
			    		//Log.d("tag", "CINEMAAAAA");
			    	}
			    	
			    	if (name.equals("Leisure ")) {
			    		i = new Intent(CategoriesActivity.this, FreeTimeActivity.class); 
			    		startActivity(i);
			    		//Log.d("tag", "LEISUREEEE");
			    	}
			    	
			    	
			    	if (name.equals("Holidays ")) {
			    		i = new Intent(CategoriesActivity.this, HolidaysActivity.class); 
			    		startActivity(i);
			    	}
			    	
			    	if (name.equals("Traffic ")) {
			    		i = new Intent(CategoriesActivity.this, TrafficActivity.class); 
			    		startActivity(i);
			    	}
			    	
			    	if (name.equals("Weather ")) {
			    		i = new Intent(CategoriesActivity.this, WeatherActivity.class); 
			    		startActivity(i);
			    	}
			    }
			});
					
		}
		catch (Exception exception) {
			Log.println(Log.ERROR, "error", exception.getMessage());
		}		
		Button back = (Button) findViewById(R.id.backBtn2);
		back.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}
}
