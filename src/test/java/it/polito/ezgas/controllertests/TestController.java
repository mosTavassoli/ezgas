package it.polito.ezgas.controllertests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.utils.Constants;

@Transactional
public class TestController {
	
	private final static String USER_END_POINT = "http://localhost:8080/user";
	private final static String INCREASE_REPUTATION = "/increaseUserReputation";
	private final static String DECREASE_REPUTATION = "/decreaseUserReputation";
	private final static String GET_ALL_USERS = "/getAllUsers";
	private final static String GET_USER_BY_ID = "/getUser";
	private final static String DELETE_USER = "/deleteUser";
	private final static String SAVE_USER = "/saveUser";
	private final static String LOGIN = "/login";
	
	private final static String GASSTATION_END_POINT = "http://localhost:8080/gasstation/";
	private static final String GAS_STATION_ADDRESS = "testaddress";
	private static final String GAS_STATION_NAME = "testname";
	private static final boolean HAS_DIESEL = true;
	private static final boolean HAS_SUPER = false;
	private static final boolean HAS_SUPERPLUS = true;
	private static final boolean HAS_GAS = true;
	private static final boolean HAS_METHANE = false;
	private static final String REPORT_TIMESTAMP = new Timestamp(new Date().getTime()).toString();
	private static final double REPORT_DEPENDABILITY = 5.0;
	/**
	 * TODO How to put some users and gasStations in db to test? 
	 */
	
	private GasStationRepository gasStationRepository;
	
	private final String GASOLINE_TYPE=Constants.DIESEL;
	private final String CAR_SHARING="carsharing";
	private final int GAS_STATION_ID=22;
	private final int USER_ID=1;
	private final double PRICE=1.23;
	private final double LAT=45.101767;
	private final double LON= 7.646787;
	
	@Test
	public void testGetGasStationById() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"getGasStation/"+GAS_STATION_ID+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetAllGasStations() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"getAllGasStations/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testSaveGasStation() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost(GASSTATION_END_POINT+"saveGasStation/");
		ObjectMapper mapper = new ObjectMapper();
		GasStationDto gasStation = new GasStationDto(GAS_STATION_ID, GAS_STATION_NAME, GAS_STATION_ADDRESS, HAS_DIESEL, HAS_SUPER, HAS_SUPERPLUS, HAS_GAS, HAS_METHANE, CAR_SHARING, LAT, LON, PRICE, PRICE, PRICE, PRICE, PRICE, USER_ID, REPORT_TIMESTAMP, REPORT_DEPENDABILITY);
		request.addHeader("content-type", "application/json");
		request.setEntity(new StringEntity(mapper.writeValueAsString(gasStation)));
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
			
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testDeleteUser() throws ClientProtocolException, IOException {
		int userId = 35;
		final String USER_ID = "/" + userId;
		HttpDelete httpDelete = new HttpDelete(USER_END_POINT + DELETE_USER + USER_ID);
		HttpResponse response = HttpClientBuilder.create().build().execute(httpDelete);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	
	@Test
	public void testGetUserById() throws ClientProtocolException, IOException {
		int userId = 1;
		final String USER_ID = "/" + userId;
		HttpUriRequest request = new HttpGet(USER_END_POINT + GET_USER_BY_ID +  USER_ID );
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetAllUsers() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(USER_END_POINT + GET_ALL_USERS);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());
		
	}
	
	
	@Test
	public void testSaveUser() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost( USER_END_POINT + SAVE_USER);
		String JSON_STRING="{\""
				+ "userName\":\"NewUser\""
				+ ",\"password\":\"NewPass\""
				+ ",\"email\":\"NewUser@email.com\""
				+ ",\"reputation\":5 "
				+ ",\"admin\": false }";
		
	    HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
		request.setEntity(stringEntity);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		assertEquals(200 , response.getStatusLine().getStatusCode());

	}
	
	
	@Test
	public void testGetGasStationsByGasolineType() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"searchGasStationByGasolineType/"+GASOLINE_TYPE+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetGasStationsByProximity() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"searchGasStationByProximity/"+LAT+"/"+LON+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"getGasStationsWithCoordinates/"+LAT+"/"+LON+"/"+GASOLINE_TYPE+"/"+CAR_SHARING+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpPost(GASSTATION_END_POINT+"setGasStationReport/"+GAS_STATION_ID+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/"+PRICE+"/"+USER_ID+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testIncreaseUserReputation() throws ClientProtocolException, IOException {
		int userId = 1;
		final String USER_ID = "/" + userId;
		
		HttpUriRequest request = new HttpPost(USER_END_POINT + INCREASE_REPUTATION + USER_ID);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		assertEquals(5, Integer.parseInt(jsonFromResponse));
	}
	
	@Test
	public void testDecreaseUserReputation() throws ClientProtocolException, IOException {
		int userId = 1;
		final String USER_ID = "/" + userId;
		
		HttpUriRequest request = new HttpPost(USER_END_POINT + DECREASE_REPUTATION + USER_ID);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		assertEquals(4, Integer.parseInt(jsonFromResponse));
	}
	
	@Test
	public void testLogin() throws ClientProtocolException, IOException {
		IdPw credentials = new IdPw("admin@ezgas.com", "admin");
		
		HttpPost request = new HttpPost(USER_END_POINT + LOGIN);
		ObjectMapper mapper = new ObjectMapper();
		request.addHeader("content-type", "application/json");
		request.setEntity(new StringEntity(mapper.writeValueAsString(credentials)));
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		LoginDto loginDto = mapper.readValue(jsonFromResponse, LoginDto.class);
		//assertEquals(200, response.getStatusLine().getStatusCode());
		assertNotNull(loginDto);
	}

}
