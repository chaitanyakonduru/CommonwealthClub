package com.logictree.commonwealth.activities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.logictree.commonwealth.R;
import com.logictree.commonwealth.model.Event;

public class EventsDetailsActivity extends Activity implements OnClickListener{

	private static final String TAG = "EventsDetailsActivity";
	private Event event;
	private TextView eventDate;
	private WebView webview;
	private ImageView speakerImage;
	private Button webpagebtn;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_event_description);

		TextView title=(TextView) findViewById(R.id.custom_title_tv);
		title.setText("Event Details");
		webpagebtn=(Button)findViewById(R.id.btn_webpage);
		webpagebtn.setOnClickListener(EventsDetailsActivity.this);
		eventDate=(TextView) findViewById(R.id.event_date);
		webview=(WebView)findViewById(R.id.webview);
		speakerImage=(ImageView)findViewById(R.id.profile_picture);
		
		Bundle bundle=getIntent().getExtras();
		if(bundle.containsKey("eventObject")&& bundle!=null)
		{
			event=(Event) bundle.get("eventObject");
			Log.v(TAG, event.getEventTitle());
			eventDate.setText(event.getEventDateTime());
			Drawable drawable=getDrawable(event.getImageUrl());
			if(drawable!=null)
			{
				speakerImage.setImageDrawable(drawable);
			}
			String description=event.getEventDescription();
			if(description==null)
			{
				description="Currently not available";
			}
			webview.loadData(description, "text/html", null);
			
		}
	}


	private Drawable getDrawable(String imageUrl) {
	
	if(imageUrl!=null)
	{
		URL url;
		try {
			url = new URL(imageUrl);
			url.openConnection();
			return Drawable.createFromStream(url.openStream(), "image");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	return null;
}


	public void onClick(View v) {
		String url=event.getNidUrl();
		if(url!=null)
		{
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		}
	}


}
