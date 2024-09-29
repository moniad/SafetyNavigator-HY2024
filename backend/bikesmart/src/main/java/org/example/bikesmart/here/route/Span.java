package org.example.bikesmart.here.route;

import lombok.Data;

import java.util.List;

@Data
public class Span {
    private Double maxSpeed;
    private List<CarAttributes> carAttributes;
    private List<TruckAttributes> truckAttributes;
    private List<WalkAttributes> walkAttributes;
    private Integer functionalClass;
    private DynamicSpeedInfo dynamicSpeedInfo;
}
