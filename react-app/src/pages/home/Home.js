import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createRoom } from './../../store/actions/actions';
import { getSession } from './../../store/actions/session';
import { clearSession } from './../../store/actions/storeActions';

class Home extends Component {

  state = {
    showHashInput: false
  }

  constructor(props) {
    super(props);

    
    this.setShowHash = this.setShowHash.bind(this);
    this.joinRoom = this.joinRoom.bind(this);
  }

  renderJoinRoomBtn() {
    return (
      <>
        <button className="button is-dark is-large themed-font m-2" type="button"
                onClick={this.props.createRoom}>
          Create Room
        </button>
        <button className="button is-dark is-large themed-font m-2" type="button"
                onClick={this.setShowHash}>
          Join Room
        </button>
      </>
    );
  }

  renderHashInput() {
    return (
      <>
      <button className="button is-dark is-large themed-font m-2" type="button">
        Join Room
      </button>
      <input type="text" className="text-input" onBlur={this.joinRoom} name="hash"
             placeholder="Room ID"/>
        </>
    );
  }

  setShowHash() {
    this.setState({showHashInput: true});
  }

  joinRoom(event) {
    this.props.clearSession();
    this.props.history.push(`/rooms/${event.target.value}`);
    this.setState({showHashInput: false})
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
              {this.state.showHashInput ? this.renderHashInput() : this.renderJoinRoomBtn()}
              </span>

          </div>
        </div>
      </div>
      
    );
  }
}


const mapStateToProps = (state) => {
  return {
    ...state
  };
}

const mapDispachToProps = (dispatch, ownProps) => {
  const rerouteToRoomPage = (response) => {
    ownProps.history.push(`/rooms/${response.data.hash}/`);
  }

  return {
    createRoom: () => dispatch(createRoom(rerouteToRoomPage)),
    getSession: (hash) => dispatch(getSession(hash)),
    clearSession: () => dispatch(clearSession)
  }
}


export default connect(mapStateToProps, mapDispachToProps)(Home);