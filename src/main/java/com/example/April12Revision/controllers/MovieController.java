package com.example.April12Revision.controllers;

import java.util.List;

import com.example.April12Revision.models.Actor;
import com.example.April12Revision.models.Movie;
import com.example.April12Revision.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MovieController {
    
    @Autowired
    private MovieService movieSvc;

    @GetMapping
    public ModelAndView getMoviesIndex(){

        ModelAndView mvc = new ModelAndView();

        List<Movie> moviesList = movieSvc.getAllMovies();
        mvc.addObject("moviesList", moviesList);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("index");

        return mvc;
    }

    @GetMapping(path="/movie")
    public ModelAndView getMovieActors(@RequestParam("movieId") Integer movieId){

        ModelAndView mvc = new ModelAndView();

        List<Actor> actorsList = movieSvc.getAllActors();
        // mvc.addObject("moviesList", moviesList);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("actors");

        return mvc;
    }


}
