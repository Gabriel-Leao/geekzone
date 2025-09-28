package com.geekzone.store.models;

public enum Categories {
    GAMES("Games"),
    BOOKS("Books"),
    MUSIC_ALBUMS("Music Albums"),
    COMICS("Comics");

    private final String displayName;

    Categories(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
