import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import MonsterList from './MonsterList';

class RoomComponent extends Component {
  render() {
    
    return (
      <div>
        <h1>Gloomhaven Room # { this.props.match.params.roomid } </h1>

        <MonsterList />

        <span className="input-group-btn">
            <Link className="button is-dark is-large themed-font m-2" to={`/`}>Back to home!</Link>
        </span>
      </div>
    );
  }
}

export default RoomComponent;