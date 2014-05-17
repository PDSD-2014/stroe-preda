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

	@Override
	protected void onCreate (Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.categories_main);
		try {
			ListView listView1 = (ListView)findViewById(R.id.listViewCat);
			CategoriesXmlParser categoriesXmlParser = new CategoriesXmlParser();
			InputStream inputstream = getAssets().open("categories.xml");
			ArrayList<Category> categories = categoriesXmlParser.parse(inputstream);
			for (int count=0; count<categories.size(); count++)
				Log.println(Log.DEBUG, "tag", categories.get(count).toString());
			CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
			listView1.setAdapter(categoryAdapter);

			listView1.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			        //String text = mStrings[position];
			        //YourImageClass img = mImages[position];
			        Intent i = new Intent(CategoriesActivity.this, CategoryActivity.class);
			        startActivity(i);
			    	//i.putExtra("TEXT", text);
			        //i.putExtra("IMAGE", i
			    	Log.println(Log.INFO,"Hello!", "Clicked! YAY!");
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
