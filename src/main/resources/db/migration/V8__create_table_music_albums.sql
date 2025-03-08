CREATE TABLE music_albums (
    id char(36) PRIMARY KEY,
    product_id char(36) REFERENCES products(id) ON DELETE CASCADE,
    artist VARCHAR(255) NOT NULL,
    record_label VARCHAR(255) NOT NULL,
    tracklist TEXT NOT NULL
);
