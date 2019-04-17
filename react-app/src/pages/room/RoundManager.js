import React from 'react';

export const incrementRoundForRoom = (room) => {
  const newRoom = {
    ...room, 
    round: room.round + 1
  };
  
  return newRoom;
}

export const RoundManager = (props) => {
  const incrementRound = () => {
    if(props.room === undefined) return;
    const newRoom = incrementRoundForRoom(props.room);
    props.incrementRound(newRoom);
  }

  return(
    <div className="round-manager">
        <strong className="themed-font has-text-white subtitle is-3">Round {(props.room !== undefined) ? props.room.round : ''} </strong>
        <button type="button" className="button is-dark themed-font is-rounded round-increment-button" onClick={incrementRound}>→</button>
    </div>
  )
}


export default RoundManager;