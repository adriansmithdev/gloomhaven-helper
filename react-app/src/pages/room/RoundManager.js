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
    <div className="round-manager field has-addons">
        <button type="button" 
          className="themed-font button has-text-black is-static">
          Round: {(props.room !== undefined) ? props.room.round : ''} 
        </button>
        <button type="button" 
          className="button is-dark themed-font round-increment-button" 
          onClick={incrementRound}>
          →
        </button>
    </div>
  )
}


export default RoundManager;