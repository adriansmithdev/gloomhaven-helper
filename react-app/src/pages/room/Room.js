import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

// Components.
import { Redirect } from 'react-router';
import RoomToolbar from "./RoomToolbar";
import MonsterList from './monster/MonsterList';
import LoadingScreen from './../common/LoadingScreen';

// Redux Store Actions.
import { getSession } from './../../store/actions/session';
import { updateRoom } from './../../store/actions/actions';
import { setStatus } from './../../store/actions/storeActions';

 
class Room extends Component {

  constructor(props) {
    super(props);

    this.updateScenario = this.updateScenario.bind(this);
  }

  updateScenario(event){
    const key = event.target.id;
    const newRoom = {...this.props.session.room, [key]: event.target.value};

    this.props.updateScenario(newRoom);
  }

  // If room not in store, attempt to pull from hash.
  componentWillMount() {
    this.props.getSession(this.props.match.params.hash);
  }


  render() {

    if(this.props.status === "SESSION_NOT_FOUND") {
      this.props.setStatus("INITIAL");
      return <Redirect to="/" />
    }
    
    return (this.props.session.room.hash === undefined) ? (
      <LoadingScreen /> 
      ) : (

       <>
        <nav className="navbar is-black">
          <div className="navbar-brand">
            <div className="navbar-item">
              <a href="/"  className="title themed-font has-text-light">
                Gloomtility
              </a>
            </div>
            <div className="navbar-item">
              <strong className="has-text-light">Room: {this.props.session.room.hash}</strong>

            </div>
          </div>
          <div className="navbar-end">
            <div className="navbar-item">
              <div className="field has-addons mr-1">
                <div className="control">
                  <span className="button is-static">Scenario Level</span>
                </div>
                <div className="control is-expanded">
                  <input className="input input-short" id="scenarioLevel" type="number" min="1" max="150"
                         defaultValue={this.props.session.room.scenarioLevel} onChange={this.updateScenario}
                  />
                </div>
              </div>
            </div>
            <div className="navbar-item">
              <div className="field has-addons mr-1">
                <div className="control">
                  <span className="button is-static">Scenario Number</span>
                </div>
                <div className="control is-expanded">
                  <input className="input input-short" id="scenarioNumber" type="number" min="1" max="150"
                         defaultValue={this.props.session.room.scenarioNumber} onChange={this.updateScenario}
                  />
                </div>
              </div>
            </div>
          </div>
        </nav>
        <div className="room-content container">
          <RoomToolbar />
          <MonsterList />

          <span className="input-group-btn">
            <Link className="button is-dark is-large themed-font m-2" to={"/"}>
              Leave Room
            </Link>
          </span>
        </div>
      </>
    );
  }


}

const mapStateToProps = (state) => {
  return {
    ...state
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setStatus: (newStatus) => dispatch(setStatus(newStatus)),
    getSession: (hash) => dispatch(getSession(hash)),
    updateScenario: (room) => dispatch(updateRoom(room))
  };
};


export default connect(mapStateToProps, mapDispatchToProps)(Room);