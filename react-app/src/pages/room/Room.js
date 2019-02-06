import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import MonsterList from './MonsterList';
import { connect } from 'react-redux';
import * as actionCreator from '../../store/actions/actions';
class Room extends Component {

    constructor(props){
        super(props);
        console.log(props);
        this.changeScenario = this.changeScenario.bind(this);
        this.createMonster = this.createMonster.bind(this);
    }

    changeScenario(event) {
      this.props.changeScenario(event.target.value);
      this.props.getRoom();
    }

    createMonster(event) {
      let newMonster = {
        name: "",
        currentHealth: 8,
        maxHealth: 8,
      }

      this.props.createMonster(newMonster)
    }

    componentWillMount() {
      this.props.getRoom();
    }


    render() {
      return (
        <div>
          <nav className="navbar is-black">
            <div className="navbar-brand">
              <div className="navbar-item">
                <h1 className="title themed-font has-text-light">Gloomhaven Helper</h1>
              </div>
              <div className="navbar-item">
                <strong className="has-text-light">Room: {this.props.match.params.roomHash}</strong>

              </div>
            </div>
            <div className="navbar-end">
              <div className="navbar-item">
                  <div className="field has-addons mr-1">
                    <div className="control">
                      <span className="button is-static">Scenario</span>
                    </div>
                    <div className="control is-expanded">
                    <input className="input input-short" type="number" onChange={this.changeScenario} min="1" max="150" defaultValue={this.props.room.scenarioNumber}/>
                    </div>
                  </div>
                </div>
            </div>
          </nav>
          <button className="button is-dark is-large themed-font m-2" onClick={this.createMonster}>Add Monster</button>
          <MonsterList/>

          <span className="input-group-btn">
            <Link className="button is-dark is-large themed-font m-2" to={`/`}>Back to home!</Link>
          </span>
        </div>
      );
    }


}

const mapStateToProps = (state) => {
  return { ...state };
}

const mapDispachToProps = (dispatch, ownProps) => {
  console.log(ownProps);
  const roomHash = ownProps.match.params.roomHash;
  return {
    getRoom: () => dispatch(actionCreator.getRoom(roomHash)),
    changeScenario: (value) => dispatch(actionCreator.changeScenario(roomHash, value)),
    createMonster: (monster) => dispatch(actionCreator.addMonster(roomHash, monster))
  };
}



export default connect(mapStateToProps, mapDispachToProps)(Room);