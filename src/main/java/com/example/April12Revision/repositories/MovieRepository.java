package com.example.April12Revision.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.example.April12Revision.models.Actor;
import com.example.April12Revision.models.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {
    
    @Autowired
    private JdbcTemplate template;

    public List<Movie> getAllMovies(){

        List<Movie> moviesList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_MOVIES, false);

        while(rs.next()){
            Movie movie = new Movie();
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setMovieName(rs.getString("movie_name"));
            movie.setPersonalRating(rs.getInt("personal_rating"));
            movie.setReleaseDate(rs.getDate("release_date"));
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setDeleted(rs.getBoolean("is_deleted"));
            moviesList.add(movie);
        }
        
        return moviesList;
    } 

    public List<Actor> getAllActors(){

        List<Actor> actorsList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_ACTORS, false);

        while(rs.next()){
            Actor actor = new Actor();
            actor.setActorId(rs.getInt("actor_id"));
            actor.setActorName(rs.getString("actor_name"));
            actor.setDeleted(rs.getBoolean("is_deleted"));
            actorsList.add(actor);
        }
        
        return actorsList;
    } 

    public Optional<List<Actor>> getActorsByMovie(int movieId){

        List<Actor> movieCast = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_ACTORS_BY_MOVIE, movieId, false);
        
        while(rs.next()){
            Actor actor = new Actor();
            actor.setActorId(rs.getInt("actor_id"));
            actor.setActorName(rs.getString("actor_name"));
            movieCast.add(actor);
        }
        
        
        return Optional.of(movieCast);

    }


}
