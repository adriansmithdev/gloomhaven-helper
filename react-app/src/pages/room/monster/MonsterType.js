import React, { Component } from 'react';

import Monster from './Monster';
import MonsterTypeHeader from './MonsterTypeHeader';
import './monster.scss';

class MonsterType extends Component{


  generateMonsterInstances() {
    return this.props.type.monsterInstances.map((instance, index) =>
      <Monster instance={instance} type={this.props.type} key={instance.id} index={index} activeStatuses={instance.activeStatues}/>
    );
  }
  
  render() {
    return (
      <div className="monster-type">
        <MonsterTypeHeader monster={this.props.type}/>
        <table>
          {this.generateMonsterInstances()}
        </table>
      </div>
    );
  }

  
}


export default MonsterType;