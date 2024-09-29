package org.example.bikesmart.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ReverseGeoSearch {
    private String title;
    private String id;
    private String resultType;
    private Address address;
    private Location position;
    private int distance;
    private MapView mapView;
    private String houseNumberType;
}
