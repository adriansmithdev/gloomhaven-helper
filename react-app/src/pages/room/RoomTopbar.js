import React from 'react';
import ConnectionMonitor from './../common/ConnectionMonitor';
import ScenarioLevel from './../common/ScenarioLevel';
import ScenarioNumber from './../common/ScenarioNumber';
import AddMonsterWidget from './monster/AddMonsterWidget';



const RoomTopbar = (props) => {
  
  
  return (
    
    <nav className="room-topbar is-black">
      <div className="topbar-item">
        <div className="topbar-item-row">
          <a href="/"  className="topbar-title themed-font">
            Gloomtility
          </a>
          <strong className="room-hash">
            <span class="hide-for-devices">Room: </span>{props.room.hash}
          </strong>
          <ConnectionMonitor status={props.eventSourceStatus}/>
        </div>
        <div className="topbar-item-row">
          <AddMonsterWidget addMonster={props.addMonster} 
            monsters={props.monsters}
            eliteToggle={props.eliteToggle} // The State
            toggleElite={props.toggleElite} // The function

            hash={props.room.hash}
            />
        </div>
        
        
      </div>
      
      <div className="topbar-item">
        <div className="topbar-item-row">
          <ScenarioLevel
            scenarioLevel={props.room.scenarioLevel} 
            confirmLevelChange={props.confirmLevelChange}/>
        </div>
      </div>
      
    </nav>
  );
}

export default RoomTopbar;