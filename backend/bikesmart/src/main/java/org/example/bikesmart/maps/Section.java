package org.example.bikesmart.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Section {
    private String id;
    private Place arrival;
    private Place departure;
    private List<PostAction> postActions;
    private Summary summary;
    private Transport transport;
    private Summary travelSummary;
    private String type;
    private List<Notice> notices;
}
