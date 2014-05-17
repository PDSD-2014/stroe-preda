package com.example.eventnotifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.util.Log;

	public class SingleThreadedServer implements Runnable {
		private String greeting = "Hello world";
		int step = 0;
		boolean ok = true;
		
		Activity context;
		Database database;
		 
		private final static String TAG = "ServerThread";
 
		// For a TCP connection (i.e. a server) we need a ServerSocket
		private ServerSocket in;
		
 
		// In the constructor we try creating the server socket, on port 9000.
		public SingleThreadedServer(Activity ctxt) {
			try {
				context = ctxt;
				database = Database.getInstance(context);
				// Beware: Only privileged users can use ports below 1023.
				in = new ServerSocket(9000);
			} catch (IOException e) {
				Log.e(TAG, "Cannot create socket. Due to: " + e.getMessage());
			}
		}
 
		@Override
		public void run() {
 
			// Always try serving incoming requests.
			while(ok) {
				//For every request we are allocated a new socket.
 
				try {
					// Wait in blocked state for a request.
					final Socket incomingRequest = in.accept();

 
					new Thread(new Runnable() {
						@Override
						public void run() {
						// When accept() returns a new request was received.
					
						// We use the incomingRequest socket for I/O
						Log.d(TAG, "New request from: " + incomingRequest.getInetAddress());
						
						String type="";
						try {
							BufferedReader fromClient = new BufferedReader(
					       		new InputStreamReader(incomingRequest.getInputStream()));
							type =  new String(fromClient.readLine());
						} catch (IOException e) {
							Log.e(TAG, "Cannot get from client.");
						}
						
						Log.d(TAG, "New type : " + type);
		 
						// Get its associated OutputStream for writing.
						OutputStream responseStream = null;
						try {
							responseStream = incomingRequest.getOutputStream();
						} catch (IOException e) {
							Log.e(TAG, "Cannot get outputstream.");
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// Wrap it with a PrinStream for convenience.
						PrintStream writer = new PrintStream(responseStream);
						
						String toClient = "";
						int i;
						if (type.equals("sport")) {
						
							for (i = 0 ; i<database.selectSport().size();i++) {
								toClient = toClient + database.selectSport().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						if (type.equals("notices")) {
							
							for (i = 0 ; i<database.selectNotices().size();i++) {
								toClient = toClient + database.selectNotices().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						if (type.equals("cinema")) {
							
							for (i = 0 ; i<database.selectCinema().size();i++) {
								toClient = toClient + database.selectCinema().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						if (type.equals("leisure")) {
							
							for (i = 0 ; i<database.selectFreeTime().size();i++) {
								toClient = toClient + database.selectFreeTime().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						if (type.equals("holidays")) {
							
							for (i = 0 ; i<database.selectHolidays().size();i++) {
								toClient = toClient + database.selectHolidays().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						if (type.equals("traffic")) {
							
							for (i = 0 ; i<database.selectTraffic().size();i++) {
								toClient = toClient + database.selectTraffic().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						if (type.equals("weather")) {
							
							for (i = 0 ; i<database.selectWeather().size();i++) {
								toClient = toClient + database.selectWeather().get(i).getDescription() + "/";
							}
							writer.print(toClient);
						}
						
						// Make sure data is sent and allocated resources are cleared.
						try {
							incomingRequest.close();
						} catch (IOException e) {
							Log.e(TAG, "Error finishing request.");
						}
		 
						Log.d(TAG, "Sent greeting.");
					// Continue the looping.
					}}).start();
							
			} catch (IOException e) {
				Log.e(TAG, "Error when accepting connection.");
			}
			

			
		}
			
			try {
				in.close();
			} catch (IOException e) {
				Log.e(TAG, "Error finishing request.");
			}			
	}
		
}
