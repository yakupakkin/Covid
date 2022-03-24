package com.corona.virus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corona.virus.entity.Vaccine;

public interface IVaccineDao extends JpaRepository<Vaccine, Integer> {

}
