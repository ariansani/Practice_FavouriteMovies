package com.example.April12Revision.services;

import java.util.List;
import java.util.Optional;

import com.example.April12Revision.Exceptions.ActorException;
import com.example.April12Revision.Exceptions.MovieException;
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

    public Optional<Movie> getMovieById(int movieId){

        Optional<Movie> opt = movieRepo.getMovieById(movieId);

        if(opt.isEmpty()){
            return Optional.empty();
          }
  
          Movie movie = opt.get();
  
          return Optional.of(movie);

    }

    public void addActorToMovie(int actorId,int movieId) throws MovieException{

        Optional<Integer> opt = movieRepo.actorAlreadyExists(actorId, movieId);

        if (opt.isPresent())
            throw new MovieException("%s is already your cast".formatted(actorId));

        if (!movieRepo.addActorToMovie(actorId,movieId))
            throw new MovieException("Cannot add %s as movieCast. Please check with admin".formatted(actorId));

    }

    public void createActor(String actorName,Boolean is_deleted) throws ActorException{

        Optional<Integer> opt = movieRepo.actorAlreadyCreated(actorName, is_deleted);

        if (opt.isPresent())
            throw new ActorException("%s is already available".formatted(actorName));

        if (!movieRepo.createActor(actorName,is_deleted))
            throw new ActorException("Cannot add %s as movieCast. Please check with admin".formatted(actorName));

    }
    
    
}
