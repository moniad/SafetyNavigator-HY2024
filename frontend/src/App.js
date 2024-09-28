import Map from './components/Map';
import Logo from './components/Logo';
import React from 'react';
import InputFieldWithButton from "./components/InputFieldWithButton";
import UserLogo from "./components/UserLogo";

const apikey = 'B1i2fhMekYEaFkkPoVJjErKYaquAglsTyib4of2WPfE'

const App = () => {
    return (
        <div style={styles.wrapper}>
            <div style={styles.contentContainer}>
                <Logo/>
                <InputFieldWithButton/>
                <UserLogo/>
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
