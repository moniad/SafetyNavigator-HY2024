package org.example.bikesmart.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class MapView {
    private double west;
    private double south;
    private double east;
    private double north;
}
