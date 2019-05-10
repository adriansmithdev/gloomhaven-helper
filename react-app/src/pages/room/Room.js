import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

// Components.
import { Redirect } from 'react-router';
import RoomTopbar from './RoomTopbar';
import RoomToolbar from './RoomToolbar';
import MonsterList from './monster/MonsterList';
import LoadingScreen from './../common/LoadingScreen';
import InitiativeTracker from './initiative/InitiativeTracker';

// Redux Store Actions.
import { getSession } from './../../store/actions/session';
import { updateRoom } from './../../store/actions/actions';
import { setStatus } from './../../store/actions/storeActions';



class Room extends Component {

  constructor(props) {
    super(props);

    this.confirmLevelChange = this.confirmLevelChange.bind(this);
    this.updateScenario = this.updateScenario.bind(this);
  }

  

  // Takes an event
  // Sets the key to the id of the element it came from
  // Sets the value to the value of the element it came from
  updateScenario(e){
    const key = e.target.id;
    const value = e.target.value;
    const newRoom = {...this.props.session.room, [key]: value};

    this.props.updateScenario(newRoom);
  }

  confirmLevelChange(e) {
    const response = window.confirm('Changing scenario level will remove all monsters in play!');
    if(response) {
      this.updateScenario(e);
    } else {
      e.target.value = e.target.defaultValue;
    }
  }

  // If room not in store, attempt to pull from hash.
  componentWillMount() {
    var evtSource = new EventSource(`http://localhost:5000/api/stream?hash=${this.props.match.params.hash}`);

    evtSource.onopen = (event) => {
      console.log("Connected to Server API");
    }

    evtSource.onmessage = (event) => {

      const data = JSON.parse(event.data);

      console.log(data);
      const action = {
        type: data.action,
        data: data.response
      }

      this.props.recieveEvent(action)
      
    };
    
    evtSource.onerror = function(event) {
      console.log(event.message);
    }
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
        <RoomTopbar {...this.props.session.room} 
          confirmLevelChange={this.confirmLevelChange}
          updateScenario={this.updateScenario}
          />
        <div className="room-content container">
          <RoomToolbar />
          <MonsterList />

          <span className="input-group-btn">
            <Link className="button is-dark is-large themed-font" to={"/"}>
              Leave Room
            </Link>
          </span>
        </div>
        
        <InitiativeTracker monsters={this.props.session.monsters}/>
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
    updateScenario: (room) => dispatch(updateRoom(room)),
    recieveEvent: (action) => dispatch(action)
  };
};


export default connect(mapStateToProps, mapDispatchToProps)(Room);