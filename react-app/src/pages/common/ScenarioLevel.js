import React from 'react';

const ScenarioLevel = (props) => {
  return (
    <div className="field has-addons">
      <div className="control">
        <span className="button is-static">Scenario Level</span>
      </div>
      <div className="control is-expanded">
        <input className="input input-short" id="scenarioLevel" type="number" min="1" max="150"
                defaultValue={props.scenarioLevel} onChange={props.confirmLevelChange}
        />
      </div>
    </div>
  );
}


export default ScenarioLevel;