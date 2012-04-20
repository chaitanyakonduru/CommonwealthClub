package com.logictree.commonwealth.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
	
	private String eventTitle;
	private String eventDateTime;
	private String link;
	private String eventDescription;
	private String imageUrl;
	private String nidUrl;
	
	public String toString() {
		return eventTitle;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getNidUrl() {
		return nidUrl;
	}
	public void setNidUrl(String nidUrl) {
		this.nidUrl = nidUrl;
	}

	public Event()
	{
		
	}


	public void writeToParcel(Parcel dest, int flags) {
		List<String> list=new ArrayList<String>();
		list.add(this.eventTitle);
		list.add(this.eventDateTime);
		list.add(this.link);
		list.add(this.eventDescription);
		list.add(this.imageUrl);
		list.add(nidUrl);
		dest.writeStringList(list);
		
	}
	
	public Event(Parcel source)
	{
		List<String> list=new ArrayList<String>();
		source.readStringList(list);
		this.eventTitle=list.get(0);
		this.eventDateTime=list.get(1);
		this.link=list.get(2);
		this.eventDescription=list.get(3);
		this.imageUrl=list.get(4);
		this.nidUrl=list.get(5);
	}
	
	public static final Creator<Event> CREATOR=new Creator<Event>() {
		
		public Event[] newArray(int size) {
			return new Event[size];
		}
		
		public Event createFromParcel(Parcel source) {
			return new Event(source);
		}
	};

	public static Creator<Event> getCreator() {
		return CREATOR;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	

}
