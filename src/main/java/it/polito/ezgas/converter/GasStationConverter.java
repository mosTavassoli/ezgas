package it.polito.ezgas.converter;

import java.util.List;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import static java.util.stream.Collectors.toList;

public class GasStationConverter {
	
	
	public static GasStationDto toDto(GasStation entity) {
		GasStationDto dto = new GasStationDto();
		
		dto.setGasStationId(entity.getGasStationId());
		dto.setGasStationName(entity.getGasStationName());
		dto.setGasStationAddress(entity.getGasStationAddress());
		dto.setHasDiesel(entity.getHasDiesel());
		dto.setHasSuper(entity.getHasSuper());
		dto.setHasSuperPlus(entity.getHasSuperPlus());
		dto.setHasGas(entity.getHasGas());
		dto.setHasMethane(entity.getHasMethane());
		dto.setCarSharing(entity.getCarSharing());
		dto.setLat(entity.getLat());
		dto.setLon(entity.getLon());
		dto.setDieselPrice(entity.getDieselPrice());
		dto.setSuperPrice(entity.getSuperPrice());
		dto.setSuperPlusPrice(entity.getSuperPlusPrice());
		dto.setGasPrice(entity.getGasPrice());
		dto.setMethanePrice(entity.getMethanePrice());
		dto.setReportUser(entity.getReportUser());
		dto.setUserDto(entity.getUser() != null ? UserConverter.toDto(entity.getUser()): null);
		dto.setReportTimestamp(entity.getReportTimestamp());
		dto.setReportDependability(entity.getReportDependability());
		
		//TODO
		/*
		 * PRICE REPORT
		 */
		
		return dto;
	}
	
	public static List<GasStationDto> toDto(List<GasStation> entityList) {
		return entityList.stream()
				.map(entity -> GasStationConverter.toDto(entity))
				.collect(toList());
	}
	
	public static GasStation toEntity(GasStationDto dto) {
		GasStation entity = new GasStation();
		
		entity.setGasStationId(dto.getGasStationId());
		entity.setGasStationName(dto.getGasStationName());
		entity.setGasStationAddress(dto.getGasStationAddress());
		entity.setHasDiesel(dto.getHasDiesel());
		entity.setHasSuper(dto.getHasSuper());
		entity.setHasSuperPlus(dto.getHasSuperPlus());
		entity.setHasGas(dto.getHasGas());
		entity.setHasMethane(dto.getHasMethane());
		entity.setCarSharing(dto.getCarSharing());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
		entity.setDieselPrice(dto.getDieselPrice());
		entity.setSuperPrice(dto.getSuperPrice());
		entity.setSuperPlusPrice(dto.getSuperPlusPrice());
		entity.setGasPrice(dto.getGasPrice());
		entity.setMethanePrice(dto.getMethanePrice());
		entity.setReportUser(dto.getReportUser());
		entity.setUser(dto.getUserDto() != null ?  UserConverter.toEntity(dto.getUserDto()): null);
		entity.setReportTimestamp(dto.getReportTimestamp());
		entity.setReportDependability(dto.getReportDependability());
		
		return entity;
	}

}


