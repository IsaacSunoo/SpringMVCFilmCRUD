package com.skilldistillery.film.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCFilmDAOImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	private final String user = "student";
	private final String pass = "student";

	private final String specificDataQuery = "INSERT title, description, release_year, rental_duration, rental_rate, length, replacement_cost FROM film";
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
	public Film addNewFilm(Scanner input) {
		Film film = new Film();
		String title = getInput(input, "Enter the name of the film: ");
		String description = getInput(input, "Enter a description for the film: ");
		int releaseYear = getInputInt(input, "Enter the release year: ");
		int rentalDuration = getInputInt(input, "Enter the rental duration: ");
		double rentalRate = getInputDouble(input, "Enter the rental rate: ");
		int length = getInputInt(input, "Enter the length of the film: ");
		double replacementCost = getInputDouble(input, "Enter the cost for replacement: ");

		String sql = specificDataQuery + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, title);
			st.setString(2, description);
			st.setInt(3, releaseYear);
			st.setInt(4, rentalDuration);
			st.setDouble(5, rentalRate);
			st.setInt(6, length);
			st.setDouble(7, replacementCost);
			int uc = st.executeUpdate();
			System.out.println(uc + " film record created.");
			ResultSet keys = st.getGeneratedKeys();
			if (keys.next()) {
				System.out.println("New film ID: " + keys.getInt(1));
				System.out.println("Film Title: " + title + ", Description: " + description + ", Release year: "
						+ releaseYear + ", Language ID: " + ", Rental duration: " + rentalDuration
						+ ", Rental rate: " + rentalRate + ", length: " + length + ", Replacement cost: "
						+ replacementCost);
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
	
	private String getInput(Scanner input, String string) {
		System.out.println(string);
		String inputString = input.nextLine();
		return inputString;
	}

	private int getInputInt(Scanner input, String string) {
		System.out.println(string);
		int inputInt = input.nextInt();
		return inputInt;
	}

	private double getInputDouble(Scanner input, String string) {
		System.out.println(string);
		double inputDouble = input.nextDouble();
		return inputDouble;
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