package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private UndirectedGraph<Country, DefaultEdge> graph ;
	private List<Country> countries ;
	private Map<Integer, Country> countryMap ;
	
	public Model() {
		
	}
	
	public List<CountryAndNum> creaGrafo(int anno) {
		this.graph = new SimpleGraph<>(DefaultEdge.class) ;
		this.countryMap = new HashMap<>() ;
		
		BordersDAO dao = new BordersDAO() ;
		
		this.countries = dao.loadAllCountries() ;
		
		// popola identity map
		for(Country c: this.countries) {
			this.countryMap.put(c.getcCode(), c) ;
		}
		
		// aggiungi i vertici
		Graphs.addAllVertices(this.graph, this.countries) ;
		
		// aggiungi gli archi
		List<IntegerPair> confini = dao.getConfinanti(anno) ;
		for(IntegerPair p: confini) {
			graph.addEdge(this.countryMap.get(p.getN1()), 
					this.countryMap.get(p.getN2())) ;
		}
		
		// calcola il grado dei vertici
		List<CountryAndNum> confinanti = new ArrayList<>() ;
		for( Country c: this.countries ) {
			int num = Graphs.neighborListOf(graph, c).size() ;
			if(num!=0) {
				confinanti.add(new CountryAndNum(c, num)) ;
			}
		}
		Collections.sort(confinanti);
		
		return confinanti ;
		
	}

	public static void main(String args[]) {
		Model m = new Model() ;
		
		List<CountryAndNum> stati = m.creaGrafo(2010) ;
		System.out.println(m.graph);
		for(CountryAndNum cn : stati) {
			System.out.format("%s: %d\n", cn.getCountry(), cn.getNum()) ;
		}
		
	}
	
}
