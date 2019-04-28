import React from 'react';



const InitiativeItem = (props) => {
  if(props.type === undefined || props.type.action === undefined) {
    console.warn('Could not create intiative item: type or action is undefined!');
    return <></>
  }

  let portrait;

  try {
    portrait = require(`./../../../assets/portraits/monsters/full/${props.type.name}.jpg`);
  } catch(e) {
    console.log('Failed to find portrait!');
    portrait = require('./../../../assets/portraits/monsters/mini/default.svg');
  }
  
  return (
    <div className="initiative-item" title={props.type.name}>
      <img src={portrait} alt={props.type.name}/>
      <strong>{props.type.action.initiative}</strong>
    </div>
  );
}

export default InitiativeItem;