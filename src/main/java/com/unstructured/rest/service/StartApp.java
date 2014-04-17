package com.unstructured.rest.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.unstructured.rest.dataobject.Cab;
import com.unstructured.rest.dataobject.PickUpPoint;
import com.unstructured.rest.dataobject.Route;
import com.unstructured.rest.dataobject.RouteList;

public class StartApp {

	static String appPropertyLocation = "src/main/resources/setting/tmc.properties";
	static String routeListLocation = null;
	static String routeDataLocation = null;
	static String cabListLocation = null;
	static String stopListLocation = null;
	
	static List<RouteList> routeList;
	static List<Cab> cabList;
	static List<PickUpPoint> pickUpPoints;
	
	/**
	 * method that loads initial set of data for cab, route, pickup points etc
	 */
	public static boolean startApp(){
		/*
		 * app status variable
		 */
		boolean successfulAppStart = false;
		
		if (!readAppProperty())
			return false;
		if(!populateCabList())
			return false;
		if(!populateLocationList())
			return false;
		if(!populateRouteList())
			return false;
		return successfulAppStart;
	}

	public static boolean readAppProperty()
	{
		boolean readPropertyFileSuccess = false;

		try {
			
			FileReader reader = new FileReader(appPropertyLocation);
			Properties appProperty = new Properties();
			appProperty.load(reader);

			routeListLocation = appProperty.getProperty("ROUTE_LIST_RESOURCE");
			routeDataLocation = appProperty.getProperty("ROUTE_RESOURCE_LOCATION");
			cabListLocation = appProperty.getProperty("CAB_LIST_RESOURCE");
			stopListLocation = appProperty.getProperty("STOP_LIST_RESOURCE");

			System.out.println("Woha, read the property file : and the data is "+ routeListLocation);
			
			readPropertyFileSuccess = true;
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return readPropertyFileSuccess;
	}

	public static boolean populateCabList()
	{
		boolean cabListReadSuccess = false;
		ObjectMapper mapper = new ObjectMapper();
			
		cabList = new ArrayList<Cab>();
		
		try {
			FileReader reader = new FileReader(cabListLocation);
			cabList = mapper.readValue(reader,new TypeReference<List<Cab>>(){});	
			
			cabListReadSuccess = true;
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cabListReadSuccess;
	}

	public static boolean populateLocationList()
	{
		boolean locationListReadSuccess = false;
		
		ObjectMapper mapper = new ObjectMapper();
		
		pickUpPoints = new ArrayList<PickUpPoint>();
		
		try {
			FileReader reader = new FileReader(stopListLocation);
			pickUpPoints = mapper.readValue(reader, new TypeReference<List<PickUpPoint>>(){});	
			
			locationListReadSuccess = true;
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return locationListReadSuccess;
	}

	public static boolean populateRouteList()
	{
		boolean routeListReadSuccess = false;
		
		ObjectMapper mapper = new ObjectMapper();
		
		routeList = new ArrayList<RouteList>();
		
		try {
			FileReader reader = new FileReader(routeListLocation);
			routeList = mapper.readValue(reader, new TypeReference<List<RouteList>>(){});	
			reader.close();
			
			
			//For each of the routes, get the route details
			for (int i = 0; i<routeList.size(); i++)
			{
				Route route = populateRouteData(routeList.get(i).getRouteNo());
				routeList.get(i).setRoute(route);
			}
			
			routeListReadSuccess = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return routeListReadSuccess;
	}

	public static Route populateRouteData(String routeNo)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		Route route = null;
		
		try{
			FileReader reader = new FileReader(routeDataLocation + '/' + routeNo + ".json");
			route = mapper.readValue(reader, new TypeReference<Route>(){});
			reader.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return route;
	}

	public static void main(String args[])
	{
		startApp();
	}
}
