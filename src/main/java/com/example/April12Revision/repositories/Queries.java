package com.example.April12Revision.repositories;

public interface Queries {

    public static final String SQL_SELECT_ACTIVE_MOVIES = "SELECT * FROM favourite_movies WHERE is_deleted = ?";
    public static final String SQL_SELECT_ACTIVE_ACTORS = "SELECT * FROM actors WHERE is_deleted = ?";
    public static final String SQL_SELECT_ACTIVE_ACTORS_BY_MOVIE = "SELECT act.actor_id, act.actor_name FROM movie_cast mc INNER JOIN actors act ON mc.actor_id = act.actor_id WHERE movie_id = ? AND act.is_deleted = ?";
    public static final String SQL_SELECT_ACTIVE_MOVIE_BY_ID = "SELECT * FROM favourite_movies WHERE movie_id = ? AND is_deleted = ?";
    public static final String SQL_CHECK_IF_MOVIECAST_EXISTS = "SELECT * FROM movie_cast WHERE actor_id = ? AND movie_id = ?";
    public static final String SQL_INSERT_MOVIECAST = "INSERT INTO movie_cast (actor_id, movie_id) values (?, ?)";
}