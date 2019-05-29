import React from 'react';
import ConnectionMonitor from './../common/ConnectionMonitor';
import ScenarioLevel from './../common/ScenarioLevel';
import ScenarioNumber from './../common/ScenarioNumber';
import AddMonsterWidget from './monster/AddMonsterWidget';



const RoomTopbar = (props) => {
  
  
  return (
    
    <nav className="room-topbar is-black">
      <div className="topbar-item">
        <a href="/"  className="title themed-font has-text-light">
          Gloomtility
        </a>
        <strong className="room-hash has-text-light ml-1">Room: {props.room.hash}</strong>
        <ConnectionMonitor status={props.eventSourceStatus}/>
      </div>
      <div className="topbar-item">
        <ScenarioLevel
          scenarioLevel={props.room.scenarioLevel} 
          confirmLevelChange={props.confirmLevelChange}/>
        <ScenarioNumber
          scenarioNumber={props.room.scenarioNumber} 
          updateScenario={props.room.updateScenario}/>
        <AddMonsterWidget addMonster={props.addMonster} 
          monsters={props.monsters}
          eliteToggle={props.eliteToggle} // The State
          toggleElite={props.toggleElite} // The function

          hash={props.room.hash}
          />

      </div>
    </nav>
  );
}

export default RoomTopbar;