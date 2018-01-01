package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{

	public enum EventType{
		STABILIZZAZIONE,
		MIGRAZIONE;

		
	}
	
	
	private final int time;
	private final EventType eventType;
	private final Country country;
	private final int numeroMigranti;
	
	
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}




	/**
	 * @return the event
	 */
	public EventType getEventType() {
		return eventType;
	}




	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}




	/**
	 * @return the numeroMigranti
	 */
	public int getNumeroMigranti() {
		return numeroMigranti;
	}




	/**
	 * @param time
	 * @param event
	 * @param numeroMigranti 
	 */
	public Event(int time, EventType event, Country c, int numeroMigranti) {
		this.time = time;
		this.eventType = event;
		this.country = c;
		this.numeroMigranti = numeroMigranti;
	}




	@Override
	public int compareTo(Event o) {
		return getTime()-o.getTime();
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [time=" + time + ", eventType=" + eventType + ", country=" + country + ", numeroMigranti="
				+ numeroMigranti + "]";
	}

	

	
	
}
