package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Map;

public class SimulationStatistics {

	private int steps;
	private List<Country> countries;
	
	
	/**
	 * @param steps
	 * @param people
	 */
	public SimulationStatistics(int steps, List<Country> countries) {
		super();
		this.steps = steps;
		this.countries = countries;
	}
	/**
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}
	/**
	 * @return the people
	 */
	public List<Country> getCountries() {
		return countries;
	}


}
