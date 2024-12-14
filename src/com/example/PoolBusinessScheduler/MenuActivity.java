package com.example.PoolBusinessScheduler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.PoolBusinessScheduler.R;

public class MenuActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	/**
	 * Execute the corresponding method based on the selected button
	 *
	 * @param
	 * @return (description of the return value)
	 */	
	public void ButtonOnClick(View v) {
		switch (v.getId()) {
	      case R.id.Button01:
              goToCustomer(v, 1);
              break;
	      case R.id.Button02:
              goToCustomerList(v);
              break;     
	      case R.id.Button03:
              goToCustomer(v, 3);
              break; 
	      case R.id.Button04:
              goToCustomer(v, 4);
              break;   
	      case R.id.Button05:
              goToCustomer(v, 5);
              break; 
	      case R.id.Button06:
              goToTodayList(v);
              break;     
		}
		
	}
	private void goToTodayList(View v) {
		// TODO Auto-generated method stub
			Intent intent = new Intent(v.getContext(), TodayListActivity.class);
			v.getContext().startActivity(intent);
		
	}
	private void goToCustomerList(View v) {
		// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), CustomerListActivity.class);
				v.getContext().startActivity(intent);
	}
	protected void goToCustomer(View v, int button){
			Intent intent = new Intent(v.getContext(), CustomerActivity.class);
			intent.putExtra("OPTION", button);
			v.getContext().startActivity(intent);
	}
}