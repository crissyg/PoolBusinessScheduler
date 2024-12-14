package com.example.PoolBusinessScheduler;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PoolBusinessScheduler.DatabaseHelper;
import com.example.PoolBusinessScheduler.FieldCheck;
import com.example.PoolBusinessScheduler.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerActivity extends Activity implements OnItemSelectedListener{
	
	private int value = 0;
	private int index;
	private TextView title;
	private EditText name;
	private EditText address;
	private EditText city;
	private EditText state;
	private EditText zip;
	private EditText email;
	private EditText phone;
	private EditText date;
	private EditText time;
	private EditText price;
	private EditText info;
	private Spinner sp;
	private Button bt1;
	private Button bt2;
	
	DatabaseHelper db;
	Customer customer;
	private ArrayList<Customer> customer_list = new ArrayList<Customer>();
	
	public static final int MENU_ABOUT = Menu.FIRST+1;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer);
		
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    value = extras.getInt("OPTION");
		    index = extras.getInt("INDEX");
		}
		processOptionView();
		setCurrentDateOnView();
		
		
	}
	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, MENU_ABOUT, Menu.NONE, "About");
		return(super.onCreateOptionsMenu(menu));
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
		final PackageManager packageManager = getApplicationContext().getPackageManager();
		try{
		String message = new String("Name: "+"Christina Gordon \n"
								    + "Assignment 3 \n"
								    + "Current Date: " + currentDateTimeString + "\n"
								    + "Package Name:"  + getApplicationContext().getPackageName()+ "\n"
								    + "Process Name:"  + this.getAppNameByPID(android.os.Process.myPid()) + "\n"
								    + "Source Dir:"    + packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0)
								    .applicationInfo.dataDir + "\n"
								    + "Last Update:"   +packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0)
			    					.lastUpdateTime + "\n"
								    + "Version Name" + packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0)
								    					.versionName + "\n"
								    + "Version Code" + packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0)
								    					.versionCode  + "\n"
								    + "Activity Name"+ this.getClass().getSimpleName());


		switch (item.getItemId()) {
			case MENU_ABOUT:
				   Toast.makeText(CustomerActivity.this,
		                    message, Toast.LENGTH_LONG)
		                    .show();
				return(true);
		}
		
		return(super.onOptionsItemSelected(item));
		}catch(Exception e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			return(false);
			
		}
	}
	public  String getAppNameByPID( int pid){
		ActivityManager manager  = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

	    for(RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()){
	        if(processInfo.pid == pid){
	            return processInfo.processName;
	        }
	    }
	    return "";
	}

	private void processOptionView() {
		// TODO Auto-generated method stub
		title 	= (TextView)findViewById(R.id.title);
		name 	= (EditText)findViewById(R.id.editText_name);
		address = (EditText)findViewById(R.id.editText_address);
		city 	= (EditText)findViewById(R.id.editText_city);
		state 	= (EditText)findViewById(R.id.editText_state);
		zip 	= (EditText)findViewById(R.id.editText_zip);
		email 	= (EditText)findViewById(R.id.editText_email);
		phone 	= (EditText)findViewById(R.id.editText_phone);
		date 	= (EditText)findViewById(R.id.editText_date);
		time 	= (EditText)findViewById(R.id.editText_time);
		price 	= (EditText)findViewById(R.id.editText_price);
		info 	= (EditText)findViewById(R.id.editText_info);
		sp      = (Spinner)findViewById(R.id.spinner1);
		sp.setOnItemSelectedListener(this);
		
		bt1 	= (Button)findViewById(R.id.button1);
		bt2 	= (Button)findViewById(R.id.button2);
		name.setOnFocusChangeListener(new OnFocusChangeListener() {
			
	        public void onFocusChange(View v, boolean hasFocus) {
	            
	        	if (value == 1 || value == 2){
	        	if (name.getText().toString().length() == 0)
	        		name.setError("Please type the name of the customer!");}	        	
	    }});
		
	
		//init database
		db = new DatabaseHelper(getApplicationContext());
		db.getWritableDatabase();
		
		switch (value){
	      case 1:
	    	  processNewCustomerView();
            break;
	      case 3:
	    	  processViewCustomer();
	    	  break;  
	      case 4:
	    	  processEditCustomer();
	    	  break;
	      case 5:
	    	  processDeleteCustomer();
	    	  break;	  
	      default:
				break;  
		}
		
		
	}

	private void processDeleteCustomer() {
		// TODO Auto-generated method stub
		title.setText("DELETE CUSTOMER");
		name.setEnabled(false);
		address.setEnabled(false);
		city.setEnabled(false);
		state.setEnabled(false);
		zip.setEnabled(false);
		email.setEnabled(false);
		phone.setEnabled(false);
		date.setEnabled(false);
		time.setEnabled(false);
		price.setEnabled(false);
		info.setEnabled(false);
		initSpinner();
		
	}

	private void processEditCustomer() {
		// TODO Auto-generated method stub
		title.setText("EDIT CUSTOMER");
		initSpinner();
		
	}

	private void readCustomers() {
		// TODO Auto-generated method stub
		ArrayList<String> spinnerArray= new ArrayList<String>(); 
		customer_list.clear();
		customer_list = db.getCustomers();
		for (int i =0; i < customer_list.size(); ++i){
			spinnerArray.add(customer_list.get(i).getCust_name());
		}
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		sp.setAdapter(spinnerArrayAdapter);
		
	}

	private void processViewCustomer() {
		// TODO Auto-generated method stub
		title.setText("VIEW CUSTOMER");
		bt1.setVisibility(View.GONE);
		bt2.setVisibility(View.GONE);
		name.setEnabled(false);
		address.setEnabled(false);
		city.setEnabled(false);
		state.setEnabled(false);
		zip.setEnabled(false);
		email.setEnabled(false);
		phone.setEnabled(false);
		date.setEnabled(false);
		time.setEnabled(false);
		price.setEnabled(false);
		info.setEnabled(false);
		initSpinner();
			
	}

	private void initSpinner() {
		// TODO Auto-generated method stub
		ArrayList<String> spinnerArray= new ArrayList<String>(); 
  	    readCustomers();
  	    
		for (int i =0; i < customer_list.size(); ++i){
			spinnerArray.add(customer_list.get(i).getCust_name());
		}
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		sp.setAdapter(spinnerArrayAdapter);
		sp.setSelection(index);
	}

	private void processNewCustomerView() {
		// TODO Auto-generated method stub
		title.setText("NEW CUSTOMER");
		sp.setVisibility(View.GONE);		
	}
	
	public void SaveOnClick(View v){	
		int selectedIndex = 0;
  		customer = new Customer();
		customer.setCust_name(name.getText().toString());
		customer.setCust_address(address.getText().toString());
		customer.setCust_city(city.getText().toString());
		customer.setCust_state(state.getText().toString());
		customer.setCust_zip(zip.getText().toString());
		customer.setCust_email(email.getText().toString());
		customer.setCust_phone(phone.getText().toString());
		customer.setCust_date(date.getText().toString());
		customer.setCust_time(time.getText().toString());
		customer.setCust_price(price.getText().toString());
		customer.setCust_info(info.getText().toString()); 

	/*//	FieldCheck validator = new FieldCheck(customer);
	//	
		if(validator.isCustomerValid()){
		
	//		DatabaseHelper db = DatabaseHelper.getDBInstance(this);
		//	boolean success = db.addCustomer(customer);
			
			if(success){
				Toast.makeText(this,"");
			}
			backToMainMenu();
		}
		else{
			AlertDialog alert = new AlertDialog.Builder(this).create();
				alert.setTitle("New Customer Field Errors");
				alert.setMessage(validator.getValidationIssues());
				alert.setCancelable(false);
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface
						{
					
						public void onClick(DialogInterface dialog, int id){
							
							dialog.dismiss(); 
						}
						});
				alert.show();

				
			
		} */
  	  FieldCheck validator = new FieldCheck(customer);

		switch (value){
	      case 1:  
	    	  
	    	
	  		if(validator.isCustomerValid()){

			
	  			db.addCustomer(customer);
	  			
	    	Toast.makeText(CustomerActivity.this, "Record Added successfully.",
					Toast.LENGTH_LONG).show();
	  		
		}else{
			
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("New Customer Field Errors");
			alert.setMessage(validator.getValidationIssues());
			alert.setCancelable(false);
	        alert.setButton(DialogInterface.BUTTON_POSITIVE,"Ok", new DialogInterface.OnClickListener() 					{
				
					public void onClick(DialogInterface dialog, int id){
						
						dialog.dismiss(); 
					}
					});
			alert.show();
			
		}
		
		
	    	break;
	    	
	      case 4:
	    	  
	    	  
	    	  
				if (validator.isCustomerValid()) {

					selectedIndex = sp.getSelectedItemPosition();
					customer.setId(customer_list.get(selectedIndex).getId());
					db.updateCustomer(customer);
					

					Toast.makeText(
							CustomerActivity.this,
							"Record " + customer.getCust_name()
									+ " updated successfully",
							Toast.LENGTH_LONG).show();
		    	  
					

				} else {

					
						AlertDialog alert = new AlertDialog.Builder(this)
								.create();
						alert.setTitle("New Customer Field Errors");
						alert.setMessage(validator.getValidationIssues());
						alert.setCancelable(false);
						alert.setButton(DialogInterface.BUTTON_POSITIVE, "Ok",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int id) {

										dialog.dismiss();
									}
								});
						alert.show();

				}
				
			break;
		    
	      case 5:
	    	selectedIndex = sp.getSelectedItemPosition();	  
	    	db.deleteCustomer(customer_list.get(selectedIndex).getId());
		    Toast.makeText(CustomerActivity.this, "Record "+ customer.getCust_name()+ " deleted successfully",
						Toast.LENGTH_LONG).show(); 
		    break;
	      default:
				break;  
		}		
		finish();
		
		
		
	}
	/**
	 * Set the current date in the field text in the view
	 *
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (description of the return value)
	 */	
	private void setCurrentDateOnView() {
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		
		// set current date into EditText
		date.setText(today.month+"/"+today.monthDay+"/"+today.year);
		
		time.setText(today.format("%k:%M:%S"));
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		try{
	    int selectedIndex = sp.getSelectedItemPosition();
	    
		name.setText(customer_list.get(selectedIndex).getCust_name());
		address.setText(customer_list.get(selectedIndex).getCust_address());
		city.setText(customer_list.get(selectedIndex).getCust_city());
		state.setText(customer_list.get(selectedIndex).getCust_state());
		zip.setText(customer_list.get(selectedIndex).getCust_zip());
		email.setText(customer_list.get(selectedIndex).getCust_email());
		phone.setText(customer_list.get(selectedIndex).getCust_phone());
		date.setText(customer_list.get(selectedIndex).getCust_date());
		time.setText(customer_list.get(selectedIndex).getCust_time());
		price.setText(customer_list.get(selectedIndex).getCust_price());
		info.setText(customer_list.get(selectedIndex).getCust_info());
		}catch(ClassCastException e){
			
		}
		
	}
	public void CancelOnClick(View v){	
		finish();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 
	

}