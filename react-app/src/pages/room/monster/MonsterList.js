import React, {Component} from 'react';
import {connect} from 'react-redux';

import MonsterType from './MonsterType';

class MonsterList extends Component {

  constructor(props) {
    super(props);

    this.monsterSelect = React.createRef();
    this.addMonster = this.addMonster.bind(this);
  }

  addMonster() {
    const monsterId = this.monsterSelect.current.value;
    this.props.addMonster(this.props.room.hash, monsterId);
  }

  generateTypes() {
    // Filter out types without any instances
    return this.props.monsters.filter(type =>
      type.monsterInstances.length > 0
    ).map(type =>
      <MonsterType key={type.id} type={type} />
    );
  }

  render() {

    return (
      <div className="monster-list">
        
        {this.generateTypes()}
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
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(MonsterList);