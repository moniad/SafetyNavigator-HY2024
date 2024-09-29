import React from 'react';

const UserLogo = () => {
    return (
        <div style={styles.logoContainer}>
            <img src="/User.png" alt="User Logo" style={styles.logo} />
        </div>
    );
};

const styles = {
    logoContainer: {
        display: 'flex',
        alignItems: 'center',
        paddingLeft: '100px',
    },
    logo: {
        width: '120px', // Set the width to make the image smaller
    },
};

export default UserLogo;