package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.entity.GasStation;

public class GasStationTest {
	
	GasStation gasStation;
 
	@Before
	public void init() {
		this.gasStation = new GasStation();
	}
	
	@Test
	public void testGasStationIdMinInt() {
		gasStation.setGasStationId(Integer.MIN_VALUE);
		assertTrue(gasStation.getGasStationId() == Integer.MIN_VALUE);
	}
	
	@Test
	public void testGasStationIdMinIntPlusOne() {
		gasStation.setGasStationId(Integer.MIN_VALUE +1);
		assertTrue(gasStation.getGasStationId() == Integer.MIN_VALUE+1);
	}
	
	@Test
	public void testGasStationIdMinusOne() {
		gasStation.setGasStationId(-1);
		assertTrue(gasStation.getGasStationId() == -1);
	}
	
	@Test
	public void testGasStationIdZero() {
		gasStation.setGasStationId(0);
		assertTrue(gasStation.getGasStationId() == 0);
	}
	
	@Test
	public void testGasStationIdMaxInt() {
		gasStation.setGasStationId(Integer.MAX_VALUE);
		assertTrue(gasStation.getGasStationId() == Integer.MAX_VALUE);
	}
	
	@Test
	public void testGasStationIdMaxIntMinusOne() {
		gasStation.setGasStationId(Integer.MAX_VALUE-1);
		assertTrue(gasStation.getGasStationId() == Integer.MAX_VALUE-1);
	}
	
}
