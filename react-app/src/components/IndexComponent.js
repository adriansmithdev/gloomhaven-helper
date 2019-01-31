import React, { Component } from 'react';
// import { Link } from 'react-router-dom';
import axios from 'axios';


class IndexComponent extends Component {

  state = {
    persons: []
  }

  constructor() {
    super();

    this.requestRoom = this.requestRoom.bind(this);
  }

  requestRoom() {
    axios.post('http://localhost:5000/newroom')
    .then(function (response) {
      console.log(response.data.roomHash);
      window.location = `/room/${response.data.roomHash}`;
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  componentDidMount() {
    

    axios.get('http://localhost:5000/room/103')
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  render() {
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
          <button type="button" onClick={this.requestRoom}>Create a Room!</button>
        </span>
      </div>
    );
  }
}

export default IndexComponent;