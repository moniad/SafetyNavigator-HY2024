import Map from './components/Map';
import Header from './components/Header';
import React from 'react';
import InputFieldWithButton from "./components/InputFieldWithButton";

const apikey = 'B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE'

function App() {
    return (
        <div style={{
            width: "100vw",
            height: "100vh"
        }}>
            <Header/>
            <InputFieldWithButton/>
            <Map apikey={apikey}/>
        </div>
    );
};

export default App;
