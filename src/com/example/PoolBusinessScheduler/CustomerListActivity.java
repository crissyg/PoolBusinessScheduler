package com.example.PoolBusinessScheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.PoolBusinessScheduler.R;

import java.util.ArrayList;

public class CustomerListActivity extends Activity {
	public static final int MENU_VIEW = Menu.FIRST+1;
	public static final int MENU_EDIT = Menu.FIRST+2;
	public static final int MENU_DELETE = Menu.FIRST+3;
	private ListView custListView;
	
	private ArrayList<Customer> customer_list = new ArrayList<Customer>();
	private ArrayList<String> cust_names = new ArrayList<String>();
	private ArrayAdapter<String> ap;
	private boolean flagDel = false;
	DatabaseHelper db;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		 setContentView(R.layout.activity_customer_list);
		 custListView = (ListView) findViewById(R.id.customernames_list);
		 
		//init database
		db = new DatabaseHelper(getApplicationContext());
		db.getWritableDatabase();	
		initAdapter();
		registerForContextMenu(custListView);
	}

	private void initAdapter() {
		// TODO Auto-generated method stub 
		customer_list.clear();
		customer_list = db.getCustomers();
		for (int i =0; i < customer_list.size(); ++i){
			cust_names.add(customer_list.get(i).getCust_name());
		}
		ap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cust_names);

		custListView.setAdapter(ap);
	}	
	private void refreshList() {
		// TODO Auto-generated method stub 
		customer_list.clear();
		cust_names.clear();
		customer_list = db.getCustomers();
		for (int i =0; i < customer_list.size(); ++i){
			cust_names.add(customer_list.get(i).getCust_name());
		}
		ap.notifyDataSetChanged();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
															ContextMenu.ContextMenuInfo menuInfo) {
		
		menu.add(Menu.NONE, MENU_VIEW, Menu.NONE, "View");
		menu.add(Menu.NONE, MENU_EDIT, Menu.NONE, "Edit");
		menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete");
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info=
			(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		switch (item.getItemId()) {
			case MENU_VIEW:
				goToCustomer( 3 ,info.position);
				return(true);		
			case MENU_EDIT:
				goToCustomer( 4 ,info.position);
				return(true);
			case MENU_DELETE:
				goToCustomer( 5 ,info.position);
				flagDel = true;
				return(true);
		}
		
		return(super.onContextItemSelected(item));
	}
	public void onPause(){
		super.onPause();
		
	}
	public void onResume(){
		super.onResume();
		if (flagDel == true)
		this.refreshList();
		
	}
	private void goToCustomer(int option, int index) {
		// TODO Auto-generated method stub
		View v = findViewById(android.R.id.content);
		Intent intent = new Intent(v.getContext(), CustomerActivity.class);
		intent.putExtra("OPTION", option );
		intent.putExtra("INDEX", index );
		v.getContext().startActivity(intent);		
	}
}