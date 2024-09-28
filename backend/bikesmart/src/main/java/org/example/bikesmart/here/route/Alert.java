package org.example.bikesmart.here.route;

import lombok.Data;

@Data
public class Alert {
    private String type;
    private String description;
    private int distance;
}

