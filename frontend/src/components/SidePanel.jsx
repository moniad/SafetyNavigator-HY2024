import React from 'react';
import { ReactComponent as Icon1 } from '../icons/1.svg';
import { ReactComponent as Icon2 } from '../icons/2.svg';
import { ReactComponent as Icon3 } from '../icons/3.svg';
import { ReactComponent as Icon4 } from '../icons/4.svg';

function PathDetails({ json, idx, setSelectedJsonIdx, isSelected = false }) {
    const properties = json.features[0].properties;

    let incline = 0;
    let decline = 0;
    let prevMessage = null;
    properties.messages.slice(1).forEach((message) => {
        if (prevMessage) {
            const diff = message[2] - prevMessage[2];
            if (isNaN(diff)) {
                console.log('diff is NaN', message, prevMessage);
            }
            if (diff > 0) {
                incline += diff;
            } else {
                decline -= diff;
            }
        }
        prevMessage = message;
    });

    const data = [
        [`${properties["track-length"] / 1000} km`, <Icon1 />],
        [`${properties["total-time"]} min`, <Icon2 />],
        [`${decline} m`, <Icon3 />],
        [`${incline} m`, <Icon4 />],
    ];
    const score = properties["safety-score"];
    const scoreColor = score > 80 ? 'green' :
                       score > 50 ? 'blue' :
                       score > 20 ? 'orange' : 'red';
    return (
        <div style={{
            ...styles.infoRow,
            backgroundColor: isSelected ? 'lightblue' : 'transparent',
        }}>
            <div style={{ padding: "1rem 0" }}><strong>{json.features[0].properties.name}</strong></div>
            <div style={styles.infoBar}>
                {data.map(([value, icon], idx2) => (
                    <div style={{
                        ...styles.infoBox,
                        borderLeft: idx2 > 0 ? '1px solid black' : 'none',
                    }}>
                        {value}
                        {icon}
                    </div>
                ))}
            </div>
            <div style={styles.safetyWrapper}><strong>Bezpieczeństwo:</strong><div style={{
                ...styles.safety,
                backgroundColor: scoreColor,
            }}>{score}</div></div>
            <div><strong>Opis trasy</strong></div>
            <div style={{ textAlign: 'right' }}>
                <button style={styles.selectOptionButton} onClick={() => setSelectedJsonIdx(idx)}>Wybierz</button>
            </div>
        </div>
    )
}

function SidePanel({ jsonArray, selectedJsonIdx, setSelectedJsonIdx }) {
    return (
        <div style={styles.sidePanel}>
            <h1 style={{ marginBottom: "1rem", paddingLeft: "1rem" }}>Side Panel</h1>
            {jsonArray.map((json, idx) => <PathDetails key={idx} json={json} isSelected={idx === selectedJsonIdx} idx={idx} setSelectedJsonIdx={setSelectedJsonIdx} />)}
        </div>
    );
}

const styles = {
    sidePanel: {
      width: '30%',
      height: '100%',
      overflowY: 'scroll',
    },
    infoRow: {
      padding: "0.5rem 1rem",
      borderBottom: '1px solid black',
    },
    infoBar: {
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      width: '100%',
    },
    infoBox: {
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      flexDirection: 'column',
      width: '100px',
      whiteSpace: 'nowrap',
    },
    safetyWrapper: {
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      flexDirection: 'row',
      justifyContent: 'space-between',
      width: '100%',
      padding: '1rem 0',
    },
    safety: {
      backgroundColor: 'green',
      width: '40px',
      height: '40px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      borderRadius: '50%',
      color: 'white',
    },
    selectOptionButton: {
      padding: '0.5rem 1rem',
      backgroundColor: 'transparent',
      color: '#6770E5',
      border: 'none',
      cursor: 'pointer',
    },
};

export default SidePanel;