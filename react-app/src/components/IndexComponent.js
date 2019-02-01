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
    axios.post('http://localhost:5000/rooms')
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
      <div className="container">
        <div className="card">
          <div className="card-content">
            <h1 className="title themed-font level-item">Gloomhaven Helper</h1>

            <p className="level">
                The Gloomhaven Helper is a tool for Gloomhaven Players who want assistance managing 
                the game, the Gloomhaven Helper is a web-based board game assistance tool that speeds 
                up the pace of play, while making it easier to manage all content. Unlike the base 
                game out of the box, our project reduces the number of physical tokens and trackers 
                players need to manage.
            </p>

            <span className="level-item">
              <button className="button is-dark is-large themed-font m-2" type="button" onClick={this.requestRoom}>Create Room</button>
              <button className="button is-dark is-large themed-font m-2" type="button" onClick={this.requestRoom}>Join Room</button>
            </span>
          </div>
        </div>
      </div>
    );
  }
}

export default IndexComponent;