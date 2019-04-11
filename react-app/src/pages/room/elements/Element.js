import React from 'react';

const Element = (props) => {
  // Convert the fill status to array index.
  const fillStrength = [
    6, 18, 36
  ];

  const elementColor = {
      AIR: '#98b0b5',
      FIRE: '#e2421f',
      EARTH: '#7da82a',
      ICE: '#56c8ef',
      LIGHT: '#eca610',
      DARK: '#607f85'
  }

  const containerStyles = (props.element !== undefined) ? {
    border: `2px solid ${elementColor[props.element.type]}aa`
  } : {
    border: '2px solid #444444'
  }

  const barStyles = (props.element !== undefined) ? {
    backgroundColor: elementColor[props.element.type],
    height: (fillStrength[props.element.strength] - 3)
  } : {
    backgroundColor: '#000000',
    height: 0
  }

  const getIcon = () => 
    (props.element && props.element.type !== undefined) ?
      <img src={require(`./../../../assets/icons/elements/${props.element.type.toLowerCase()}.svg`)} alt={props.element.type} /> :
      ""

  return (
    <div style={containerStyles} className="element" onClick={props.cycleElementStatus}>  
      <div style={barStyles}></div>     
      {getIcon()}
       
    </div>
  );
}


export default Element;