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

import com.corona.virus.dto.VaccineDTO;
import com.corona.virus.entity.Vaccine;
import com.corona.virus.service.inf.IVaccineService;
import com.corona.virus.util.CoronaUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class VaccineManager implements IVaccineService {

	@Override
	public List<VaccineDTO> getAllVaccines() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		List<VaccineDTO> vaccinesByCountries = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("src/main/resources/vaccines.json"));
			jsonObject = (JSONObject) obj;
			Set<String> countries = jsonObject.keySet();
			for (String countryName : countries) {
				VaccineDTO vaccineDTO = new VaccineDTO();
				JSONObject country = (JSONObject) jsonObject.get(countryName);
				JSONObject getAll = (JSONObject) country.get("All");
				vaccineDTO.setAbbreviation(CoronaUtil.toString(getAll.get("abbreviation")));
				vaccineDTO.setAdministered(CoronaUtil.toString(getAll.get("administered")));
				vaccineDTO.setCapital_city(CoronaUtil.toString(getAll.get("capital_city")));
				vaccineDTO.setContinent(CoronaUtil.toString(getAll.get("continent")));
				vaccineDTO.setCountry(CoronaUtil.toString(getAll.get("country")));
				vaccineDTO.setElevation_in_meters(CoronaUtil.toString(getAll.get("elevation_in_meters")));
				vaccineDTO.setIso(CoronaUtil.toString(getAll.get("iso")));
				vaccineDTO.setLife_expectancy(CoronaUtil.toString(getAll.get("life_expectancy")));
				vaccineDTO.setLocation(CoronaUtil.toString(getAll.get("location")));
				vaccineDTO.setPeople_partially_vaccinated(
						CoronaUtil.toString(getAll.get("people_partially_vaccinated")));
				vaccineDTO.setPeople_vaccinated(CoronaUtil.toString(getAll.get("people_vaccinated")));
				vaccineDTO.setPopulation(CoronaUtil.toString(getAll.get("population")));
				vaccineDTO.setSq_km_area(CoronaUtil.toString(getAll.get("sq_km_area")));
				vaccineDTO.setUpdated(CoronaUtil.toString(getAll.get("updated")));
				vaccinesByCountries.add(vaccineDTO);
			}
			System.out.println(vaccinesByCountries.size());
			return vaccinesByCountries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vaccinesByCountries;
	}

	@Override
	public Map<String, Map<String, Vaccine>> readVaccinesData() {

		FileReader reader;
		try {
			// JSON parser object to parse read file
			JSONParser jsonParser = new JSONParser();

			reader = new FileReader("src/main/resources/vaccines.json");
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			Type vaccinesType = new TypeToken<Map<String, Map<String, Vaccine>>>() {
			}.getType();
			Gson gson = getGsonInstance();
			return gson.fromJson(obj.toString(), vaccinesType);

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