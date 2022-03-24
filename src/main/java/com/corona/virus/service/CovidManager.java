package com.corona.virus.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corona.virus.dao.ICaseDao;
import com.corona.virus.dto.CaseRecordDTO;
import com.corona.virus.dto.CaseRecordMapper;
import com.corona.virus.entity.CaseRecord;
import com.corona.virus.service.inf.ICovidService;
import com.corona.virus.util.CoronaUtil;

@Service
public class CovidManager implements ICovidService {

	@Autowired
	ICaseDao caseDao;

	@Override
	public List<CaseRecordDTO> getAllCases() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		List<CaseRecordDTO> caseByCountries = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("src/main/resources/cases.json"));
			jsonObject = (JSONObject) obj;
			Set<String> countries = jsonObject.keySet();

			for (String countryName : countries) {
				CaseRecordDTO caseByCountry = mapToDTO(jsonObject, countryName);
				caseByCountries.add(caseByCountry);
			}
			return caseByCountries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caseByCountries;
	}

	private CaseRecordDTO mapToDTO(JSONObject jsonObject, String countryName) {
		List<CaseRecordDTO> caseByCountries = new ArrayList<>();
		CaseRecordDTO caseDTO = new CaseRecordDTO();
		JSONObject country = (JSONObject) jsonObject.get(countryName);
		JSONObject getAll = (JSONObject) country.get("All");
		if (getAll.get("country") != null) {
			caseDTO.setCountry(CoronaUtil.toString(getAll.get("country")));
			caseDTO.setAbbreviation(CoronaUtil.toString(getAll.get("abbreviation")));
			caseDTO.setCapital_city(CoronaUtil.toString(getAll.get("capital_city")));
			caseDTO.setConfirmed(CoronaUtil.toString(getAll.get("confirmed")));
			caseDTO.setContinent(CoronaUtil.toString(getAll.get("continent")));
			caseDTO.setDeaths(CoronaUtil.toString(getAll.get("deaths")));
			caseDTO.setElevation_in_meters(CoronaUtil.toString(getAll.get("elevation_in_meters")));
			caseDTO.setIso(CoronaUtil.toString(getAll.get("iso")));
			caseDTO.setLat(CoronaUtil.toString(getAll.get("lat")));
			caseDTO.setLife_expectancy(CoronaUtil.toString(getAll.get("life_expectancy")));
			caseDTO.setLocation(CoronaUtil.toString(getAll.get("location")));
			caseDTO.setLongitude(CoronaUtil.toString(getAll.get("long")));
			caseDTO.setPopulation(CoronaUtil.toString(getAll.get("population")));
			caseDTO.setRecovered(CoronaUtil.toString(getAll.get("recovered")));
			caseDTO.setSq_km_area(CoronaUtil.toString(getAll.get("sq_km_area")));
			caseDTO.setUpdated(CoronaUtil.toString(getAll.get("updated")));
			caseByCountries.add(caseDTO);
		}
		return caseDTO;
	};

	@Override
	public void saveCases(CaseRecordDTO caseRecordDTO) {
		CaseRecord caseRecord = CaseRecordMapper.INSTANCE.dtoToEntity(caseRecordDTO);
		caseDao.saveAndFlush(caseRecord);
	}

	@Override
	public HashMap<String, Double> calculatePercentageOfCasesByCountry() {
		List<CaseRecordDTO> caseRecordDTOs = getAllCases();
		HashMap<String, Double> percentageMap = new HashMap<>();
		for (CaseRecordDTO caseRecordDTO : caseRecordDTOs) {
			if (caseRecordDTO.getCountry() != null && !caseRecordDTO.getCountry().equals("")) {
				Double deaths = Double.valueOf(caseRecordDTO.getDeaths() != null ? caseRecordDTO.getDeaths() : "0");
				Double population = Double
						.valueOf(caseRecordDTO.getPopulation() != null ? caseRecordDTO.getPopulation() : "0");
				if (Double.compare(deaths, 0) > 0 && Double.compare(population, 0) > 0) {
					double percentage = (deaths / population) * 100;
					String formatted = String.format("%,.4f", percentage);
					percentageMap.put(caseRecordDTO.getContinent(), Double.valueOf(formatted));
				}
			}
		}
		return percentageMap;
	}

	@Override
	public HashMap<String, Double> calculatePercentageOfCasesByContinent() {
		List<CaseRecordDTO> caseRecordDTOs = getAllCases();
		HashMap<String, Double> percentageMap = new HashMap<>();
		for (CaseRecordDTO caseRecordDTO : caseRecordDTOs) {
			if (caseRecordDTO.getContinent() != null && !caseRecordDTO.getContinent().equals("")) {
				Double deaths = Double.valueOf(caseRecordDTO.getDeaths() != null ? caseRecordDTO.getDeaths() : "0");
				Double population = Double
						.valueOf(caseRecordDTO.getPopulation() != null ? caseRecordDTO.getPopulation() : "0");
				if (Double.compare(deaths, 0) > 0 && Double.compare(population, 0) > 0) {
					double percentage = (deaths / population);
					String formatted = String.format("%,.4f", percentage);
					percentageMap.put(caseRecordDTO.getContinent(), Double.valueOf(formatted));
				}
			}
		}

		return percentageMap;
	}
}
