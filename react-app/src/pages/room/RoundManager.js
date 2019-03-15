import React, { Component } from 'react';
import connect from "react-redux/es/connect/connect";
import {incrementRound} from "../../store/actions/actions";

class RoundManager extends Component {

  constructor(props){
    super(props);

    this.incrementRound = this.incrementRound.bind(this);
  }

  incrementRound(){
    const newRoom = {...this.props.session.room, round: this.props.session.room.round + 1};
    this.props.incrementRound(newRoom);
  }


  render() {
    return(
    <div className="column">
      <div>
        <strong className="has-text-light">Round {this.props.round}</strong>
        <button onClick={this.incrementRound}>Next Round</button>
      </div>
    </div>
    )}
}

const mapStateToProps = (state) => {
  return {
    ...state
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    incrementRound: (room) => dispatch(incrementRound(room))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(RoundManager);