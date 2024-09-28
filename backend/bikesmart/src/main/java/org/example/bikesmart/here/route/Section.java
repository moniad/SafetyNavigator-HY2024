package org.example.bikesmart.here.route;

import lombok.Data;
import java.util.List;

@Data
public class Section {
    private String id;
    private Departure departure;
    private Arrival arrival;
    private Summary summary;
    private String polyline;
    private Transport transport;
    private List<Alert> alerts;
    private List<Surface> surface;
    private Elevation elevation;
    private BikeFacilities bikeFacilities;
}

