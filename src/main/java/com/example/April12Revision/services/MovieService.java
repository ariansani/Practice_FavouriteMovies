package com.example.April12Revision.services;

import java.util.List;
import java.util.Optional;

import com.example.April12Revision.models.Actor;
import com.example.April12Revision.models.Movie;
import com.example.April12Revision.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepo;

    public List<Movie> getAllMovies(){

        return movieRepo.getAllMovies();

    }

    public List<Actor> getAllActors(){
        
        return movieRepo.getAllActors();

    }

    public Optional<List<Actor>> getActorsByMovie(int movieId){

        Optional<List<Actor>> opt = movieRepo.getActorsByMovie(movieId);
        if(opt.isEmpty()){
          return Optional.empty();
        }

        List<Actor> actorList = opt.get();

        return Optional.of(actorList);

    }
    
}
