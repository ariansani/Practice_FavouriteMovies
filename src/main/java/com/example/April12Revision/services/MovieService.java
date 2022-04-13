package com.example.April12Revision.services;

import java.util.List;

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
    
}
