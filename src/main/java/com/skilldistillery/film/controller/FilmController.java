package com.skilldistillery.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.FilmDAO;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO dao;
	
	@RequestMapping(path = "ping.do", method = RequestMethod.GET)
	  public String getInfo() {
	    return "film";
	  }
	
	@RequestMapping(path="GetFilm.do", params="filmId", method=RequestMethod.GET)
	public ModelAndView getFilmByID(int filmId) {
		ModelAndView mv = new ModelAndView();
		System.out.println(filmId);
		mv.addObject("film", dao.getFilmbyFilmId(filmId));
		mv.setViewName("film");
		return mv;
	}
	
	@RequestMapping(path="GetFilm.do", method=RequestMethod.GET)
	public ModelAndView getFilmByTitle(String title) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", dao.getFilmbyTitle(title));
		mv.setViewName("film");
		return mv;
	}
	
//	*************************  NOT FINISHED
	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public ModelAndView addNewFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.addNewFilm(film);
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmAdded.do");
		return mv;
	}
	
//	**********************  NOT FINISHED
	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.deleteFilm(film);
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmDeleted.do");
		return mv;
	}
	
//	**********************  NOT FINISHED
	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.editFilm(film);
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmUpdated.do");
		return mv;
	}
	
	public FilmDAO getDao() {
		return dao;
	}

	public void setDao(FilmDAO dao) {
		this.dao = dao;
	}
}
