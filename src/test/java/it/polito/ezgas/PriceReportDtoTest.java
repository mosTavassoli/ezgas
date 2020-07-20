package it.polito.ezgas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.PriceReportDto;

public class PriceReportDtoTest {
	PriceReportDto priceReportDto;

	@Before
	public void init() {
		this.priceReportDto = new PriceReportDto();
	}
	
    Integer gasStationId = 13;
    Double dieselPrice = 1.33;
    Double superPrice = 1.23;
    Double superPlusPrice = 1.76;
    Double gasPrice = 1.78;
    Double methanePrice = 1.08;
    Double premiumDieselPrice = 1.67;
    Integer userId = 6;
	

	@Test
	public void testPriceReportDtoConstructor() {
		priceReportDto = new PriceReportDto(this.gasStationId,this.dieselPrice,this.superPrice,this.superPlusPrice, this.gasPrice, this.methanePrice, this.premiumDieselPrice, this.userId);
		assertEquals(this.gasStationId, priceReportDto.getGasStationId());
		assertEquals(this.dieselPrice, priceReportDto.getDieselPrice());
		assertEquals(this.superPrice, priceReportDto.getSuperPrice());
		assertEquals(this.superPlusPrice, priceReportDto.getSuperPlusPrice());
		assertEquals(this.gasPrice, priceReportDto.getGasPrice());
		assertEquals(this.methanePrice, priceReportDto.getMethanePrice());
		assertEquals(this.premiumDieselPrice, priceReportDto.getPremiumDieselPrice());
		assertEquals(this.userId, priceReportDto.getUserId());
	}

	@Test
	public void testGasStationIdMinInt() {
		priceReportDto.setGasStationId(Integer.MIN_VALUE);
		int actualValue = priceReportDto.getGasStationId();
		assertEquals(Integer.MIN_VALUE, actualValue);
	}
	
	@Test
	public void testGasStationIdMinIntPlusOne() {
		priceReportDto.setGasStationId(Integer.MIN_VALUE + 1);
		int actualValue = priceReportDto.getGasStationId();
		assertEquals(Integer.MIN_VALUE + 1, actualValue);
	}
	
	@Test
	public void testGasStationIdMinusOne() {
		priceReportDto.setGasStationId(-1);
		int actualValue = priceReportDto.getGasStationId();
		assertEquals(-1, actualValue);	
	}
	
	@Test
	public void testGasStationIdZero() {
		priceReportDto.setGasStationId(0);
		int actualValue = priceReportDto.getGasStationId();
		assertEquals(0, actualValue);
	}
	
	@Test
	public void testGasStationIdMaxInt() {
		priceReportDto.setGasStationId(Integer.MAX_VALUE);
		int actualValue = priceReportDto.getGasStationId();
		assertEquals(Integer.MAX_VALUE, actualValue);
	}
	
	@Test
	public void testGasStationIdMaxIntMinusOne() {
		priceReportDto.setGasStationId(Integer.MAX_VALUE -1);
		int actualValue = priceReportDto.getGasStationId();
		assertEquals(Integer.MAX_VALUE -1, actualValue);
	}
	
	@Test
	public void testPremiumDieselPrice() {
		priceReportDto.setPremiumDieselPrice(this.premiumDieselPrice);
		assertEquals(this.premiumDieselPrice,this.priceReportDto.getPremiumDieselPrice());
	}
	
	@Test
	public void testSuperPrice() {
		priceReportDto.setSuperPrice(this.superPrice);
		assertEquals(this.superPrice,this.priceReportDto.getSuperPrice());
	}
	@Test
	public void testSuperPlusPrice() {
		priceReportDto.setSuperPlusPrice(this.superPlusPrice);
		assertEquals(this.superPlusPrice,this.priceReportDto.getSuperPlusPrice());
	}
	@Test
	public void testGasPrice() {
		priceReportDto.setGasPrice(this.gasPrice);
		assertEquals(this.gasPrice,this.priceReportDto.getGasPrice());
	}
	@Test
	public void testMethanePrice() {
		priceReportDto.setMethanePrice(this.methanePrice);
		assertEquals(this.methanePrice,this.priceReportDto.getMethanePrice());
	}
	@Test
	public void testDieselPrice() {
		priceReportDto.setDieselPrice(this.dieselPrice);
		assertEquals(this.dieselPrice,this.priceReportDto.getDieselPrice());
	}
	
	@Test
	public void testUserIdMinInt() {
		priceReportDto.setUserId(Integer.MIN_VALUE);
		int actualValue = priceReportDto.getUserId();
		assertEquals(Integer.MIN_VALUE, actualValue);
	}
	
	@Test
	public void testUserIdMinIntPlusOne() {
		priceReportDto.setUserId(Integer.MIN_VALUE + 1);
		int actualValue = priceReportDto.getUserId();
		assertEquals(Integer.MIN_VALUE + 1, actualValue);
	}
	
	@Test
	public void testUserIdMinusOne() {
		priceReportDto.setUserId(-1);
		int actualValue = priceReportDto.getUserId();
		assertEquals(-1, actualValue);	
	}
	
	@Test
	public void testUserIdZero() {
		priceReportDto.setUserId(0);
		int actualValue = priceReportDto.getUserId();
		assertEquals(0, actualValue);
	}
	
	@Test
	public void testUserIdMaxInt() {
		priceReportDto.setUserId(Integer.MAX_VALUE);
		int actualValue = priceReportDto.getUserId();
		assertEquals(Integer.MAX_VALUE, actualValue);
	}
	
	@Test
	public void testUserIdMaxIntMinusOne() {
		priceReportDto.setUserId(Integer.MAX_VALUE -1);
		int actualValue = priceReportDto.getUserId();
		assertEquals(Integer.MAX_VALUE -1, actualValue);
	}
}
