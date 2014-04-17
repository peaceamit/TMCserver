package com.unstructured.rest.dataobject;

import java.util.ArrayList;
import java.util.List;

public class Route {

	String routeNo;
	ArrayList<PickUpPoint> stopList;
	
	Route()
	{
		// fill up route data to the route no variable
		stopList = new ArrayList<PickUpPoint>();
	}
	
	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public ArrayList<PickUpPoint> getStopList() {
		return stopList;
	}

	public void setStopList(ArrayList<PickUpPoint> stopList) {
		this.stopList = stopList;
	}
}
