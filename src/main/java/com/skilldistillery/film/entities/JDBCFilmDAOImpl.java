package com.skilldistillery.film.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFilmDAOImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	private final String user = "student";
	private final String pass = "student";

	private final String specificDataQuery = "INSERT film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost)";
	private final String fullDataQuery = "SELECT * FROM film";
	private final String deleteQuery = "DELETE FROM film";
	private final String shortFilm = "SELECT id, title, description FROM film";

	public JDBCFilmDAOImpl() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	@Override
	public Film getFilmbyFilmId(int id) {
		Film film = new Film();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = shortFilm + " WHERE id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
//				film = new Film(title, description, releaseYear, langId, rentDur, rentRate, length, repCost)

				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				System.out.println(rs.getInt(1));
//				film = new Film(rs.getString(1), rs.getString(2), rs.getShort(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Film getFilmbyTitle(String title) {
		Film film = new Film();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = shortFilm + " WHERE title LIKE ?";

			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, "%" + title + "%");

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setId(rs.getInt(1));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	@Override
	public Film addNewFilm(Film film) {

		String sql = specificDataQuery + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setShort(3, film.getReleaseYear());
			st.setInt(4, 1);
			st.setInt(5, film.getRentDur());
			st.setDouble(6, film.getRentRate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getRepCost());
			int uc = st.executeUpdate();
			System.out.println(uc + " film record created.");
			ResultSet keys = st.getGeneratedKeys();
		      if (keys.next()) {
		        int filmId = keys.getInt(1);
		        film.setId(filmId);
			}
			conn.commit();
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error during inserts.");
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException se) {
					System.err.println("Error rolling back.");
					se.printStackTrace();
				}
			}
		}
		return film;
	}
	
	@Override
	public boolean deleteFilm(Film film) {
		String sql = deleteQuery + "WHERE id = ?";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, film.getId());
			int updateCount = st.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}

		return true;
	}

	@Override
	public Film editFilm(Film film) {

		return null;
	}

}