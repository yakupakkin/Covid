package com.corona.virus.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corona.virus.entity.CaseRecord;
import com.corona.virus.entity.Vaccine;
import com.corona.virus.service.inf.DataCleanser;
import com.corona.virus.service.inf.ICovidService;
import com.corona.virus.service.inf.IVaccineService;

@Service
public class CorrelationEngine implements DataCleanser<CaseRecord, Vaccine> {
	// JAVA Program to find correlation coefficient

	@Autowired
	ICovidService covidService;
	@Autowired
	IVaccineService vaccineService;

	private final Map<String, Map<String, CaseRecord>> cases;
	private final Map<String, Map<String, Vaccine>> vaccines;

	public CorrelationEngine(Map<String, Map<String, CaseRecord>> cases, Map<String, Map<String, Vaccine>> vaccines) {
		this.cases = cases;
		this.vaccines = vaccines;
	}

	public double compute() {

		if (cases.size() != vaccines.size()) {

			clean(cases, vaccines);

			if (cases.size() != vaccines.size()) {
				throw new IllegalArgumentException("cleaning data was not successful. data is corrupt");
			}

		}

		double[][] data = reduce();
		return doCalculate(data);
	}

	public static double doCalculate(double[][] xy) {
		if (xy.length != 2) {
			throw new IllegalArgumentException("correlaction calculation data should contain exactly two arrays");
		}
		return new PearsonsCorrelation().correlation(xy[0], xy[1]);
	}

	private double[][] reduce() {
		List<Double> reducedCase = new ArrayList<>();
		List<Double> reducedVacc = new ArrayList<>();
		for (Map<String, CaseRecord> next : cases.values()) {
			Collection<CaseRecord> values = next.entrySet().stream()
					.filter(entry -> "All".equalsIgnoreCase(entry.getKey()))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();

			values.stream().filter(c -> c.getPopulation() != null).forEach(c -> {
				Long deaths = c.getDeaths();
				Long population = c.getPopulation();
				double val = deaths.doubleValue() / population;
				reducedCase.add(val);
			});
		}

		for (Map<String, Vaccine> next : vaccines.values()) {
			Collection<Vaccine> values = next.entrySet().stream()
					.filter(entry -> "All".equalsIgnoreCase(entry.getKey()))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();

			values.stream().filter(v -> v.getPopulation() != null).forEach(v -> {
				Long vaccinated = v.getPeopleVaccinated();
				Long population = v.getPopulation();
				double val = vaccinated.doubleValue() / population;
				reducedVacc.add(val);
			});
		}

		double[] xs = reducedCase.stream().mapToDouble(Double::doubleValue).toArray();
		double[] ys = reducedVacc.stream().mapToDouble(Double::doubleValue).toArray();

		return new double[][] { xs, ys };
	}

}
