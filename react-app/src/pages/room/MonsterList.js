import React, {Component} from 'react';
import {connect} from 'react-redux';

import MonsterType from './MonsterType';

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

  generateTypes() {
    const types = this.props.monsters.map(type => {
      return <MonsterType type={type} />;
    });
  }

  render() {
    console.log(this.props);
    let monsterName = '';
    let idDisplayed = 1;
    
    const monsterTypes = this.props.allMonsterNames.map((monsterName, index) =>
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
          {this.generateTypes()}
        </ul>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    ...state.session
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    addMonster: (hash, monsterName) => dispatch(addMonster(hash, monsterName))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(MonsterList);