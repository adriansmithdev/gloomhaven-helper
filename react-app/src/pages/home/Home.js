import React, {Component} from 'react';
import {connect} from 'react-redux';
import {createRoom} from './../../store/actions/actions';
import {getSession} from './../../store/actions/session';
import {clearSession} from './../../store/actions/storeActions';
import FAQ from './../home/FAQ';

class Home extends Component {

  state = {
    showHashInput: false,
    showLevelInput: false,
  };

  constructor(props) {
    super(props);

    this.scenarioLvl = React.createRef();
    this.scenarioNum = React.createRef();

    this.setShow = this.setShow.bind(this);
    this.createRoom = this.createRoom.bind(this);
    this.joinRoom = this.joinRoom.bind(this);
  }

  renderCreateRoomBtn() {
    return (
      <button className="button is-dark is-large themed-font m-2" id="showLevelInput" type="button"
              onClick={this.setShow}>
        Create Room
      </button>
    );
  }

  renderJoinRoomBtn() {
    return (
      <button className="button is-dark is-large themed-font m-2" id="showHashInput" type="button"
              onClick={this.setShow}>
        Join Room
      </button>
    );
  }

  setShow(event) {
    this.setState({[event.target.id]: true});
  }

  renderDefaultButtons() {
    return (
      <>
        {this.renderCreateRoomBtn()}
        {this.renderJoinRoomBtn()}
      </>
    );
  }

  renderOptionInputs() {
    if (this.state.showHashInput) {
      return (
        <>
          <button className="button is-dark is-large themed-font m-2" type="button">
            Join Room
          </button>
          <input type="text" className="text-input" autoFocus onBlur={this.joinRoom} name="hash"
                 placeholder="Room ID"/>
        </>
      );
    } else if (this.state.showLevelInput) {
      return (
        <>
          <input type="text" className="text-input" autoFocus name="scenarioNum" placeholder="Scenario Number"
                 ref={this.scenarioNum}/>
          <select className="text-input" name="scenarioLevel" ref={this.scenarioLvl}>
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
          </select>
          <button className="button is-dark is-large themed-font m-2" type="button" onClick={this.createRoom}>
            Create Room
          </button>
        </>
      );
    }
  }

  joinRoom(event) {
    this.props.clearSession();
    this.props.history.push(`/rooms/${event.target.value}`);
    this.setState({showHashInput: false});
  }

  createRoom() {
    const newRoom = {scenarioNumber: this.scenarioNum.current.value, scenarioLevel: this.scenarioLvl.current.value};
    this.props.createRoom(newRoom);
    this.setState({showLevelInput: false});
  }

  render() {
    return (
      <div className="container">
        <div className="card">
          <div className="card-content">
            <h1 className="title themed-font level-item">Gloomtility</h1>

            <p className="level">
              Gloomtility is a tool for Gloomhaven Players who want assistance managing
              the game, Gloomtility is a web-based board game assistance tool that speeds
              up the pace of play, while making it easier to manage all content. Unlike the base
              game out of the box, our project reduces the number of physical tokens and trackers
              players need to manage.
            </p>
            
            <span className="level-item">
              {this.state.showLevelInput || this.state.showHashInput ? this.renderOptionInputs() : this.renderDefaultButtons()}
            </span>

            <hr className='break'/>

            <FAQ/>

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
};

const mapDispatchToProps = (dispatch, ownProps) => {
  const rerouteToRoomPage = (response) => {
    ownProps.history.push(`/rooms/${response.data.hash}/`);
  };

  return {
    createRoom: (room) => dispatch(createRoom(rerouteToRoomPage, room)),
    getSession: (hash) => dispatch(getSession(hash)),
    clearSession: () => dispatch(clearSession)
  }
};


export default connect(mapStateToProps, mapDispatchToProps)(Home);