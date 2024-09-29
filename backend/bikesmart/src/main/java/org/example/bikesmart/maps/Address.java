package org.example.bikesmart.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Address {
    private String label;
    private String countryCode;
    private String countryName;
    private String stateCode;
    private String state;
    private String county;
    private String countyCode;
    private String district;
    private String houseNumber;
    private String city;
    private String street;
    private String postalCode;
}
