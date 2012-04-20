package com.logictree.commonwealth.activities;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.logictree.commonwealth.R;

/**
 * @author Chaitanya
 */

public class HomeActivity extends TabActivity {

	private TabHost mTabHost;
	private Intent mIntent;
	private int bgId;
	
	/**
	 * This method will call automatically when the activity loads
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		TextView title=(TextView) findViewById(R.id.custom_title_tv);
		title.setText("Events");
		findViewById(R.id.btn_webpage).setVisibility(View.GONE);
		
		mTabHost=getTabHost();

	        mIntent = new Intent().setClass(this, EventsActivity.class);
	        setupTab("Events", mIntent,R.drawable.events_icon);
	        mIntent = new Intent().setClass(this, ContactUsActivity.class);
	        setupTab("Contact us", mIntent, R.drawable.contact_icon);
	        mTabHost.setCurrentTab(0);
	       
	    }
	
	
	    
	    private void setupTab(final String tag, final Intent content, final int resId) {
	    	View tabView = createTabView(mTabHost.getContext(), resId,tag);
	    	TabSpec spec = mTabHost.newTabSpec(tag).setIndicator(tabView).setContent(content);
	    	mTabHost.addTab(spec);
	    }
	    
	    /**
	     * 
	     * 
	     * @param context
	     * context of the given activity
	     * @param resId
	     * Resourse Id
	     * @param tag
	     * text which you want to show
	     * @return
	     *  View
	     */
	    private View createTabView(final Context context, final int resId,String tag) {
	    	
	    	if ("Events".equalsIgnoreCase(tag)) {
				bgId =R.drawable.left_tab_indicator;
			} else {
				bgId = R.drawable.right_tab_indicator;
			}

	    	View view = LayoutInflater.from(context).inflate(R.layout.n_view_tab_main, null);
	    	TextView tv=(TextView)view.findViewById(R.id.ll_ic_tab_text);
	    	tv.setText(tag);
	    	tv.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
	    	view.setBackgroundResource(bgId);
	    	return view;
	    }
	
		
		
	}


