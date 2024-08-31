-- Insert some movies
INSERT INTO movie (id, name, description, length, genre) VALUES
(1, 'The Matrix', 'A computer hacker learns about the true nature of reality and his role in the war against its controllers.', '136 min', 'Sci-Fi'),
(2, 'Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea.', '148 min', 'Action');

-- Insert some tickets
INSERT INTO ticket (id, movie_id, round, seat) VALUES
(1, 1, 'Evening', 'A1'),
(2, 2, 'Morning', 'B5');

-- Insert some customers
INSERT INTO customer (id, name, ticket_id) VALUES
(1, 'John Doe', 1),
(2, 'Jane Smith', 2);

-- Insert some theatres
INSERT INTO theatre (id, movie, round, seat, ticket_id) VALUES
(1, 'The Matrix', 'Evening', 'A1', 1),
(2, 'Inception', 'Morning', 'B5', 2);
