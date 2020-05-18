package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.entity.GasStation;

public class GasStationTest {
 	
	GasStation gasStation;
	int actualValue;
	@Before
	public void init() {
		this.gasStation = new GasStation();
	}
	
	@Test
	public void testGasStationIdMinInt() {
		gasStation.setGasStationId(Integer.MIN_VALUE);
		actualValue = gasStation.getGasStationId();
		assertEquals(Integer.MIN_VALUE, actualValue);
	}
	
	@Test
	public void testGasStationIdMinIntPlusOne() {
		gasStation.setGasStationId(Integer.MIN_VALUE +1);
		actualValue = gasStation.getGasStationId();
		assertEquals(Integer.MIN_VALUE +1, actualValue);
	}
	
	@Test
	public void testGasStationIdMinusOne() {
		gasStation.setGasStationId(-1);
		actualValue = gasStation.getGasStationId();
		assertEquals(-1, actualValue);
	}
	
	@Test
	public void testGasStationIdZero() {
		gasStation.setGasStationId(0);	
		actualValue = gasStation.getGasStationId();
		assertEquals(0, actualValue);
	}
	
	@Test
	public void testGasStationIdMaxInt() {
		gasStation.setGasStationId(Integer.MAX_VALUE);
		actualValue = gasStation.getGasStationId();
		assertEquals(Integer.MAX_VALUE, actualValue);
	}
	
	@Test
	public void testGasStationIdMaxIntMinusOne() {
		gasStation.setGasStationId(Integer.MAX_VALUE-1);
		actualValue = gasStation.getGasStationId();
		assertEquals(Integer.MAX_VALUE-1, actualValue);
	}
	
}