package com.example.PoolBusinessScheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

	public static String DATABASENAME 		= "androidadvancesqlite";
	public static String CUST_TABLE 		= "customer";
	public static String colCustomerId 		= "id";
	public static String colCustomerName    = "customer_name";
	public static String colCustomerAddress = "customer_address";
	public static String colCustomerCity    = "customer_city";
	public static String colCustomerState	= "customer_state";
	public static String colCustomerZip 	= "customer_zip";
	public static String colCustomerEmail   = "customer_email";
	public static String colCustomerPhone   = "customer_phone";
	public static String colCustomerDate    = "customer_date";
	public static String colCustomerTime    = "customer_time";
	public static String colCustomerPrice   = "customer_price";
	public static String colCustomerInfo    = "customer_info";
	private ArrayList<Customer> custList = new ArrayList<Customer>();
	Context c;
	
	public static DatabaseHelper dbInstance=null;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASENAME, null, 33);
		c = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE if not exists cust_table(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "customer_name"   + " TEXT ,"
				+ "customer_address"+ " TEXT,"
				+ "customer_city"+ " TEXT,"
				+ "customer_state"+ " TEXT,"
				+ "customer_zip"+ " TEXT,"
				+ "customer_email"+ " TEXT,"
				+ "customer_phone"+ " TEXT,"
				+ "customer_date"+ " TEXT,"
				+ "customer_time"+ " TEXT,"
				+ "customer_price"+ " TEXT,"
				+ "customer_info"   + " TEXT)");

	}
	
	public static DatabaseHelper getDBInstance(Context context){
		
		if (dbInstance ==null){
			dbInstance = new DatabaseHelper(context);
			
		}
		return dbInstance;
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + CUST_TABLE);
		onCreate(db);
	}

	public void addCustomer(Customer cm) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("customer_name", cm.getCust_name());
		contentValues.put("customer_address", cm.getCust_address());
		contentValues.put("customer_city", cm.getCust_city());
		contentValues.put("customer_state", cm.getCust_state());
		contentValues.put("customer_zip", cm.getCust_zip());
		contentValues.put("customer_email", cm.getCust_email());
		contentValues.put("customer_phone", cm.getCust_phone());
		contentValues.put("customer_date", cm.getCust_date());
		contentValues.put("customer_time", cm.getCust_time());
		contentValues.put("customer_price", cm.getCust_price());
		contentValues.put("customer_info", cm.getCust_info());
		db.insert("cust_table", null, contentValues);
		db.close();

	}
	
	
	public ArrayList<Customer> getCustomers() {

		custList.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from cust_table", null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					Customer item = new Customer();
					item.setId(cursor.getString(cursor.getColumnIndex("id")));
					item.setCust_name(cursor.getString(cursor.getColumnIndex("customer_name")));
					item.setCust_address(cursor.getString(cursor.getColumnIndex("customer_address")));
					item.setCust_city(cursor.getString(cursor.getColumnIndex("customer_city")));
					item.setCust_state(cursor.getString(cursor.getColumnIndex("customer_state")));
					item.setCust_zip(cursor.getString(cursor.getColumnIndex("customer_zip")));
					item.setCust_email(cursor.getString(cursor.getColumnIndex("customer_email")));
					item.setCust_phone(cursor.getString(cursor.getColumnIndex("customer_phone")));
					item.setCust_date(cursor.getString(cursor.getColumnIndex("customer_date")));
					item.setCust_time(cursor.getString(cursor.getColumnIndex("customer_time")));
					item.setCust_price(cursor.getString(cursor.getColumnIndex("customer_price")));
					item.setCust_info(cursor.getString(cursor.getColumnIndex("customer_info")));
					custList.add(item);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return custList;
	}
	
	public ArrayList<Customer> getCustomersByDate( String d) {

		custList.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from cust_table where customer_date = ? ", new String[]{d});
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					Customer item = new Customer();
					item.setId(cursor.getString(cursor.getColumnIndex("id")));
					item.setCust_name(cursor.getString(cursor.getColumnIndex("customer_name")));
					item.setCust_address(cursor.getString(cursor.getColumnIndex("customer_address")));
					item.setCust_city(cursor.getString(cursor.getColumnIndex("customer_city")));
					item.setCust_state(cursor.getString(cursor.getColumnIndex("customer_state")));
					item.setCust_zip(cursor.getString(cursor.getColumnIndex("customer_zip")));
					item.setCust_email(cursor.getString(cursor.getColumnIndex("customer_email")));
					item.setCust_phone(cursor.getString(cursor.getColumnIndex("customer_phone")));
					item.setCust_date(cursor.getString(cursor.getColumnIndex("customer_date")));
					item.setCust_time(cursor.getString(cursor.getColumnIndex("customer_time")));
					item.setCust_price(cursor.getString(cursor.getColumnIndex("customer_price")));
					item.setCust_info(cursor.getString(cursor.getColumnIndex("customer_info")));
					custList.add(item);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return custList;
	}

	// update

	public void updateCustomer(Customer cm) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("customer_name", cm.getCust_name());
		contentValues.put("customer_address", cm.getCust_address());
		contentValues.put("customer_city", cm.getCust_city());
		contentValues.put("customer_state", cm.getCust_state());
		contentValues.put("customer_zip", cm.getCust_zip());
		contentValues.put("customer_email", cm.getCust_email());
		contentValues.put("customer_phone", cm.getCust_phone());
		contentValues.put("customer_date", cm.getCust_date());
		contentValues.put("customer_time", cm.getCust_time());
		contentValues.put("customer_price", cm.getCust_price());
		contentValues.put("customer_info", cm.getCust_info());
		db.update("cust_table", contentValues, "id="
				+ cm.getId(), null);

		db.close();
	}


	public void deleteCustomer(String cust_id) {
		String[] args = { cust_id };
		getWritableDatabase().delete("cust_table", "id=?", args);

	}

	
}