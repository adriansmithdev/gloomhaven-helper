import React, {Component} from 'react';
import {connect} from 'react-redux';

import Monster from './Monster';

import {addMonster} from './../../store/actions/actions';

class MonsterList extends Component {

  constructor(props) {
    super(props);

    this.monsterSelect = React.createRef();
    this.addMonster = this.addMonster.bind(this);
  }

  addMonster() {
    const monsterName = this.monsterSelect.current.value;
    this.props.addMonster(this.props.room.hash, monsterName);
  }

  render() {
    let monsterName = '';
    let idDisplayed = 1;
    const monsters = this.props.room.monsterInstances
      .sort((a, b) => a.monster.name.localeCompare(b.monster.name))
      .map((monster, index) => {
          if (monsterName === monster.monster.name) {
            idDisplayed += 1;
            return <Monster instance={monster} key={monster.id} id={idDisplayed} firstElement={false}/>
          }
          monsterName = monster.monster.name;
          idDisplayed = 1;
          return <Monster instance={monster} key={monster.id} id={idDisplayed} firstElement={true}/>
        }
      );
    const monsterTypes = this.props.room.monsterNames.map((monsterName, index) =>
      <option value={monsterName} key={index}>{monsterName}</option>
    );

    return (
      <div className="monster-list m-2">
        <div className="control">
          <div className="select">
            <select className="input" ref={this.monsterSelect}>
              {monsterTypes}
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

        <ul>
          {monsters}
        </ul>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    room: state.room
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    addMonster: (hash, monsterName) => dispatch(addMonster(hash, monsterName))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(MonsterList);