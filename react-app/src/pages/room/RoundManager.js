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
        <div className="round-text themed-font">
          Round: {(props.room !== undefined) ? props.room.round : ''} 
        </div>
        <button type="button" 
          className="round-increment-button themed-font" 
          onClick={incrementRound}>
          →
        </button>
    </div>
  )
}


export default RoundManager;