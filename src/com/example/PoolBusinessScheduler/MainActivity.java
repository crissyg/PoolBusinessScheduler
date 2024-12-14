package com.example.PoolBusinessScheduler;
///////////////////////////////////////////////////////////////////////////////
//Assignment:      Assignment 3
//Author:          Christina Gordon
//CSIS4020
//Description: SCHEDULER APP WITH SQL LITE DATABASE
//Date Edited: 		   03/22/2014
//References:     Tatiana Fetecua tfassig5  
///////////////////////////////////////////////////////////////////////////////


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.PoolBusinessScheduler.R;

public class MainActivity extends Activity {
    boolean m_bSplashActive;
    boolean m_bPaused;

    long m_dwSplashTime = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		m_bPaused = false;
        m_bSplashActive = true;
        
        Thread splashTimer = new Thread()
        {
            public void run()
            {
                try
                {
                    //Wait loop
                    long ms = 0;
                    while(m_bSplashActive && ms < m_dwSplashTime)
                    {
                        sleep(100);
                        //Only advance the timer if we're running.
                        if(!m_bPaused)
                            ms += 100;
                    }
                    //Advance to the next screen.
                    startActivity(new Intent("CLEARSPLASHPOOL"));
                    finish();
                }
                catch(Exception e)
                {
                    //Thread exception
                    System.out.println(e.toString());
                }
            }
        };
		
        splashTimer.start();
		setContentView(R.layout.activity_main);
		return;
	}

	 protected void onStop()
	    {
	        super.onStop();
	    }
	    protected void onPause()
	    {
	        super.onPause();
	        m_bPaused = true;
	    }
	    protected void onResume()
	    {
	        super.onResume();
	        m_bPaused = false;
	    }
	    protected void onDestroy()
	    {
	        super.onDestroy();
	    }
	    public boolean onKeyDown(int keyCode, KeyEvent event)
	    {
	        //if we get any key, clear the Splash Screen
	        super.onKeyDown(keyCode, event);
	        m_bSplashActive = false;
	        return true;
	    }
	    public void onNext( View v ){
	        Intent intent = new Intent(v.getContext(), MenuActivity.class);
	        v.getContext().startActivity(intent);
	    }    
}