import React from 'react';

const EliteToggle = (props) => {
  
  const isActive = (props.toggleStatus === true)

  const buttonText = (isActive) ? ' Elite' : 'Normal';
  const classes = (isActive) ? 'button is-black elite-toggle themed-font active' : 'button is-dark elite-toggle themed-font';
  return (
    <button className={classes}
      onClick={props.onClick}>
      {buttonText}
    </button>
  );
    
}

export default EliteToggle;