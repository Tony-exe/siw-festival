-- Dati di prova: vengono ricaricati identici a ogni avvio (ddl-auto=create).
-- Le date dei festival sono relative a oggi: Venezia e' gia' concluso, Roma e Cannes sono futuri.
-- Password degli utenti = lo username (admin/admin, user1/user1, user2/user2).

-- UTENTI
INSERT INTO utente (username, password, ruolo) VALUES ('admin', '$2a$10$EYd7j7jbAKMXV1lMTmIhU.AFygk/EmREypiUmcDebb5SfHbeBszlC', 'ADMIN');
INSERT INTO utente (username, password, ruolo) VALUES ('user1', '$2a$10$sz16Djc4wKYrhjRqQH.JAuXFZktdP8xGzPEFOYnXk5oZ4BEdZ4qAW', 'USER');
INSERT INTO utente (username, password, ruolo) VALUES ('user2', '$2a$10$mZ3RAUt9dutRWEuXeRb2tuInR4lo1nD4Ta655VZIoJ6IycNenfDqG', 'USER');

-- REGISTI
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Francis Ford', 'Coppola', '1939-04-07', 'Statunitense');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Federico', 'Fellini', '1920-01-20', 'Italiana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Sergio', 'Leone', '1929-01-03', 'Italiana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Martin', 'Scorsese', '1942-11-17', 'Statunitense');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Quentin', 'Tarantino', '1963-03-27', 'Statunitense');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Giuseppe', 'Tornatore', '1956-05-27', 'Italiana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Roberto', 'Benigni', '1952-10-27', 'Italiana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Paolo', 'Sorrentino', '1970-05-31', 'Italiana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Joon-ho', 'Bong', '1969-09-14', 'Sudcoreana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Alfonso', 'Cuarón', '1961-11-28', 'Messicana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Roman', 'Polański', '1933-08-18', 'Francese');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Jean-Pierre', 'Jeunet', '1953-09-03', 'Francese');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Jane', 'Campion', '1954-04-30', 'Neozelandese');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Christopher', 'Nolan', '1970-07-30', 'Britannica');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Matteo', 'Garrone', '1968-10-15', 'Italiana');
INSERT INTO regista (nome, cognome, data_nascita, nazionalita) VALUES ('Ryūsuke', 'Hamaguchi', '1978-12-16', 'Giapponese');

-- ATTORI
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Marlon', 'Brando', '1924-04-03', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Al', 'Pacino', '1940-04-25', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Martin', 'Sheen', '1940-08-03', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Marcello', 'Mastroianni', '1924-09-28', 'Italiana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Anita', 'Ekberg', '1931-09-29', 'Svedese');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Claudia', 'Cardinale', '1938-04-15', 'Italiana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Clint', 'Eastwood', '1930-05-31', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Eli', 'Wallach', '1915-12-07', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Robert', 'De Niro', '1943-08-17', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Jodie', 'Foster', '1962-11-19', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('John', 'Travolta', '1954-02-18', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Uma', 'Thurman', '1970-04-29', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Samuel L.', 'Jackson', '1948-12-21', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Philippe', 'Noiret', '1930-10-01', 'Francese');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Salvatore', 'Cascio', '1979-11-07', 'Italiana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Roberto', 'Benigni', '1952-10-27', 'Italiana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Nicoletta', 'Braschi', '1960-04-19', 'Italiana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Toni', 'Servillo', '1959-01-25', 'Italiana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Kang-ho', 'Song', '1967-01-17', 'Sudcoreana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Yalitza', 'Aparicio', '1993-12-11', 'Messicana');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Adrien', 'Brody', '1973-04-14', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Audrey', 'Tautou', '1976-08-09', 'Francese');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Holly', 'Hunter', '1958-03-20', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Harvey', 'Keitel', '1939-05-13', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Leonardo', 'DiCaprio', '1974-11-11', 'Statunitense');
INSERT INTO attore (nome, cognome, data_nascita, nazionalita) VALUES ('Hidetoshi', 'Nishijima', '1971-03-29', 'Giapponese');

