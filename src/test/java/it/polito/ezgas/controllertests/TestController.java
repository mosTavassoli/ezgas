package it.polito.ezgas.controllertests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;

public class TestController {
	
	private final static String USER_END_POINT = "http://localhost:8080/user";
	private final static String INCREASE_REPUTATION = "/increaseUserReputation";
	private final static String DECREASE_REPUTATION = "/decreaseUserReputation";
	private final static String LOGIN = "/login";
	
	private final static String GASSTATION_END_POINT = "http://localhost:8080/gasstation";
	
	/**
	 * TODO How to put some users and gasStations in db to test? 
	 */

	@Test
	public void testGetGasStationById() {
		
	}
	
	@Test
	public void testGetAllGasStations() {
		
	}
	
	@Test
	public void testSaveGasStation() {
		
	}
	
	@Test
	public void testDeleteUser() {
		
	}
	
	@Test
	public void testGetGasStationsByGasolineType() {
		
	}
	
	@Test
	public void testGetGasStationsByProximity() {
		
	}
	
	@Test
	public void testGetGasStationsWithCoordinates() {
		
	}
	
	@Test
	public void testSetGasStationReport() {
		
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
