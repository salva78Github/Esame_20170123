package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.borders.exception.CountryException;

public class Model {

	private UndirectedGraph<Country, DefaultEdge> graph;
	private BordersDAO dao = new BordersDAO();

	private UndirectedGraph<Country, DefaultEdge> creaGrafo(int year) throws CountryException {

		this.graph = new SimpleGraph<>(DefaultEdge.class);
		List<Country> vertexList = this.retrieveCountries(year);
		System.out.println("<creaGrafo> numero vertici/paesi: " + vertexList.size());
		// crea i vertici del grafo
		Graphs.addAllVertices(graph, vertexList);

		// crea gli archi del grafo --versione 3
		// faccio fare tutto il lavoro al dao
		// che mi dà la lista della coppia dei vertici
		List<CountryPair> listaCountryPairAdiacenti = dao.retrieveListaCountryPairAdiacenti(year);
		for (CountryPair cp : listaCountryPairAdiacenti) {
			graph.addEdge(cp.getC1(), cp.getC2());
		}

		return this.graph;

	}

	private List<Country> retrieveCountries(int year) throws CountryException {
		// TODO Auto-generated method stub
		return dao.loadAllCountries(year);
	}

	public List<Country> retrieveCountriesInfo(int year) throws CountryException {
		creaGrafo(year);
		List<Country> countries = new ArrayList<Country>(this.graph.vertexSet());
		for (Country c : this.graph.vertexSet()) {
			c.setNumeroDiStatiConfinanti(Graphs.neighborListOf(this.graph, c).size());
		}
		Collections.sort(countries);
		return countries;

	}

	public SimulationStatistics doSimulation(Country c) {
		Simulator sim = new Simulator(this.graph);
		sim.load(c);
		sim.run();
		SimulationStatistics ss = sim.collectStatistics();
		return ss;

	}

}
