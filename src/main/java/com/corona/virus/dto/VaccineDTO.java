package com.corona.virus.dto;

import lombok.Data;

@Data
public class VaccineDTO {
	private Integer id;
	private String administered;
	private String people_vaccinated;
	private String people_partially_vaccinated;
	private String country;
	private String population;
	private String sq_km_area;
	private String life_expectancy;
	private String elevation_in_meters;
	private String continent;
	private String abbreviation;
	private String location;
	private String iso;
	private String capital_city;
	private String updated;
}
