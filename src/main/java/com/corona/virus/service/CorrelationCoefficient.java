package com.corona.virus.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corona.virus.service.inf.ICorrelationCoefficient;
import com.corona.virus.service.inf.ICovidService;
import com.corona.virus.service.inf.IVaccineService;

@Service
public class CorrelationCoefficient implements ICorrelationCoefficient {
	// JAVA Program to find correlation coefficient

	@Autowired
	ICovidService covidService;
	@Autowired
	IVaccineService vaccineService;

	// function that returns correlation coefficient.
	float correlationCoefficient(double X[], double Y[], int n) {

		double sum_X = 0, sum_Y = 0, sum_XY = 0;
		double squareSum_X = 0, squareSum_Y = 0;

		for (int i = 0; i < n; i++) {
			// sum of elements of array X.
			sum_X = sum_X + X[i];

			// sum of elements of array Y.
			sum_Y = sum_Y + Y[i];

			// sum of X[i] * Y[i].
			sum_XY = sum_XY + X[i] * Y[i];

			// sum of square of array elements.
			squareSum_X = squareSum_X + X[i] * X[i];
			squareSum_Y = squareSum_Y + Y[i] * Y[i];
		}

		// use formula for calculating correlation
		// coefficient.
		float corr = (float) (n * sum_XY - sum_X * sum_Y)
				/ (float) (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));

		return corr;
	}

	@Override
	public String calculateCorrelation() {

		HashMap<String, Double> cases = covidService.calculatePercentageOfCasesByContinent();
		List<Double> caseList = cases.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		double[] X = caseList.stream().mapToDouble(Double::doubleValue).toArray(); // via method reference

		HashMap<String, Double> vaccines = vaccineService.calculatePercentageOfVaccinesByContinent();
		List<Double> vaccineList = vaccines.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		double[] Y = vaccineList.stream().mapToDouble(Double::doubleValue).toArray(); // via method reference

		// Function call to correlationCoefficient.

		float correlation = correlationCoefficient(X, Y, cases.size());

		return String.valueOf(correlation);
	}


}
