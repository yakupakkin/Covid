package com.corona.virus.service.inf;

import java.util.List;
import java.util.Map;

import com.corona.virus.dto.VaccineDTO;
import com.corona.virus.entity.Vaccine;

public interface IVaccineService {

	public List<VaccineDTO> getAllVaccines();

	Map<String, Map<String, Vaccine>> readVaccinesData();
}
