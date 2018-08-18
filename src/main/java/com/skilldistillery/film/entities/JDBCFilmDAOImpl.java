package com.skilldistillery.film.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCFilmDAOImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	private final String user = "student";
	private final String pass = "student";

	private final String specificDataQuery = "INSERT film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost)";
	private final String deleteQuery = "DELETE FROM film";
	private final String shortFilm = "SELECT id, title, description FROM film";
	private final String updateFilm = "UPDATE film SET title = ?, description = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?";
	private final String getActors = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON film_actor.actor_id = actor.id JOIN film on film.id = film_actor.film_id WHERE film_id = ?";
	private final String getCategory = "SELECT name FROM category JOIN film_category fc ON fc.category_id = category.id JOIN film f ON f.id = fc.film_id WHERE f.id = ?";

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
				film.setActors(getActorsByFilmId(id));
				film.setCategory(getCategoryByFilmId(id));
				
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
				film.setActors(getActorsByFilmId(rs.getInt(1)));
				film.setCategory(getCategoryByFilmId(rs.getInt(1)));
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
	public boolean deleteFilm(int filmId) {
		String sql = deleteQuery + " WHERE id = ?";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, filmId);
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
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			PreparedStatement stmt = conn.prepareStatement(getActors);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actors;
	}
	
	@Override
	public Category getCategoryByFilmId(int filmId) {
		Category category = new Category();
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			PreparedStatement stmt = conn.prepareStatement(getCategory);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				category = new Category(id, name);
				
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		return category;
	}

	@Override
	public boolean updateFilm(Film film) {
		String sql = updateFilm + " WHERE id = ?";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(7, film.getId());
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getRentDur());
			st.setDouble(4, film.getRentRate());
			st.setInt(5, film.getLength());
			st.setDouble(6, film.getRepCost());
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

}