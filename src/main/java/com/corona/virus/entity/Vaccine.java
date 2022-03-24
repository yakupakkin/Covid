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
public class Vaccine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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
