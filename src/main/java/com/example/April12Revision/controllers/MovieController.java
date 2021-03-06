package com.example.April12Revision.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.April12Revision.Exceptions.ActorException;
import com.example.April12Revision.Exceptions.MovieException;
import com.example.April12Revision.models.Actor;
import com.example.April12Revision.models.Movie;
import com.example.April12Revision.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MovieController {

    @Autowired
    private MovieService movieSvc;

    @GetMapping
    public ModelAndView getMoviesIndex() {

        ModelAndView mvc = new ModelAndView();

        List<Movie> moviesList = movieSvc.getAllMovies();
        List<Actor> allActorsList = movieSvc.getAllActors();

        mvc.addObject("messageMovie", "");
        mvc.addObject("messageActor", "");
        mvc.addObject("moviesList", moviesList);
        mvc.addObject("actorsList", allActorsList);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("index");

        return mvc;
    }

    @GetMapping(path = "/movie")
    public ModelAndView getMovieActors(@RequestParam("movieId") Integer movieId) {

        ModelAndView mvc = new ModelAndView();

        Optional<List<Actor>> opt = movieSvc.getActorsByMovie(movieId);
        Optional<Movie> optMovie = movieSvc.getMovieById(movieId);
        List<Actor> allActorsList = movieSvc.getAllActors();

        List<Actor> movieCast = opt.get();

        Movie movie = optMovie.get();

        mvc.addObject("message", "");
        mvc.addObject("allActorsList", allActorsList);
        mvc.addObject("movieCast", movieCast);
        mvc.addObject("movie", movie);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("actors");

        return mvc;
    }

    @PostMapping(path = "/addActor")
    public ModelAndView addActorToMovie(@RequestBody MultiValueMap<String, String> form) {

        ModelAndView mvc = new ModelAndView();

        Integer actorId = Integer.parseInt(form.getFirst("addActor"));
        Integer movieId = Integer.parseInt(form.getFirst("movieId"));

        try {
            movieSvc.addActorToMovie(actorId, movieId);

        } catch (MovieException ex) {
            mvc.addObject("message", "Error: %s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            ex.printStackTrace();
        }

        List<Actor> allActorsList = movieSvc.getAllActors();

        Optional<List<Actor>> opt = movieSvc.getActorsByMovie(movieId);
        Optional<Movie> optMovie = movieSvc.getMovieById(movieId);

        List<Actor> movieCast = opt.get();
        Movie movie = optMovie.get();

        mvc.addObject("allActorsList", allActorsList);
        mvc.addObject("movieCast", movieCast);
        mvc.addObject("movie", movie);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("actors");
        return mvc;
    }

    @PostMapping(path = "/createActor")
    public ModelAndView createActor(@RequestBody MultiValueMap<String, String> form) {

        ModelAndView mvc = new ModelAndView();

        String actorName = form.getFirst("actorName");
        try {
            movieSvc.createActor(actorName, false);

        } catch (ActorException ex) {
            mvc.addObject("messageActor", "Error: %s".formatted(ex.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            ex.printStackTrace();
        }

        List<Movie> moviesList = movieSvc.getAllMovies();
        List<Actor> allActorsList = movieSvc.getAllActors();
        mvc.addObject("moviesList", moviesList);
        mvc.addObject("actorsList", allActorsList);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("index");

        return mvc;
    }

    // @PostMapping(path = "/createMovie")
    // public ModelAndView createMovie(@RequestBody MultiValueMap<String, String> form) {

    //     ModelAndView mvc = new ModelAndView();
    //     String movieName = form.getFirst("movieName");
    //     Integer rating = Integer.parseInt(form.getFirst("rating"));
    //     String dateStr = form.getFirst("releaseDate");

    //     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //     Date releaseDate;
    //     try {
    //         releaseDate = format.parse(dateStr);
    //     } catch (ParseException e) {
    //         releaseDate = null;
    //         e.printStackTrace();
    //     }

    //     String synopsis = form.getFirst("synopsis");
    //     Boolean isDeleted = false;

    //     try {
    //         movieSvc.createMovie(movieName, rating, releaseDate, synopsis, isDeleted);
    //     } catch (MovieException e) {
    //         mvc.addObject("messageMovie", "Error: %s".formatted(e.getReason()));
    //         mvc.setStatus(HttpStatus.BAD_REQUEST);
    //         e.printStackTrace();
    //     }

    //     List<Movie> moviesList = movieSvc.getAllMovies();
    //     List<Actor> allActorsList = movieSvc.getAllActors();
    //     mvc.addObject("moviesList", moviesList);
    //     mvc.addObject("actorsList", allActorsList);
    //     mvc.setStatus(HttpStatus.OK);
    //     mvc.setViewName("index");

    //     return mvc;
    // }
    @PostMapping(path = "/createMovie")
    public ModelAndView createMovieTransactional(@RequestBody MultiValueMap<String, String> form) {

        ModelAndView mvc = new ModelAndView();
        String movieName = form.getFirst("movieName");
        Integer rating = Integer.parseInt(form.getFirst("rating"));
        String dateStr = form.getFirst("releaseDate");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate;
        try {
            releaseDate = format.parse(dateStr);
        } catch (ParseException e) {
            releaseDate = null;
            e.printStackTrace();
        }

        String synopsis = form.getFirst("synopsis");
        
        Boolean isDeleted = false;

        Integer actorId = Integer.parseInt(form.getFirst("addActor"));

        try {
            movieSvc.createMovieWithActor(movieName, rating, releaseDate, synopsis, isDeleted, actorId);
        } catch (MovieException e) {
            mvc.addObject("messageMovie", "Error: %s".formatted(e.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }

        List<Movie> moviesList = movieSvc.getAllMovies();
        List<Actor> allActorsList = movieSvc.getAllActors();
        mvc.addObject("moviesList", moviesList);
        mvc.addObject("actorsList", allActorsList);
        mvc.setStatus(HttpStatus.OK);
        mvc.setViewName("index");

        return mvc;
    }

}
