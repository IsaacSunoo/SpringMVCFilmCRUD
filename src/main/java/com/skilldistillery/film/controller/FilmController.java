package com.skilldistillery.film.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FilmController {

	
	@RequestMapping(path = "GetFilm.do", method = RequestMethod.GET)
	  public String getInfo() {
	    return "film";
	  }
}
