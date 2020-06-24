package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation,Integer>{
	
	public List<GasStation> findByHasMethaneOrderByMethanePriceAsc(boolean hasMethane);
	
	public List<GasStation> findByHasGasOrderByGasPriceAsc(boolean hasGas);
	
	public List<GasStation> findByHasSuperOrderBySuperPriceAsc(boolean hasSuper);
	
	public List<GasStation> findByHasSuperPlusOrderBySuperPlusPriceAsc(boolean hasSuperPlus);
	
	public List<GasStation> findByHasDieselOrderByDieselPriceAsc(boolean hasDiesel);
	
	@Query("SELECT gs " +
			"FROM GasStation gs " +
			"GROUP BY gs " +
			"HAVING ( 6371 * " + 
			"    ACOS( " + 
			"        COS( RADIANS( LAT ) ) * " + 
			"        COS( RADIANS( ?1 ) ) * " + 
			"        COS( RADIANS( ?2 ) - " + 
			"        RADIANS( LON ) ) + " + 
			"        SIN( RADIANS( LAT ) ) * " + 
			"        SIN( RADIANS( ?1 ) ) " + 
			"    ) " + 
			") <= ?3 " +
			"ORDER BY ACOS( " + 
			"        COS( RADIANS( LAT ) ) * " + 
			"        COS( RADIANS( ?1 ) ) * " + 
			"        COS( RADIANS( ?2 ) - " + 
			"        RADIANS( LON ) ) + " + 
			"        SIN( RADIANS( LAT ) ) * " + 
			"        SIN( RADIANS( ?1 ) ) " + 
			"    )")
	public List<GasStation> findByProximity(double lat, double lon, double radius);
	
	public List<GasStation> findByCarSharingOrderByGasStationName(String carSharing);
	
}
