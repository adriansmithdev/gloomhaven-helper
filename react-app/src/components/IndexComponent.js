import React, { Component } from 'react';
import { BrowserRouter as Router, Link } from 'react-router-dom';


class IndexComponent extends Component {
  render() {
    var uuid = require("uuid");
    const mockRoomId = uuid.v4();

    return (
      <div>
        <h1>Gloomhaven Helper</h1>

        <p>
            The Gloomhaven Helper is a tool for Gloomhaven Players who want assistance managing 
            the game, the Gloomhaven Helper is a web-based board game assistance tool that speeds 
            up the pace of play, while making it easier to manage all content. Unlike the base 
            game out of the box, our project reduces the number of physical tokens and trackers 
            players need to manage.
        </p>

        <span className="input-group-btn">
            <Link to={`/room/${mockRoomId}`}>Create a Room!</Link>
        </span>
      </div>
    );
  }
}

export default IndexComponent;