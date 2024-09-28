package org.example.bikesmart.geojsonParsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrouterController {
    @Autowired
    private BRouterService bRouterService;

    @GetMapping("/getData")
    public GeoJson fetchData() {
        return bRouterService.getData();
    }
}
