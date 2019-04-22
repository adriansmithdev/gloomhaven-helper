import React, {Component} from 'react';
import {connect} from 'react-redux';

import ElementList from './elements/ElementList';

import { addMonster, updateElement, incrementRound } from './../../store/actions/actions';
import EliteSwitch from './elements/EliteSwitch.js';
import RoundManager from './RoundManager';


export class RoomToolbar extends Component {

  constructor(props) {
    super(props);

    this.state = {
      isElite: false
    }

    this.monsterSelect = React.createRef();
    this.eliteToggle = React.createRef();
    this.addMonster = this.addMonster.bind(this);
    this.updateEliteStatus = this.updateEliteStatus.bind(this);

  }

  addMonster() {
    const monsterId = this.monsterSelect.current.value;
    if(this.props.room !== undefined) {
      this.props.addMonster(this.props.room.hash, monsterId, this.state.isElite);
    } else {
      console.error('Failed to add monster becuase room is undefined.')
    }
  }

  updateEliteStatus() {
    this.setState({
      ...this.state,
      isElite: !this.state.isElite
    });
  }

  render() {
    const monsterTypes = (this.props.monsters !== undefined) ? 
      this.props.monsters.map((type, index) =>
        <option value={type.id} key={index}>{type.name}</option>
      ) : '';

    const hasProps = this.props.room !== undefined;

    const { hash, elements } = (hasProps) ? this.props.room : {};
    return (
      <div className="room-toolbar columns">
        <div className="column is-half">
          <div className="is-horizontal field">
            <div className="control">
              <div className="select">
                <select className="input" ref={this.monsterSelect}>
                  {monsterTypes || ''}
                </select>
              </div>
            </div>
            <div className="control mr-1">
              <div className="level-right">
                <button className="button is-dark themed-font add-monster-button" onClick={this.addMonster}>
                  + Monster
                </button>
              </div>
            </div>
            
          <EliteSwitch updateEliteStatus={this.updateEliteStatus}/>
          </div>
        </div>
        
        <div className="column"> 
          <ElementList 
            hash={hash} 
            elements={elements} 
            updateElement={this.props.updateElement}
            />
        </div>
        <div className="column">

          <RoundManager room={this.props.room} incrementRound={this.props.incrementRound}/>
        </div>
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
    addMonster: (hash, monsterId, isElite) => dispatch(addMonster(hash, monsterId, isElite)),
    updateElement: (hash, element) => dispatch(updateElement(hash, element)),
    incrementRound: (room) => dispatch(incrementRound(room))
  };
}


export default connect(mapStateToProps, mapDispachToProps)(RoomToolbar);