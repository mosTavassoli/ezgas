package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation,Integer>{
	
	public List<GasStation> findByHasMethaneOrderByMethanePriceAsc(boolean hasMethane);
	
	public List<GasStation> findByHasGasOrderByGasPriceAsc(boolean hasGas);
	
	public List<GasStation> findByHasSuperOrderBySuperPriceAsc(boolean hasSuper);
	
	public List<GasStation> findByHasSuperPlusOrderBySuperPlusPriceAsc(boolean hasSuperPlus);
	
	public List<GasStation> findByHasDieselOrderByDieselPriceAsc(boolean hasDiesel);
	
	public List<GasStation> findByLatBetweenAndLonBetween(double latStart, double latEnd, double lonStart, double lonEnd);
	
	public List<GasStation> findByCarSharing(String carSharing);
	
}
