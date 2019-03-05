import React, { Component } from 'react';

import Monster from './Monster';
import MonsterInfo from "./MonsterInfo";

class MonsterType extends Component{


  generateMonsterInstances() {
    return this.props.type.monsterInstances.map((instance, index) =>
      <Monster instance={instance} type={this.props.type} key={instance.id} index={index} activeStatuses={instance.activeStatues}/>
    );
  }
  
  render() {
    return (
      <>
        <MonsterInfo monster={this.props.type}/>
        {this.generateMonsterInstances()}
      </>
    );
  }

  
}


export default MonsterType;