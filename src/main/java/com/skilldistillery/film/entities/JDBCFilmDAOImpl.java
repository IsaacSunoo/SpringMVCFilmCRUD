package com.skilldistillery.film.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCFilmDAOImpl implements FilmDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	
	private final String user = "student";
	private final String pass = "student";
	
	private final String fullDataQuery = "SELECT title, description, release_year, language_id, rental_duration"
			+ " rental_rate, length, replacement_cost FROM film";
	
	public JDBCFilmDAOImpl() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	@Override
	public Film getFilmbyFilmId(int id) {
		Film film = null;
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = fullDataQuery + " WHERE id = ?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				film = new Film(rs.getString(1), rs.getString(2), rs.getShort(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Film getFilmbyTitle(String title) {
		return null;
		
	}
	
	@Override
	public Film addNewFilm() {
		return null;
	}

	@Override
	public Film deleteFilm() {
		return null;
	}

	@Override
	public Film editFilm() {
		return null;
	}

}
