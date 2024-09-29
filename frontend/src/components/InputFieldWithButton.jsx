import React, { useState, useEffect } from 'react';

const InputFieldWithButton = ({ setJsonArray, mapCenter, apikey }) => {
    const [inputValue, setInputValue] = useState('');
    const [isDropdownVisible, setDropdownVisible] = useState(false);
    const [lastHashtag, setLastHashtag] = useState('');
    const [suggestions, setSuggestions] = useState([]);
    const [initialDropdownVisible, setInitialDropdownVisible] = useState(true); // For showing the initial dropdown
    const [promptData, setPromptData] = useState([]);

    // Function to detect the last hashtag and trigger API call
    const detectLastHashtag = (text) => {
        const regex = /#(\w+)/g; // Detects hashtags followed by words
        const matches = [...text.matchAll(regex)];
        if (matches.length > 0) {
            const lastMatch = matches[matches.length - 1][1]; // Gets the last hashtag word
            setLastHashtag(lastMatch); // Set the last detected hashtag word
        }
    };

    // Function to simulate API call for the last hashtag word
    const callApiForHashtag = (hashtag) => {
        fetch(
            `https://autosuggest.search.hereapi.com/v1/autosuggest?at=${mapCenter.lat},${mapCenter.lng}&lang=pl&q=${hashtag}&apiKey=${apikey}`
        )
            .then((response) => response.json())
            .then((data) => {
                setSuggestions(data.items.map((item) => item));
            });
    };

    // Effect hook to call the API for the last detected hashtag
    useEffect(() => {
        if (lastHashtag) {
            callApiForHashtag(lastHashtag);
        }
    }, [lastHashtag]);

    // Handle changes in the input field
    const handleInputChange = (event) => {
        const newValue = event.target.value;
        setInputValue(newValue);

        // Show the initial dropdown if the input is empty, otherwise hide it
        if (newValue.trim() === '') {
            setInitialDropdownVisible(true);  // Show initial dropdown when input is empty
            setSuggestions([]); // Clear suggestions when input is empty
        } else {
            setInitialDropdownVisible(false); // Hide initial dropdown when input has content
            detectLastHashtag(newValue); // Detect hashtags on input change
        }
    };

    // Show dropdown when input is focused
    const handleInputFocus = () => {
        setDropdownVisible(true);
    };

    const handleClearClick = () => {
        setInputValue('');
        setDropdownVisible(false); // Hide dropdown on clear
        setSuggestions([]); // Clear suggestions
        setInitialDropdownVisible(true); // Reset initial dropdown visibility on clear
        setPromptData([]); // Clear prompt data
    };

    const handleSend = () => {
        const url = "http://localhost:8081/ai";
        // no cors
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                message: inputValue,
                locations: promptData,
            }),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setJsonArray([data.routingResponse]);
                handleClearClick();
            });
    }

    const promptPropositions = [
        "Wyznacz najbardziej komfortową i bezpieczną trasę do <b>pkt na mapie</b>, preferując drogi o <b>małym ruchu samochodowym</b>, <b>małym hałasie</b>, <b>wśród zieleni</b>.",
        "Wyznacz trasę do <b>50 km</b> dla <b>rodziny z dziećmi</b> w odległości do <b>2 godz. drogi samochodem</b> z miejscem na <b>piknik</b>, <b>atrakcjami dla dzieci</b> i <b>miejscem parkingu</b>.",
        "Wyznacz trasę w formie pętli o długości <b>80-100 km</b> dla <b>roweru szosowego</b> na <b>północy Krakowa</b> przez <b>pkt A</b> oraz <b>pkt B</b>.",
        "Wyznacz trasę do <b>30 km</b> dla <b>roweru górskiego</b> przez <b>Gorce</b>, przewyższenie <b>poniżej 2000 m</b>, <b>drogami szutrowymi</b>, <b>leśnymi</b>, jazdy <b>ścieżkami typu single track</b>, nachylenie <b>poniżej 10%</b>.",
        "Zaproponuj trasę na <b>4-dniową</b> wyprawę rowerową po <b>Małopolsce</b> z dziennym limitem <b>70 km</b>, uwzględniając <b>atrakcje krajoznawcze</b>, preferuj trasy <b>ścieżkami rowerowymi</b>, <b>szutrowymi</b>, <b>lasem</b>, <b>wzdłuż rzek</b>. Zaplanuj nocleg na <b>polach namiotowych</b> z dobrymi opiniami.",
        "Wyznacz trasę do <b>100 km</b> z <b>Oświęcimia</b>, fragmentem <b>Wiślanej Trasy Rowerowej</b>, uwzględnij powrót <b>pociągiem</b> i drogę na stację, wyświetl godziny odjazdu i koszt biletu z rowerem w weekend.",
    ]

    return (
        <div style={styles.container}>
            <input
                style={styles.input}
                type="text"
                placeholder="Opisz swoją trasę, a my zaplanujemy najlepszą drogę z pomocą AI"
                value={inputValue}
                onChange={handleInputChange}
                onFocus={handleInputFocus} // Show dropdown when input is focused
            />
            <button style={styles.clearButton} onClick={handleClearClick}>
                ✖️
            </button>
            <button style={styles.microphoneButton}>
                🎤
            </button>
            <div style={styles.separator}></div>
            <button style={styles.goButton} onClick={handleSend}>
                GO
            </button>

            {/* Show initial dropdown if no hashtag detected and suggestions are empty */}
            {isDropdownVisible && initialDropdownVisible && suggestions.length === 0 && (
                <div style={styles.dropdown}>
                    {promptPropositions.map((prompt, index) => (
                        <div
                            key={index}
                            style={styles.dropdownItem}
                            dangerouslySetInnerHTML={{ __html: prompt }}
                            onClick={() => {
                                setInputValue(prompt.replace(/<b>/g, '').replace(/<\/b>/g, ''));
                                setDropdownVisible(false);
                                setSuggestions([]);
                                setInitialDropdownVisible(true);
                                setPromptData([]); // Clear prompt data
                            }}
                        ></div>
                    ))}
                </div>
            )}

            {/* Show suggestions from the API based on last hashtag */}
            {isDropdownVisible && suggestions.length > 0 && (
                <div style={styles.dropdown}>
                    {suggestions.map((suggestion, index) => (
                        <div
                            key={index}
                            style={styles.dropdownItem}
                            onClick={() => {
                                setInputValue(inputValue.replace(`#${lastHashtag}`, suggestion.title));
                                setDropdownVisible(false);
                                setSuggestions([]);
                                setInitialDropdownVisible(true);
                                setPromptData((pre) => [...pre, suggestion.position]);
                            }}
                        >
                            {suggestion.title}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

const styles = {
    container: {
        display: 'flex',
        alignItems: 'center',
        width: '90%',
        maxWidth: '800px',
        margin: '20px auto',
        border: '1px solid #b9b9ff',
        borderRadius: '50px',
        padding: '5px',
        boxShadow: '0px 0px 5px rgba(0, 0, 0, 0.1)',
        position: 'relative', // Needed for dropdown positioning
    },
    input: {
        flex: 1,
        border: 'none',
        outline: 'none',
        borderRadius: '50px',
        padding: '10px 20px',
        fontSize: '14px',
        color: '#333',
    },
    clearButton: {
        border: 'none',
        background: 'none',
        fontSize: '18px',
        cursor: 'pointer',
        marginRight: '10px',
        color: '#b9b9b9',
    },
    microphoneButton: {
        border: 'none',
        background: 'none',
        fontSize: '18px',
        cursor: 'pointer',
        marginRight: '10px',
        color: '#b9b9b9',
    },
    separator: {
        width: '1px',
        height: '30px',
        backgroundColor: '#d8d8d8',
        marginRight: '10px',
    },
    goButton: {
        background: 'linear-gradient(135deg, #7B61FF, #A365F6)',
        border: 'none',
        borderRadius: '50%',
        color: 'white',
        padding: '10px',
        fontSize: '14px',
        cursor: 'pointer',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
        transition: 'all 0.3s ease',
    },
    dropdown: {
        position: 'absolute',
        top: '100%',
        left: 0,
        width: '100%',
        backgroundColor: 'white',
        border: '1px solid #b9b9ff',
        borderRadius: '10px',
        boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.1)',
        marginTop: '10px',
        zIndex: 10,
        padding: '10px',
    },
    dropdownItem: {
        padding: '10px 0',
        fontSize: '14px',
        color: '#333',
        cursor: 'pointer',
        borderBottom: '1px solid #f0f0f0',
    },
};

export default InputFieldWithButton;
