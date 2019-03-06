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
  const containerStyles = {
    position: 'relative',
    width: '100%',
    backgroundColor: '#262626',
    height: props.height || defaultHeight,
    padding: 0
  }

  const barStyles = {
    position: 'absolute',
    backgroundColor: `hsl(${healthHue}, 100%, 35%)`,
    background: `-webkit-linear-gradient(top,
      hsl(${healthHue}, 100%, 35%) 0%,
      hsl(${healthHue}, 100%, 20%) 50%, 
      hsl(${healthHue}, 100%, 15%) 51%, 
      hsl(${healthHue}, 100%, 25%) 100%
    )`,
    height: (props.height || defaultHeight),
    width: `${healthPercent}%`,
    maxWidth: '100%',
    transition: 'background 0.5s, width 0.4s'

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