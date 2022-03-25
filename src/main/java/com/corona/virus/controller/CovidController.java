package com.corona.virus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corona.virus.dto.CaseRecordDTO;
import com.corona.virus.dto.VaccineDTO;
import com.corona.virus.service.CorrelationEngine;
import com.corona.virus.service.inf.ICovidService;
import com.corona.virus.service.inf.IVaccineService;

@RestController
@RequestMapping("/api/covid/")
public class CovidController {

	@Autowired
	ICovidService covidService;
	@Autowired
	IVaccineService vaccineService;

	@GetMapping("/all-cases")
	public List<CaseRecordDTO> getAllCases() {
		return covidService.getAllCases();
	}

	@GetMapping("/all-vaccines")
	public List<VaccineDTO> getAllVaccines() {
		return vaccineService.getAllVaccines();
	}

	@GetMapping("/get-correlation-by-country")
	public double getCorrelationByCountry() {
		CorrelationEngine correlationEngine = new CorrelationEngine(covidService.readCasesData(),
				vaccineService.readVaccinesData());
		return correlationEngine.compute();
	}

	@GetMapping("/get-correlation-by-continent")
	public double getCorrelationByContinent() {
		CorrelationEngine correlationEngine = new CorrelationEngine(
				covidService.readCasesData(), vaccineService.readVaccinesData());
		return correlationEngine.compute();
	}
}
