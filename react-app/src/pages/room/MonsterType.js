import React, { Component } from 'react';

import Monster from './Monster';
import MonsterInfo from "./MonsterInfo";

class MonsterType extends Component{


  generateMonsterInstances() {
    return this.props.type.monsterInstances.map((instance, index) =>
      <Monster instance={instance} type={this.props.type} key={instance.id} index={index} activeStatuses={instance.activeStatues}/>
    );
  }

  generateMonsterInfoHeader() {
    return <MonsterInfo monster={this.props.type}/>;
  }
  
  render() {
    return (
      <>
        {this.props.type.monsterInstances.length !== 0 ? this.generateMonsterInfoHeader() : ''}
        {this.generateMonsterInstances()}
      </>
    );
  }

  
}


export default MonsterType;