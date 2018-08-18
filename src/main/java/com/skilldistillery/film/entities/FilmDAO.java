package com.skilldistillery.film.entities;

public interface FilmDAO {
	public Film getFilmbyFilmId(int id);
	public Film getFilmbyTitle(String title);
	public Film addNewFilm(Film film);
	public boolean deleteFilm(Film film);
	public Film editFilm(Film film);
	// retrieve film and have the option of editing it
	
}
