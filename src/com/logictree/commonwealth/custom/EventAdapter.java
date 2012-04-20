package com.logictree.commonwealth.custom;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.logictree.commonwealth.R;
import com.logictree.commonwealth.model.Event;

public class EventAdapter extends ArrayAdapter<Event> {

	private Context mContext;
	private List<Event> eventsList;
	public EventAdapter(Context context, int textViewResourceId,
			List<Event> objects) {
		super(context, textViewResourceId, objects);
		mContext=context;
		eventsList=objects;
	}
	
	
	public int getCount() {
		return eventsList.size();
	}
	
	public Event getItem(int position) {
		return eventsList.get(position);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.events_custom_list_layout, null);
			holder.title=(TextView)convertView.findViewById(R.id.eventtitle);
			holder.eventDate=(TextView)convertView.findViewById(R.id.eventtime);
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}
		
		Event event=getItem(position);
		holder.title.setText(event.getEventTitle());
		holder.eventDate.setText(event.getEventDateTime());
		return convertView;
	}
	
	
	public static class ViewHolder
	{
		TextView title;
		TextView eventDate;
		
		
	}
	
}
