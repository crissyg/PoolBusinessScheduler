package com.example.PoolBusinessScheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.PoolBusinessScheduler.R;

import java.util.ArrayList;
import java.util.Collections;

public class TodayListActivity extends Activity{
	private ListView todayListView=null;
	
	private ArrayAdapter<String> adapter;
	
	private ArrayList<Customer> customer_list = new ArrayList<Customer>();
	private ArrayList<String> cust_names = new ArrayList<String>();
	final Context context = this;
	private Boolean withAddress;
	
	DatabaseHelper db;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		 setContentView(R.layout.activity_today_list);		 
		 todayListView = (ListView) findViewById(R.id.today_list);
	  
		//init database
		db = new DatabaseHelper(getApplicationContext());
		db.getWritableDatabase();	
		
		initAdapter();	
		callDialog();
	}

	private void callDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		// set title
					alertDialogBuilder.setTitle("Confirmation");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Display with address?")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// current activity
								withAddress = true;
								refreshList();
								
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								withAddress = false;
								refreshList();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();		
	}

	private void initAdapter() {
	
		adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cust_names);
		// TODO Auto-generated method stub 
		customer_list.clear();
		todayListView.setAdapter(adapter);						
	}
	
	private void refreshList(){
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		
		// set current date into EditText
		String date = new String(today.month+"/"+today.monthDay+"/"+today.year);
		customer_list = db.getCustomersByDate(date);
		for (int i =0; i < customer_list.size(); ++i){	     
			if ( this.withAddress == true )
				cust_names.add(customer_list.get(i).getCust_time() + "\n"+customer_list.get(i).getCust_name() + "," +customer_list.get(i).getCust_address() );
			else{	
				cust_names.add(customer_list.get(i).getCust_time() + "\n"+customer_list.get(i).getCust_name() );
			}
				
		}
		Collections.sort(cust_names);
		adapter.notifyDataSetChanged();
	}
}