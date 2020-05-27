package it.polito.ezgas;

import static it.polito.ezgas.GasStationRepositoryTest.distanceInKilometersBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.service.impl.UserServiceimpl;
import it.polito.ezgas.utils.Constants;


@RunWith( SpringRunner.class )
@DataJpaTest
public class GasStationServiceimplTest {
	
	@Autowired
	private GasStationRepository gasStationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private GasStationService gasStationService;
	
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService(UserRepository userRepository) {
            return new UserServiceimpl(userRepository);
        }
    }
	
	@TestConfiguration
    static class GasStationImplTestContextConfiguration {
  
        @Bean
        @Autowired
        public GasStationService gasStationService(GasStationRepository gasStationRepository) {
            return new GasStationServiceimpl(gasStationRepository);
        }
    }
	
	private final int NUMBER_OF_GAS_STATIONS=15; //from 1 to maxint
	private final int NUMBER_OF_USERS=15;
	private final int NUMBER_OF_CAR_SHARING=2; //from 1 to maxint
	private final int INVALID_USER_ID=-33;
	private final int INVALID_GAS_STATION_ID=-24;
	private final double MAX_PRICE=5.00;
	private final double MAX_DEPENDABILITY=5.00;
	
	private final String INVALID_GAS_TYPE="invalidGas";
	private final String VALID_CARSHARING="0";
	
	private int validGasStationId;
	private int validUserId;
	private List<GasStation> gasStationList;
	
	@Before
	public void init() {
		Random random = new Random();
		gasStationList = new ArrayList<GasStation>();
		User user=null;
		for(int i=0;i<NUMBER_OF_GAS_STATIONS;i++) {
			gasStationList.add(gasStationRepository.save(new GasStation(
					"gasstationname"+i, 
					"gasstationaddress"+i, 
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					Integer.toString(random.nextInt(NUMBER_OF_CAR_SHARING)), 
					random.nextDouble()*Constants.MAX_LAT-Constants.MAX_LAT, // ignoring the other half of the possible values to test empty list when looking for gas stations by proximity
					random.nextDouble()*Constants.MAX_LON*2-Constants.MAX_LON, 
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					i,
					"reporttimestamp"+i,
					random.nextDouble()*MAX_DEPENDABILITY)));
		}
		
		//setup for proximity tests
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, "sharing", 45.101767, 7.646787, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //0km
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, "sharing1", 45.107317, 7.636762, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //1km
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", false, true, true, true, true, "sharing", 45.113210, 7.627207, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //2km
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, "sharing1", 45.119454, 7.618003, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //3km
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", false, true, true, true, true, "sharing1", 45.126049, 7.609262, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //4km
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, "sharing", 45.131268, 7.598965, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //4.99km
		gasStationList.add(gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, "sharing", 45.131359, 7.598632, 1.11, 2.22, 3.33, 4.44, 5.55, 123321, "stamp", 7.77))); //5.01km
		
		if(gasStationList.get(0)!=null) {
			this.validGasStationId = gasStationList.get(0).getGasStationId();
		}
		for(int i=0;i<NUMBER_OF_USERS;i++) {
			user = userRepository.save(new User("user"+i, "password"+i, "user"+i+"@example.com", i));
		}
		if(user!=null) {
			validUserId = user.getUserId();
		}
	}
	
	@Test(expected = InvalidGasStationException.class)
	public void testGetGasStationByIdNegative() throws InvalidGasStationException {
		gasStationService.getGasStationById(-23);
	}
	
	@Test
	public void testGetGasStationByIdDoesNotExist() throws InvalidGasStationException {
		assertNull(gasStationService.getGasStationById(9999));
	}
	
	@Test
	public void testGetGasStationByIdPositiveAndExists() throws InvalidGasStationException {
		GasStationDto gasStationDto = gasStationService.getGasStationById(this.validGasStationId);
		assertEquals(validGasStationId,(int)gasStationDto.getGasStationId());
	}
	
	@Test(expected = PriceException.class)
	public void testSaveGasStationInvalidNegativePrice() throws PriceException,GPSDataException {
		GasStationDto gasStationDto = new GasStationDto();
		gasStationDto.setHasDiesel(true);
		gasStationDto.setDieselPrice(-12);
		gasStationService.saveGasStation(gasStationDto);
	}
	
	@Test(expected = GPSDataException.class)
	public void testSaveGasStationInvalidCoordinates() throws PriceException,GPSDataException {
		GasStationDto gasStationDto = new GasStationDto();
		gasStationDto.setLat(-500.11);
		gasStationDto.setLon(343.12);
		gasStationService.saveGasStation(gasStationDto);
	}
	
	@Test
	public void testSaveGasStationValid() throws PriceException,GPSDataException {
		GasStationDto gasStationDtoSent = new GasStationDto(99999, "gasStationName", "gasStationAddress", true, false, true, true, false, "carSharing", 12.3, 23.43, 1.23, 2.34, 3.45, 4.56, 5.67, 1234, "reportTimestamp", 5.43);
		GasStationDto gasStationDtoReceived = gasStationService.saveGasStation(gasStationDtoSent);
		assertTrue(new ReflectionEquals(gasStationDtoSent,"gasStationId","reportDependability").matches(gasStationDtoReceived));
		assertEquals(0.0,gasStationDtoReceived.getReportDependability(),0.0);
	}
	
	@Test
	public void testGetAllGasStationsEmpty() {
		List<GasStationDto> gasStationDtoList;
		for(GasStation gasStation : this.gasStationList) {
			gasStationRepository.delete(gasStation);
		}
		gasStationDtoList = gasStationService.getAllGasStations();
		assertEquals(0,gasStationDtoList.size());
	}
	
	@Test
	public void testGetAllGasStationsNotEmpty() {
		List<GasStationDto> gasStationDtoList;
		gasStationDtoList = gasStationService.getAllGasStations();
		assertEquals(this.gasStationList.size(),gasStationDtoList.size());		
	}
	
	@Test
	public void testDeleteGasStationValid() throws InvalidGasStationException {
		gasStationService.deleteGasStation(gasStationList.get(0).getGasStationId());
		assertNull(gasStationRepository.findOne(gasStationList.get(0).getGasStationId()));		
	}
	
	@Test(expected = InvalidGasStationException.class)
	public void testDeleteGasStationIdNegative() throws InvalidGasStationException {
		gasStationService.deleteGasStation(-300);
	}
	
	@Test
	public void testDeleteGasStationIdDoesNotExist() throws InvalidGasStationException {
		assertNull(gasStationService.deleteGasStation(9999));
	}
	
	@Test
	public void testDeleteGasStationDeleteFails() throws InvalidGasStationException {
		GasStationRepository gasStationRepositoryMock = mock(GasStationRepository.class);
		GasStationService gasStationService;
		
		when(gasStationRepositoryMock.exists(gasStationList.get(0).getGasStationId())).thenReturn(true);
		gasStationService = new GasStationServiceimpl(gasStationRepositoryMock);
		assertNull(gasStationService.deleteGasStation(gasStationList.get(0).getGasStationId()));
	}
	
	@Test
	public void testGetGasStationsByGasolineTypeDiesel() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByGasolineType(Constants.DIESEL);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertEquals(true,gasStationDto.getHasDiesel());
		}
	}
	
	@Test
	public void testGetGasStationsByGasolineTypeGas() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByGasolineType(Constants.GAS);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertEquals(true,gasStationDto.getHasGas());
		}
	}
	
	@Test
	public void testGetGasStationsByGasolineTypeSuper() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByGasolineType(Constants.SUPER);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertEquals(true,gasStationDto.getHasSuper());
		}
	}
	
	@Test
	public void testGetGasStationsByGasolineTypeSuperPlus() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByGasolineType(Constants.SUPER_PLUS);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertEquals(true,gasStationDto.getHasSuperPlus());
		}
	}
	
	@Test
	public void testGetGasStationsByGasolineTypeMethane() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByGasolineType(Constants.METHANE);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertEquals(true,gasStationDto.getHasMethane());
		}
	}
	
	@Test
	public void testGetGasStationsByGasolineTypeNull() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByGasolineType(Constants.NULL);
		assertNull(gasStationDtoList);
	}
	
	@Test(expected = InvalidGasTypeException.class)
	public void testGetGasStationsByGasolineTypeInvalid() throws InvalidGasTypeException {
		gasStationService.getGasStationsByGasolineType(this.INVALID_GAS_TYPE);
	}	
	
	@Test
	public void testGetGasStationByCarSharingValid() {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationByCarSharing(this.VALID_CARSHARING);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertEquals(this.VALID_CARSHARING,gasStationDto.getCarSharing());
		}
	}
	
	@Test
	public void testGetGasStationByCarSharingNull() {
		assertNull(null,gasStationService.getGasStationByCarSharing(Constants.NULL));
	}
	
	@Test
	public void testSetReportValid() throws InvalidGasStationException, PriceException, InvalidUserException {
		GasStation gasStation;
		
		gasStationService.setReport(this.validGasStationId, 1.23, 2.34, 3.45, 4.56, 5.67, this.validUserId);
		gasStation = gasStationRepository.getOne(this.validGasStationId);
		assertEquals(this.validGasStationId,(int)gasStation.getGasStationId());
		assertEquals(1.23,gasStation.getDieselPrice(),0.001);
		assertEquals(2.34,gasStation.getSuperPrice(),0.001);
		assertEquals(3.45,gasStation.getSuperPlusPrice(),0.001);
		assertEquals(4.56,gasStation.getGasPrice(),0.001);
		assertEquals(5.67,gasStation.getMethanePrice(),0.001);
		assertEquals(this.validUserId,(int)gasStation.getReportUser());
	}
	
	@Test(expected = PriceException.class)
	public void testSetReportInvalidPrice() throws InvalidGasStationException, PriceException, InvalidUserException {
		GasStation gasStation;
		
		gasStation = gasStationRepository.getOne(this.validGasStationId);
		gasStation.setHasDiesel(true);
		gasStation = gasStationRepository.save(gasStation);
		gasStationService.setReport(this.validGasStationId, -1.23, 2.34, 3.45, 4.56, 5.67, this.validUserId);
	}
	
	@Test(expected = InvalidUserException.class)
	public void testSetReportInvalidUser() throws InvalidGasStationException, PriceException, InvalidUserException {
		gasStationService.setReport(this.validGasStationId, 1.23, 2.34, 3.45, 4.56, 5.67, this.INVALID_USER_ID);
	}
	
	@Test(expected = InvalidGasStationException.class)
	public void testSetReportInvalidGasStation() throws InvalidGasStationException, PriceException, InvalidUserException {
		gasStationService.setReport(this.INVALID_GAS_STATION_ID, 1.23, 2.34, 3.45, 4.56, 5.67, this.validUserId);
	}
	
	@Test(expected = GPSDataException.class)
	public void testGetGasStationsByProximityInvalidCoordinates() throws GPSDataException{
		gasStationService.getGasStationsByProximity(100, 0);
	}
	
	@Test
	public void testGetGasStationsByProximityEmptyList() throws GPSDataException{
		List<GasStationDto> gasStationDtoList = gasStationService.getGasStationsByProximity(20, 0);
		assertEquals(0,gasStationDtoList.size());
	}
	
	@Test
	public void testGetGasStationsByProximityNonEmptyList() throws GPSDataException{
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsByProximity(45.101767, 7.646787);
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertTrue(distanceInKilometersBetween(45.101767, 7.646787, gasStationDto.getLat(),gasStationDto.getLon())<=5);
		}
		
		assertEquals(6,gasStationDtoList.size());
	}
	
	@Test
	public void testGetGasStationsWithoutCoordinatesNullGasolineType() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoListActual, gasStationDtoListExpected;
		
		gasStationDtoListExpected = gasStationService.getGasStationByCarSharing("0");
		gasStationDtoListActual = gasStationService.getGasStationsWithoutCoordinates("null", "0");
		
		assertEquals(gasStationDtoListExpected.toString(),gasStationDtoListActual.toString());
	}
	
	@Test
	public void testGetGasStationsWithoutCoordinatesNullGasolineTypeNullCarSharing() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoListActual;
	
		gasStationDtoListActual = gasStationService.getGasStationsWithoutCoordinates("null", "null");
		
		assertNull(gasStationDtoListActual);
	}
	
	@Test
	public void testGetGasStationsWithoutCoordinatesNullCarSharing() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoListActual, gasStationDtoListExpected;
		
		gasStationDtoListExpected = gasStationService.getGasStationsByGasolineType("Diesel");
		gasStationDtoListActual = gasStationService.getGasStationsWithoutCoordinates("Diesel", "null");
		
		assertEquals(gasStationDtoListExpected.toString(),gasStationDtoListActual.toString());
	}
	
	@Test
	public void testGetGasStationsWithoutCoordinatesValid() throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList;
		
		gasStationDtoList = gasStationService.getGasStationsWithoutCoordinates("Diesel", "0");
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertTrue(gasStationDto.getHasDiesel());
			assertEquals("0",gasStationDto.getCarSharing());
		}
		
	}
	
	@Test(expected = InvalidGasTypeException.class)
	public void testGetGasStationsWithoutCoordinatesInvalidGasType() throws InvalidGasTypeException {
		gasStationService.getGasStationsWithoutCoordinates("invalid", "0");
	}
	
	@Test(expected = InvalidGasTypeException.class)
	public void testGetGasStationsWithCoordinatesInvalidGasType() throws InvalidGasTypeException, GPSDataException {
		gasStationService.getGasStationsWithCoordinates(0, 0, "invalid", "0");
	}
	
	@Test(expected = GPSDataException.class)
	public void testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude() throws InvalidGasTypeException, GPSDataException {
		gasStationService.getGasStationsWithCoordinates(1000, 1000, "Diesel", "0");
	}
	
	@Test
	public void testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates() throws InvalidGasTypeException, GPSDataException {
		assertEquals(gasStationService.getGasStationsByProximity(0, 0),
				gasStationService.getGasStationsWithCoordinates(0, 0, "null", "null"));
	}
	
	@Test
	public void testGetGasStationsWithCoordinatesValid() throws InvalidGasTypeException, GPSDataException {
		List<GasStationDto> gasStationDtoList = gasStationService.getGasStationsWithCoordinates(45.101767, 7.646787, "Diesel", "sharing");
		
		for(GasStationDto gasStationDto : gasStationDtoList) {
			assertTrue(distanceInKilometersBetween(45.101767, 7.646787, gasStationDto.getLat(), gasStationDto.getLon())<=5);
			assertTrue(gasStationDto.getHasDiesel());
			assertEquals("sharing",gasStationDto.getCarSharing());
		}
		
	}
	
	
}
