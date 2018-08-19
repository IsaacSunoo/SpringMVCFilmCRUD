package com.skilldistillery.film.entities;

import java.util.List;

public interface FilmDAO {
	public Film getFilmbyFilmId(int id);
	public List<Film> getFilmbyTitle(String title);
	public boolean deleteFilm(int filmId);
	public boolean updateFilm(Film film);
	public Film addNewFilm(Film film);
	public List<Actor> getActorsByFilmId(int filmId);
	public String getCategoryByFilmId(int filmId);
}
