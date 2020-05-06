package it.polito.ezgas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation,Integer>{

}
