import React from 'react';
import ConnectionMonitor from './../common/ConnectionMonitor';
import ScenarioLevel from './../common/ScenarioLevel';
import ScenarioNumber from './../common/ScenarioNumber';



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
        <ScenarioLevel
          scenarioLevel={props.scenarioLevel} 
          confirmLevelChange={props.confirmLevelChange}/>
        <ScenarioNumber
          scenarioNumber={props.scenarioNumber} 
          updateScenario={props.updateScenario}/>
      </div>
    </nav>
  );
}

export default RoomTopbar;