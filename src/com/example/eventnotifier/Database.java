package com.example.eventnotifier;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "eventNotifier";
	public static final int DATABASE_VERSION = 1;
	
	
	public static final String ASSOCIATIONS_COURSE_TEACHER_TABLE_NAME = "associations_courses_teachers";
	public static final String ASSOCIATION_COURSE_TEACHER_ID = "association_course_teacher_id";
	public static final String COURSES_TABLE_NAME = "courses";
	public static final String COURSE_ID = "course_id";
	public static final String COURSE_NAME = "name";
	public static final String TEACHERS_TABLE_NAME = "teachers";
	public static final String TEACHER_ID = "teacher_id";
	public static final String TEACHER_NAME = "name";
	
	public static final String SPORT_TABLE_NAME = "sport";
	public static final String SPORT_ID = "id";
	public static final String SPORT_DESCRIPTION = "description";
	public static final String SPORT_COLOUR = "colour";
	
	public static final String WEATHER_TABLE_NAME = "weather";
	public static final String WEATHER_ID = "id";
	public static final String WEATHER_DESCRIPTION = "description";
	public static final String WEATHER_COLOUR = "colour";
	
	public static final String TRAFFIC_TABLE_NAME = "traffic";
	public static final String TRAFFIC_ID = "id";
	public static final String TRAFFIC_DESCRIPTION = "description";
	public static final String TRAFFIC_COLOUR = "colour";
	
	
	public static final String CINEMA_TABLE_NAME = "cinema";
	public static final String CINEMA_ID = "id";
	public static final String CINEMA_DESCRIPTION = "description";
	public static final String CINEMA_COLOUR = "colour";
	
	public static final String HOLIDAYS_TABLE_NAME = "holidays";
	public static final String HOLIDAYS_ID = "id";
	public static final String HOLIDAYS_DESCRIPTION = "description";
	public static final String HOLIDAYS_COLOUR = "colour";
	
	public static final String FREETIME_TABLE_NAME = "free_time";
	public static final String FREETIME_ID = "id";
	public static final String FREETIME_DESCRIPTION = "description";
	public static final String FREETIME_COLOUR = "colour";
	
	public static final String NOTICES_TABLE_NAME = "notices";
	public static final String NOTICES_ID = "id";
	public static final String NOTICES_DESCRIPTION = "description";
	public static final String NOTICES_COLOUR = "colour";
	
	public static final String FOREIGN_TABLE_NAME = "foreign";
	public static final String FOREIGN_ID = "id";
	public static final String FOREIGN_DESCRIPTION = "description";
	public static final String FOREIGN_COLOUR = "colour";
	
	private static Database instance;
	
	public static Database getInstance(Context context) {
		if (instance == null)
			instance = new Database(context.getApplicationContext());
		
		return instance;
	}

	private Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_SPORT_TABLE = "CREATE TABLE " + SPORT_TABLE_NAME + "("
				+ SPORT_ID + " INTEGER PRIMARY KEY, "
				+ SPORT_DESCRIPTION + " TEXT, "
				+ SPORT_COLOUR + " INTEGER)";
		
		String CREATE_WEATHER_TABLE = "CREATE TABLE " + WEATHER_TABLE_NAME + "("
				+ WEATHER_ID + " INTEGER PRIMARY KEY, "
				+ WEATHER_DESCRIPTION + " TEXT, "
				+ WEATHER_COLOUR + " INTEGER)";
		
		String CREATE_TRAFFIC_TABLE = "CREATE TABLE " + TRAFFIC_TABLE_NAME + "("
				+ TRAFFIC_ID + " INTEGER PRIMARY KEY, "
				+ TRAFFIC_DESCRIPTION + " TEXT, "
				+ TRAFFIC_COLOUR + " INTEGER)";
		
		String CREATE_CINEMA_TABLE = "CREATE TABLE " + CINEMA_TABLE_NAME + "("
				+ CINEMA_ID + " INTEGER PRIMARY KEY, "
				+ CINEMA_DESCRIPTION + " TEXT, "
				+ CINEMA_COLOUR + " INTEGER)";
		
		String CREATE_HOLIDAYS_TABLE = "CREATE TABLE " + HOLIDAYS_TABLE_NAME + "("
				+ HOLIDAYS_ID + " INTEGER PRIMARY KEY, "
				+ HOLIDAYS_DESCRIPTION + " TEXT, "
				+ HOLIDAYS_COLOUR + " INTEGER)";
		
		String CREATE_FREETIME_TABLE = "CREATE TABLE " + FREETIME_TABLE_NAME + "("
				+ FREETIME_ID + " INTEGER PRIMARY KEY, "
				+ FREETIME_DESCRIPTION + " TEXT, "
				+ FREETIME_COLOUR + " INTEGER)";
		
		String CREATE_NOTICES_TABLE = "CREATE TABLE " + NOTICES_TABLE_NAME + "("
				+ NOTICES_ID + " INTEGER PRIMARY KEY, "
				+ NOTICES_DESCRIPTION + " TEXT, "
				+ NOTICES_COLOUR + " INTEGER)";
		
		String CREATE_FOREIGN_TABLE = "CREATE TABLE " + FOREIGN_TABLE_NAME + "("
				+ FOREIGN_ID + " INTEGER PRIMARY KEY, "
				+ FOREIGN_DESCRIPTION + " TEXT, "
				+ FOREIGN_COLOUR + " INTEGER)";
		
		
		/*String CREATE_TEACHERS_TABLE = "CREATE TABLE " + TEACHERS_TABLE_NAME + "("
				+ TEACHER_ID + " INTEGER PRIMARY KEY, "
				+ TEACHER_NAME + " TEXT)";	*/
		
		db.execSQL(CREATE_SPORT_TABLE);
		db.execSQL(CREATE_WEATHER_TABLE);
		db.execSQL(CREATE_TRAFFIC_TABLE);
		db.execSQL(CREATE_CINEMA_TABLE);
		db.execSQL(CREATE_HOLIDAYS_TABLE);
		db.execSQL(CREATE_FREETIME_TABLE);
		db.execSQL(CREATE_NOTICES_TABLE);
		db.execSQL(CREATE_FOREIGN_TABLE);
		//db.execSQL(CREATE_TEACHERS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SPORT_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TRAFFIC_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + WEATHER_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CINEMA_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + HOLIDAYS_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + FREETIME_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + NOTICES_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + FOREIGN_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TEACHERS_TABLE_NAME);
		
		onCreate(db);
	}
	
	public void addSport(Sport sport) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(SPORT_ID, sport.getId());
		contentValues.put(SPORT_DESCRIPTION, sport.getDescription());
		contentValues.put(SPORT_COLOUR, sport.getColour());
		
		db.insert(SPORT_TABLE_NAME, null, contentValues);
		
		db.close();
	}
	
	public void addWeather(Weather weather){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(WEATHER_ID, weather.getId());
		contentValues.put(WEATHER_DESCRIPTION, weather.getDescription());
		contentValues.put(WEATHER_COLOUR, weather.getColour());
		
		db.insert(WEATHER_TABLE_NAME, null, contentValues);
		
		db.close();
	
		
	}
	
	public void addTraffic(Traffic traffic){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(TRAFFIC_ID, traffic.getId());
		contentValues.put(TRAFFIC_DESCRIPTION, traffic.getDescription());
		contentValues.put(TRAFFIC_COLOUR, traffic.getColour());
		
		db.insert(TRAFFIC_TABLE_NAME, null, contentValues);
		
		db.close();
		
	}
	
	public void addCinema(Cinema cinema){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(CINEMA_ID, cinema.getId());
		contentValues.put(CINEMA_DESCRIPTION, cinema.getDescription());
		contentValues.put(CINEMA_COLOUR, cinema.getColour());
		
		db.insert(CINEMA_TABLE_NAME, null, contentValues);
		
		db.close();
		
	}
	
	public void addHolidays(Holidays holidays){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(HOLIDAYS_ID, holidays.getId());
		contentValues.put(HOLIDAYS_DESCRIPTION, holidays.getDescription());
		contentValues.put(HOLIDAYS_COLOUR, holidays.getColour());
		
		db.insert(HOLIDAYS_TABLE_NAME, null, contentValues);
		
		db.close();
		
	}
	
	public void addFreeTime(FreeTime freeTime){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(FREETIME_ID, freeTime.getId());
		contentValues.put(FREETIME_DESCRIPTION, freeTime.getDescription());
		contentValues.put(FREETIME_COLOUR, freeTime.getColour());
		
		db.insert(HOLIDAYS_TABLE_NAME, null, contentValues);
		
		db.close();

		
	}
	
	public void addNotices(Notices notices){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(NOTICES_ID, notices.getId());
		contentValues.put(NOTICES_DESCRIPTION, notices.getDescription());
		contentValues.put(NOTICES_COLOUR, notices.getColour());
		
		db.insert(HOLIDAYS_TABLE_NAME, null, contentValues);
		
		db.close();
	}
	
	public void addForeign(Foreign foreign){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(FOREIGN_ID, foreign.getId());
		contentValues.put(FOREIGN_DESCRIPTION, foreign.getColour());
		contentValues.put(FOREIGN_COLOUR, foreign.getColour());
		
		db.insert(HOLIDAYS_TABLE_NAME, null, contentValues);
		
		db.close();
		
	}
	
	/*public void addTeacher(Teacher teacher) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(TEACHER_ID, teacher.getTeacherId());
		contentValues.put(TEACHER_NAME, teacher.getName());
		
		db.insert(TEACHERS_TABLE_NAME, null, contentValues);
		
		db.close();
	}*/
	
	public List<Sport> selectSport(){
		SQLiteDatabase db = this.getReadableDatabase();
		String SELECT_SPORT_QUERY;
		
		SELECT_SPORT_QUERY = "SELECT * FROM " + SPORT_TABLE_NAME;
		Cursor cursor = db.rawQuery(SELECT_SPORT_QUERY, null);
		ArrayList<Sport> result = new ArrayList<Sport>();
		
		if (cursor.moveToFirst()) {			
			do {
				Sport sport = new Sport();
				sport.setId(Integer.parseInt(cursor.getString(0)));
				sport.setDescription(cursor.getString(1));
				sport.setColour(Integer.parseInt(cursor.getString(2)));
				result.add(sport);
			} while (cursor.moveToNext());
		}
		Log.d("Sport: ", result.toString());
		return result;
		
	}
		
	/*public List<Teacher> selectTeachers(int courseId) {
		SQLiteDatabase db = this.getReadableDatabase();
		String SELECT_TEACHERS_QUERY;
		if (courseId != -1)
			SELECT_TEACHERS_QUERY = "SELECT t." + TEACHER_ID + ", " + TEACHER_NAME + " FROM " + TEACHERS_TABLE_NAME + " t, " + ASSOCIATIONS_COURSE_TEACHER_TABLE_NAME + " act"
				+ " WHERE act." + COURSE_ID + "=" + courseId + " AND t." + TEACHER_ID + " = act." + TEACHER_ID;
		else
			SELECT_TEACHERS_QUERY = "SELECT * FROM " + TEACHERS_TABLE_NAME;
		
		Log.d("MY_TAG", SELECT_TEACHERS_QUERY);
		Cursor cursor = db.rawQuery(SELECT_TEACHERS_QUERY, null);
		ArrayList<Teacher> result = new ArrayList<Teacher>();
		if (cursor.moveToFirst()) {			
			do {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(Integer.parseInt(cursor.getString(0)));
				teacher.setName(cursor.getString(1));
				result.add(teacher);
			} while (cursor.moveToNext());
		}
		return result;
	}
	
	public List<Course> selectCourses(int teacherId) {
		SQLiteDatabase db = this.getReadableDatabase();
		String SELECT_COURSES_QUERY;
		if (teacherId != -1)
			SELECT_COURSES_QUERY = "SELECT c." + COURSE_ID + ", "+ COURSE_NAME + " FROM " + COURSES_TABLE_NAME + " c, " + ASSOCIATIONS_COURSE_TEACHER_TABLE_NAME + " act"
				+ " WHERE act." + TEACHER_ID + "=" + teacherId + " AND c." + COURSE_ID + " = act." + COURSE_ID;
		else
			SELECT_COURSES_QUERY = "SELECT * FROM " + COURSES_TABLE_NAME; 
		Cursor cursor = db.rawQuery(SELECT_COURSES_QUERY, null);
		ArrayList<Course> result = new ArrayList<Course>();
		if (cursor.moveToFirst()) {
			do {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(cursor.getString(0)));
				course.setName(cursor.getString(1));
				result.add(course);
			} while (cursor.moveToNext());
		}
		return result;
	}	
	*/
	public int generateNextId(String tableName, String primaryKeyName) {
		SQLiteDatabase db = this.getReadableDatabase();
		String SELECT_MAX_ID_FROM_TABLE = "SELECT MAX(" + primaryKeyName + ") FROM " + tableName;
		Cursor cursor = db.rawQuery(SELECT_MAX_ID_FROM_TABLE, null);
		if (cursor != null && cursor.moveToFirst() && cursor.getString(0) != null)
			return Integer.parseInt(cursor.getString(0)) + 1;
		
		return 0;
	}
	
} 
