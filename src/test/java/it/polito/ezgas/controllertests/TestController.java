package it.polito.ezgas.controllertests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.utils.Constants;

public class TestController {
	
	private final static String USER_END_POINT = "http://localhost:8080/user";
	private final static String INCREASE_REPUTATION = "/increaseUserReputation";
	private final static String DECREASE_REPUTATION = "/decreaseUserReputation";
	private final static String GET_ALL_USERS = "/getAllUsers";
	private final static String LOGIN = "/login";
	
	private final static String GASSTATION_END_POINT = "http://localhost:8080/gasstation";
	
	/**
	 * TODO How to put some users and gasStations in db to test? 
	 */
	
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
	public void testGetAllUsers() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet(USER_END_POINT + GET_ALL_USERS);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromRespone = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userArray = mapper.readValue(jsonFromRespone, UserDto[].class);
		
		assertEquals(1,userArray.length);
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
		request.setEntity(new StringEntity(mapper.writeValueAsString(credentials)));
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		LoginDto loginDto = mapper.readValue(jsonFromResponse, LoginDto.class);

		assertNotNull(loginDto);
	}

}
