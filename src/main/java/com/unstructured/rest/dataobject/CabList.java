package com.unstructured.rest.dataobject;

import java.util.ArrayList;
import java.util.List;

public class CabList {
 
	List<Cab> cabList;
	
	public CabList()
	{
		cabList = new ArrayList<Cab>();
	}

	public List<Cab> getCabList() {
		return cabList;
	}

	public void setCabList(List<Cab> cabList) {
		this.cabList = cabList;
	}
	
	public void addCab(Cab cab)
	{
		cabList.add(cab);
	}
}
