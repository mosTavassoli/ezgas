package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;

public class GasStationDtoTest {
	
	GasStationDto gasStationDto;
	private Integer gasStationId=2342;
    private String gasStationName="GAS STATION NAME";
    private String gasStationAddress="GAS STATION ADDRESS";
    private boolean hasDiesel=true;
    private boolean hasSuper=true;
    private boolean hasSuperPlus=true;
    private boolean hasGas=true;
    private boolean hasMethane=true;
    
    private Boolean hasSuperBoolean=true;
    private Boolean hasGasBoolean=true;
    private Boolean hasSuperPlusBoolean=true;
    
    
    private String carSharing="CAR SHARING";
    private double lat=123.321;
    private double lon=546.234;
    private double dieselPrice=56.98;
    private double superPrice=23.67;
    private double superPlusPrice=99.1;
    private double gasPrice=32.33;
    private double methanePrice=65.78;
    private Integer reportUser=735;
    private String reportTimestamp="2020-05-11";
    private double reportDependability=3;
    
	UserDto userDto = new UserDto(15, "Username15", "pass15", "test15@email.com", 3, false);
	
    private final double acceptableLatitudeAndLongitudeDelta=0.0001;
    private final double acceptablePriceDelta=0.001;
    private final double acceptableReportDependabilityDelta=0.001;
	
    @Before
	public void init() {
		this.gasStationDto=new GasStationDto();
	}
	
