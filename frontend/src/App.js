import Map from './components/Map';
import Header from './components/Header';
import React, { useState } from 'react';
import Prompt from "./components/TextRoutePrompt";

const apikey = 'B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE'

function App() {
    const [inputValue, setInputValue] = useState('');
    const handleInputChange = (newValue) => {
        // if (newValue === "#") {
        //     return;
        // }
        setInputValue(newValue);
    };

  return (
    <div style={{
      width: "100vw",
      height: "100vh"
    }}>
      <Header />
        <Prompt
            value={inputValue}
            onChange={handleInputChange}
        />
      <Map apikey={apikey} />
    </div>
  );
};

export default App;
