-- drop table if exists
DROP DATABASE IF EXISTS favourite_movies;

-- create a new database
CREATE DATABASE favourite_movies;

-- select database
USE favourite_movies;

-- create table
CREATE TABLE favourite_movies (
	movie_id INT NOT NULL AUTO_INCREMENT,
    movie_name VARCHAR(256) NOT NULL,
    personal_rating INT,
    release_date DATE,
    synopsis VARCHAR(1000),
    is_deleted BOOL NOT NULL,
    
    PRIMARY KEY(movie_id)
);

CREATE TABLE actors (
	actor_id INT NOT NULL AUTO_INCREMENT,
    actor_name VARCHAR(256) NOT NULL,
    is_deleted BOOL NOT NULL,
    
    PRIMARY KEY(actor_id)
    );

CREATE TABLE movie_cast (
    movie_id INT NOT NULL,
	actor_id INT NOT NULL,
	
    FOREIGN KEY (movie_id) REFERENCES favourite_movies (movie_id),
    FOREIGN KEY (actor_id) REFERENCES actors(actor_id),
    PRIMARY KEY(movie_id, actor_id)
    );
    