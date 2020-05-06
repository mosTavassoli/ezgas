package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation,Integer>{
	
//	@Query("SELECT gs FROM GasStation gs WHERE gs.hasMethane = 1")
//	public List<GasStation> getGasStationHasMethane();
}
