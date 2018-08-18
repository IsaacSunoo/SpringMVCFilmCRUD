package com.skilldistillery.film.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private short releaseYear;
	private int langId;
	private int rentDur;
	private double rentRate;
	private int length;
	private double repCost;
	private String features;
	private String rating;
	private String language;
	private List<Actor> actors = new ArrayList<>();
	private Category category = new Category();

public Film() {
	
}

public Film(String title, String description, short releaseYear, int langId, int rentDur, double rentRate, int length,
		double repCost) {
	this.title = title;
	this.description = description;
	this.releaseYear = releaseYear;
	this.langId = langId;
	this.rentDur = rentDur;
	this.rentRate = rentRate;
	this.length = length;
	this.repCost = repCost;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public short getReleaseYear() {
	return releaseYear;
}

public void setReleaseYear(short releaseYear) {
	this.releaseYear = releaseYear;
}

public int getLangId() {
	return langId;
}

public void setLangId(int langId) {
	this.langId = langId;
}

public int getRentDur() {
	return rentDur;
}

public void setRentDur(int rentDur) {
	this.rentDur = rentDur;
}

public double getRentRate() {
	return rentRate;
}

public void setRentRate(double rentRate) {
	this.rentRate = rentRate;
}

public int getLength() {
	return length;
}

public void setLength(int length) {
	this.length = length;
}

public double getRepCost() {
	return repCost;
}

public void setRepCost(double repCost) {
	this.repCost = repCost;
}

public String getFeatures() {
	return features;
}

public void setFeatures(String features) {
	this.features = features;
}

public String getRating() {
	return rating;
}

public void setRating(String rating) {
	this.rating = rating;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public List<Actor> getActors() {
	return actors;
}

public void setActors(List<Actor> actors) {
	this.actors = actors;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

@Override
public String toString() {
	return "Film -- ID: " + id + ", title: " + title + ", description: " + description + ", release year: " + releaseYear
			+ ", rent duration: " + rentDur + ", rent rate:" + rentRate + ", length: " + length
			+ ", replacement cost: " + repCost + ", language: " + language + ".";
}




}