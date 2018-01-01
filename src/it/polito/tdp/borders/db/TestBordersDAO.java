package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.exception.CountryException;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryPair;

public class TestBordersDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BordersDAO dao = new BordersDAO();
		try {
			List<Country> cList = dao.loadAllCountries(1978);
			List<CountryPair> cpList = dao.retrieveListaCountryPairAdiacenti(1978);
			System.out.println(cList);
			System.out.println(cpList);
			
		} catch (CountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
