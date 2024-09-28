package org.example.bikesmart.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Scoring {
    private double queryScore;
    private FieldScore fieldScore;
}
