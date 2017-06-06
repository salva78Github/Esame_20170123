package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento> {
	
	// c'è un solo tipo di evento: INGRESSO
	
	private int num ; // quante persone
	private Country country ; // in quale stato
	private int t ;
	public Evento(int num, Country country, int t) {
		super();
		this.num = num;
		this.country = country;
		this.t = t;
	} // a quale istante di tempo
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	/**
	 * @param t the t to set
	 */
	public void setT(int t) {
		this.t = t;
	}
	
	/** 
	 * Ordinamento per tempo crescente
	 */
	@Override
	public int compareTo(Evento other) {
		return this.t - other.t;
	}
	
	

}
