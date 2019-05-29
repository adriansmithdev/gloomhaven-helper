import React, { Component } from 'react';
import { connect } from 'react-redux';
import { addMonster, drawAction } from './../../../store/actions/actions';

import MonsterType from './MonsterType';

class MonsterList extends Component {

  constructor(props) {
    super(props);

    this.monsterSelect = React.createRef();
  }

  generateTypes() {
    // Filter out types without any instances
    return this.props.monsters.filter(type =>
      type.monsterInstances.length > 0
    ).map(type =>
      <MonsterType hash={this.props.room.hash} 
        key={type.id} 
        type={type}
        eliteToggle={this.props.eliteToggle} 
        addMonster={this.props.addMonster}
        drawAction={this.props.drawAction}
        
        />
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
    ...state.session,
    eliteToggle: state.eliteToggle
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    addMonster: (hash, monsterId, isElite) => dispatch(addMonster(hash, monsterId, isElite)),
    drawAction: (hash, monsterId) => dispatch(drawAction(hash, monsterId))
  };
};


export default connect(mapStateToProps, mapDispatchToProps)(MonsterList);