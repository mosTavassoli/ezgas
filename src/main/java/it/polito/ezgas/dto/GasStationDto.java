package it.polito.ezgas.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import it.polito.ezgas.utils.Constants;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import it.polito.ezgas.entity.User;

public class GasStationDto {

	
	Integer gasStationId;
	String gasStationName;
	String gasStationAddress;
	boolean hasDiesel;
    boolean hasSuper;
    boolean hasSuperPlus;
    boolean hasGas;
    boolean hasMethane;
    //****************************
	boolean hasPremiumDiesel;
    private String carSharing;
    double lat;
    double lon;
    Double dieselPrice;
    Double superPrice;
    Double superPlusPrice;
    Double gasPrice;
    Double methanePrice;
    //****************************************
	Double premiumDieselPrice;

    Integer reportUser;
    UserDto userDto;
    String reportTimestamp;
    double reportDependability;

 
	
	/*
	public GasStationDto(Integer gasStationId, String gasStationName, String gasStationAddress, 
			boolean hasDiesel, boolean hasSuper, boolean hasSuperPlus, boolean hasGas, boolean hasMethane, String carSharing, 
			double lat, double lon, 
			double dieselPrice, double superPrice, double superPlusPrice, double gasPrice, double methanePrice, 
			Integer reportUser, String reportTimestamp, double reportDependability) {
		this.gasStationId = gasStationId;
		this.gasStationName = gasStationName;
		this.gasStationAddress = gasStationAddress;
		this.carSharing = carSharing;
		this.hasDiesel = hasDiesel;
		this.hasGas = hasGas;
		this.hasMethane = hasMethane;
		this.hasSuper = hasSuper;
		this.hasSuperPlus = hasSuperPlus;
		this.lat = lat;
		this.lon = lon;
		this.dieselPrice = dieselPrice;
		this.superPrice = superPrice;
		this.superPlusPrice = superPlusPrice;
		this.gasPrice = gasPrice;
		this.methanePrice = methanePrice;
		this.reportUser = reportUser;
		this.userDto = null;
		this.reportTimestamp = reportTimestamp;
		this.reportDependability = reportDependability;
		
		
	}*/
	
	//***********************************************************************
	public GasStationDto(Integer gasStationId, String gasStationName, String gasStationAddress,
						 boolean hasDiesel, boolean hasSuper, boolean hasSuperPlus, boolean hasGas, boolean hasMethane,
						 boolean hasPremiumDiesel, String carSharing, double lat, double lon,
						 Double dieselPrice, Double superPrice, Double superPlusPrice, Double gasPrice, Double methanePrice,
						 Double premiumDieselPrice, Integer reportUser, String reportTimestamp, double reportDependability) {
		this.gasStationId = gasStationId;
		this.gasStationName = gasStationName;
		this.gasStationAddress = gasStationAddress;
		this.carSharing = carSharing;
		this.hasDiesel = hasDiesel;
		this.hasGas = hasGas;
		this.hasMethane = hasMethane;
		this.hasSuper = hasSuper;
		this.hasSuperPlus = hasSuperPlus;
		this.hasPremiumDiesel = hasPremiumDiesel;
		this.lat = lat;
		this.lon = lon;
		this.dieselPrice = dieselPrice;
		this.superPrice = superPrice;
		this.superPlusPrice = superPlusPrice;
		this.gasPrice = gasPrice;
		this.methanePrice = methanePrice;
		this.premiumDieselPrice = premiumDieselPrice;
		this.reportUser = reportUser;
		this.userDto = null;
		this.reportTimestamp = reportTimestamp;
		this.reportDependability = reportDependability;
	}

	public double getReportDependability() {
		return reportDependability;
	}

	public void setReportDependability(double reportDependability) {
		this.reportDependability = reportDependability;
	}

	public GasStationDto() { }

	public Integer getGasStationId() {
		return gasStationId;
	}

	public void setGasStationId(Integer gasStationId) {
		this.gasStationId = gasStationId;
	}

	public String getGasStationName() {
		return gasStationName;
	}

	public void setGasStationName(String gasStationName) {
		this.gasStationName = gasStationName;
	}
	
	public String getGasStationAddress() {
		return gasStationAddress;
	}
	
	public void setGasStationAddress(String gasStationAddress) {
		this.gasStationAddress = gasStationAddress;
	}

	public boolean getHasDiesel() {
		return hasDiesel;
	}

	public void setHasDiesel(boolean hasDiesel) {
		this.hasDiesel = hasDiesel;
	}

	public Boolean getHasSuper() {
		return hasSuper;
	}

	public void setHasSuper(Boolean hasSuper) {
		this.hasSuper = hasSuper;
	}

	public Boolean getHasSuperPlus() {
		return hasSuperPlus;
	}

	public void setHasSuperPlus(Boolean hasSuperPlus) {
		this.hasSuperPlus = hasSuperPlus;
	}

	public Boolean getHasGas() {
		return hasGas;
	}

	public void setHasGas(Boolean hasGas) {
		this.hasGas = hasGas;
	}
//*************************************************************************
	public boolean getHasPremiumDiesel() { return hasPremiumDiesel; }

