package com.example.eventnotifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
 
public class CinemaActivity extends Activity {
	private final static String TAG = "CinemaActivity";
 
	Activity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate."); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cinema_main);
		context = this;
 
		final String TAG = "ClientSocket";
		// The address of the server.
		final String friendId = "127.0.0.1";

		// Create then start the "worker" thread.
		new Thread(new Runnable() {
			@Override
			public void run() {
				// The text field that is to be updated.
				final ListView responseListView = (ListView)findViewById(R.id.listViewCat);
				String responseGreeting = "";
				try {
					// Open the socket.
					Socket clientSocket = new Socket(friendId, 9000);
					
					PrintWriter toServer = null;
					toServer = new PrintWriter(clientSocket.getOutputStream(), true);
					toServer.println("cinema");

					// Get and wrap its input stream for reading the response.
					BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					// Do the actual reading.
					responseGreeting = responseReader.readLine();

					// Release the resource.
					clientSocket.close();
				} catch (UnknownHostException e) {
					Log.e(TAG, "Unknown host");
					Toast.makeText(CinemaActivity.this, "Unknown host.", Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					Log.e(TAG, "Error opening client socket.");
					Toast.makeText(CinemaActivity.this, "Cannot open socket..", Toast.LENGTH_SHORT).show();
				}

				// We need a final local variable in order to access it from an inner class.
				final String[] listresponse = responseGreeting.split("/");			
												
				// Do the UI update.
				responseListView.post(new Runnable() {
					@Override
					public void run() {
						if (listresponse != null) {
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
						              android.R.layout.simple_list_item_1, android.R.id.text1, listresponse);
							responseListView.setAdapter(adapter); 
						}
						
					}
						
				});
				
				
			}
			
		}).start();
 	
		Button getGreeting = (Button)findViewById(R.id.get);
		getGreeting.setOnClickListener(new View.OnClickListener() {
		 
					@Override
					public void onClick(View v) {
						setResult(RESULT_OK);
						finish();
					}	
				});
 
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}


