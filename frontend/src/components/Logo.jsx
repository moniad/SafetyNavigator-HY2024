import React from 'react';

const Logo = () => {
    return (
        <div style={styles.logoContainer}>
            <div>
                <img src="/SmartBikeLogo.png" alt="Logo" style={styles.logo}/>
                <h5>Safety Navigator</h5>
            </div>
        </div>
    );
};

const styles = {
    logoContainer: {
        flex: 1, // Takes all the space on the left
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100%', // Automatically fill height
        paddingRight: '10px', // Space between the logo and text field
    },
    logo: {
        maxWidth: '100%', // Ensure logo fills the width of the container
        height: 'auto', // Keep the aspect ratio of the logo
        objectFit: 'contain', // Make sure the logo fits inside the container without distortion
    }
};

export default Logo;