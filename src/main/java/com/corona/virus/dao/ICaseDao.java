package com.corona.virus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corona.virus.entity.CaseRecord;

public interface ICaseDao extends JpaRepository<CaseRecord, Integer> {

}
