package it.polito.ezgas.controllertests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.utils.Constants;

public class TestController {
	
	private GasStationRepository gasStationRepository;
	
	private final String BASE="http://localhost:8080/gasstation/";
	private final String GASOLINE_TYPE=Constants.DIESEL;
	private final String CAR_SHARING="carsharing";
	private final int GAS_STATION_ID=1234;
	private final int USER_ID=1234;
	private final double PRICE=1.23;
	private final double LAT=45.101767;
	private final double LON= 7.646787;
	
	@Before
	public void init() throws ClientProtocolException, IOException {
		
	}
	
	@Test
	public void testGetGasStationById() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"getGasStationById/"+GAS_STATION_ID+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetAllGasStations() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"getAllGasStations/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testSaveGasStation() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"saveGasStation/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testDeleteUser() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"deleteUser/"+USER_ID+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetGasStationsByGasolineType() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"getGasStationsByGasolineType/"+GASOLINE_TYPE+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetGasStationsByProximity() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"searchGasStationByProximity/"+LAT+"/"+LON+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"getGasStationsWithCoordinates/"+LAT+"/"+LON+"/"+GASOLINE_TYPE+"/"+CAR_SHARING+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(BASE+"SetGasStationReport/"+GAS_STATION_ID+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}

}
