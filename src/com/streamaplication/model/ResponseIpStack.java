package com.streamaplication.model;

import com.google.gson.annotations.SerializedName;

public class ResponseIpStack {

	    private String ip;
	    @SerializedName("country_name")
	    private String countryName;
	    @SerializedName("region_name")
	    private String regionName;
	    private String city;
	    private String latitude;
	    private String longitude;
	    
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getRegionName() {
			return regionName;
		}
		public void setRegionName(String regionName) {
			this.regionName = regionName;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
}
