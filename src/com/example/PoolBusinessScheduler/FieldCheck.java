package com.example.PoolBusinessScheduler;

import android.graphics.Color;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FieldCheck {
	
	private static final String TAG = "FieldCheck";

	public static final int VALID_TEXT_COLOR = Color.BLACK;
	public static final int INVALID_TEXT_COLOR = Color.RED;
	
	private Customer customer;
	
	private ArrayList<String> validationErrorList;
	
	private List<String> statesList = Arrays.asList(new String [ ]
			
			{"AL","AK","AZ", "AR", "CA", "CO", "CT", "DE", "FL","GA", "HI","ID", "IL","IN", "IA", "KS",
			"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ","NM", "NY", 
			"NC","ND","OH","OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"}
			);
	
	SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yy");
	SimpleDateFormat timeFormat= new SimpleDateFormat("HH:mm");
	
	public FieldCheck(Customer customer){
		
		try
		{
		Log.i(TAG, "===INSIDE contructor===");
		this.customer = customer;
		validationErrorList = new ArrayList<String>();
		validateCustomer();
		}
		catch (Exception e){
		
		Log.e(TAG, "***Error: " + e.getMessage());
		e.printStackTrace();
		}
		
		}
	
	public boolean isCustomerValid(){
		
		boolean validCustomer = true;
		
		try
		{
			Log.i(TAG,"---INSIDE isCustomerValid---");
			
			if (validationErrorList.size() >0){
				
				validCustomer = false;
			}
			
			
		}
		
		catch (Exception e){
			
			Log.e(TAG,"***Error: "+ e.getMessage());
			e.printStackTrace();
			
		}
		
		return validCustomer;
		
	}
	
	
	public String getValidationIssues(){
		
		String formattedValidationErrors = "";
		
		try{
			
			Log.i (TAG,"===INSIDE getValidationIssues===");
			if(validationErrorList.size() == 0){
				formattedValidationErrors = "None";
				
			}
			else {
				
				Iterator iter = validationErrorList.iterator();
				while (iter.hasNext()){
					
					String error = (String)iter.next();
					
					formattedValidationErrors = formattedValidationErrors+"-" + error +"";
				}
			}
		}
		catch(Exception e){
			
			Log.e(TAG, "***Error: " + e.getMessage());
			
			e.printStackTrace();
			
		}
		
		return formattedValidationErrors;
		
	}
	
	
	private void validateCustomer(){
		
		
		try{
			
			Log.i (TAG, "===INSIDE validateCustomer===");
			
			String name = customer.getCust_name();
			String address = customer.getCust_address();
			String city = customer.getCust_city();
			String state = customer.getCust_state();
			String zip = customer.getCust_zip();
			String email = customer.getCust_email();
			String phone = customer.getCust_phone();
			String date = customer.getCust_date();
			String time = customer.getCust_time();
			String price = customer.getCust_price();
			
			boolean emptyName = false;
			boolean emptyAddress = false;
			boolean dateExists = false;
			
			
			//NAME
			
			if (name.isEmpty())
			{
				emptyName = true;
				validationErrorList.add("Name is required field");
				
			}
			
			if((!name.toUpperCase().matches("[A-Z ]+"))&& (!emptyName)){
				
				validationErrorList.add("Name can only contain letters and space");
				
			}
			
			
			//ADDRESS
			
			if(address.isEmpty()){
				
				emptyAddress= true;
				validationErrorList.add("Address is a required field");
			}
			
			
			if ((!address.toUpperCase().matches("[A-Z0-9 ]+")) && (!emptyAddress)){
				
				validationErrorList.add("Address can only contain integers, letters, and space");
				
			}
			
			
			//city
			
			if((!city.toUpperCase().matches("[A-Z ]+")) && (!city.isEmpty()))
			{
				validationErrorList.add("City can only contain letters");
				
			}
			
			
			//state
			
			if((!state.toUpperCase().matches("[A-Z ]+")) && (!state.isEmpty()))
			{
				validationErrorList.add("State abbreviation can only contain 2 letters");
				
			}
			
			//statelist
			
			if((!statesList.contains(state)) && (!state.isEmpty()) )
			{
				validationErrorList.add("No such state");
				
			}
			
			
			//zipcode
			
			if((zip.length() !=5) && (!zip.isEmpty()) )
			{
				validationErrorList.add("Zip code must contain 5 integers");
				
			}
			
			//phone
			
			if((phone.length() !=10) && (!phone.isEmpty()) )
			{
				validationErrorList.add("Phone number  must contain 10 integers which includes area code");
				
			}
			
			/*//date
			
			if(!date.isEmpty()){
				
				if(date.length() !=8){
					validationErrorList.add("Date must contain 8 characters and follow mm/ddd/yy");
				}
				
				else{
					
					try{
						dateFormat.setLenient(false);
						dateFormat.parse(date);
						dateExists= true;
						
					}
					catch(ParseException e){
						validationErrorList.add("Date must follow mm/dd/yy format");

						}
					}
				}
			*/
			
			//email
			
			if((email.indexOf("@") == -1) && (!email.isEmpty()))
			{
				validationErrorList.add("email address missing @ sign");
				
			}
			
			if(email.isEmpty()){
				
				validationErrorList.add("email is a required field");
			}
				
			//price
			
			if(!price.isEmpty())
			{
				int decimalLoc = price.indexOf(".");
				if(decimalLoc >-1)	
				{	
					if(price.substring(decimalLoc + 1).length()!=2)	
					{
						validationErrorList.add("Price must be in whole dollar amount");
						
					}
				}
			}
			
			/*
				
			//time
			
			if(!time.isEmpty()|| (dateExists)){
				
				if(time.length() !=5){
					validationErrorList.add("Time must contain 5 characters");
				}
				
				else{
					
					try{
						timeFormat.setLenient(false);
						timeFormat.parse(time);
						
					}
					catch(ParseException e){
						validationErrorList.add("Time must follow hh:mm");

						}
					}
				}*/
			
			
			
		}
		catch(Exception e){
			
			Log.e(TAG, "***Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
}