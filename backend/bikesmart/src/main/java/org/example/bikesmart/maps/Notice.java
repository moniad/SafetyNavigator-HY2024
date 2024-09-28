package org.example.bikesmart.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Notice {
    private String code;
    private String severity;
    private String title;
}