import Map from './components/Map';
import React, { useState } from 'react';

const apikey = 'B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE'

function App() {
  return (
    <div>
      <Map apikey={apikey} />
    </div>
  );
};

export default App;
