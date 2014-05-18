package com.example.eventnotifier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class MainActivity extends Activity {
	
	Activity context;
	SingleThreadedServer sts;
	//Database database;
	
	@Override
    public void onDestroy()
    {
        super.onDestroy();
        sts.onDestroy();
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		//database = Database.getInstance(this);
		
		sts = new SingleThreadedServer(context);
		Thread greetingServer = new Thread(sts);
		greetingServer.start();
		
		Button start = (Button) findViewById(R.id.startButton);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//testDB();
				System.out.println("buton");
				Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
				startActivity(intent);
				//populateDB();
				/*
				database.selectSport();
				database.selectTraffic();
				database.selectWeather();
				database.seectCinema();
				database.selectNotices();
				database.selectHolidays();
				database.selectFreeTime();
				*/
			}
		});

	}

	public void populateDB(){
		/*
		Sport sport1 = new Sport(1, "O noua retragere pentru Simona Halep" , 2);
		Sport sport2 = new Sport(2, "S-au pus in vanzare biletele pentru meciul Steaua-Astra de vineri 23 mai 2014" , 2);
		Sport sport3 = new Sport(3, "Romania campiona europeana pe echipe la gimnastica" , 2);
		
		Weather weather1 = new Weather(1, "Cod portocaliu in 5 judete din tara. Sunt asteptate precipitatii abundente", 1);
		Weather weather2 = new Weather(2, "Se anunta o vara secetoasa. Temperaturile vor depasi mediile anuale in toate regiunile tarii", 2);
		Weather weather3 = new Weather(3, "Sfarsit de saptamana fara nori si cu soare", 2);
		
		Traffic traffic1 = new Traffic(1, "Traficul pe Calea Victoriei este ingreunat din cauza lucrarilor de amenajare", 1);
		Traffic traffic2 = new Traffic(2, "Transalpina si Transfagarasan se vor deschide circulatiei pe 1 iunie", 2);
		
		Cinema cinema1 = new Cinema(1, "Doua noi filme americane vor fi filmate in Romania", 1);
		Cinema cinema2 = new Cinema(2, "Weekendul acesta au loc premiile de la Cannes", 2);
		
		Holidays holidays1 = new Holidays(1, "Bugetarii vor avea liber de Rusalii luni 9 iunie", 1);
		Holidays holidays2 = new Holidays(2, "Anul acesta de ziua Marinei vor avea loc festivitati speciale la Constanta", 2);
		
		FreeTime freeTime1 = new FreeTime(1, "Bucurestenii pot inchiria biciclete in Parcul Herastrau de la un nou centru aflat la intrarea de la Arcul de Triumf", 1);
		FreeTime freeTime2 = new FreeTime(2, "Sambata va avea loc Noaptea Alba a Muzeelor in toata tara", 1);
		
		Notices notices1 = new Notices(1, "Statul Roman recomanda cetatenilor sa nu calatoreasca in Ucraina in perioada urmatoare", 1);
		Notices notices2 = new Notices(2, "Ministerul Educatiei anunta noi schimbari pentru urmatorul an scolar", 2);
		
		
		//add entries to database
		
		database.addSport(sport1);
		database.addSport(sport2);
		database.addSport(sport3);
		
		database.addWeather(weather1);
		database.addWeather(weather2);
		
		database.addTraffic(traffic1);
		database.addTraffic(traffic2);
		
		database.addCinema(cinema1);
		database.addCinema(cinema2);
		
		database.addHolidays(holidays1);
		database.addHolidays(holidays2);
		
		database.addFreeTime(freeTime1);
		database.addFreeTime(freeTime2);
		
		database.addNotices(notices1);
		database.addNotices(notices2);
		

		//Log.d("Sport: ", sport.toString());
		database.addWeather(weather3);
		*/
	}
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
