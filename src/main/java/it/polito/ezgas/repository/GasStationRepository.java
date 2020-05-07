package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation,Integer>{
	
	public List<GasStation> findGasStationByHasMethane(boolean hasMethane);
	
	public List<GasStation> findGasStationByHasGas(boolean hasGas);
	
	public List<GasStation> findGasStationByHasSuper(boolean hasSuper);
	
	public List<GasStation> findGasStationByHasSuperPlus(boolean hasSuperPlus);
	
	public List<GasStation> findGasStationByHasDiesel(boolean hasDiesel);
	
	@Query("SELECT gs FROM GasStation gs WHERE gs.lat = ?1 AND gs.lon = ?2")
	public List<GasStation> findGasStationByLatAndLon(double lat, double lon);
	
	
}
