package it.polito.ezgas;

import static it.polito.ezgas.GasStationRepositoryTest.distanceInKilometersBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.utils.Constants;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GasStationRepositoryTest {
	
	@Autowired 
	private GasStationRepository gasStationRepository;
	private final int NUMBER_OF_GAS_STATIONS=15;
	private final int NUMBER_OF_CAR_SHARING=2;
	private final int NUMBER_OF_GAS_PROXIMITY = 5;
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
					random.nextBoolean(),
					Integer.toString(random.nextInt(NUMBER_OF_CAR_SHARING)), 
					random.nextDouble()*Constants.MAX_LAT-Constants.MAX_LAT, // ignoring the other half of the possible values to test empty list when looking for gas stations by proximity
					random.nextDouble()*Constants.MAX_LON*2-Constants.MAX_LON, 
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					random.nextDouble()*MAX_PRICE,
					i,
					"reporttimestamp"+i,
					random.nextDouble()*MAX_DEPENDABILITY));
		}
	}
	
	@Test
	public void testFindByProximity() {
		//setup for proximity tests
		gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, true, "sharing", 45.101767, 7.646787, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //0m
		gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, true, "sharing1", 45.103047, 7.644117, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //250m
		gasStationRepository.save(new GasStation("name", "address", false, true, true, true, true, true, "sharing", 45.104367, 7.641588, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //500m
		gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, true, "sharing1", 45.106264, 7.639662, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //750m
		gasStationRepository.save(new GasStation("name", "address", false, true, true, true, true, true, "sharing1", 45.107773, 7.637318, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //999m
		gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, true, "sharing", 45.107781, 7.637224, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //1010m
		gasStationRepository.save(new GasStation("name", "address", true, true, true, true, true, true, "sharing", 45.108089, 7.636838, 1.11, 2.22, 3.33, 4.44, 5.55, 6.66, 123321, "stamp", 7.77)); //1050m
		
		List<GasStation> gasStationList;
		double previousDistance=0, currentDistance;
		final double LAT=45.101767,LON=7.646787, RADIUS=1;
		
		gasStationList = gasStationRepository.findByProximity(LAT,LON,RADIUS);
		for(GasStation gasStation : gasStationList) {
			currentDistance = distanceInKilometersBetween(LAT, LON, gasStation.getLat(),gasStation.getLon());
			assertTrue(currentDistance>=previousDistance);
			assertTrue(currentDistance<=1);
			previousDistance=currentDistance;
		}
		
		assertEquals(5,gasStationList.size());
	}
	
	@Test
	public void testFindByCarSharingOrderByGasStationName() {
		int i,j,sum=0;
		List<GasStation> gasStationList;
		for(i=0;i<NUMBER_OF_CAR_SHARING;i++) {
			gasStationList=gasStationRepository.findByCarSharingOrderByGasStationName(Integer.toString(i));
			for(j=0;j<gasStationList.size()-1;j++) {
				assertEquals(Integer.toString(i),gasStationList.get(j).getCarSharing());
				assertTrue("Expected "+gasStationList.get(j).getGasStationName()+" to be lexicographically before "+
							gasStationList.get(j+1).getGasStationName()+"!",
						gasStationList.get(j).getGasStationName().compareTo(
						gasStationList.get(j+1).getGasStationName())<=0);
			}
			assertEquals(Integer.toString(i),gasStationList.get(j).getCarSharing());
			sum+=gasStationList.size();
		}
		assertEquals(NUMBER_OF_GAS_STATIONS,sum);
	}
	
	@Test
	public void testSaveNewGasStation() {
		GasStation gasStation;
		gasStation = new GasStation("gas station name", "gas station address", 
				true, true, false, true, false, true, "car sharing", 20, 80, 1.43, 1.65, 1.22, 
				1.20, 1.01, 1.51, 100234, "report timestamp", 5.34);
		gasStation = gasStationRepository.save(gasStation);
		assertNotNull(gasStation);
	}
	
	@Test
	public void testUpdateOldGasStation() {
		GasStation gasStation;
		int oldId, newId;
		final String oldName="old name";
		final String newName="new name";
		String name;
		
		gasStation = new GasStation("gas station name", "gas station address", 
				true, true, false, true, false, true, "car sharing", 20, 80, 1.43, 1.65, 1.22, 
				1.20, 1.01, 1.1, 100234, "report timestamp", 5.34);
		gasStation.setGasStationName(oldName);
		gasStation = gasStationRepository.save(gasStation);
		oldId=gasStation.getGasStationId();
		name=gasStation.getGasStationName();
		
		assertEquals(oldName, name);
		
		gasStation.setGasStationName(newName);
		gasStation = gasStationRepository.save(gasStation);
		newId=gasStation.getGasStationId();
		name=gasStation.getGasStationName();
		
		assertEquals(oldId,newId);
		assertEquals(newName,name);
	}

	@Test
	public void testFindByHasMethaneOrderByMethanePriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasMethane());
			assertTrue("Expected "+gasStationList.get(i).getMethanePrice()+" to be smaller than "+
						gasStationList.get(i+1).getMethanePrice()+"!",
					gasStationList.get(i).getMethanePrice()<gasStationList.get(i+1).getMethanePrice());
		}
		assertEquals(true,gasStationList.get(i).getHasMethane());
	}
	
	@Test
	public void testFindByHasMethaneOrderByMethanePriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasMethane());
			assertTrue("Expected "+gasStationList.get(i).getMethanePrice()+" to be smaller than "+
						gasStationList.get(i+1).getMethanePrice()+"!",
					gasStationList.get(i).getMethanePrice()<gasStationList.get(i+1).getMethanePrice());
		}
		assertEquals(false,gasStationList.get(i).getHasMethane());
	}
	
	@Test
	public void testFindByHasMethaneOrderByMethanePriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(false);
		assertEquals(gasStationRepository.findAll().size(),gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasDieselOrderByDieselPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasDiesel());
			assertTrue("Expected "+gasStationList.get(i).getDieselPrice()+" to be smaller than "+
						gasStationList.get(i+1).getDieselPrice()+"!",
					gasStationList.get(i).getDieselPrice()<gasStationList.get(i+1).getDieselPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasDiesel());
	}
	
	@Test
	public void testFindByHasDieselOrderByDieselPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasDiesel());
			assertTrue("Expected "+gasStationList.get(i).getDieselPrice()+" to be smaller than "+
						gasStationList.get(i+1).getDieselPrice()+"!",
					gasStationList.get(i).getDieselPrice()<gasStationList.get(i+1).getDieselPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasDiesel());
	}
	
	@Test
	public void testFindByHasDieselOrderByDieselPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasDieselOrderByDieselPriceAsc(false);
		assertEquals(gasStationRepository.findAll().size(),gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasSuperOrderBySuperPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasSuper());
			assertTrue("Expected "+gasStationList.get(i).getSuperPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPrice()+"!",
					gasStationList.get(i).getSuperPrice()<gasStationList.get(i+1).getSuperPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasSuper());
	}
	
	@Test
	public void testFindByHasSuperOrderBySuperPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasSuper());
			assertTrue("Expected "+gasStationList.get(i).getSuperPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPrice()+"!",
					gasStationList.get(i).getSuperPrice()<gasStationList.get(i+1).getSuperPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasSuper());
	}
	
	@Test
	public void testFindByHasSuperOrderBySuperPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasSuperOrderBySuperPriceAsc(false);
		assertEquals(gasStationRepository.findAll().size(),gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasSuperPlusOrderBySuperPlusPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasSuperPlus());
			assertTrue("Expected "+gasStationList.get(i).getSuperPlusPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPlusPrice()+"!",
					gasStationList.get(i).getSuperPlusPrice()<gasStationList.get(i+1).getSuperPlusPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasSuperPlus());
	}
	
	@Test
	public void testFindByHasSuperPlusOrderBySuperPlusPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasSuperPlus());
			assertTrue("Expected "+gasStationList.get(i).getSuperPlusPrice()+" to be smaller than "+
						gasStationList.get(i+1).getSuperPlusPrice()+"!",
					gasStationList.get(i).getSuperPlusPrice()<gasStationList.get(i+1).getSuperPlusPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasSuperPlus());
	}
	
	@Test
	public void testFindByHasSuperPlusOrderBySuperPlusPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(false);
		assertEquals(gasStationRepository.findAll().size(),gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	@Test
	public void testFindByHasGasOrderByGasPriceAscTrue() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasGasOrderByGasPriceAsc(true);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(true,gasStationList.get(i).getHasGas());
			assertTrue("Expected "+gasStationList.get(i).getGasPrice()+" to be smaller than "+
						gasStationList.get(i+1).getGasPrice()+"!",
					gasStationList.get(i).getGasPrice()<gasStationList.get(i+1).getGasPrice());
		}
		assertEquals(true,gasStationList.get(i).getHasGas());
	}
	
	@Test
	public void testFindByHasGasOrderByGasPriceAscFalse() {
		int i;
		List<GasStation> gasStationList = gasStationRepository.findByHasGasOrderByGasPriceAsc(false);
		for(i=0;i<gasStationList.size()-1;i++) {
			assertEquals(false,gasStationList.get(i).getHasGas());
			assertTrue("Expected "+gasStationList.get(i).getGasPrice()+" to be smaller than "+
						gasStationList.get(i+1).getGasPrice()+"!",
					gasStationList.get(i).getGasPrice()<gasStationList.get(i+1).getGasPrice());
		}
		assertEquals(false,gasStationList.get(i).getHasGas());
	}
	
	@Test
	public void testFindByHasGasOrderByGasPriceAscTotal() {
		List<GasStation> gasStationListTrue = gasStationRepository.findByHasGasOrderByGasPriceAsc(true);
		List<GasStation> gasStationListFalse = gasStationRepository.findByHasGasOrderByGasPriceAsc(false);
		assertEquals(gasStationRepository.findAll().size(),gasStationListTrue.size()+gasStationListFalse.size());
	}
	
	public static double distanceInKilometersBetween(double lat1, double lon1, double lat2, double lon2) {
		double deltaLatitude, deltaLongitude, partial;
		final double EARTH_RADIUS_KILOMETERS = 6371;
	    
		deltaLatitude = Math.toRadians(lat2-lat1);
	    deltaLongitude = Math.toRadians(lon2-lon1);
	    
	    partial = Math.sin(deltaLatitude/2) * Math.sin(deltaLatitude/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(deltaLongitude/2) * Math.sin(deltaLongitude/2);
	    
	    return EARTH_RADIUS_KILOMETERS * 2 * Math.atan2(Math.sqrt(partial), Math.sqrt(1-partial));
	}
	
}
