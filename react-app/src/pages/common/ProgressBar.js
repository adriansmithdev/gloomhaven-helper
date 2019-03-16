import React from 'react';


function determineHealthHue(healthPercent) {

  const hue = healthPercent/100 * 125;
  
  return hue;
}

function healthPercentage(current, max) {
  if(current === undefined || max === undefined) return 0;
  return (current/max*100);
}

const ProgressBar = (props) => {
  
  const healthPercent = healthPercentage(props.current, props.max);
  const healthHue = determineHealthHue(healthPercent);
  const defaultHeight = '2rem';

  const barStyles = {
    backgroundColor: `hsl(${healthHue}, 100%, 35%)`,
    background: `-webkit-linear-gradient(top,
      hsl(${healthHue}, 100%, 35%) 0%,
      hsl(${healthHue}, 100%, 20%) 50%, 
      hsl(${healthHue}, 100%, 15%) 51%, 
      hsl(${healthHue}, 100%, 25%) 100%
    )`,
    width: `${healthPercent}%`,

  }

  const textStyles = {
    
  }

  function getTitleText() {
    return props.title !== undefined ? props.title + ": " : "";
  }

  function getContentText() {
    if(props.usePercentage === true) {
      return `${healthPercent}%`;
    } else {
      return `${props.current}/${props.max}`;
    }
  }

  return (
    <div className="progress-bar">
      <div className="progress-bar-progress" style={barStyles}></div>
      <p className="progress-title" style={textStyles}>{getTitleText() + getContentText()}</p>
    </div>
  );
};

export default ProgressBar;