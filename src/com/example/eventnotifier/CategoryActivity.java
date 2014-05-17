package com.example.eventnotifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class CategoryActivity extends Activity {
	private final static String TAG = "MainActivity";
	private String greeting = "Hello world";
	private boolean ok = true;
 

	
	public void onDestroy(){
		ok = false;
		super.onDestroy();
	}
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
                Log.d(TAG, "onCreate."); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_main);
 
		EditText greetingField = (EditText)findViewById(R.id.greeting);
 
		/**
		 * When the text changes, we change the greeting accordingly.
		 */
		greetingField.addTextChangedListener(new TextWatcher() {
 
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
 
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
 
			@Override
			public void afterTextChanged(Editable s) {
				greeting = s.toString();
			}
		});
 
		Thread greetingServer = new Thread(new SingleThreadedServer());
		greetingServer.start();
		
		Button getGreeting = (Button)findViewById(R.id.get);
		getGreeting.setOnClickListener(new View.OnClickListener() {
		 
					@Override
					public void onClick(View v) {
						final String TAG = "ClientSocket";
						// The address of the server.
						final String friendId = ((EditText)findViewById(R.id.friend_identity)).getText().toString();
		 
						// Create then start the "worker" thread.
						new Thread(new Runnable() {
							@Override
							public void run() {
								// The text field that is to be updated.
								final EditText responseGreetingField = (EditText)findViewById(R.id.response);
								String responseGreeting = "";
								try {
									// Open the socket.
									Socket clientSocket = new Socket(friendId, 9000);
									// Get and wrap its input stream for reading the response.
									BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
									// Do the actual reading.
									responseGreeting = responseReader.readLine();
									// Release the resource.
									clientSocket.close();
								} catch (UnknownHostException e) {
									Log.e(TAG, "Unknown host");
									Toast.makeText(CategoryActivity.this, "Unknown host.", Toast.LENGTH_SHORT).show();
								} catch (IOException e) {
									Log.e(TAG, "Error opening client socket.");
									Toast.makeText(CategoryActivity.this, "Cannot open socket..", Toast.LENGTH_SHORT).show();
								}
		 
								// We need a final local variable in order to access it from an inner class.
								final String response = responseGreeting;
		 
								// Do the UI update.
								responseGreetingField.post(new Runnable() {
									@Override
									public void run() {
										if (response != null) {
											// Set the contents of the text field to the received response.
											responseGreetingField.setText(response);
										} else {
											Toast.makeText(CategoryActivity.this, "Your friend said nothing.", Toast.LENGTH_SHORT).show();
										}
									}	
								});
							}
						}).start();
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

