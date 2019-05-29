import React from 'react';

const ScenarioLevel = (props) => {
  return (
    <div className="scenario-level">
      <label className="scenario-label">
        <span className="hide-for-devices">Scenario </span>
        Level
        <input className="scenario-input" id="scenarioLevel" type="number" min="1" max="150"
              defaultValue={props.scenarioLevel} onChange={props.confirmLevelChange}
      />
      </label>
    </div>
  );
}


export default ScenarioLevel;