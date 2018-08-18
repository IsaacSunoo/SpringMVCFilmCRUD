package com.skilldistillery.film.entities;

import java.util.Scanner;

public interface FilmDAO {
	public Film getFilmbyFilmId(int id);
	public Film getFilmbyTitle(String title);
	public boolean deleteFilm(Film film);
	public Film editFilm(Film film);
	Film addNewFilm(Scanner input);
	
}
