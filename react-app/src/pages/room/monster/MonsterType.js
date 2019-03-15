import React, { Component } from 'react';

import Monster from './Monster';
import MonsterTypeHeader from './MonsterTypeHeader';
import './monster.scss';

class MonsterType extends Component{


  generateMonsterInstances() {
    return this.props.type.monsterInstances.map((instance, index) =>
      isNormalTest(this.props, instance, index)
    );
  }
  generateEliteInstances() {
    return this.props.type.monsterInstances.map((instance, index) =>
      isEliteTest(this.props, instance, index)
    );
  }



  render() {
    return (
      <div className="monster-type">
        <MonsterTypeHeader monster={this.props.type}/>
        <table>
          { this.generateEliteInstances() }
          {this.generateMonsterInstances()}
        </table>
      </div>
    );
  }

  
}



function isEliteTest(props, instance, index) {

  if(instance.isElite){
    return <Monster instance={instance} type={props.type} key={instance.id} index={index} activeStatuses={instance.activeStatues}/>
  }
    
}

function isNormalTest(props, instance, index) {

  if(!instance.isElite){
    return <Monster instance={instance} type={props.type} key={instance.id} index={index} activeStatuses={instance.activeStatues}/>
  }
    
}
export default MonsterType;