package com.skilldistillery.film.entities;

public interface FilmDAO {
	public Film getFilmbyFilmId(int id);
	public Film getFilmbyTitle(String title);
	public boolean deleteFilm(int filmId);
	public boolean editFilm(Film film);
	public Film addNewFilm(Film film);
	
}
