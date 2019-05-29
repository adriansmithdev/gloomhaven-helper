import React from 'react';

const ScenarioNumber = (props) => {
  return (
    <div className="field has-addons">
      <div className="control">
        <span className="button is-static">
          <span className="hide-for-devices">Scenario </span>
          Number
        </span>
      </div>
      <div className="control is-expanded">
        <input className="input input-short" id="scenarioNumber" type="number" min="1" max="150"
                defaultValue={props.scenarioNumber} onChange={props.updateScenario}
        />
      </div>
    </div>
  );
}


export default ScenarioNumber;