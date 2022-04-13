package com.example.April12Revision.repositories;

public interface Queries {

    public static final String SQL_SELECT_ACTIVE_MOVIES = "SELECT * FROM favourite_movies WHERE is_deleted = ?";
    public static final String SQL_SELECT_ACTIVE_ACTORS = "SELECT * FROM actors WHERE is_deleted = ?";

}
