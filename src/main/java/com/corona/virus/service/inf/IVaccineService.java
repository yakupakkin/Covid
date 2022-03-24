package com.corona.virus.service.inf;

import java.util.HashMap;
import java.util.List;

import com.corona.virus.dto.VaccineDTO;

public interface IVaccineService {

	public List<VaccineDTO> getAllVaccines();

	void saveVaccine(VaccineDTO vaccineDTO);

	HashMap<String, Double> calculatePercentageOfVaccinesByContinent();
}
