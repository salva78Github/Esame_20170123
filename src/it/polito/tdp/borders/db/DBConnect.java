package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

import it.polito.tdp.borders.exception.CountryException;


public class DBConnect {

	static private final String jdbcUrl = "jdbc:mysql://localhost/countries?user=root&password=salva_root";
	static private DBConnect instance = null;
	private static DataSource ds;

	private DBConnect() {
		instance = this;
	}

	public static DBConnect getInstance() {
		if (instance == null)
			return new DBConnect();
		else {
			return instance;
		}
	}

	public Connection getConnection() throws CountryException {

		if (ds == null) {
			// crea il DataSource
			try {
				ds = DataSources.pooledDataSource(DataSources.unpooledDataSource(jdbcUrl));
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new CountryException("Errore nella creazione del datasource", sqle);
			}
		}

		try {
			Connection c = ds.getConnection();
			return c;
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
			throw new CountryException("Errore nel recupero della connessione. ", sqle);
		}

	}

	public void closeResources(Connection c, Statement s, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (s != null) {
				s.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
