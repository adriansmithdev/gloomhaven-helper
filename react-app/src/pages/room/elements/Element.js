import React from 'react';

const Element = (props) => {

  const getIcon = () => 
    (props.element && props.element.type !== undefined) ?
      <img className="element-image" src={require(`./../../../assets/icons/elements/${props.element.type.toLowerCase()}.svg`)} alt={props.element.type} /> :
      ""

  return (
    <div className={`element ${props.element.type.toLowerCase()}`} onClick={props.cycleElementStatus}>  
      <div className={`element-bar strength-${props.element.strength}`}></div>     
      {getIcon()}
    </div>
  );
}


export default Element;