import React, {
    useEffect,
    useRef
} from 'react';
import H from '@here/maps-api-for-javascript';
import inclineLayerProcess from '../utils/inclineLayer.js';
import mapLayers from '../utils/mapLayers';

const Map = ({
    apikey,
    geoJson,
    mapLayer,
    setMapLayer
}) => {
    const mapRef = useRef(null);
    const map = useRef(null);
    const platform = useRef(null);

    useEffect(() => {
        // Check if the map object has already been created
        if (!map.current) {
            // Create a platform object with the API key and useCIT option
            platform.current = new H.service.Platform({
                apikey
            });
            // Obtain the default map types from the platform object:
            const defaultLayers = platform.current.createDefaultLayers({
                pois: true
            });
            // Create a new map instance with the Tile layer, center and zoom level
            // Instantiate (and display) a map:
            const newMap = new H.Map(
                mapRef.current,
                defaultLayers.vector.normal.map, {
                    zoom: 14,
                    center: {
                        lat: 50.061458,
                        lng: 19.936683,
                    },
                }
            );

            // Add panning and zooming behavior to the map
            const behavior = new H.mapevents.Behavior(
                new H.mapevents.MapEvents(newMap)
            );

            const ui = new H.ui.UI(newMap);
            const layerControll = new H.ui.Control();
            const layerControllElement = new H.ui.base.Element();

            layerControllElement.renderInternal = (ele) => {
                ele.innerHTML = `
                <div style="background-color: white; padding: 5px; border-radius: 5px; box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); cursor: pointer;">
                    Switch layer
                </div>
                `;
                ele.addEventListener('click', () => {
                    setMapLayer(pre => {
                        if (pre === mapLayers.basic) {
                            return mapLayers.incline;
                        } else if (pre === mapLayers.incline) {
                            return mapLayers.basic;
                        }
                    })
                });
                return ele;
            };
            layerControll.addChild(layerControllElement);
            ui.addControl(
                "layer",
                layerControll
            );

            // Set the map object to the reference
            map.current = newMap;
        }
    }, [apikey]);

    useEffect(() => {
        if (geoJson && Object.keys(geoJson).length > 0) {

            if (mapLayer === mapLayers.incline) {
                const coordinates = inclineLayerProcess(geoJson);
                const maxIncline = Math.max(...coordinates.map(({
                    incline
                }) => Math.abs(incline)));
                coordinates.forEach(({
                    from,
                    to,
                    incline
                }) => {
                    const lineString = new H.geo.LineString();
                    lineString.pushPoint(new H.geo.Point(from[0], from[1]));
                    lineString.pushPoint(new H.geo.Point(to[0], to[1]));

                    const getColorForIncline = (incline) => {
                        const colors = [
                            'darkblue', // -20
                            'blue',     // -10
                            'green',    // 0
                            'yellow',   // 10
                            'red'       // 20
                        ];
                        const index = Math.round((incline + maxIncline) / maxIncline / 2);
                        return colors[Math.max(0, Math.min(colors.length - 1, index))];
                    };

                    const polyline = new H.map.Polyline(lineString, {
                        style: {
                            lineWidth: 5,
                            strokeColor: getColorForIncline(incline)
                        }
                    });

                    map.current.addObject(polyline);
                });

            } else if (mapLayer === mapLayers.basic) {
                const coordinatesReversed = geoJson.features[0].geometry.coordinates.map(([lng, lat, _]) => [lat, lng]);
                const lineString = H.geo.LineString.fromLatLngArray(coordinatesReversed.flat());
                const multiLineString = new H.geo.MultiLineString([lineString]);

                const routeLine = new H.map.Polyline(multiLineString, {
                    style: {
                        strokeColor: 'violet',
                        lineWidth: 5
                    }
                });
    
                map.current.addObject(routeLine);
            }
    
        }
    }, [geoJson, mapLayer]);

    // Return a div element to hold the map
    return < div style = {
        {
            width: "100%",
            height: "calc(100% - 72px)",
            boxSizing: "border-box"
        }
    }
    ref = {
        mapRef
    }
    />;
}

export default Map;
