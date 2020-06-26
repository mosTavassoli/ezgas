package it.polito.ezgas.controllertests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.PriceReportDto;
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
	private static final boolean HAS_PREMIUM_PRICE = true;
	private static final String REPORT_TIMESTAMP = "05-22-2020";
	private static final double REPORT_DEPENDABILITY = 5.0;
	
	private final String GASOLINE_TYPE=Constants.DIESEL;
	private final String CAR_SHARING="carsharing";
	
	//This id should be set to a real gas station id present in the database
	private final int GAS_STATION_ID=202;
	//This is should be set to a real user id present in the database
	private final int USER_ID=46;
	
	private final double PRICE=1.23;
	private final double LAT=45.101767;
	private final double LON= 7.646787;
	private final int RADIUS= 1;
	
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
		GasStationDto gasStation = new GasStationDto(GAS_STATION_ID, GAS_STATION_NAME, GAS_STATION_ADDRESS, HAS_DIESEL, HAS_SUPER, HAS_SUPERPLUS, HAS_GAS, HAS_METHANE, HAS_PREMIUM_PRICE, CAR_SHARING, LAT, LON, PRICE, PRICE, PRICE, PRICE, PRICE, PRICE, USER_ID, REPORT_TIMESTAMP, REPORT_DEPENDABILITY);
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
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"searchGasStationByProximity/"+LAT+"/"+LON+"/"+RADIUS+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(GASSTATION_END_POINT+"getGasStationsWithCoordinates/"+LAT+"/"+LON+"/"+RADIUS+"/"+GASOLINE_TYPE+"/"+CAR_SHARING+"/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException {		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		
		HttpUriRequest getRequest = new HttpGet(GASSTATION_END_POINT+"getAllGasStations/");
		HttpResponse getResponse = HttpClientBuilder.create().build().execute(getRequest);
		List<GasStationDto> gasStationDtoList = mapper.readValue(EntityUtils.toString(getResponse.getEntity()), new TypeReference<List<GasStationDto>>(){});
		Integer gasStationId = gasStationDtoList.stream().mapToInt(GasStationDto::getGasStationId).max().getAsInt();
		
		HttpPost request = new HttpPost(GASSTATION_END_POINT+"setGasStationReport/");	
		
		PriceReportDto priceReportDto = new PriceReportDto(gasStationId, PRICE, PRICE, PRICE, PRICE, PRICE, PRICE, USER_ID);
		request.addHeader("content-type", "application/json");
		request.setEntity(new StringEntity(mapper.writeValueAsString(priceReportDto)));
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testIncreaseUserReputation() throws ClientProtocolException, IOException {
		final String USER_ID = "/" + this.USER_ID;
		
		HttpUriRequest request = new HttpPost(USER_END_POINT + INCREASE_REPUTATION + USER_ID);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testDecreaseUserReputation() throws ClientProtocolException, IOException {
		final String USER_ID = "/" + this.USER_ID;
		
		HttpUriRequest request = new HttpPost(USER_END_POINT + DECREASE_REPUTATION + USER_ID);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testLogin() throws ClientProtocolException, IOException {
		IdPw credentials = new IdPw("admin@ezgas.com", "admin");
		
		HttpPost request = new HttpPost(USER_END_POINT + LOGIN);
		ObjectMapper mapper = new ObjectMapper();
		request.addHeader("content-type", "application/json");
		request.setEntity(new StringEntity(mapper.writeValueAsString(credentials)));
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	//Home controller tests
	@Test
	public void testAdmin() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/admin");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testIndex() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testMap() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/map");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	@Test
	public void testHomeControllerLogin() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/login");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	@Test
	public void testUpdate() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/update");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	@Test
	public void testSignup() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/signup");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}

}
