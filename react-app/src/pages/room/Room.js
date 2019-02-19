import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import MonsterList from './MonsterList';
import LoadingScreen from './../common/LoadingScreen';
import { Redirect } from 'react-router';

import { getRoom, updateScenario, setStatus } from './../../store/actions/actions'
 
class Room extends Component {

  constructor(props) {
    super(props);

    this.updateScenario = this.updateScenario.bind(this);
  }

  updateScenario(event) {
    // Create new object for sending to server.
    const newRoom = {...this.props.room, scenarioNumber: event.target.value};

    console.log(newRoom);
    this.props.updateScenario(newRoom);
  }

  componentWillMount() {
    if(this.props.room.hash === undefined) {
      this.props.getRoom(this.props.match.params.hash);
    }
  }


  render() {

    if(this.props.status === "ROOM_NOT_FOUND") {
      this.props.setStatus("INITIAL");
      return <Redirect to="/" />
    }
    
    return (this.props.room.hash === undefined) ? (
      <LoadingScreen /> 
      ) : (

       <>
        <nav className="navbar is-black">
          <div className="navbar-brand">
            <div className="navbar-item">
              <h1 className="title themed-font has-text-light">Gloomhaven Helper</h1>
            </div>
            <div className="navbar-item">
              <strong className="has-text-light">Room: {this.props.room.hash}</strong>

            </div>
          </div>
          <div className="navbar-end">
            <div className="navbar-item">
              <div className="field has-addons mr-1">
                <div className="control">
                  <span className="button is-static">Scenario</span>
                </div>
                <div className="control is-expanded">
                  <input className="input input-short" type="number" min="1" max="150"
                    defaultValue={this.props.room.scenarioNumber} onChange={this.updateScenario}
                  />
                </div>
              </div>
            </div>
          </div>
        </nav>
        <MonsterList />

        <span className="input-group-btn">
          <Link className="button is-dark is-large themed-font m-2" to={`/`}>Back to home!</Link>
        </span>
        </>
    );
  }


}

const mapStateToProps = (state) => {
  return {
    ...state
  };
}

const mapDispachToProps = (dispatch) => {
  return {
    setStatus: (newStatus) => {dispatch(setStatus(newStatus))},
    getRoom: (hash) => {dispatch(getRoom(hash))},
    updateScenario: (room) => {dispatch(updateScenario(room))}
  };
}


export default connect(mapStateToProps, mapDispachToProps)(Room);