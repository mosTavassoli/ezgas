package it.polito.ezgas;

import static org.junit.Assert.assertNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidGasStationException;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.utils.Constants;

@RunWith(SpringRunner.class)
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
	
	private final int NUMBER_OF_GAS_STATIONS=15;
	private final int NUMBER_OF_USERS=15;
	private final int NUMBER_OF_CAR_SHARING=2;
	private final double MAX_PRICE=5.00;
	private final double MAX_DEPENDABILITY=5.00;
	
	@Before
	public void init() {
		Random random = new Random();
		for(int i=0;i<NUMBER_OF_GAS_STATIONS;i++) {
			gasStationRepository.save(new GasStation(
					"gasstationname"+i, 
					"gasstationaddress"+i, 
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					random.nextBoolean(),
					Integer.toString(random.nextInt(NUMBER_OF_CAR_SHARING)), 
					random.nextDouble()*Constants.MAX_LAT*2-Constants.MAX_LAT, 
					random.nextDouble()*Constants.MAX_LON*2-Constants.MAX_LON, 
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					i,
					"reporttimestamp"+i,
					random.nextDouble()*MAX_DEPENDABILITY));
		}
		for(int i=0;i<NUMBER_OF_USERS;i++) {
			userRepository.save(new User("user"+i, "password"+i, "user"+i+"@example.com", i));
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
		assertNull(gasStationService.getGasStationById(2));
	}
	
}
