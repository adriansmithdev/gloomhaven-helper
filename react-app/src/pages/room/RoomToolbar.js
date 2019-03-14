import React, { Component } from 'react';
import { connect } from 'react-redux';

import ElementList from './elements/ElementList';

import { addMonster } from './../../store/actions/actions';
import EliteSwitch from './elements/EliteSwitch';


class RoomToolbar extends Component {

  constructor(props) {
    super(props);

    this.monsterSelect = React.createRef();
    this.addMonster = this.addMonster.bind(this);
  }

  addMonster() {
    const monsterId = this.monsterSelect.current.value;
    this.props.addMonster(this.props.room.hash, monsterId);
  }


  render() {
    const monsterTypes = this.props.monsters.map((type, index) =>
      <option value={type.id} key={index}>{type.name}</option>
    );

    return (
      <div className="room-toolbar">
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
        <ElementList />
        <EliteSwitch />
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
    addMonster: (hash, monsterId) => dispatch(addMonster(hash, monsterId))
  };
}


export default connect(mapStateToProps, mapDispachToProps)(RoomToolbar);