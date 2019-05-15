import React from 'react';
import ConnectionMonitor from '../common/ConnectionMonitor';


const RoomTopbar = (props) => {
  return (
    <nav className="room-topbar is-black">
      <div className="topbar-item">
        <a href="/"  className="title themed-font has-text-light">
          Gloomtility
        </a>
        <strong className="room-hash has-text-light ml-1">Room: {props.hash}</strong>
        <ConnectionMonitor status={props.eventSourceStatus}/>
      </div>
      <div className="topbar-item">
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
        <div className="field has-addons">
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
    </nav>
  );

  
}

export default RoomTopbar;