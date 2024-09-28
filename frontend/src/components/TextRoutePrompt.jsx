import React from 'react';

const Prompt = ({ value, onChange, type = "text"}) => {
    return (
        <div className="textfield">
            <input
                type={type}
                value={value}
                onChange={e => onChange(e.target.value)}
                placeholder="Dokąd Cię poprowadzić?..."
                className="textfield-input"
            />
        </div>
    );
};

export default Prompt;