import React, { PureComponent as Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

// Components.
import { Redirect } from 'react-router';
import Titlebar from './Titlebar';
import TopToolbar from './TopToolbar';
import AddMonsterWidget from './monster/AddMonsterWidget';
import ScenarioLevel from './../common/ScenarioLevel';
import StickyToolbar from './StickyToolbar';
import MonsterList from './monster/MonsterList';
import LoadingScreen from './../common/LoadingScreen';
import InitiativeTracker from './initiative/InitiativeTracker';
import { toast } from 'react-toastify';

// Redux Store Actions.
import { updateRoom, addMonster } from './../../store/actions/actions';
import { setStatus } from './../../store/actions/storeActions';
import { apiURL } from './../../axios.config';

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
    const eventSource = new EventSource(`${apiURL}stream?hash=${this.props.match.params.hash}`);
    this.eventSource = eventSource

    // Counter for failed connection attempts.
    let attempts = 0;

    eventSource.onopen = (event) => {
      // Reset failed attempts count.
      this.props.setEventSourceStatus('CONNECTION_STARTED');
    }

    eventSource.onmessage = (event) => {
      const data = JSON.parse(event.data);

      const action = {
        type: data.action,
        data: data.response
      }

      this.props.recieveEvent(action);
    };

    const setEventSourceStatus = this.props.setEventSourceStatus;

    eventSource.onerror = function(event) {
      setEventSourceStatus('CONNECTION_PENDING');

      // Display error after several attempts.
      const timeoutHandler = () => {
        attempts++;
        if(eventSource.readyState === 2 || attempts === 3) {
          toast.error('Unable to connect to server!');
          setEventSourceStatus('CONNECTION_LOST');
        } else if(eventSource.readyState === 0 && attempts < 3) {
          setEventSourceStatus('CONNECTION_PENDING');
          checkConnection()
        } else if(eventSource.readyState === 1) {
          attempts = 0;
        }
      }
      const checkConnection = async function() {
        setTimeout(timeoutHandler, 5000);
      }
      checkConnection();
    }
  }

  componentWillUnmount() {
    if(this !== undefined && 
       this.eventSource !== undefined &&
       this.eventSource.readyState !== 2) {
      this.eventSource.close();
    }
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
        <Titlebar 
          hash={this.props.session.room.hash}
          eventSourceStatus={this.props.eventSourceStatus}
          />
        <TopToolbar>
          <AddMonsterWidget 
            addMonster={this.props.addMonster} 
            monsters={this.props.session.monsters}
            eliteToggle={this.props.eliteToggle} // The State
            toggleElite={this.props.toggleElite} // The function
            hash={this.props.session.room.hash}
          />
          <ScenarioLevel
            scenarioLevel={this.props.session.room.scenarioLevel} 
            confirmLevelChange={this.confirmLevelChange}/>
        </TopToolbar>
        <StickyToolbar />
        <div className="room-content">
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
    toggleElite: () => dispatch({type: 'TOGGLE_ELITE'}),
    addMonster: (hash, monsterId, isElite) => dispatch(addMonster(hash, monsterId, isElite)),
    clearSession: () => dispatch({type: 'CLEAR_SESSION'}),
    setStatus: (newStatus) => dispatch(setStatus(newStatus)),
    updateScenario: (room) => dispatch(updateRoom(room)),
    recieveEvent: (action) => dispatch(action),
    setEventSourceStatus: (newStatus) => dispatch({
      type: 'SET_EVENT_SOURCE_STATUS', 
      data: newStatus
    }),
    
  };
};


export default connect(mapStateToProps, mapDispatchToProps)(Room);