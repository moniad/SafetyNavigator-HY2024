package org.example.bikesmart.ai.model;

public enum Score {
    VERY_LOW("Very low"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    VERY_HIGH("Very high");

    private final String name;

    Score(String name) {
        this.name = name.toLowerCase(); // Store in lowercase for easier comparison
    }

    public String getName() {
        return name;
    }
}
