package org.example.bikesmart.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class FieldScore {
    private double country;
    private String city;
    private List<Double> streets;
    private double postalCode;
}
