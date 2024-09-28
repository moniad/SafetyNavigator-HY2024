package org.example.bikesmart.geojsonParsers;

import lombok.Data;

@Data
public class Feature {
    private String type;
    private Properties properties;
    private Geometry geometry;

    // Gettery i settery
}

