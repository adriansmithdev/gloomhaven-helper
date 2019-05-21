import React from 'react';

const MonsterAttribute = (props) => {

  return (
    <div className="monster-attribute" title={`${props.alt} ${props.value}` }>
      <img src={props.src} className="inline-icon" alt={props.alt}/>
      <p>{props.value}</p>
    </div>
  );

}

export default MonsterAttribute;