	public void setHasPremiumDiesel(boolean hasPremiumDiesel) {
		this.hasPremiumDiesel = hasPremiumDiesel;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getDieselPrice() {
		return dieselPrice;
	}

	public void setDieselPrice(Double dieselPrice) {
		this.dieselPrice = dieselPrice;
	}

	public Double getSuperPrice() {
		return superPrice;
	}

	public void setSuperPrice(Double superPrice) {
		this.superPrice = superPrice;
	}

	public Double getSuperPlusPrice() {
		return superPlusPrice;
	}

	public void setSuperPlusPrice(Double superPlusPrice) {
		this.superPlusPrice = superPlusPrice;
	}

	public Double getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(Double gasPrice) {
		this.gasPrice = gasPrice;
	}
//****************************************************************************
	public Double getPremiumDieselPrice() {
		return premiumDieselPrice;
	}

	public void setPremiumDieselPrice(Double premiumDieselPrice) {
		this.premiumDieselPrice = premiumDieselPrice;
	}

	public Integer getReportUser() {
		return reportUser;
	}

	public void setReportUser(Integer reportUser) {
		this.reportUser = reportUser;
	}

	public String getReportTimestamp() {
		return reportTimestamp;
	}

	public void setReportTimestamp(String reportTimestamp) {
		this.reportTimestamp = reportTimestamp;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public boolean getHasMethane() {
		return hasMethane;
	}

	public void setHasMethane(boolean hasMethane) {
		this.hasMethane = hasMethane;
	}

	public Double getMethanePrice() {
		return methanePrice;
	}

	public void setMethanePrice(Double methanePrice) {
		this.methanePrice = methanePrice;
	}

	public void setHasSuper(boolean hasSuper) {
		this.hasSuper = hasSuper;
	}

	public void setHasSuperPlus(boolean hasSuperPlus) {
		this.hasSuperPlus = hasSuperPlus;
	}

	public void setHasGas(boolean hasGas) {
		this.hasGas = hasGas;
	}

	public String getCarSharing() {
		return carSharing;
	}

	public void setCarSharing(String carSharing) {
		if(carSharing==null) {
			this.carSharing = carSharing;
		}
		else if(carSharing.equals("null")) {
			this.carSharing=null;
		}
		else {
			this.carSharing = carSharing;
		}
	}
	
	
	public boolean checkPrices() {
		if(this.hasDiesel && this.dieselPrice != null && this.dieselPrice < 0) {
				return false;
		}
		if(this.hasGas && this.gasPrice!= null && this.gasPrice < 0) {
				return false;
		}
		if(this.hasMethane && this.methanePrice!= null&& this.methanePrice < 0) {
				return false;
		}
		if(this.hasSuper && this.superPrice!= null && this.superPrice < 0) {
				return false;
		}
		if(this.hasSuperPlus && this.superPlusPrice!= null && this.superPlusPrice < 0) {
				return false;
		}
		if(this.hasPremiumDiesel && this.premiumDieselPrice!= null && this.superPlusPrice < 0) {
				return false;
		}
		return true;
	}
	
	
	public static boolean checkCoordinates(double lat, double lon) {
		if(lat < Constants.MIN_LAT || lat >= Constants.MAX_LAT)
			return false;
		if(lon < Constants.MIN_LON || lon >= Constants.MAX_LON)
			return false;
		return true;
	}
	
	
	public double computeReportDependability() {
		if(this.reportTimestamp == null)
			return 0;
		
		double obsolescence;
		Integer userReputation = (int) this.reportDependability;
		Date today = new Date();
		SimpleDateFormat toFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date reportDate;
		try {
			reportDate = toFormat.parse(this.reportTimestamp);
		}catch(ParseException e) {
			reportDate = new Date();
		}
		long diffInMillies = today.getTime() - reportDate.getTime();
		long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diffInDays > 7)
			obsolescence = 0;
		else obsolescence = 1 - (double) diffInDays / 7;
		
		return 50 * (userReputation + 5) / 10 + 50 * obsolescence;
	}

	
	@Override
	public String toString() {
		return "\n{\n"
				+ "gasStationId = " + this.gasStationId + ",\n"
				+ "gasStationName = " + this.gasStationName + ",\n"
				+ "gasStationAddress = " + this.gasStationAddress + ",\n"
				+ "hasDiesel = " + this.hasDiesel + ",\n"
				+ "hasSuper = " + this.hasSuper + ",\n"
				+ "hasSuperPlus = " + this.hasSuperPlus + ",\n"
				+ "hasGas = " + this.hasGas + ",\n"
				+ "hasMethane = " + this.hasMethane + ",\n"
				+ "carSharing = " + this.carSharing + ",\n"
				+ "lat = " + this.lat + ",\n"
				+ "lon = " + this.lon + ",\n"
				+ "dieselPrice = " + this.dieselPrice + ",\n"
				+ "superPrice = " + this.superPrice + ",\n"
				+ "superPlusPrice = " + this.superPlusPrice + ",\n"
				+ "gasPrice = " + this.gasPrice + ",\n"
				+ "methanePrice = " + this.methanePrice + ",\n"
				+ "reportUser = " + this.reportUser + ",\n"
				+ "userDto = " + this.userDto + ",\n"
				+ "reportTimestamp = " + this.reportTimestamp + ",\n"
				+ "reportDependability = " + this.reportDependability + ",\n"
				+ "}\n";
	}
	

}
