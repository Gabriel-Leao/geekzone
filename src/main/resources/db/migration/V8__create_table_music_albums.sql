CREATE TABLE music_albums (
    id char(36) PRIMARY KEY,
    product_id char(36) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    record_label VARCHAR(255) NOT NULL,
    tracklist TEXT NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
