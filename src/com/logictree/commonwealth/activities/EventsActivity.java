package com.logictree.commonwealth.activities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.logictree.commonwealth.custom.EventAdapter;
import com.logictree.commonwealth.model.Event;

/**
 * @author Chaitanya
 */


public class EventsActivity extends ListActivity implements OnItemClickListener{

	protected static final int SUCCESS = 100;
	protected static final int FAILURE = 101;
	protected static final int PROGRESSDIALOG = 1000;
	private static final String TAG = "EventsActivity";
	private List<Event> eventsList=null;
	private ListView listview;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showDialog(PROGRESSDIALOG);
		getEventsList();
		setTitle("Events");
		listview=getListView();
		listview.setOnItemClickListener(EventsActivity.this);
		
	}
	
	private void getEventsList() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				EventHandler eventhandler = new EventHandler();
				try {
					URL url = new URL(
							"http://www.commonwealthclub.org/events/calendar/xml");
					url.openConnection();
					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser parser = factory.newSAXParser();
					parser.parse(url.openStream(), eventhandler);
					eventsList = eventhandler.getEventsList();
					if (eventsList != null && eventsList.size() > 0) {
						Message.obtain(handler, SUCCESS, eventsList).sendToTarget();
					}

				} catch (MalformedURLException e) {
					Message.obtain(handler, FAILURE, e.getMessage())
							.sendToTarget();
				} catch (IOException e) {
					Message.obtain(handler, FAILURE, e.getMessage())
							.sendToTarget();
				} catch (ParserConfigurationException e) {
					Message.obtain(handler, FAILURE, e.getMessage())
							.sendToTarget();
				} catch (SAXException e) {
					Message.obtain(handler, FAILURE, e.getMessage())
							.sendToTarget();
				}
			}
		}).start();
	}

	Handler handler = new Handler() {

		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (msg.what == SUCCESS) {
				removeDialog(PROGRESSDIALOG);
				List<Event> list = (List<Event>) msg.obj;
				setListAdapter(new EventAdapter(EventsActivity.this, -1,
						list));
				
			} else if (msg.what == FAILURE) {
				removeDialog(PROGRESSDIALOG);
				Object object = msg.obj;
				showToast("Some Error" + object.toString());
			}
		}

	};
	

	/**
	 * It will display Toast with Given Message
	 * 
	 * @param string
	 *            Message which you want to be shown
	 */

	private void showToast(String string) {
		Toast.makeText(EventsActivity.this, string, Toast.LENGTH_SHORT)
				.show();
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Event event=eventsList.get(arg2);
		Log.v(TAG, event.getEventTitle());
		Intent intent=new Intent(EventsActivity.this,EventsDetailsActivity.class);
		intent.putExtra("eventObject", event);
		startActivity(intent);
		
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESSDIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(
					EventsActivity.this);
			progressDialog.setMessage("Loading....");
			progressDialog.show();
			return progressDialog;
		default:
			break;
		}
		return super.onCreateDialog(id);
	}


}
