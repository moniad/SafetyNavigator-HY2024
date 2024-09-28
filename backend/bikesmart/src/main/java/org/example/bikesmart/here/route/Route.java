package org.example.bikesmart.here.route;


import lombok.Data;
import java.util.List;

@Data
public class Route {
    private String id;
    private List<Section> sections;
}
