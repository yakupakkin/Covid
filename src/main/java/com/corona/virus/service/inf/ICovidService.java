package com.corona.virus.service.inf;

import java.util.HashMap;
import java.util.List;

import com.corona.virus.dto.CaseRecordDTO;

public interface ICovidService {

	List<CaseRecordDTO> getAllCases();

	void saveCases(CaseRecordDTO caseRecordDTO);

	HashMap<String, Double> calculatePercentageOfCasesByCountry();

	HashMap<String, Double> calculatePercentageOfCasesByContinent();

}
