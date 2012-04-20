package com.logictree.commonwealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.logictree.commonwealth.R;

public class SplashScreenActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_splashscreen);
		
		new Thread(new Runnable() {
			
			public void run() {
			
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
				finally
				{
					startActivity(new Intent(SplashScreenActivity.this,HomeActivity.class));
					finish();
				}
			}
		}).start();
		
	}

}
