import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';


class IndexComponent extends Component {

  state = {
    persons: []
  }

  componentDidMount() {
    axios.get('http://localhost:5000/rooms')
    .then(response => {
      console.log(response);
    }).catch(function (error) {
      console.log(error);
    });

    axios.get('http://localhost:5000/room/103')
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

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