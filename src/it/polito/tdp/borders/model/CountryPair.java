package it.polito.tdp.borders.model;

public class CountryPair {

	private final Country c1;
	private final Country c2;
	
	/**
	 * @param c1
	 * @param c2
	 */
	public CountryPair(Country c1, Country c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	/**
	 * @return the c1
	 */
	public Country getC1() {
		return c1;
	}

	/**
	 * @return the c2
	 */
	public Country getC2() {
		return c2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryPair [c1=" + c1 + ", c2=" + c2 + "]";
	}
	
	
	
	
	
	
}
