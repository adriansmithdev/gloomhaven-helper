import React, { Component } from 'react';
import { BrowserRouter as Router, Link } from 'react-router-dom';
import MonsterList from './MonsterList';

class RoomComponent extends Component {
  render() {

    // Dummy object to be replaced with live data later.
    const monsters = [
      {
        name: "Skeleton",
        maxHealth: 100,
        currentHealth: 100
      },
      {
        name: "Zombie",
        maxHealth: 125,
        currentHealth: 75
      }
    ];


    return (
      <div>
        <h1>Gloomhaven Room # { this.props.match.params.roomid } </h1>

        <MonsterList monsters={monsters}/>

        <span className="input-group-btn">
            <Link to={`/`}>Back to home!</Link>
        </span>
      </div>
    );
  }
}

export default RoomComponent;