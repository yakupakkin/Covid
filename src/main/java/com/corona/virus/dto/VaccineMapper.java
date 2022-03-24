package com.corona.virus.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.corona.virus.entity.Vaccine;

@Mapper
public interface VaccineMapper {

	VaccineMapper INSTANCE = Mappers.getMapper(VaccineMapper.class);

	VaccineDTO entityToDTO(Vaccine vaccine);

	Vaccine dtoToEntity(VaccineDTO vaccineDTO);
}
