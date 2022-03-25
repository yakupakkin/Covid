package com.corona.virus.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseRecord {

	@SerializedName("confirmed")
	@Expose
	private String confirmed;
	@Expose
	@SerializedName("recovered")
	private String recovered;
	@Expose
	@SerializedName("deaths")
	private Long deaths;
	@Expose
	@SerializedName("country")
	private String country;
	@Expose
	@SerializedName("population")
	private Long population;
	@Expose
	@SerializedName("sq_km_area")
	private String sq_km_area;
	@Expose
	@SerializedName("life_expectancy")
	private String life_expectancy;
	@Expose
	@SerializedName("elevation_in_meters")
	private String elevation_in_meters;
	@Expose
	@SerializedName("continent")
	private String continent;
	@Expose
	@SerializedName("abbreviation")
	private String abbreviation;
	@Expose
	@SerializedName("location")
	private String location;
	@Expose
	@SerializedName("iso")
	private String iso;
	@Expose
	@SerializedName("capital_city")
	private String capital_city;
	@Expose
	@SerializedName("lat")
	private String lat;
	@Expose
	@SerializedName("longitude")
	private String longitude;
	@Expose
	@SerializedName("updated")
	private String updated;

}
