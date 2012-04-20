package com.logictree.commonwealth.activities;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.logictree.commonwealth.model.Event;

public class EventHandler extends DefaultHandler {

	private Event event;
	private boolean in = false;
	private List<Event> eventsList;
	private StringBuffer buffer;

	public EventHandler() {
		buffer = new StringBuffer();

	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		super.startElement(uri, localName, qName, attributes);
		if (localName.equalsIgnoreCase("events")) {
			eventsList = new ArrayList<Event>();
		}
		if (localName.equalsIgnoreCase("event")) {
			event = new Event();
			
		} else if (localName.equalsIgnoreCase("title")) {
			in = true;
			buffer.setLength(0);
		} else if (localName.equalsIgnoreCase("eventdatetime")) {
			in = true;
			buffer.setLength(0);
		} else if (localName.equalsIgnoreCase("link")) {
			in = true;
			buffer.setLength(0);
		} else if (localName.equalsIgnoreCase("description")) {
			in = true;
			buffer.setLength(0);
		} else if (localName.equalsIgnoreCase("image")) {
			in = true;
			buffer.setLength(0);
		} else if (localName.equalsIgnoreCase("nidurl")) {
			in = true;
			buffer.setLength(0);
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (in) {
			buffer.append(ch, start, length);
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		super.endElement(uri, localName, qName);
		if (localName.equalsIgnoreCase("event")) {
			eventsList.add(event);
		} else if (localName.equalsIgnoreCase("title")) {
			event.setEventTitle(buffer.toString());
			in = false;
		} else if (localName.equalsIgnoreCase("eventdatetime")) {
			
				event.setEventDateTime(buffer.toString());
				in = false;
			
		} else if (localName.equalsIgnoreCase("link")) {
			event.setLink(buffer.toString());
			in = false;

		} else if (localName.equalsIgnoreCase("description")) {
			event.setEventDescription(buffer.toString());
			in = false;
		}
		 else if (localName.equalsIgnoreCase("image")) {
			event.setImageUrl(buffer.toString());
			in = false;

		}
		 else if (localName.equalsIgnoreCase("nidurl")) {
			event.setNidUrl(buffer.toString());
			in = false;
		}
	}

	public List<Event> getEventsList() {
		return eventsList;
	}

}
