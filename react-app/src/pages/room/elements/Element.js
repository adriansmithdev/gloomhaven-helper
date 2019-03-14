import React from 'react';
import { connect } from 'react-redux';

const Element = (props) => {
  // Convert the fill status to array index.
  const fillStrength = [
    '0px', '18px', '36px'
  ];

  const barStyles = {
    backgroundColor: '#dddddd',
    height: fillStrength[props.element.strength]
  }

  return (
    <div className="element">  
      <div style={barStyles}></div>     
      <img src={require(`./../../../assets/icons/elements/${props.element.name}.svg`)} />
       
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