package com.example.April12Revision.repositories;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.example.April12Revision.models.Actor;
import com.example.April12Revision.models.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Movie> getAllMovies() {

        List<Movie> moviesList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_MOVIES, false);

        while (rs.next()) {
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

    public List<Actor> getAllActors() {

        List<Actor> actorsList = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_ACTORS, false);

        while (rs.next()) {
            Actor actor = new Actor();
            actor.setActorId(rs.getInt("actor_id"));
            actor.setActorName(rs.getString("actor_name"));
            actor.setDeleted(rs.getBoolean("is_deleted"));
            actorsList.add(actor);
        }

        return actorsList;
    }

    public Optional<List<Actor>> getActorsByMovie(int movieId) {

        List<Actor> movieCast = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_ACTORS_BY_MOVIE, movieId, false);

        while (rs.next()) {
            Actor actor = new Actor();
            actor.setActorId(rs.getInt("actor_id"));
            actor.setActorName(rs.getString("actor_name"));
            movieCast.add(actor);
        }

        return Optional.of(movieCast);

    }

    public Optional<Movie> getMovieById(int movieId) {

        Movie movie = new Movie();

        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ACTIVE_MOVIE_BY_ID, movieId, false);

        while (rs.next()) {
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setMovieName(rs.getString("movie_name"));
            movie.setPersonalRating(rs.getInt("personal_rating"));
            movie.setReleaseDate(rs.getDate("release_date"));
            movie.setSynopsis(rs.getString("synopsis"));
        }

        return Optional.of(movie);

    }

    public Optional<Integer> actorAlreadyExists(int actorId, int movieId) {
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_IF_MOVIECAST_EXISTS, actorId, movieId);
        int count = 0;
        if (!rs.next())
            return Optional.empty();

        count++;
        return Optional.of(count);

    }

    public boolean addActorToMovie(int actorId, int movieId) {
        int count = template.update(Queries.SQL_INSERT_MOVIECAST, actorId, movieId);

        return 1 == count;
    }

    public Optional<Integer> actorAlreadyCreated(String actorName, Boolean is_deleted) {
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_IF_ACTOR_EXISTS, actorName, is_deleted);
        int count = 0;
        if (!rs.next())
            return Optional.empty();

        count++;
        return Optional.of(count);

    }

    public boolean createActor(String actorName, Boolean is_deleted) {
        int count = template.update(Queries.SQL_INSERT_ACTOR, actorName, is_deleted);

        return 1 == count;
    }

    public Optional<Integer> movieAlreadyCreated(String movieName, Boolean is_deleted) {
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_IF_MOVIE_EXISTS, movieName, is_deleted);
        int count = 0;
        if (!rs.next())
            return Optional.empty();

        count++;
        return Optional.of(count);

    }

    public boolean createMovie(String movieName, Integer rating, Date releaseDate, String synopsis, Boolean isDeleted) {
        int count = template.update(Queries.SQL_INSERT_MOVIE, movieName, rating, releaseDate, synopsis, isDeleted);

        return 1 == count;
    }

    public Integer createMovieWithTransactional(String movieName,Integer rating,Date releaseDate,String synopsis,Boolean isDeleted){
        
        KeyHolder keyHolder= new GeneratedKeyHolder();
        java.sql.Date sqlDate
            = new java.sql.Date(releaseDate.getTime());
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(Queries.SQL_INSERT_MOVIE,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, movieName);
            ps.setInt(2, rating);
            ps.setDate(3, sqlDate);
            ps.setString(4, synopsis);
            ps.setBoolean(5, isDeleted);
            return ps;
        },keyHolder);
        
        BigInteger generatedMovieId = (BigInteger) keyHolder.getKey();
        return generatedMovieId.intValue();
    }

}
