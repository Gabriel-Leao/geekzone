package com.geekzone.store.models;

public enum Category {
    GAMES("Games"),
    BOOKS("Books"),
    MUSIC_ALBUMS("Music Albums"),
    COMICS("Comics");

    private final String displayName;

    Category(String displayName) {
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
