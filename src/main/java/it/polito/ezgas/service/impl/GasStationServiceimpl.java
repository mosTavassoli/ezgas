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
import it.polito.ezgas.utils.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

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
		logger.log(Level.INFO, "getGasStationById - gasStationId = " + gasStationId);
		
		if(gasStationId < 0)
			throw new InvalidGasStationException("InvalidGasStationException: gasStationId = " + gasStationId);
		
		if(!gasStationRepository.exists(gasStationId))
			return null;
		
		return GasStationConverter.toDto(gasStationRepository.findOne(gasStationId));
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		if(!gasStationDto.checkPrices())
			throw new PriceException("PriceException: " + gasStationDto.toString());
		
		if(!gasStationDto.checkCoordinates())
			throw new GPSDataException("GPSDataException: " + gasStationDto.toString());
		
		GasStation gasStation = gasStationRepository.save(GasStationConverter.toEntity(gasStationDto));
		logger.log(Level.INFO, "saveGasStation - gasStationId = " + gasStation.getGasStationId());
		
		return GasStationConverter.toDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		logger.log(Level.INFO, "getAllGasStations");
		
		if(gasStationRepository.count() > 0)
			return GasStationConverter.toDto(gasStationRepository.findAll());
		return new ArrayList<GasStationDto>();
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		logger.log(Level.INFO, "deleteGasStation - gasStationId = " + gasStationId);
		
		if(gasStationId < 0)
			throw new InvalidGasStationException("InvalidGasStationException: gasStationId = " + gasStationId);
		
		if(gasStationRepository.exists(gasStationId))
			gasStationRepository.delete(gasStationId);
		else return null;
		
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		logger.log(Level.INFO, "getGasStationsByGasolineType - gasolinetype = " + gasolinetype);
		
		switch(gasolinetype.toLowerCase()){
			case Constants.DIESEL:
				return GasStationConverter.toDto(gasStationRepository.findGasStationByHasDiesel(true));
			case Constants.SUPER:
				return GasStationConverter.toDto(gasStationRepository.findGasStationByHasSuper(true));
			case Constants.SUPER_PLUS:
				return GasStationConverter.toDto(gasStationRepository.findGasStationByHasSuperPlus(true));
			case Constants.GAS:
				return GasStationConverter.toDto(gasStationRepository.findGasStationByHasGas(true));
			case Constants.METHANE:
				return GasStationConverter.toDto(gasStationRepository.findGasStationByHasMethane(true));
			default:
				throw new InvalidGasTypeException("InvalidGasTypeException: gasolinetype = " + gasolinetype);
		}
		
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		List<GasStation> gasStations = gasStationRepository.findGasStationByLatAndLon(lat, lon);
		return GasStationConverter.toDto(gasStations);
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		
		List<GasStationDto> gasStationsByProximity = getGasStationsByProximity(lat, lon);
		List<GasStationDto> gasStationsByGasolineType = getGasStationsByGasolineType(gasolinetype);
		List<GasStationDto> gasStationsByCarSharing = getGasStationByCarSharing(carsharing);
		List<GasStationDto> gasStations = new ArrayList<GasStationDto>();
		
		
		if(gasStationsByGasolineType != null && 
				!gasStationsByGasolineType.isEmpty() &&
				gasStationsByCarSharing != null &&
				!gasStationsByCarSharing.isEmpty()) {
			
			gasStations = gasStationsByGasolineType.stream()
					.filter(gs -> gasStationsByCarSharing.stream()
									.map(e -> e.getGasStationId())
									.collect(toList())
									.contains(gs.getGasStationId()))
					.filter(gs -> gasStationsByProximity.stream()
									.map(e -> e.getGasStationId())
									.collect(toList())
									.contains(gs.getGasStationId()))
					.collect(toList());		
		}
	return gasStations;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		/**
		 * ISSUE:
		 * 
		 * getGasStationsWithoutCoordinates not mapped in GasStationConverter.
		 * It could not be called.
		 * 
		 */
		List<GasStationDto> gasStationsByGasolineType = getGasStationsByGasolineType(gasolinetype);
		List<GasStationDto> gasStationsByCarSharing = getGasStationByCarSharing(carsharing);
		List<GasStationDto> gasStations = new ArrayList<GasStationDto>();
		
		if(gasStationsByGasolineType != null && 
				!gasStationsByGasolineType.isEmpty() &&
				gasStationsByCarSharing != null &&
				!gasStationsByCarSharing.isEmpty()) {
			
			gasStations = gasStationsByGasolineType.stream()
					.filter(gs -> gasStationsByCarSharing.stream()
									.map(e -> e.getGasStationId())
									.collect(toList())
									.contains(gs.getGasStationId()))
					.collect(toList());
					
		}
		return gasStations;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		List<GasStation> gasStations = gasStationRepository.findGasStationByCarSharing(carSharing);
		return GasStationConverter.toDto(gasStations);
	}
	
	
	
	
	

}
