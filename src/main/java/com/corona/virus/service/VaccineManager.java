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

import com.corona.virus.dao.IVaccineDao;
import com.corona.virus.dto.VaccineDTO;
import com.corona.virus.dto.VaccineMapper;
import com.corona.virus.entity.Vaccine;
import com.corona.virus.service.inf.IVaccineService;
import com.corona.virus.util.CoronaUtil;

@Service
public class VaccineManager implements IVaccineService {

	@Autowired
	IVaccineDao vaccineDao;

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
			return vaccinesByCountries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vaccinesByCountries;
	}

	@Override
	public void saveVaccine(VaccineDTO vaccineDTO) {

		Vaccine vacc = VaccineMapper.INSTANCE.dtoToEntity(vaccineDTO);
		vaccineDao.save(vacc);
	}

	@Override
	public HashMap<String, Double> calculatePercentageOfVaccinesByContinent() {
		List<VaccineDTO> caseRecordDTOs = getAllVaccines();
		HashMap<String, Double> percentageMap = new HashMap<>();
		Double total = 0.0;
		for (VaccineDTO caseRecordDTO : caseRecordDTOs) {
			if (caseRecordDTO.getContinent() != null && !caseRecordDTO.getContinent().equals("")) {

				Double deaths = Double.valueOf(caseRecordDTO.getPeople_vaccinated() != null ? caseRecordDTO.getPeople_vaccinated() : "0");
				Double population = Double
						.valueOf(caseRecordDTO.getPopulation() != null ? caseRecordDTO.getPopulation() : "0");
				if (Double.compare(deaths, 0) > 0 && Double.compare(population, 0) > 0) {
					double percentage = (deaths / population);
					String formatted = String.format("%,.4f", percentage);
					total = Double.valueOf(formatted) + total;
					percentageMap.put(caseRecordDTO.getContinent(), total);
				}
			}
		}

		return percentageMap;
	}
}
