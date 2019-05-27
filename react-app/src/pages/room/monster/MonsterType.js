import React, { Component } from 'react';

import MonsterInstance from './MonsterInstance';
import MonsterTypeHeader from './MonsterTypeHeader';
import MonsterActions from './MonsterActions';

export function sortByToken(instances) {
  return instances.sort((i1, i2) =>
    i1.token - i2.token
  );
}

class MonsterType extends Component{

  generateInstances(instances) {
    return instances.map(instance => 
      <MonsterInstance instance={instance} 
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
        <div className="monster-type-body">
          <MonsterActions monster={this.props.type}/>
          <div className="monster-instances">
            {this.generateInstances(eliteInstances)}
            {this.generateInstances(normalInstances)}
          </div>
        </div>
      </div>
    );
  }

  
}

export default MonsterType;