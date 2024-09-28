package org.example.bikesmart.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class GeoSearch {
    private String title;
    private String id;
    private String resultType;
    private String localityType;
    private AddressGeoSearch address;
    private Location position;
    private int distance;
    private MapView mapView;
    private Scoring scoring;
}
