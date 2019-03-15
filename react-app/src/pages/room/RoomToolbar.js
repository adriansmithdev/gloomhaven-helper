import React, {Component} from 'react';
import {connect} from 'react-redux';

import ElementList from './elements/ElementList';

import {addMonster} from './../../store/actions/actions';
import EliteSwitch from './elements/EliteSwitch.js';
import RoundManager from "./RoundManager";


class RoomToolbar extends Component {

  constructor(props) {
    super(props);

    this.state = {
      isElite: false
    }

    this.monsterSelect = React.createRef();
    this.addMonster = this.addMonster.bind(this);
    this.eliteToggle = React.createRef();
    this.updateEliteStatus = this.updateEliteStatus.bind(this);

  }

  addMonster() {
    const monsterId = this.monsterSelect.current.value;
    this.props.addMonster(this.props.room.hash, monsterId, this.state.isElite);
  }

  updateEliteStatus() {
    this.setState({
      ...this.state,
      isElite: !this.state.isElite

    })
  }

  render() {
    const monsterTypes = this.props.monsters.map((type, index) =>
      <option value={type.id} key={index}>{type.name}</option>
    );

    return (
      <div className="room-toolbar columns">
        <div className="column is-horizontal field">
          <div className="control">
            <div className="select">
              <select className="input" ref={this.monsterSelect}>
                {monsterTypes || ''}
              </select>
            </div>
          </div>
          <div className="control">
            <div className="level-right">
              <button className="button is-dark themed-font" onClick={this.addMonster}>
                + Add Monster
              </button>
            </div>
          </div>
        </div>
        <EliteSwitch updateEliteStatus={this.updateEliteStatus}/>
        <ElementList/>
        <RoundManager round={this.props.room.round}/>
      </div>
    );
  }

}

const mapStateToProps = (state) => {
  return {
    ...state.session
  };
}

const mapDispachToProps = (dispatch) => {
  return {
    addMonster: (hash, monsterId, isElite) => dispatch(addMonster(hash, monsterId, isElite))
  };
}


export default connect(mapStateToProps, mapDispachToProps)(RoomToolbar);