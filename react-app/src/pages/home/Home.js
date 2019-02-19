import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createRoom, getRoom } from './../../store/actions/actions';
import { ToastContainer } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";   


class Home extends Component {

  state = {
    showHashInput: false
  }

  constructor(props) {
    super(props);

    this.joinRoom = this.joinRoom.bind(this);
    this.setShowHash = this.setShowHash.bind(this);
  }  
  renderJoinRoomBtn() {
    return (
      <button className="button is-dark is-large themed-font m-2" type="button"
        onClick={this.setShowHash}>
        Join Room
      </button>
    );
  }

  renderHashInput(){
    return (
      <input type="text" className="text-input" onBlur={this.joinRoom} name="hash"
      placeholder="Room ID"/>
    );
  }

  setShowHash(){
    this.setState({showHashInput : true});
  }

  joinRoom(event) {
    this.props.getRoom(event.target.value);
    if(this.props.room !== undefined && this.props.room.hash !== undefined) {
      this.props.history.push(`/rooms/${this.props.room.hash}`);
    }
    this.setState({showHashInput: false})
  }


  render() {
    return (
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
              <button className="button is-dark is-large themed-font m-2" type="button"
                onClick={this.props.createRoom}>
                Create Room
              </button>
              {this.state.showHashInput ? this.renderHashInput() : this.renderJoinRoomBtn()}
            </span>

            <ToastContainer/>

          </div>
        </div>
    );
  }
}


const mapStateToProps = (state) => {
  return {};
}

const mapDispachToProps = (dispatch, ownProps) => {
  const rerouteToRoomPage = (response) => {
    console.log(response);
    ownProps.history.push(`/rooms/${response.data.hash}`);
  }

  return {
    createRoom: () => dispatch(createRoom(rerouteToRoomPage)),
    getRoom: (hash) => dispatch(getRoom(hash))
  }
}



export default connect(mapStateToProps, mapDispachToProps)(Home);