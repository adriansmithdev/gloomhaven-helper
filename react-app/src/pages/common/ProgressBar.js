import React from 'react';


function determinHealthColor(healthPercent) {

  const red = (healthPercent < 50) ? 155 : calculateDamageFactor(healthPercent) * 200
  const green = (healthPercent >= 50) ? 155 : (healthPercent / 100) * 200
  
  return `rgb(${red},${green},0)`;
}

function calculateDamageFactor(healthPercent) {
  return Math.abs((healthPercent/100) - 1);
}

function healthPercentage(current, max) {
  if(current === undefined || max === undefined) return 0;
  return (current/max*100);
}

const ProgressBar = (props) => {
  
  const healthPercent = healthPercentage(props.current, props.max);
  const defaultHeight = '2rem';
  const containerStyles = {
    position: 'relative',
    width: '100%',
    backgroundColor: '#262626',
    height: props.height || defaultHeight,
    padding: 0,
    border: '2px ridge gray',
    boxSizing: 'content-box'
  }

  const barStyles = {
    position: 'absolute',

    backgroundColor: determinHealthColor(healthPercent),
    height: (props.height || defaultHeight),
    width: `${healthPercent}%`,
    maxWidth: '100%',

  }

  const textStyles = {
    position: 'absolute',
    textAlign: 'center',
    height: props.height || defaultHeight,
    lineHeight: props.height || defaultHeight,
    textSize: 12,
    color: '#ffffff',
    width: '100%',
    textTransform: 'capitalize',
    textShadow: '0px 0px 4px #000000'
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
    <div style={containerStyles}>
      <div style={barStyles}></div>
      <p style={textStyles}>{getTitleText() + getContentText()}</p>
    </div>
  );
};

export default ProgressBar;