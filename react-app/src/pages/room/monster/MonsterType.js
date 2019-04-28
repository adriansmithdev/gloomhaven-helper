import React, { Component } from 'react';

import Monster from './Monster';
import MonsterTypeHeader from './MonsterTypeHeader';

export function sortByToken(instances) {
  return instances.sort((i1, i2) =>
    i1.token - i2.token
  );
}

class MonsterType extends Component{

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

    sortByToken(eliteInstances);
    sortByToken(normalInstances);

    return (
      <div id={this.props.type.name} className="monster-type">
        <MonsterTypeHeader {...this.props}/>
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