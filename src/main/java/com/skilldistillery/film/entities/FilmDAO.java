package com.skilldistillery.film.entities;

import java.util.List;

public interface FilmDAO {
	public Film getFilmbyFilmId(int id);
	public Film getFilmbyTitle(String title);
	public boolean deleteFilm(int filmId);
	public boolean updateFilm(Film film);
	public Film addNewFilm(Film film);
	List<Actor> getActorsByFilmId(int filmId);
	public Category getCategoryByFilmId(int filmId);
}
