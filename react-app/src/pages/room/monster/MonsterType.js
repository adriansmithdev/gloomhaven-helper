import React, { Component } from 'react';

import Monster from './Monster';
import MonsterTypeHeader from './MonsterTypeHeader';
import './monster.scss';

class MonsterType extends Component{

  sortByToken(instances) {
    return instances.sort((i1, i2) =>
      i1.token - i2.token
    );
  }

  generateInstances(instances) {
    return instances.map(instance => 
      <Monster instance={instance} 
        type={this.props.type} 
        key={instance.id}
      />
    );
  }

  render() {
    const eliteInstances = [];
    const normalInstances = [];
    this.props.type.monsterInstances.forEach(instance => {
      if(instance.isElite) {
        eliteInstances.push({...instance});
      } else {
        normalInstances.push({...instance});
      }
    });

    this.sortByToken(eliteInstances);
    this.sortByToken(normalInstances);

    return (
      <div className="monster-type">
        <MonsterTypeHeader monster={this.props.type}/>
        <table>
          <tbody>
            {this.generateInstances(eliteInstances)}
            {this.generateInstances(normalInstances)}
          </tbody>
        </table>
      </div>
    );
  }

  
}

export default MonsterType;