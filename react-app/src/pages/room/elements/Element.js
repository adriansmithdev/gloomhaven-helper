import React from 'react';
import { connect } from 'react-redux';

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
      DARK: '#202830'
  }

  const containerStyles = {
    border: `2px solid ${elementColor[props.element.type]}aa`
  }

  const barStyles = {
    backgroundColor: elementColor[props.element.type],
    height: (fillStrength[props.element.strength] - 3)
  }

  return (
    <div style={containerStyles} className="element">  
      <div style={barStyles}></div>     
      <img src={require(`./../../../assets/icons/elements/${props.element.type.toLowerCase()}.svg`)} alt={props.element.type} />
       
    </div>
  );
}

const mapStateToProps = (state) => {
    return {};
}

const mapDispatchToProps = (dispatch) => {
    return {
        //UI event functions
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(Element);