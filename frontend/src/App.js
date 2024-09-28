import Map from './components/Map';
import Logo from './components/Logo';
import React, { useState } from 'react';
import InputFieldWithButton from "./components/InputFieldWithButton";
import UserLogo from "./components/UserLogo";
import mapLayers from './utils/mapLayers';

const apikey = 'B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE'

const tmpgeojson = {
  type: "FeatureCollection",
  features: [
    {
      type: "Feature",
      properties: {
        creator: "BRouter-1.7.7",
        name: "brouter_trekking_0",
        cost: "4067",
        messages: [
          ["Longitude", "Latitude", "Elevation", "Distance", "CostPerKm", "ElevCost", "TurnCost", "NodeCost", "InitialCost", "WayTags", "NodeTags", "Time", "Energy"],
          ["19936708", "50061084", "218", "42", "3050", "0", "8", "0", "0", "reversedirection=yes highway=pedestrian surface=sett bicycle=yes smoothness=excellent", "", "7", "710"],
          ["19936581", "50060941", "218", "18", "3050", "0", "11", "0", "0", "highway=pedestrian surface=paving_stones bicycle=yes smoothness=good", "", "9", "994"],
          ["19936251", "50060220", "215", "85", "1150", "0", "2", "0", "0", "highway=living_street surface=sett bicycle=yes", "", "20", "2029"],
          ["19936228", "50059532", "212", "77", "1150", "0", "0", "0", "0", "highway=living_street surface=sett bicycle=yes cycleway=opposite oneway=yes oneway:bicycle=no", "highway=crossing crossing=unmarked", "30", "3013"],
          ["19936222", "50059470", "212", "7", "1150", "0", "0", "0", "0", "highway=living_street surface=sett bicycle=yes cycleway=opposite oneway=yes oneway:bicycle=no", "railway=unknown", "31", "3115"],
          ["19936221", "50059456", "212", "2", "1150", "0", "0", "0", "0", "highway=living_street surface=sett bicycle=yes cycleway=opposite oneway=yes oneway:bicycle=no", "", "31", "3144"],
          ["19934884", "50059375", "211", "96", "1150", "0", "92", "0", "0", "reversedirection=yes highway=residential surface=asphalt", "", "44", "4414"],
          ["19934799", "50059372", "211", "6", "1150", "0", "0", "0", "0", "highway=residential surface=asphalt oneway=yes", "railway=unknown", "44", "4492"],
          ["19934536", "50059344", "210", "19", "1150", "0", "4", "0", "0", "highway=residential surface=asphalt oneway=yes", "highway=crossing bicycle=yes crossing=uncontrolled", "47", "4717"],
          ["19934544", "50059313", "210", "3", "1050", "0", "73", "0", "0", "reversedirection=yes highway=path surface=asphalt foot=designated bicycle=designated", "", "47", "4752"],
          ["19934551", "50059288", "210", "3", "1050", "0", "0", "0", "0", "reversedirection=yes highway=path surface=paved foot=designated bicycle=designated", "railway=unknown", "47", "4788"],
          ["19934558", "50059259", "210", "3", "1050", "0", "0", "0", "0", "reversedirection=yes highway=path surface=asphalt foot=designated bicycle=designated", "railway=unknown", "48", "4822"],
          ["19934563", "50059237", "210", "2", "1050", "0", "0", "0", "0", "reversedirection=yes highway=path surface=asphalt foot=designated bicycle=designated", "highway=crossing bicycle=yes crossing=uncontrolled", "48", "4845"],
          ["19934568", "50059218", "210", "2", "1050", "0", "0", "0", "0", "reversedirection=yes highway=path surface=asphalt foot=designated bicycle=designated", "", "48", "4869"],
          ["19935055", "50057858", "208", "156", "1050", "0", "0", "100", "0", "reversedirection=yes highway=footway surface=asphalt bicycle=yes", "highway=crossing bicycle=no crossing=uncontrolled", "67", "6743"],
          ["19935853", "50055578", "215", "260", "1050", "0", "0", "0", "0", "reversedirection=yes highway=footway surface=asphalt bicycle=yes", "", "109", "10975"],
          ["19935879", "50055508", "216", "8", "1050", "0", "0", "0", "0", "highway=cycleway surface=asphalt", "", "112", "11212"],
          ["19936536", "50055454", "219", "48", "1150", "0", "67", "0", "0", "reversedirection=yes highway=living_street surface=asphalt foot=yes bicycle=yes oneway=no oneway:bicycle=no smoothness=good", "", "129", "12984"],
          ["19936518", "50055403", "220", "6", "7050", "0", "91", "0", "0", "highway=pedestrian surface=sett bicycle=no", "", "132", "13252"],
          ["19935162", "50055048", "220", "107", "7050", "0", "41", "100", "0", "highway=pedestrian surface=sett bicycle=no smoothness=intermediate", "barrier=sally_port bicycle=no", "169", "16915"],
          ["19934812", "50054801", "222", "42", "7050", "0", "15", "100", "0", "highway=pedestrian surface=sett bicycle=no", "barrier=sally_port foot=yes bicycle=no", "180", "18021"],
          ["19934831", "50054737", "223", "7", "44000", "0", "2", "0", "0", "highway=steps surface=sett bicycle=no", "", "182", "18303"],
          ["19934833", "50054732", "223", "1", "7050", "0", "0", "0", "0", "highway=pedestrian surface=sett bicycle=no", "", "183", "18344"],
          ["19934858", "50054736", "224", "2", "5050", "0", "89", "0", "0", "highway=footway surface=sett", "", "184", "18430"],
          ["19934881", "50054740", "224", "3", "40000", "0", "2", "0", "0", "highway=steps surface=paving_stones", "barrier=gate", "185", "18561"],
          ["19934892", "50054742", "224", "1", "40000", "0", "2", "0", "0", "highway=steps surface=paving_stones", "", "186", "18605"],
          ["19935002", "50054764", "224", "8", "5050", "0", "4", "0", "0", "highway=footway", "", "189", "18976"],
          ["19935025", "50054766", "224", "2", "40000", "0", "7", "0", "0", "highway=steps", "", "190", "19066"],
          ["19935285", "50054796", "225", "20", "5050", "0", "175", "0", "0", "highway=footway", "", "199", "19908"],
          ["19935609", "50054839", "225", "26", "5050", "0", "44", "0", "0", "reversedirection=yes highway=footway", "", "208", "20835"],
          ["19935633", "50054841", "225", "2", "40000", "0", "0", "0", "0", "reversedirection=yes highway=steps", "", "209", "20904"]
        ],
        times: [0, 1.077, 7.105, 9.944, 14.91, 17.175, 20.292, 23.615, 24.361, 30.131, 31.151, 31.445, 32.98, 34.73, 39.067, 41.226, 44.145, 44.92, 46.35, 47.166, 47.522, 47.88, 48.224, 48.455, 48.688, 49.276, 50.356, 56.558, 66.486, 66.888, 67.428, 67.972, 68.383, 83.601, 87.858, 100.357, 109.738, 112.105, 116.517, 121.365, 129.824, 132.501, 135.309, 138.115, 169.12, 172.09, 174.315, 175.482, 178.139, 180.18, 181.736, 182.998, 183.412, 184.274, 184.7, 185.144, 185.585, 186.022, 188.826, 189.732, 190.626, 195.721, 196.147, 199.045, 200.183, 202.393, 203.407, 205.496, 206.925, 208.318, 209.001]
      },
      geometry: {
        type: "LineString",
        coordinates: [
          [19.936683, 50.061458, 218.25],
          [19.936719, 50.061405, 218.5],
          [19.936708, 50.061084, 218.25],
          [19.936581, 50.060941, 218.0],
          [19.936355, 50.060630, 217.0],
          [19.936290, 50.060467, 216.25],
          [19.936251, 50.060220, 215.5],
          [19.936223, 50.059937, 214.5],
          [19.936216, 50.059870, 214.25],
          [19.936228, 50.059532, 212.75],
          [19.936222, 50.059470, 212.5],
          [19.936221, 50.059456, 212.5],
          [19.936069, 50.059462, 212.25],
          [19.935883, 50.059452, 212.0],
          [19.935413, 50.059417, 211.5],
          [19.935190, 50.059401, 211.5],
          [19.934884, 50.059375, 211.25],
          [19.934799, 50.059372, 211.0],
          [19.934630, 50.059366, 210.5],
          [19.934536, 50.059344, 210.25],
          [19.934544, 50.059313, 210.25],
          [19.934551, 50.059288, 210.25],
          [19.934558, 50.059259, 210.0],
          [19.934563, 50.059237, 210.0],
          [19.934568, 50.059218, 210.0],
          [19.934582, 50.059178, 210.0],
          [19.934618, 50.059098, 210.0],
          [19.934774, 50.058645, 209.5],
          [19.935030, 50.057915, 208.5],
          [19.935040, 50.057892, 208.5],
          [19.935055, 50.057858, 208.5],
          [19.935066, 50.057823, 208.5],
          [19.935081, 50.057794, 208.5],
          [19.935449, 50.056736, 210.25],
          [19.935528, 50.056509, 210.75],
          [19.935727, 50.055939, 211.5],
          [19.935853, 50.055578, 215.5],
          [19.935879, 50.055508, 216.75],
          [19.936068, 50.055505, 217.25],
          [19.936265, 50.055491, 218.0],
          [19.936536, 50.055454, 219.5],
          [19.936518, 50.055403, 220.25],
          [19.936500, 50.055353, 220.75],
          [19.936416, 50.055334, 221.0],
          [19.935162, 50.055048, 220.75],
          [19.935005, 50.055013, 220.75],
          [19.934888, 50.054965, 220.5],
          [19.934852, 50.054928, 220.75],
          [19.934814, 50.054853, 221.75],
          [19.934812, 50.054801, 222.5],
          [19.934823, 50.054766, 223.25],
          [19.934831, 50.054737, 223.75],
          [19.934833, 50.054732, 223.75],
          [19.934858, 50.054736, 224.0],
          [19.934867, 50.054737, 224.0],
          [19.934878, 50.054739, 224.25],
          [19.934881, 50.054740, 224.25],
          [19.934892, 50.054742, 224.25],
          [19.934978, 50.054755, 224.75],
          [19.935002, 50.054764, 224.75],
          [19.935025, 50.054766, 224.75],
          [19.935185, 50.054794, 224.75],
          [19.935189, 50.054784, 225.0],
          [19.935285, 50.054796, 225.0],
          [19.935317, 50.054809, 224.75],
          [19.935400, 50.054825, 224.75],
          [19.935429, 50.054840, 224.5],
          [19.935506, 50.054848, 224.75],
          [19.935554, 50.054837, 225.0],
          [19.935609, 50.054839, 225.0],
          [19.935633, 50.054841, 225.0]
        ]
      }
    }
  ]
}

function App() {

  const [mapLayer, setMapLayer] = useState(mapLayers.basic);

  return (
    <div style={styles.wrapper}>
      <div style={styles.contentContainer}>
        <Logo />
        <InputFieldWithButton />
        <UserLogo />
      </div>
      <Map
        apikey={apikey}
        geoJson={tmpgeojson}
        mapLayer={mapLayer}
        setMapLayer={setMapLayer}
      />
    </div>
  );
}

const styles = {
  wrapper: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '100vw',
    height: '100vh',
    flexDirection: 'column',
  },
  contentContainer: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '95%', // Adjust width based on your preference
  },
};

export default App;
