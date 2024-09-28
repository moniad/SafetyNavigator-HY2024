function process(geoJson) {
    // each subarray is of form [lng, lat, alt], process them to contain incline
    let processed = [];
    let prev = null;
    for (let i = 0; i < geoJson.features[0].geometry.coordinates.length; i++) {
        let [lng, lat, alt] = geoJson.features[0].geometry.coordinates[i];
        if (prev !== null) {
            let [prevLng, prevLat, prevAlt] = prev;
            let incline = (alt - prevAlt) / Math.sqrt((lng - prevLng) ** 2 + (lat - prevLat) ** 2);
            processed.push(
                {
                    incline: incline,
                    from: [prevLat, prevLng],
                    to: [lat, lng],
                }
            );
        }
        prev = [lng, lat, alt];
    }

    return processed;
}

module.exports = process;