-- FILM
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Il Padrino', 1972, 175, 'Drammatico', 'Stati Uniti', (SELECT id FROM regista WHERE nome = 'Francis Ford' AND cognome = 'Coppola'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Apocalypse Now', 1979, 147, 'Guerra', 'Stati Uniti', (SELECT id FROM regista WHERE nome = 'Francis Ford' AND cognome = 'Coppola'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('La dolce vita', 1960, 174, 'Drammatico', 'Italia', (SELECT id FROM regista WHERE nome = 'Federico' AND cognome = 'Fellini'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('8½', 1963, 138, 'Drammatico', 'Italia', (SELECT id FROM regista WHERE nome = 'Federico' AND cognome = 'Fellini'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Il buono, il brutto, il cattivo', 1966, 178, 'Western', 'Italia', (SELECT id FROM regista WHERE nome = 'Sergio' AND cognome = 'Leone'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('C''era una volta in America', 1984, 229, 'Drammatico', 'Italia', (SELECT id FROM regista WHERE nome = 'Sergio' AND cognome = 'Leone'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Taxi Driver', 1976, 114, 'Drammatico', 'Stati Uniti', (SELECT id FROM regista WHERE nome = 'Martin' AND cognome = 'Scorsese'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('The Wolf of Wall Street', 2013, 180, 'Commedia', 'Stati Uniti', (SELECT id FROM regista WHERE nome = 'Martin' AND cognome = 'Scorsese'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Pulp Fiction', 1994, 154, 'Crime', 'Stati Uniti', (SELECT id FROM regista WHERE nome = 'Quentin' AND cognome = 'Tarantino'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Nuovo Cinema Paradiso', 1988, 155, 'Drammatico', 'Italia', (SELECT id FROM regista WHERE nome = 'Giuseppe' AND cognome = 'Tornatore'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('La vita è bella', 1997, 116, 'Commedia', 'Italia', (SELECT id FROM regista WHERE nome = 'Roberto' AND cognome = 'Benigni'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('La grande bellezza', 2013, 141, 'Drammatico', 'Italia', (SELECT id FROM regista WHERE nome = 'Paolo' AND cognome = 'Sorrentino'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Parasite', 2019, 132, 'Thriller', 'Corea del Sud', (SELECT id FROM regista WHERE nome = 'Joon-ho' AND cognome = 'Bong'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Roma', 2018, 135, 'Drammatico', 'Messico', (SELECT id FROM regista WHERE nome = 'Alfonso' AND cognome = 'Cuarón'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Il pianista', 2002, 150, 'Drammatico', 'Francia', (SELECT id FROM regista WHERE nome = 'Roman' AND cognome = 'Polański'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Il favoloso mondo di Amélie', 2001, 122, 'Commedia', 'Francia', (SELECT id FROM regista WHERE nome = 'Jean-Pierre' AND cognome = 'Jeunet'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Lezioni di piano', 1993, 121, 'Drammatico', 'Nuova Zelanda', (SELECT id FROM regista WHERE nome = 'Jane' AND cognome = 'Campion'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Inception', 2010, 148, 'Fantascienza', 'Stati Uniti', (SELECT id FROM regista WHERE nome = 'Christopher' AND cognome = 'Nolan'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Gomorra', 2008, 137, 'Crime', 'Italia', (SELECT id FROM regista WHERE nome = 'Matteo' AND cognome = 'Garrone'));
INSERT INTO film (titolo, anno, durata, genere, paese_produzione, regista_id) VALUES ('Drive My Car', 2021, 179, 'Drammatico', 'Giappone', (SELECT id FROM regista WHERE nome = 'Ryūsuke' AND cognome = 'Hamaguchi'));

-- CAST
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Don Vito Corleone', (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM attore WHERE nome = 'Marlon' AND cognome = 'Brando'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Michael Corleone', (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM attore WHERE nome = 'Al' AND cognome = 'Pacino'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Colonnello Walter E. Kurtz', (SELECT id FROM film WHERE titolo = 'Apocalypse Now'), (SELECT id FROM attore WHERE nome = 'Marlon' AND cognome = 'Brando'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Capitano Benjamin L. Willard', (SELECT id FROM film WHERE titolo = 'Apocalypse Now'), (SELECT id FROM attore WHERE nome = 'Martin' AND cognome = 'Sheen'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Marcello Rubini', (SELECT id FROM film WHERE titolo = 'La dolce vita'), (SELECT id FROM attore WHERE nome = 'Marcello' AND cognome = 'Mastroianni'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Sylvia', (SELECT id FROM film WHERE titolo = 'La dolce vita'), (SELECT id FROM attore WHERE nome = 'Anita' AND cognome = 'Ekberg'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Guido Anselmi', (SELECT id FROM film WHERE titolo = '8½'), (SELECT id FROM attore WHERE nome = 'Marcello' AND cognome = 'Mastroianni'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Claudia', (SELECT id FROM film WHERE titolo = '8½'), (SELECT id FROM attore WHERE nome = 'Claudia' AND cognome = 'Cardinale'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Il Biondo', (SELECT id FROM film WHERE titolo = 'Il buono, il brutto, il cattivo'), (SELECT id FROM attore WHERE nome = 'Clint' AND cognome = 'Eastwood'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Tuco', (SELECT id FROM film WHERE titolo = 'Il buono, il brutto, il cattivo'), (SELECT id FROM attore WHERE nome = 'Eli' AND cognome = 'Wallach'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('David "Noodles" Aaronson', (SELECT id FROM film WHERE titolo = 'C''era una volta in America'), (SELECT id FROM attore WHERE nome = 'Robert' AND cognome = 'De Niro'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Travis Bickle', (SELECT id FROM film WHERE titolo = 'Taxi Driver'), (SELECT id FROM attore WHERE nome = 'Robert' AND cognome = 'De Niro'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Iris', (SELECT id FROM film WHERE titolo = 'Taxi Driver'), (SELECT id FROM attore WHERE nome = 'Jodie' AND cognome = 'Foster'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Jordan Belfort', (SELECT id FROM film WHERE titolo = 'The Wolf of Wall Street'), (SELECT id FROM attore WHERE nome = 'Leonardo' AND cognome = 'DiCaprio'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Vincent Vega', (SELECT id FROM film WHERE titolo = 'Pulp Fiction'), (SELECT id FROM attore WHERE nome = 'John' AND cognome = 'Travolta'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Mia Wallace', (SELECT id FROM film WHERE titolo = 'Pulp Fiction'), (SELECT id FROM attore WHERE nome = 'Uma' AND cognome = 'Thurman'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Jules Winnfield', (SELECT id FROM film WHERE titolo = 'Pulp Fiction'), (SELECT id FROM attore WHERE nome = 'Samuel L.' AND cognome = 'Jackson'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Alfredo', (SELECT id FROM film WHERE titolo = 'Nuovo Cinema Paradiso'), (SELECT id FROM attore WHERE nome = 'Philippe' AND cognome = 'Noiret'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Salvatore "Totò" Di Vita (bambino)', (SELECT id FROM film WHERE titolo = 'Nuovo Cinema Paradiso'), (SELECT id FROM attore WHERE nome = 'Salvatore' AND cognome = 'Cascio'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Guido Orefice', (SELECT id FROM film WHERE titolo = 'La vita è bella'), (SELECT id FROM attore WHERE nome = 'Roberto' AND cognome = 'Benigni'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Dora', (SELECT id FROM film WHERE titolo = 'La vita è bella'), (SELECT id FROM attore WHERE nome = 'Nicoletta' AND cognome = 'Braschi'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Jep Gambardella', (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM attore WHERE nome = 'Toni' AND cognome = 'Servillo'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Kim Ki-taek', (SELECT id FROM film WHERE titolo = 'Parasite'), (SELECT id FROM attore WHERE nome = 'Kang-ho' AND cognome = 'Song'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Cleo', (SELECT id FROM film WHERE titolo = 'Roma'), (SELECT id FROM attore WHERE nome = 'Yalitza' AND cognome = 'Aparicio'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Władysław Szpilman', (SELECT id FROM film WHERE titolo = 'Il pianista'), (SELECT id FROM attore WHERE nome = 'Adrien' AND cognome = 'Brody'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Amélie Poulain', (SELECT id FROM film WHERE titolo = 'Il favoloso mondo di Amélie'), (SELECT id FROM attore WHERE nome = 'Audrey' AND cognome = 'Tautou'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Ada McGrath', (SELECT id FROM film WHERE titolo = 'Lezioni di piano'), (SELECT id FROM attore WHERE nome = 'Holly' AND cognome = 'Hunter'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('George Baines', (SELECT id FROM film WHERE titolo = 'Lezioni di piano'), (SELECT id FROM attore WHERE nome = 'Harvey' AND cognome = 'Keitel'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Dom Cobb', (SELECT id FROM film WHERE titolo = 'Inception'), (SELECT id FROM attore WHERE nome = 'Leonardo' AND cognome = 'DiCaprio'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Franco', (SELECT id FROM film WHERE titolo = 'Gomorra'), (SELECT id FROM attore WHERE nome = 'Toni' AND cognome = 'Servillo'));
INSERT INTO interpretazione (personaggio, film_id, attore_id) VALUES ('Yūsuke Kafuku', (SELECT id FROM film WHERE titolo = 'Drive My Car'), (SELECT id FROM attore WHERE nome = 'Hidetoshi' AND cognome = 'Nishijima'));

-- SALE
INSERT INTO sala (nome, indirizzo, capienza) VALUES ('Sala Grande', 'Lungomare Marconi 30, Venezia Lido', 1032);
INSERT INTO sala (nome, indirizzo, capienza) VALUES ('Sala Darsena', 'Lungomare Marconi 30, Venezia Lido', 1409);
INSERT INTO sala (nome, indirizzo, capienza) VALUES ('Sala Sinopoli', 'Viale Pietro de Coubertin 30, Roma', 1200);
INSERT INTO sala (nome, indirizzo, capienza) VALUES ('Sala Petrassi', 'Viale Pietro de Coubertin 30, Roma', 700);
INSERT INTO sala (nome, indirizzo, capienza) VALUES ('Cinema Lumière', 'Via Azzo Gardino 65, Bologna', 400);

-- FESTIVAL (date relative a oggi)
INSERT INTO festival (nome, anno, citta, data_inizio, data_fine, descrizione) VALUES ('Mostra Internazionale d''Arte Cinematografica', CAST(EXTRACT(YEAR FROM (CURRENT_DATE + (-50))) AS INTEGER), 'Venezia', CURRENT_DATE + (-50), CURRENT_DATE + (-40), 'Il più antico festival cinematografico del mondo, al Lido di Venezia.');
INSERT INTO festival (nome, anno, citta, data_inizio, data_fine, descrizione) VALUES ('Festa del Cinema di Roma', CAST(EXTRACT(YEAR FROM (CURRENT_DATE + (10))) AS INTEGER), 'Roma', CURRENT_DATE + (10), CURRENT_DATE + (20), 'Rassegna internazionale all''Auditorium Parco della Musica.');
INSERT INTO festival (nome, anno, citta, data_inizio, data_fine, descrizione) VALUES ('Festival de Cannes', CAST(EXTRACT(YEAR FROM (CURRENT_DATE + (30))) AS INTEGER), 'Cannes', CURRENT_DATE + (30), CURRENT_DATE + (40), 'Il festival della Croisette, con la Palma d''oro.');

-- FILM PARTECIPANTI AI FESTIVAL
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Il Padrino'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'La dolce vita'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = '8½'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Il buono, il brutto, il cattivo'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'La grande bellezza'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Roma'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Drive My Car'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Nuovo Cinema Paradiso'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'C''era una volta in America'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Il favoloso mondo di Amélie'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Inception'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'The Wolf of Wall Street'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Il Padrino'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'La grande bellezza'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Pulp Fiction'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Parasite'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Il pianista'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Lezioni di piano'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Taxi Driver'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Apocalypse Now'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'La vita è bella'));
INSERT INTO festival_film (festival_id, film_id) VALUES ((SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Gomorra'));

-- PROIEZIONI (due per film; nessuna sovrapposizione nella stessa sala)
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-50), '10:00', '12:18', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = '8½'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-49), '10:00', '12:18', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = '8½'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-48), '10:00', '12:59', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Drive My Car'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-47), '10:00', '12:59', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Drive My Car'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-46), '10:00', '12:55', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-45), '10:00', '12:55', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-44), '10:00', '12:58', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Il buono, il brutto, il cattivo'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-43), '10:00', '12:58', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Il buono, il brutto, il cattivo'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-42), '10:00', '12:54', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'La dolce vita'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-41), '10:00', '12:54', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'La dolce vita'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-40), '10:00', '12:21', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-50), '14:00', '16:21', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-49), '14:00', '16:15', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Roma'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (-48), '14:00', '16:15', 'COMPLETED', (SELECT id FROM festival WHERE nome = 'Mostra Internazionale d''Arte Cinematografica'), (SELECT id FROM film WHERE titolo = 'Roma'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (10), '10:00', '13:49', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'C''era una volta in America'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (11), '10:00', '13:49', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'C''era una volta in America'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (12), '10:00', '12:55', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (13), '10:00', '12:55', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (14), '10:00', '12:02', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Il favoloso mondo di Amélie'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (15), '10:00', '12:02', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Il favoloso mondo di Amélie'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (16), '10:00', '12:28', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Inception'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (17), '10:00', '12:28', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Inception'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (18), '10:00', '12:21', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (19), '10:00', '12:21', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (20), '10:00', '12:35', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Nuovo Cinema Paradiso'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (10), '14:00', '16:35', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'Nuovo Cinema Paradiso'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (11), '14:00', '17:00', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'The Wolf of Wall Street'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (12), '14:00', '17:00', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festa del Cinema di Roma'), (SELECT id FROM film WHERE titolo = 'The Wolf of Wall Street'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (30), '10:00', '12:27', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Apocalypse Now'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (31), '10:00', '12:27', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Apocalypse Now'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (32), '10:00', '12:17', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Gomorra'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (33), '10:00', '12:17', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Gomorra'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (34), '10:00', '12:30', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Il pianista'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (35), '10:00', '12:30', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Il pianista'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (36), '10:00', '11:56', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'La vita è bella'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (37), '10:00', '11:56', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'La vita è bella'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (38), '10:00', '12:01', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Lezioni di piano'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (39), '10:00', '12:01', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Lezioni di piano'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (40), '10:00', '12:12', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Parasite'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (30), '14:00', '16:12', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Parasite'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (31), '14:00', '16:34', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Pulp Fiction'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (32), '14:00', '16:34', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Pulp Fiction'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (33), '14:00', '15:54', 'SCHEDULED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Taxi Driver'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));
INSERT INTO proiezione (data, ora, ora_fine, stato, festival_id, film_id, sala_id) VALUES (CURRENT_DATE + (34), '14:00', '15:54', 'CANCELLED', (SELECT id FROM festival WHERE nome = 'Festival de Cannes'), (SELECT id FROM film WHERE titolo = 'Taxi Driver'), (SELECT id FROM sala WHERE nome = 'Sala Grande'));

-- RECENSIONI
INSERT INTO recensione (testo, voto, data, film_id, utente_id) VALUES ('Un capolavoro assoluto, Brando è monumentale.', 5, CURRENT_DATE - 45, (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM utente WHERE username = 'user1'));
INSERT INTO recensione (testo, voto, data, film_id, utente_id) VALUES ('Lungo ma ipnotico: ogni scena è memorabile.', 4, CURRENT_DATE - 44, (SELECT id FROM film WHERE titolo = 'Il Padrino'), (SELECT id FROM utente WHERE username = 'user2'));
INSERT INTO recensione (testo, voto, data, film_id, utente_id) VALUES ('Roma come non l''avevo mai vista, Servillo straordinario.', 5, CURRENT_DATE - 42, (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM utente WHERE username = 'user1'));
INSERT INTO recensione (testo, voto, data, film_id, utente_id) VALUES ('Bello ma a tratti troppo compiaciuto.', 3, CURRENT_DATE - 41, (SELECT id FROM film WHERE titolo = 'La grande bellezza'), (SELECT id FROM utente WHERE username = 'user2'));
INSERT INTO recensione (testo, voto, data, film_id, utente_id) VALUES ('Cambia genere ogni mezz''ora e funziona sempre.', 5, CURRENT_DATE - 5, (SELECT id FROM film WHERE titolo = 'Parasite'), (SELECT id FROM utente WHERE username = 'user2'));
INSERT INTO recensione (testo, voto, data, film_id, utente_id) VALUES ('Dialoghi indimenticabili, montaggio geniale.', 4, CURRENT_DATE - 3, (SELECT id FROM film WHERE titolo = 'Pulp Fiction'), (SELECT id FROM utente WHERE username = 'user1'));
