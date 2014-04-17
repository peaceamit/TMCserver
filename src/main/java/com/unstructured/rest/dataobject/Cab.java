package com.unstructured.rest.dataobject;

public class Cab {

	int cabNo;
	int cabRoute;
	Location location;
	
	public int getCabNo() {
		return cabNo;
	}
	
	public void setCabNo(int cabNo) {
		this.cabNo = cabNo;
	}
	
	public int getCabRoute() {
		return cabRoute;
	}
	
	public void setCabRoute(int cabRoute) {
		this.cabRoute = cabRoute;
	}
	
	public Location getCurrentLocation() {
		return location;
	}
	
	public void setCurrentLocation(Location currentLocation) {
		this.location = currentLocation;
	}
	
}
