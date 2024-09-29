package org.example.bikesmart.ai.logic;

import lombok.Data;
import org.example.bikesmart.maps.Location;

import java.util.List;

@Data
public class RequestFromFrontend {
    private String message;
    private List<Location> locations;
}
