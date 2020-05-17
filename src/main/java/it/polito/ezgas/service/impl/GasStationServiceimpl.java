package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.utils.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {
	
	@Autowired
	GasStationRepository gasStationRepository;
	@Autowired
	UserService userService;
	
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
		
		if(!GasStationDto.checkCoordinates(gasStationDto.getLat(), gasStationDto.getLon()))
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
		
		if(gasStationRepository.exists(gasStationId)) {
			gasStationRepository.delete(gasStationId);
			if(!gasStationRepository.exists(gasStationId))
				return true;
		}
		
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		logger.log(Level.INFO, "getGasStationsByGasolineType - gasolinetype = " + gasolinetype);
		
		switch(gasolinetype){
			case Constants.DIESEL:
				return GasStationConverter.toDto(gasStationRepository.findByHasDieselOrderByDieselPriceAsc(true));
			case Constants.SUPER:
				return GasStationConverter.toDto(gasStationRepository.findByHasSuperOrderBySuperPriceAsc(true));
			case Constants.SUPER_PLUS:
				return GasStationConverter.toDto(gasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc(true));
			case Constants.GAS:
				return GasStationConverter.toDto(gasStationRepository.findByHasGasOrderByGasPriceAsc(true));
			case Constants.METHANE:
				return GasStationConverter.toDto(gasStationRepository.findByHasMethaneOrderByMethanePriceAsc(true));
			case Constants.NULL:
				return null;
			default:
				throw new InvalidGasTypeException("InvalidGasTypeException: gasolinetype = " + gasolinetype);
		}
		
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		logger.log(Level.INFO, "getGasStationsByProximity - lat = " + lat + ", lon = " + lon);
		/*
		 * To get work properly:
		 * 
		 * Add a final / after lon value in the request URL for some problems 
		 * in recognize the end of a double number.
		 * 
		 * What happens without final /: 60.13 -> 60.0
		 */
		
		if(!GasStationDto.checkCoordinates(lat, lon))
			throw new GPSDataException("GPSDataException: lat = " + lat + ", lon = " + lon );
		
		return GasStationConverter
				.toDto(gasStationRepository
						.findByLatBetweenAndLonBetween(lat - Constants.KM1_LAT, 
														lat + Constants.KM1_LAT, 
														lon - Constants.KM1_LON, 
														lon + Constants.KM1_LON));
}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		
		logger.log(Level.INFO, "getGasStationsWithCoordinates - "
				+ "lat = " + lat 
				+ ", lon = " + lon 
				+ ", gasolinetype = " + gasolinetype 
				+ ", carsharing = " + carsharing);
		
		List<GasStationDto> gasStationsByProximity = getGasStationsByProximity(lat, lon);
		List<GasStationDto> gasStationsWithoutCoordinates = getGasStationsWithoutCoordinates(gasolinetype, carsharing);
		
		if(gasStationsWithoutCoordinates != null)
			return gasStationsByProximity.stream()
					.filter(gs -> gasStationsWithoutCoordinates.stream()
							.map(e -> e.getGasStationId())
							.collect(toList())
							.contains(gs.getGasStationId()))
					.collect(toList());
		else return gasStationsByProximity;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		/**
		 * ISSUE:
		 * getGasStationsWithoutCoordinates not mapped in GasStationConverter.
		 * It could not be called out of this class.
		 */
		logger.log(Level.INFO, "getGasStationsWithoutCoordinates - gasolinetype = " + gasolinetype + ", carsharing = " + carsharing);
		
		List<GasStationDto> gasStationsByGasolineType = getGasStationsByGasolineType(gasolinetype);
		List<GasStationDto> gasStationsByCarSharing = getGasStationByCarSharing(carsharing);
				
		if(gasStationsByGasolineType != null && gasStationsByCarSharing != null)	
			return gasStationsByGasolineType.stream()
					.filter(gs -> gasStationsByCarSharing.stream()
									.map(e -> e.getGasStationId())
									.collect(toList())
									.contains(gs.getGasStationId()))
					.collect(toList());
		else if(gasStationsByGasolineType != null)
			return gasStationsByGasolineType;
		else if(gasStationsByCarSharing != null)
			return gasStationsByCarSharing;
		else return null;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		
		logger.log(Level.INFO, "setReport - gasStationId = " + gasStationId 
				+ ", dieselPrice = " + dieselPrice
				+ ", superPrice = " + superPrice
				+ ", superPlusPrice = " + superPlusPrice
				+ ", gasPrice = " + gasPrice
				+ ", methanePrice = " + methanePrice
				+ ", userId = " + userId);
		
		GasStationDto gasStationDto = getGasStationById(gasStationId);
		
		gasStationDto.setDieselPrice(dieselPrice);
		gasStationDto.setSuperPrice(superPrice);
		gasStationDto.setSuperPlusPrice(superPlusPrice);
		gasStationDto.setGasPrice(gasPrice);
		gasStationDto.setMethanePrice(methanePrice);
		gasStationDto.setReportUser(userId);
		gasStationDto.setUserDto(userService.getUserById(userId));
		gasStationDto.setReportTimestamp(new Timestamp(new Date().getTime()).toString());
		gasStationDto.setReportDependability(gasStationDto.computeReportDependability());
		
		if(!gasStationDto.checkPrices())
			throw new PriceException("PriceException: " + gasStationDto.toString());              
		
		gasStationRepository.save(GasStationConverter.toEntity(gasStationDto));
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		/**
		 * ISSUE:
		 * getGasStationByCarSharing not mapped in GasStationConverter.
		 * It could not be called.
		 */
		logger.log(Level.INFO, "getGasStationByCarSharing - carSharing = " + carSharing);
		
		switch(carSharing) {
			case Constants.NULL:
				return null;
			default:
				return GasStationConverter
						.toDto(gasStationRepository.findByCarSharingOrderByGasStationName(carSharing));
		}
	}
	
	
	
	
	

}
