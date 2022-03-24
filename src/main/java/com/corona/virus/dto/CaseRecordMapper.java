package com.corona.virus.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.corona.virus.entity.CaseRecord;

@Mapper
public interface CaseRecordMapper {

	CaseRecordMapper INSTANCE = Mappers.getMapper(CaseRecordMapper.class);

	@Mapping(source = "confirmed", target = "confirmed")
	CaseRecordDTO entityToDTO(CaseRecord caseRecord);

	@Mapping(source = "confirmed", target = "confirmed")
	CaseRecord dtoToEntity(CaseRecordDTO caseRecordDTO);
}
