package com.logictree.commonwealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.logictree.commonwealth.R;

public class ContactUsActivity extends Activity implements OnClickListener{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_contactus);
		findViewById(R.id.btn_website).setOnClickListener(this);
		findViewById(R.id.btn_email).setOnClickListener(this);
		findViewById(R.id.btn_facebook).setOnClickListener(this);
		findViewById(R.id.btn_twitter).setOnClickListener(this);
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_website:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("http://www.commonwealthclub.org")));
			break;
		case R.id.btn_email:
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"club@commonwealthclub.org"});
			intent.setType("mail/Html");
			startActivity(Intent.createChooser(intent, "sending..."));
			break;
		case R.id.btn_twitter:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("http://twitter.com/#!/cwclub")));
			break;
		case R.id.btn_facebook:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("https://www.facebook.com/thecommonwealthclub?ref=ts")));
			break;
		}
	}

}
