import Map from './components/Map';
import Logo from './components/Logo';
import React from 'react';
import InputFieldWithButton from "./components/InputFieldWithButton";

const apikey = 'B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE'

const App = () => {
    return (
        <div style={styles.wrapper}>
            <div style={styles.contentContainer}>
                <Logo/>
                <InputFieldWithButton/>
            </div>
            <Map apikey={apikey}/>
        </div>
    );
};

const styles = {
    wrapper: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        width: "100vw",
        height: "100vh",
        flexDirection: 'column', // Align header and input vertically
    },
    contentContainer: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        width: '80%', // Center content on the page, adjust this width as needed
    },
};

export default App;
