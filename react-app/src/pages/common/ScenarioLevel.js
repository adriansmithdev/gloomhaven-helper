import React from 'react';

const ScenarioLevel = (props) => {
  return (
    <label className="scenario-level mr-1">
      <span className="scenario-level-label themed-font">
        <span className="hide-for-devices">Scenario </span>
        Level
      </span>
      
      <input className="scenario-input" 
        id="scenarioLevel" 
        type="number" 
        min="1" 
        max="150"
        defaultValue={props.scenarioLevel} onChange={props.confirmLevelChange}
        />
    </label>
  );
}


export default ScenarioLevel;