	@Test
	public void testGasStationDoesNotHaveDiesel() {
		gasStationDto.setHasDiesel(false);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testGasStationDoesNotHaveGas() {
		gasStationDto.setHasGas(false);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testGasStationDoesNotHaveMethane() {
		gasStationDto.setHasMethane(false);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testGasStationDoesNotHaveSuper() {
		gasStationDto.setHasSuper(false);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testGasStationDoesNotHaveSuperPlus() {
		gasStationDto.setHasSuperPlus(false);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNegativeDieselPrice() {
		gasStationDto.setHasDiesel(true);
		gasStationDto.setDieselPrice(-2.5);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNegativeGasPrice() {
		gasStationDto.setHasGas(true);
		gasStationDto.setGasPrice(-2.5);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNegativeMethanePrice() {
		gasStationDto.setHasMethane(true);
		gasStationDto.setMethanePrice(-2.5);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNegativeSuperPrice() {
		gasStationDto.setHasSuper(true);
		gasStationDto.setSuperPrice(-2.5);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNegativeSuperPlusPrice() {
		gasStationDto.setHasSuperPlus(true);
		gasStationDto.setSuperPlusPrice(-2.5);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneDieselPrice() {
		gasStationDto.setHasDiesel(true);
		gasStationDto.setDieselPrice(-1.0);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneGasPrice() {
		gasStationDto.setHasGas(true);
		gasStationDto.setGasPrice(-1.0);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneMethanePrice() {
		gasStationDto.setHasMethane(true);
		gasStationDto.setMethanePrice(-1.0);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneSuperPrice() {
		gasStationDto.setHasSuper(true);
		gasStationDto.setSuperPrice(-1.0);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneSuperPlusPrice() {
		gasStationDto.setHasSuperPlus(true);
		gasStationDto.setSuperPlusPrice(-1.0);
		assertEquals(false,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNonNegativeDieselPrice() {
		gasStationDto.setHasDiesel(true);
		gasStationDto.setDieselPrice(1.56);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNonNegativeGasPrice() {
		gasStationDto.setHasGas(true);
		gasStationDto.setGasPrice(1.56);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNonNegativeMethanePrice() {
		gasStationDto.setHasMethane(true);
		gasStationDto.setMethanePrice(1.56);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNonNegativeSuperPrice() {
		gasStationDto.setHasSuper(true);
		gasStationDto.setSuperPrice(1.56);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testNonNegativeSuperPlusPrice() {
		gasStationDto.setHasSuperPlus(true);
		gasStationDto.setSuperPlusPrice(1.56);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testLatitudeLargerThanUpperBound() {
		assertEquals(false,GasStationDto.checkCoordinates(120, 0));
	}
	
	@Test
	public void testLatitudeSmallerThanLowerBound() {
		assertEquals(false,GasStationDto.checkCoordinates(-120, 0));
	}
	
	@Test
	public void testLongitudeLargerThanUpperBound() {
		assertEquals(false,GasStationDto.checkCoordinates(0, 200));
	}
	
	@Test
	public void testLongitudeSmallerThanLowerBound() {
		assertEquals(false,GasStationDto.checkCoordinates(0, -200));
	}
	
	@Test
	public void testLatitudeAndLongitudeOutOfBounds() {
		assertEquals(false,GasStationDto.checkCoordinates(120, -200));
	}
	
	@Test
	public void testLatitudeAndLongitudeInsideBounds() {
		assertEquals(true,GasStationDto.checkCoordinates(43.63, 168.111));
	}
	
	@Test
	public void testToStringWithUninitializedAttributes() {
		assertEquals("\n{\n"
				+ "gasStationId = null,\n"
				+ "gasStationName = null,\n"
				+ "gasStationAddress = null,\n"
				+ "hasDiesel = false,\n"
				+ "hasSuper = false,\n"
				+ "hasSuperPlus = false,\n"
				+ "hasGas = false,\n"
				+ "hasMethane = false,\n"
				+ "hasPremiumDiesel = false,\n"
				+ "carSharing = null,\n"
				+ "lat = 0.0,\n"
				+ "lon = 0.0,\n"
				+ "dieselPrice = null,\n"
				+ "superPrice = null,\n"
				+ "superPlusPrice = null,\n"
				+ "gasPrice = null,\n"
				+ "methanePrice = null,\n"
				+ "premiumDieselPrice = null,\n"
				+ "reportUser = null,\n"
				+ "userDto = null,\n"
				+ "reportTimestamp = null,\n"
				+ "reportDependability = 0.0,\n"
				+ "}\n", gasStationDto.toString());
	}
	
	@Test
	public void testToStringWithInitializedAttributes() {
		gasStationDto = new GasStationDto(123, "station", "5th street", true, false, 
				false, true, false, true, "CarCar", 35.0, 45.787, 0.54, -110.0, 12.0, 356.768, 
				0.007, 1.1, 4321, "12/12/2012", 4.36);
		assertEquals("\n{\n"
				+ "gasStationId = 123,\n"
				+ "gasStationName = station,\n"
				+ "gasStationAddress = 5th street,\n"
				+ "hasDiesel = true,\n"
				+ "hasSuper = false,\n"
				+ "hasSuperPlus = false,\n"
				+ "hasGas = true,\n"
				+ "hasMethane = false,\n"
				+ "hasPremiumDiesel = true,\n"
				+ "carSharing = CarCar,\n"
				+ "lat = 35.0,\n"
				+ "lon = 45.787,\n"
				+ "dieselPrice = 0.54,\n"
				+ "superPrice = -110.0,\n"
				+ "superPlusPrice = 12.0,\n"
				+ "gasPrice = 356.768,\n"
				+ "methanePrice = 0.007,\n"
				+ "premiumDieselPrice = 1.1,\n"
				+ "reportUser = 4321,\n"
				+ "userDto = null,\n"
				+ "reportTimestamp = 12/12/2012,\n"
				+ "reportDependability = 4.36,\n"
				+ "}\n", gasStationDto.toString());
	}
	
	
	@Test
	public void testGasStationId() {
		this.gasStationDto.setGasStationId(this.gasStationId);
		assertEquals(this.gasStationId, this.gasStationDto.getGasStationId());
	}
	
	@Test
	public void testGasStationName() {
		this.gasStationDto.setGasStationName(this.gasStationName);
		assertEquals(this.gasStationName, this.gasStationDto.getGasStationName());
	}
	
	@Test
	public void testGasStationAddress() {
		this.gasStationDto.setGasStationAddress(this.gasStationAddress);
		assertEquals(this.gasStationAddress, this.gasStationDto.getGasStationAddress());
	}
	
	@Test
	public void testHasDiesel() {
		this.gasStationDto.setHasDiesel(this.hasDiesel);
		assertEquals(this.hasDiesel, this.gasStationDto.getHasDiesel());
	}
	
	@Test
	public void testHasSuper() {
		this.gasStationDto.setHasSuper(this.hasSuper);
		assertEquals(this.hasSuper, this.gasStationDto.getHasSuper());
	}
	
	@Test
	public void testHasSuperPlus() {
		this.gasStationDto.setHasSuperPlus(this.hasSuperPlus);
		assertEquals(this.hasSuperPlus, this.gasStationDto.getHasSuperPlus());
	}
	
	@Test
	public void testHasGas() {
		this.gasStationDto.setHasGas(this.hasGas);
		assertEquals(this.hasGas, this.gasStationDto.getHasGas());
	}
	
	@Test
	public void testHasSuperBoolean() {
		this.gasStationDto.setHasSuper(this.hasSuperBoolean);
		assertEquals(this.hasSuperBoolean, this.gasStationDto.getHasSuper());
	}
	
	@Test
	public void testHasSuperPlusBoolean() {
		this.gasStationDto.setHasSuperPlus(this.hasSuperPlusBoolean);
		assertEquals(this.hasSuperPlusBoolean, this.gasStationDto.getHasSuperPlus());
	}
	
	@Test
	public void testHasGasBoolean() {
		this.gasStationDto.setHasGas(this.hasGasBoolean);
		assertEquals(this.hasGasBoolean, this.gasStationDto.getHasGas());
	}
	
	@Test
	public void testHasMethane() {
		this.gasStationDto.setHasMethane(this.hasMethane);
		assertEquals(this.hasMethane, this.gasStationDto.getHasMethane());
	}
	
	@Test
	public void testCarSharing() {
		this.gasStationDto.setCarSharing(this.carSharing);
		assertEquals(this.carSharing, this.gasStationDto.getCarSharing());
	}
	
	@Test
	public void testLat() {
		this.gasStationDto.setLat(this.lat);
		assertEquals(this.lat, this.gasStationDto.getLat(),this.acceptableLatitudeAndLongitudeDelta);
	}
	
	@Test
	public void testLon() {
		this.gasStationDto.setLon(this.lon);
		assertEquals(this.lon, this.gasStationDto.getLon(),this.acceptableLatitudeAndLongitudeDelta);
	}
	
	@Test
	public void testDieselPrice() {
		this.gasStationDto.setDieselPrice(this.dieselPrice);
		assertEquals(this.dieselPrice, this.gasStationDto.getDieselPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testSuperPrice() {
		this.gasStationDto.setSuperPrice(this.superPrice);
		assertEquals(this.superPrice, this.gasStationDto.getSuperPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testSuperPlusPrice() {
		this.gasStationDto.setSuperPlusPrice(this.superPlusPrice);
		assertEquals(this.superPlusPrice, this.gasStationDto.getSuperPlusPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testGasPrice() {
		this.gasStationDto.setGasPrice(this.gasPrice);
		assertEquals(this.gasPrice, this.gasStationDto.getGasPrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testMethanePrice() {
		this.gasStationDto.setMethanePrice(this.methanePrice);
		assertEquals(this.methanePrice, this.gasStationDto.getMethanePrice(),this.acceptablePriceDelta);
	}
	
	@Test
	public void testReportUser() {
		this.gasStationDto.setReportUser(this.reportUser);
		assertEquals(this.reportUser, this.gasStationDto.getReportUser());
	}
	
	@Test
	public void testReportTimestamp() {
		this.gasStationDto.setReportTimestamp(this.reportTimestamp);
		assertEquals(this.reportTimestamp, this.gasStationDto.getReportTimestamp());
	}

	@Test
	public void testReportDependability() {
		this.gasStationDto.setReportDependability(this.reportDependability);
		assertEquals(this.reportDependability, this.gasStationDto.getReportDependability(),this.acceptableReportDependabilityDelta);
	}
	
	@Test
	public void testUserDto() {
		this.gasStationDto.setUserDto(userDto);
		assertNotNull(this.gasStationDto.getUserDto());
	}
	
	@Test
	public void testComputeReportDependabilityMoreThan7Days() {
		Date reportDate = new Date(System.currentTimeMillis()-8*24*60*60*1000);
		SimpleDateFormat toFormat = new SimpleDateFormat("MM-dd-yyyy");
		this.gasStationDto.setReportTimestamp(toFormat.format(reportDate));
		this.gasStationDto.setReportDependability(this.reportDependability);

		
		double obsolescence;
		Integer userReputation = (int)this.gasStationDto.getReportDependability();
		Date today = new Date();
		
		
		long diffInMillies = today.getTime() - reportDate.getTime();
		long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diffInDays > 7)
			obsolescence = 0;
		else obsolescence = 1 - (double) diffInDays / 7;
		
		double reportDependability = 50 * (userReputation + 5) / 10 + 50 * obsolescence;
		
		assertEquals(reportDependability, this.gasStationDto.computeReportDependability(), acceptableReportDependabilityDelta);
	}
	
	@Test
	public void testComputeReportDependabilityLessThan7Days() {
		Date reportDate = new Date(System.currentTimeMillis()-24*60*60*1000);
		SimpleDateFormat toFormat = new SimpleDateFormat("MM-dd-yyyy");
		this.gasStationDto.setReportTimestamp(toFormat.format(reportDate));
		this.gasStationDto.setReportDependability(this.reportDependability);
		
		double obsolescence;
		Integer userReputation = (int)this.gasStationDto.getReportDependability();
		Date today = new Date();
		
		long diffInMillies = today.getTime() - reportDate.getTime();
		long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diffInDays > 7)
			obsolescence = 0;
		else obsolescence = 1 - (double) diffInDays / 7;
		
		this.gasStationDto.setReportDependability(this.reportDependability);

		
		double reportDependability = 50 * (userReputation + 5) / 10 + 50 * obsolescence;
		
		assertEquals(reportDependability, this.gasStationDto.computeReportDependability(), acceptableReportDependabilityDelta);
	}
	
	@Test
	public void testComputeReportDependabilityWithNullReportTimestamp() {
		this.gasStationDto.setReportTimestamp(null);
		
		assertEquals(0, this.gasStationDto.computeReportDependability(), acceptableReportDependabilityDelta);
	}

}
