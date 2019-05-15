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
import { toast } from 'react-toastify';

// Redux Store Actions.
import { updateRoom } from './../../store/actions/actions';
import { setStatus } from './../../store/actions/storeActions';
import { apiURL } from './../../store/actions/axios.config';

class Room extends Component {

  eventSource = undefined;

  constructor(props) {
    super(props);

    this.confirmLevelChange = this.confirmLevelChange.bind(this);
    this.updateScenario = this.updateScenario.bind(this);

    this.props.clearSession();
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
    this.eventSource = new EventSource(`${apiURL}stream?hash=${this.props.match.params.hash}`);

    this.eventSource.onopen = (event) => {
      this.props.setEventSourceStatus('CONNECTION_STARTED');
    }

    this.eventSource.onmessage = (event) => {

      const data = JSON.parse(event.data);

      const action = {
        type: data.action,
        data: data.response
      }

      this.props.recieveEvent(action)
      
    };

    const setEventSourceStatus = this.props.setEventSourceStatus;

    this.eventSource.onerror = function(event) {
      toast.error('Unable to connect to server!');
      setEventSourceStatus('CONNECTION_LOST');
    }
  }

  componentWillUnmount() {
    this.eventSource.close();
    this.props.setEventSourceStatus('NO_CONNECTION');
    this.props.clearSession();
  }


  render() {

    if(this.props.status === 'SESSION_NOT_FOUND') {
      this.props.setStatus('INITIAL');
      toast.error('Couldn\'t connect to room');
      return <Redirect to="/" />
    }
    
    return (this.props.session.room.hash === undefined) ? (
      <LoadingScreen /> 
      ) : (

       <>
        <RoomTopbar 
          {...this.props.session.room} 
          confirmLevelChange={this.confirmLevelChange}
          updateScenario={this.updateScenario}
          eventSourceStatus={this.props.eventSourceStatus}
          key={this.props.session.room.scenarioNumber}
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
    clearSession: () => dispatch({type: 'CLEAR_SESSION'}),
    setStatus: (newStatus) => dispatch(setStatus(newStatus)),
    updateScenario: (room) => dispatch(updateRoom(room)),
    recieveEvent: (action) => dispatch(action),
    setEventSourceStatus: (newStatus) => dispatch({
      type: 'SET_EVENT_SOURCE_STATUS', 
      data: newStatus
    })
  };
};


export default connect(mapStateToProps, mapDispatchToProps)(Room);