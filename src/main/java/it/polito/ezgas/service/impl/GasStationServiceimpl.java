package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.GasStationService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {
	
	@Autowired
	GasStationRepository gasStationRepository;
	Logger logger = Logger.getLogger(GasStationServiceimpl.class.getName());

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		logger.log(Level.INFO, "GET - gas station with ID = " + gasStationId);
		GasStation gasStation = gasStationRepository.findOne(gasStationId);
		return GasStationConverter.toDto(gasStation);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		GasStation gasStation = gasStationRepository.save(GasStationConverter.toEntity(gasStationDto));
		logger.log(Level.INFO, "POST - saved gas station with ID = " + gasStation.getGasStationId());
		return GasStationConverter.toDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		logger.log(Level.INFO, "GET - all gas stations");
		List<GasStation> gasStations = gasStationRepository.findAll();
		return GasStationConverter.toDto(gasStations);
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
//		List<GasStation> gasStations = new ArrayList<GasStation>();
//		if(gasolinetype != null) {
//			if(gasolinetype.equals("methane"))
//				gasStations = gasStationRepository.getGasStationHasMethane();
//		}
//		return GasStationConverter.toDto(gasStations);
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
