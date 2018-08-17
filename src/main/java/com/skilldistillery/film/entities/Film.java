package com.skilldistillery.film.entities;

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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((features == null) ? 0 : features.hashCode());
	result = prime * result + id;
	result = prime * result + langId;
	result = prime * result + ((language == null) ? 0 : language.hashCode());
	result = prime * result + length;
	result = prime * result + ((rating == null) ? 0 : rating.hashCode());
	result = prime * result + releaseYear;
	result = prime * result + rentDur;
	long temp;
	temp = Double.doubleToLongBits(rentRate);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(repCost);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Film other = (Film) obj;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (features == null) {
		if (other.features != null)
			return false;
	} else if (!features.equals(other.features))
		return false;
	if (id != other.id)
		return false;
	if (langId != other.langId)
		return false;
	if (language == null) {
		if (other.language != null)
			return false;
	} else if (!language.equals(other.language))
		return false;
	if (length != other.length)
		return false;
	if (rating == null) {
		if (other.rating != null)
			return false;
	} else if (!rating.equals(other.rating))
		return false;
	if (releaseYear != other.releaseYear)
		return false;
	if (rentDur != other.rentDur)
		return false;
	if (Double.doubleToLongBits(rentRate) != Double.doubleToLongBits(other.rentRate))
		return false;
	if (Double.doubleToLongBits(repCost) != Double.doubleToLongBits(other.repCost))
		return false;
	if (title == null) {
		if (other.title != null)
			return false;
	} else if (!title.equals(other.title))
		return false;
	return true;
}

@Override
public String toString() {
	return "Film -- ID: " + id + ", title: " + title + ", description: " + description + ", release year: " + releaseYear
			+ ", rent duration: " + rentDur + ", rent rate:" + rentRate + ", length: " + length
			+ ", replacement cost: " + repCost + ", language: " + language + ".";
}




}