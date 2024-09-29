package org.example.bikesmart.ai.model;

public enum Language {

    ENGLISH("English"),
    POLISH("Polski"),
    GERMAN("Deutsch"),
    SPANISH("Español"),
    CHINESE("中文"),
    FRENCH("Français");

    private final String name;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
