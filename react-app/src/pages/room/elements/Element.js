import React from 'react';
import { connect } from 'react-redux';

const Element = (props) => {
  // Convert the fill status to array index.
  const fillStrength = [
    6, 18, 36
  ];

  const elementColor = {
      wind: '#98b0b5',
      fire: '#e2421f',
      earth: '#7da82a',
      ice: '#56c8ef',
      light: '#eca610',
      dark: '#202830'
  }

  const containerStyles = {
    border: `2px solid ${elementColor[props.element.name]}aa`
  }

  const barStyles = {
    backgroundColor: elementColor[props.element.name],
    height: (fillStrength[props.element.strength] - 3)
  }

  return (
    <div style={containerStyles} className="element">  
      <div style={barStyles}></div>     
      <img src={require(`./../../../assets/icons/elements/${props.element.name}.svg`)} alt={props.element.name} />
       
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