package com.corona.virus.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corona.virus.dto.CaseRecordDTO;
import com.corona.virus.dto.VaccineDTO;
import com.corona.virus.service.inf.ICorrelationCoefficient;
import com.corona.virus.service.inf.ICovidService;
import com.corona.virus.service.inf.IVaccineService;

@RestController
@RequestMapping("/api/covid/")
public class CovidController {

	@Autowired
	ICovidService covidService;
	@Autowired
	IVaccineService vaccineService;
	@Autowired
	ICorrelationCoefficient correlationCoefficient;
	@GetMapping("/all-cases")
	public List<CaseRecordDTO> getAllCases() {
		return covidService.getAllCases();
	}

	@GetMapping("/all-vaccines")
	public List<VaccineDTO> getAllVaccines() {
		return vaccineService.getAllVaccines();
	}

	@GetMapping("/all-percentages")
	public HashMap<String, Double> getAllPercentages() {
		return covidService.calculatePercentageOfCasesByCountry();
	}

	@GetMapping("/all-percentages-by-continent")
	public HashMap<String, Double> getAllPercentagesByContinent() {
		return covidService.calculatePercentageOfCasesByContinent();
	}

	@GetMapping("/get-correlation")
	public String getCorrelation() {
		return correlationCoefficient.calculateCorrelation();
	}
}
