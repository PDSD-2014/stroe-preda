package com.example.eventnotifier;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

	public class SingleThreadedServer implements Runnable {
		private String greeting = "Hello world";
		boolean ok = true;
		 
		private final static String TAG = "ServerThread";
 
		// For a TCP connection (i.e. a server) we need a ServerSocket
		private ServerSocket in;
		
 
		// In the constructor we try creating the server socket, on port 9000.
		public SingleThreadedServer() {
			try {
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
						writer.print(greeting);
		 
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
