import React, { Component } from 'react';
import { BrowserRouter as Router, Link } from 'react-router-dom';

class RoomComponent extends Component {
  render() {
    return (
      <div>
        <h1>Gloomhaven Room # { this.props.match.params.roomid } </h1>

        <span className="input-group-btn">
            <Link to={`/`}>Back to home!</Link>
        </span>
      </div>
    );
  }
}

export default RoomComponent;