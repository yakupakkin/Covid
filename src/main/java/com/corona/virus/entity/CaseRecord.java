package com.corona.virus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "confirmed")
	private String confirmed;
	@Column(name = "recovered")
	private String recovered;
	@Column(name = "deaths")
	private String deaths;
	@Column(name = "country")
	private String country;
	@Column(name = "population")
	private String population;
	@Column(name = "sq_km_area")
	private String sq_km_area;
	@Column(name = "life_expectancy")
	private String life_expectancy;
	@Column(name = "elevation_in_meters")
	private String elevation_in_meters;
	@Column(name = "continent")
	private String continent;
	@Column(name = "abbreviation")
	private String abbreviation;
	@Column(name = "location")
	private String location;
	@Column(name = "iso")
	private String iso;
	@Column(name = "capital_city")
	private String capital_city;
	@Column(name = "lat")
	private String lat;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "updated")
	private String updated;

}
