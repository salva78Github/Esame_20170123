package it.polito.tdp.borders.db;

import it.polito.tdp.borders.exception.CountryException;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryPair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {

	public List<Country> loadAllCountries() throws CountryException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "SELECT ccode,StateAbb,StateNme FROM country ORDER BY StateAbb ";

		try {
			conn = DBConnect.getInstance().getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();

			List<Country> list = new LinkedList<Country>();

			while (rs.next()) {

				Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));

				list.add(c);
			}

			return list;

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
			throw new CountryException("Errore DB", sqle);
		} finally {
			DBConnect.getInstance().closeResources(conn, st, rs);
		}

	}


	public List<Country> loadAllCountries(int year) throws CountryException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "SELECT c1.CCode code, c1.StateAbb abb, c1.StateNme name FROM contiguity c, country c1 WHERE c.state1no = c1.CCode AND year = ? UNION SELECT c2.CCode code, c2.StateAbb abb, c2.StateNme name FROM contiguity c, country c2 WHERE c.state2no = c2.CCode AND year <= ?";

		try {
			conn = DBConnect.getInstance().getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, year);
			st.setInt(2, year);
			rs = st.executeQuery();

			List<Country> list = new LinkedList<Country>();

			while (rs.next()) {
				Country c = new Country(rs.getInt("code"), rs.getString("abb"), rs.getString("name"));
				list.add(c);
			}

			return list;

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
			throw new CountryException("Errore DB", sqle);
		} finally {
			DBConnect.getInstance().closeResources(conn, st, rs);
		}

	}


	public List<CountryPair> retrieveListaCountryPairAdiacenti(int year) throws CountryException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "SELECT c1.CCode c1_code, c1.StateAbb c1_abb, c1.StateNme c1_name, c2.CCode c2_code, c2.StateAbb c2_abb, c2.StateNme c2_name FROM contiguity c, country c1, country c2 WHERE c.state1no = c1.CCode AND c.state2no = c2.CCode AND conttype = 1 AND year <= ?";

		try {
			conn = DBConnect.getInstance().getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, year);			
			rs = st.executeQuery();
			List<CountryPair> list = new LinkedList<CountryPair>();

			while (rs.next()) {
				Country c1 = new Country(rs.getInt("c1_code"), rs.getString("c1_abb"), rs.getString("c1_name"));
				Country c2 = new Country(rs.getInt("c2_code"), rs.getString("c2_abb"), rs.getString("c2_name"));
				CountryPair cp = new CountryPair(c1, c2);				
				list.add(cp);
			}

			return list;

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
			throw new CountryException("Errore DB", sqle);
		} finally {
			DBConnect.getInstance().closeResources(conn, st, rs);
		}
	}



}
