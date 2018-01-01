package it.polito.tdp.borders.model;

import it.polito.tdp.borders.exception.CountryException;

public class TestSimulator {

	public static void main(String[] args) {
	
		Country country = new Country(220, "FRN", "France");
		Model model = new Model();
		try {
			model.retrieveCountriesInfo(1978);
			SimulationStatistics ss = model.doSimulation(country);
			
			System.out.println("<main> steps: " + ss.getSteps() + "\n");
			for(Country c : ss.getCountries()){
				System.out.println(c.toString4Statistics() + "\n");
			}
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
