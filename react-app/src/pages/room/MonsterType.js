import React, { Component } from 'react';

import Monster from './Monster';

class MonsterType extends Component{


  generateMonsterInstances() {
    return this.props.type.monsterInstances.map((instance, index) =>
      <Monster instance={instance} type={this.props.type} key={instance.id} index={index}/>
    );
  }
  render() {
    return (
      <>
        {this.generateMonsterInstances()}
      </>
    );
  }
}


export default MonsterType;