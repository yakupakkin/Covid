package com.corona.virus.service.inf;

import java.util.List;
import java.util.Map;

import com.corona.virus.dto.CaseRecordDTO;
import com.corona.virus.entity.CaseRecord;

public interface ICovidService {

	List<CaseRecordDTO> getAllCases();

	Map<String, Map<String, CaseRecord>> readCasesData();

}
