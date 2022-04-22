INSERT INTO favourite_movies (movie_name, personal_rating, release_date, synopsis, is_deleted)
VALUES ('Shawshank Redemption', 9 ,'1994-09-22', 'Andy Dufresne, a successful banker, is arrested for the murders of his wife and her lover, and is sentenced to life imprisonment at the Shawshank prison. He becomes the most unconventional prisoner.',false),
('Wolf of Wall Street', 8, '2014-01-09', 'Introduced to life in the fast lane through stockbroking, Jordan Belfort takes a hit after a Wall Street crash. He teams up with Donnie Azoff, cheating his way to the top as his relationships slide.',false),
('The Big Short', 8 ,'2015-12-11','In the mid-2000s, a few finance experts observe the instability in the US housing market and predict its collapse. Through their research, they discover the flaws and corruption in the system.',false),
('Interstellar', 9 ,'2014-11-05','When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.',false);

INSERT INTO actors (actor_name,is_deleted)
VALUES ('Tim Robbins',false),
('Leonardo Di Caprio',false),
('Steve Carell',false),
('Matthew McConaughey',false),
('Morgan Freeman',false),
('Brad Pitt',false),
('Arian Sani',false),
('Syainda Abdul',false);

INSERT INTO movie_cast(movie_id, actor_id)
values(1,1),
(2,2),
(3,3),
(2,4),
(4,4),
(1,5),
(3,6)
