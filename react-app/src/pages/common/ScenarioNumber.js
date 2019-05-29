import React from 'react';

const ScenarioNumber = (props) => {
  return (
    <div className="scenario-number">
      <label className="scenario-label">
        <span className="hide-for-devices">Scenario </span>
        Number
        <input className="scenario-input" id="scenarioNumber" type="number" min="1" max="150"
                defaultValue={props.scenarioNumber} onChange={props.updateScenario}
        />
      </label>
    </div>
        
  );
}


export default ScenarioNumber;