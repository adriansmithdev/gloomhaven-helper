import React from 'react';
import { connect } from 'react-redux';
import { incrementRound } from "../../store/actions/actions";

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
        <strong className="themed-font has-text-white subtitle is-3">Round {(props.room !== undefined) ? props.round : ''} </strong>
        <button className="button is-dark themed-font is-rounded round-increment-button" onClick={incrementRound}>→</button>
    </div>
  )
}

const mapStateToProps = (state) => {
  return {
    room: {...state.session.room}
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    incrementRound: (room) => dispatch(incrementRound(room))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(RoundManager);