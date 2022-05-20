-- Main tables

CREATE TABLE IF NOT EXISTS users (
    id INTEGER AUTO INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS songs (
    id INTEGER AUTO INCREMENT,
    title VARCHAR(200),
    genre_id INTEGER,
    bpm FLOAT,
    bar_type_id INTEGER,
    user_id INTEGER,
    FOREIGN KEY(genre_id) REFERENCES genre(id),
    FOREIGN KEY(bar_type_id) REFERENCES bar_types(id)
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS artists (
    id INTEGER AUTO INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE
);

CREATE TABLE IF NOT EXISTS genres (
    id INTEGER AUTO INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS bar_types (
    id INTEGER AUTO INCREMENT,
    beat_count INTEGER(2),
    beat_value INTEGER(2)
);

-- Relation tables

CREATE TABLE IF NOT EXISTS rel_songs_artists (
    id INTEGER AUTO INCREMENT,
    song_id INTEGER,
    artist_id INTEGER,
    FOREIGN KEY(song_id) REFERENCES song(id),
    FOREIGN KEY(artist_id) REFERENCES artist(id)
);