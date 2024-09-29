package org.example.bikesmart.geojsonParsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Properties {
    private String creator;
    private String name;
    @JsonProperty("track-length")
    private String trackLength;
    @JsonProperty("filtered ascend")
    private String filteredAscend;
    @JsonProperty("plain-ascend")
    private String plainAscend;
    @JsonProperty("total-time")
    private String totalTime;
    @JsonProperty("total-energy")
    private String totalEnergy;
    private String cost;
    private String safety;
    private List<List<String>> messages;
    private List<Double> times;

    // Gettery i settery
}
