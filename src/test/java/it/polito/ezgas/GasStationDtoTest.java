package it.polito.ezgas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.GasStationDto;

public class GasStationDtoTest {
	
	GasStationDto gasStationDto;
	
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
		gasStationDto.setDieselPrice(-1);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneGasPrice() {
		gasStationDto.setHasGas(true);
		gasStationDto.setGasPrice(-1);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneMethanePrice() {
		gasStationDto.setHasMethane(true);
		gasStationDto.setMethanePrice(-1);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneSuperPrice() {
		gasStationDto.setHasSuper(true);
		gasStationDto.setSuperPrice(-1);
		assertEquals(true,gasStationDto.checkPrices());
	}
	
	@Test
	public void testMinusOneSuperPlusPrice() {
		gasStationDto.setHasSuperPlus(true);
		gasStationDto.setSuperPlusPrice(-1);
		assertEquals(true,gasStationDto.checkPrices());
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
	public void testLatitudeOutOfBounds() {
		assertEquals(false,GasStationDto.checkCoordinates(120, 0));
	}
	
	@Test
	public void testLongitudeOutOfBounds() {
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
				+ "carSharing = null,\n"
				+ "lat = 0.0,\n"
				+ "lon = 0.0,\n"
				+ "dieselPrice = 0.0,\n"
				+ "superPrice = 0.0,\n"
				+ "superPlusPrice = 0.0,\n"
				+ "gasPrice = 0.0,\n"
				+ "methanePrice = 0.0,\n"
				+ "reportUser = null,\n"
				+ "userDto = null,\n"
				+ "reportTimestamp = null,\n"
				+ "reportDependability = 0.0,\n"
				+ "}\n", gasStationDto.toString());
	}
	
	@Test
	public void testToStringWithInitializedAttributes() {
		gasStationDto = new GasStationDto(123, "station", "5th street", true, false, 
				false, true, false, "CarCar", 35, 45.787, 0.54, -110, 12, 356.768, 
				0.007, 4321, "12/12/2012", 4.36);
		assertEquals("\n{\n"
				+ "gasStationId = 123,\n"
				+ "gasStationName = station,\n"
				+ "gasStationAddress = 5th street,\n"
				+ "hasDiesel = true,\n"
				+ "hasSuper = false,\n"
				+ "hasSuperPlus = false,\n"
				+ "hasGas = true,\n"
				+ "hasMethane = false,\n"
				+ "carSharing = CarCar,\n"
				+ "lat = 35.0,\n"
				+ "lon = 45.787,\n"
				+ "dieselPrice = 0.54,\n"
				+ "superPrice = -110.0,\n"
				+ "superPlusPrice = 12.0,\n"
				+ "gasPrice = 356.768,\n"
				+ "methanePrice = 0.007,\n"
				+ "reportUser = 4321,\n"
				+ "userDto = null,\n"
				+ "reportTimestamp = 12/12/2012,\n"
				+ "reportDependability = 4.36,\n"
				+ "}\n", gasStationDto.toString());
	}
}
