package it.polito.ezgas;

import static org.junit.Assert.*;

import javax.persistence.Column;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.entity.GasStation;

public class GasStationTest {
 	
	GasStation gasStation;
	int actualValue;
	private Integer gasStationId=2342;
    private String gasStationName="GAS STATION NAME";
    private String gasStationAddress="GAS STATION ADDRESS";
    private boolean hasDiesel=true;
    private boolean hasSuper=true;
    private boolean hasSuperPlus=true;
    private boolean hasGas=true;
    private boolean hasMethane=true;
    
    private String carSharing="CAR SHARING";
    private double lat=123.321;
    private double lon=546.234;
    private double dieselPrice=56.98;
    private double superPrice=23.67;
    private double superPlusPrice=99.1;
    private double gasPrice=32.33;
    private double methanePrice=65.78;
    private Integer reportUser=735;
    private String reportTimestamp="01/01/1000";
    private double reportDependability=139.695;
	
    private final double acceptableLatitudeAndLongitudeDelta=0.0001;
    private final double acceptablePriceDelta=0.001;
    private final double acceptableReportDependabilityDelta=0.001;

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
	
	@Test
	public void testGasStationId() {
		this.gasStation.setGasStationId(this.gasStationId);
		assertEquals(this.gasStationId, this.gasStation.getGasStationId());
	}
	
	@Test
	public void testGasStationName() {
		this.gasStation.setGasStationName(this.gasStationName);
		assertEquals(this.gasStationName, this.gasStation.getGasStationName());
	}
	
	@Test
	public void testGasStationAddress() {
		this.gasStation.setGasStationAddress(this.gasStationAddress);
		assertEquals(this.gasStationAddress, this.gasStation.getGasStationAddress());
	}
	
	@Test
	public void testHasDiesel() {
		this.gasStation.setHasDiesel(this.hasDiesel);
		assertEquals(this.hasDiesel, this.gasStation.getHasDiesel());
	}
	
	@Test
	public void testHasSuper() {
		this.gasStation.setHasSuper(this.hasSuper);
		assertEquals(this.hasSuper, this.gasStation.getHasSuper());
	}
	
	@Test
	public void testHasSuperPlus() {
		this.gasStation.setHasSuperPlus(this.hasSuperPlus);
		assertEquals(this.hasSuperPlus, this.gasStation.getHasSuperPlus());
	}
	
	@Test
	public void testHasGas() {
		this.gasStation.setHasGas(this.hasGas);
		assertEquals(this.hasGas, this.gasStation.getHasGas());
	}
	
	@Test
	public void testHasMethane() {
		this.gasStation.setHasMethane(this.hasMethane);
		assertEquals(this.hasMethane, this.gasStation.getHasMethane());
	}
	
	@Test
	public void testCarSharing() {
		this.gasStation.setCarSharing(this.carSharing);
		assertEquals(this.carSharing, this.gasStation.getCarSharing());
	}
	
	@Test
	public void testLat() {
		this.gasStation.setLat(this.lat);
		assertEquals(this.lat, this.gasStation.getLat(),this.acceptableLatitudeAndLongitudeDelta);
	}
	
	@Test
	public void testLon() {
		this.gasStation.setLon(this.lon);
		assertEquals(this.lon, this.gasStation.getLon(),this.acceptableLatitudeAndLongitudeDelta);
	}
	
	@Test
	public void testDieselPrice() {
		this.gasStation.setDieselPrice(this.dieselPrice);
		assertEquals(this.dieselPrice, this.gasStation.getDieselPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testSuperPrice() {
		this.gasStation.setSuperPrice(this.superPrice);
		assertEquals(this.superPrice, this.gasStation.getSuperPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testSuperPlusPrice() {
		this.gasStation.setSuperPlusPrice(this.superPlusPrice);
		assertEquals(this.superPlusPrice, this.gasStation.getSuperPlusPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testGasPrice() {
		this.gasStation.setGasPrice(this.gasPrice);
		assertEquals(this.gasPrice, this.gasStation.getGasPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testMethanePrice() {
		this.gasStation.setMethanePrice(this.methanePrice);
		assertEquals(this.methanePrice, this.gasStation.getMethanePrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testReportUser() {
		this.gasStation.setReportUser(this.reportUser);
		assertEquals(this.reportUser, this.gasStation.getReportUser());
	}
	
	@Test
	public void testReportTimestamp() {
		this.gasStation.setReportTimestamp(this.reportTimestamp);
		assertEquals(this.reportTimestamp, this.gasStation.getReportTimestamp());
	}

	@Test
	public void testReportDependability() {
		this.gasStation.setReportDependability(this.reportDependability);
		assertEquals(this.reportDependability, this.gasStation.getReportDependability(),this.acceptableReportDependabilityDelta);
	}
	
	@Test
	public void testGasStation() {
		
	}


	
}