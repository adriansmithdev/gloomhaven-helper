import React from 'react';
import ConnectionMonitor from '../common/ConnectionMonitor';

const Titlebar = (props) => {
  return (
    <nav className="titlebar">
      <div className="titlebar-contents">
        <a href="/"  className="titlebar-title themed-font">
          Gloomtility
        </a>
        <strong className="room-hash">
          <span className="hide-for-devices">Room: </span>{props.hash}
        </strong>
        <ConnectionMonitor status={props.eventSourceStatus}/>
      </div>
    </nav>
  );
}

export default Titlebar;