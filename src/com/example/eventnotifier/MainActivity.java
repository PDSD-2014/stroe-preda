package com.example.eventnotifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class MainActivity extends Activity {
	
	Activity context;
	//Database database;
	//private static final String url = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql339866";
	//private static final String user = "sql339866";
	//private static final String password = "kL5!cS2!";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		//database = Database.getInstance(this);
		
		Button start = (Button) findViewById(R.id.startButton);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//testDB();
				System.out.println("buton");
				Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
				startActivity(intent);
			}
		});

	}
/*
	public void testDB(){
		try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
             System.out.println("Database connection success"); 
             Log.d("MyApp","I am here");
            String result = "Database connection success\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Sport");
            ResultSetMetaData rsmd = rs.getMetaData();
          //  System.out.println(rsmd);
          
	}catch(Exception e){
		e.printStackTrace();
		Log.d("MyApp","Noooooooooooooooooooooooooooooooooooooooooooooooooooo");}
		
		
	}
*/
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
}
