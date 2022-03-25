package com.corona.virus.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {
	@SerializedName("administered")
    @Expose
	private Long administered;
	@SerializedName("people_vaccinated")
    @Expose
	private Long peopleVaccinated;
	@SerializedName("people_partially_vaccinated")
    @Expose
	private Long peoplePartiallyVaccinated;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private Long population;
    @SerializedName("sq_km_area")
    @Expose
    private Double sqKmArea;
    @SerializedName("life_expectancy")
    @Expose
    private String lifeExpectancy;
    @SerializedName("elevation_in_meters")
	private Integer elevationInMeters;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("iso")
    @Expose
    private Integer iso;
    @SerializedName("capital_city")
    @Expose
    private String capitalCity;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("updated")
    @Expose
    private String updated;
}
