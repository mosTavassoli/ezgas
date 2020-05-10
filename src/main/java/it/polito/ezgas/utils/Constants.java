package it.polito.ezgas.utils;

public interface Constants {
	static final String GET_USER_BY_ID = "/getUser/{userId}";
	static final String GET_ALL_USERS = "/getAllUsers";
	static final String SAVE_USER = "/saveUser";
	static final String DELETE_USER = "/deleteUser/{userId}";
	static final String INCREASE_REPUTATION = "/increaseUserReputation/{userId}";
	static final String DECREASE_REPUTATION = "/decreaseUserReputation/{userId}";
	static final String LOGIN = "/login";
	static final String GET_GASSTATION_BY_ID = "/getGasStation/{gasStationId}";
	static final String GET_ALL_GASSTATIONS = "/getAllGasStations";
	static final String SAVE_GASSTATION = "/saveGasStation";
	static final String DELETE_GASSTATION = "/deleteGasStation/{gasStationId}";
	static final String GET_GASSTATIONS_BY_NEIGHBORHOOD = "/searchGasStationByNeighborhood/{neighborhood}";
	static final String GET_GASSTATIONS_BY_GASOLINETYPE = "/searchGasStationByGasolineType/{gasolinetype}";
	static final String GET_GASSTATIONS_BY_PROXIMITY = "/searchGasStationByProximity/{myLat}/{myLon}";
	static final String SET_GASSTATION_REPORT = "/setGasStationReport/{gasStationId}/{dieselPrice}/{superPrice}/{superPlusPrice}/{gasPrice}/{methanePrice}/{userId}";
	static final String GET_GASSTATIONS_WITH_COORDINATES = "/getGasStationsWithCoordinates/{myLat}/{myLon}/{gasolineType}/{carSharing}";
	static final String GET_GASSTATIONS_WITHOUT_COORDINATES = "/getGasStationsWithoutCoordinates/{gasolineType}/{carSharing}";
	
    static final String METHANE = "methane";
    static final String SUPER = "gasoline";
    static final String GAS = "lpg";
    static final String SUPER_PLUS = "premium gasoline";
    static final String DIESEL = "diesel";
    
    static final double MIN_LAT = -90;
    static final double MAX_LAT = 90;
    static final double MIN_LON = -180;
    static final double MAX_LON = 180;

}
