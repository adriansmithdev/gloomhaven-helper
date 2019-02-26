import React, { Component } from 'react';

import Monster from './Monster';

class MonsterType extends Component{


  generateMonsterInstances() {
    return this.props.type.map(instance =>
      <Monster instance={instance} key={instance.id}/>
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