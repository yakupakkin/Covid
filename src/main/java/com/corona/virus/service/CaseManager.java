package com.corona.virus.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.corona.virus.dto.CaseRecordDTO;
import com.corona.virus.entity.CaseRecord;
import com.corona.virus.service.inf.ICovidService;
import com.corona.virus.util.CoronaUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class CaseManager implements ICovidService {


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
			System.out.println(caseByCountries.size());
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
	public Map<String, Map<String, CaseRecord>> readCasesData() {

		FileReader reader;
		try {
			// JSON parser object to parse read file
			JSONParser jsonParser = new JSONParser();

			reader = new FileReader("src/main/resources/cases.json");
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			Type casesType = new TypeToken<Map<String, Map<String, CaseRecord>>>() {
			}.getType();
			Gson gson = getGsonInstance();
			return gson.fromJson(obj.toString(), casesType);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Gson getGsonInstance() {
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

}
