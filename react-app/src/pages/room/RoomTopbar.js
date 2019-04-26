import React from 'react';


const RoomTopbar = (props) => {
  return (
    <nav className="navbar is-black">
      <div className="navbar-brand">
        <div className="navbar-item">
          <div>
            <a href="/"  className="title themed-font has-text-light">
              Gloomtility
            </a>
          </div>
          <div>
            <strong className="has-text-light ml-1">Room: {props.hash}</strong>
          </div>
        </div>
        <div className="navbar-item">
        </div>
      </div>
      <div className="navbar-end">
        <div className="navbar-item">
          <div className="field has-addons mr-1">
            <div className="control">
              <span className="button is-static">Scenario Level</span>
            </div>
            <div className="control is-expanded">
              <input className="input input-short" id="scenarioLevel" type="number" min="1" max="150"
                      defaultValue={props.scenarioLevel} onChange={props.confirmLevelChange}
              />
            </div>
          </div>
        </div>
        <div className="navbar-item">
          <div className="field has-addons mr-1">
            <div className="control">
              <span className="button is-static">Scenario Number</span>
            </div>
            <div className="control is-expanded">
              <input className="input input-short" id="scenarioNumber" type="number" min="1" max="150"
                      defaultValue={props.scenarioNumber} onChange={props.updateScenario}
              />
            </div>
          </div>
        </div>
      </div>
    </nav>
  );

  
}

export default RoomTopbar;