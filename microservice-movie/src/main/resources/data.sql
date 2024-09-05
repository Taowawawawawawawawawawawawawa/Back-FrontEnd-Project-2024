-- Mock data for movies with base64-encoded pictures
INSERT INTO movie (id, name, description, length, genre, picture) 
VALUES (1, 'Inception', 'A mind-bending thriller', '148', 'Sci-Fi', 'cGljMDE=');  -- "pic01" in base64
INSERT INTO movie (id, name, description, length, genre, picture) 
VALUES (2, 'The Matrix', 'A dystopian future', '136', 'Action', 'cGljMDI=');     -- "pic02" in base64
INSERT INTO movie (id, name, description, length, genre, picture) 
VALUES (3, 'Interstellar', 'A journey through space', '169', 'Sci-Fi', 'cGljMDM='); -- "pic03" in base64
