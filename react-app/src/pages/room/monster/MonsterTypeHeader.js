import React, {Component} from 'react';
import {connect} from 'react-redux';
import Attack from './../../../assets/icons/stats/attack.svg';
import Movement from './../../../assets/icons/stats/movement.svg';
import Range from './../../../assets/icons/stats/range.svg';


class MonsterTypeHeader extends Component {
  render() {
    let monster = this.props.monster;
    let portrait;
    try {
      portrait = require(`./../../../assets/portraits/monsters/${monster.name}.png`);
    } catch(e) {
      console.log('Failed to find portrait!');
      portrait = require('./../../../assets/portraits/monsters/default.svg');
    }
    return (
      <div className="monster-type-header themed-font">
        <img src={portrait} 
          className="portrait"
          alt=""
        />
        <div className="monster-name">
          {monster.name}
        </div>
        <div className="monster-stats">
          <div className="monster-stat" title={`Allows a character to do a base ${monster.attack} damage with each attack.`}>
            <img src={Attack} className="inline-icon" alt="Attack: " />
            {monster.attack}
          </div>
          <div className="monster-stat" title={`Allows a character to move up to ${monster.movement} hex(es) on the map.`}>
            <img src={Movement} className="inline-icon" alt="Movement: " />
            {monster.movement}
          </div>
          <div className="monster-stat" title={`Allows a character to attack other characters within ${monster.range} hex(es) of distance.`}>
            <img src={Range} className="inline-icon" alt="Range: " />
            {monster.range}
          </div>
          <div className="monster-stat has-text-warning" title={`Allows an Elite character to do a base ${monster.eliteAttack} damage with each attack.`}>
           <img src={Attack} className="inline-icon" alt="Elite Attack: " />
           {monster.eliteAttack}
         </div>
         <div className="monster-stat has-text-warning" title={`Allows a character to move up to ${monster.eliteMove} hex(es) on the map.`}>
           <img src={Movement} className="inline-icon" alt="Elite Movement: " />
           {monster.eliteMove}
         </div>
         <div className="monster-stat has-text-warning" title={`Allows a character to attack other characters within ${monster.eliteRange} hex(es) of distance.`}>
           <img src={Range} className="inline-icon" alt="Elite Range: " />
           {monster.eliteRange}
         </div>

        </div>
    
        <div className="">
          {monster.attributes !== undefined ? "Traits: " + monster.attributes : ''}
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {

  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    //UI Events
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(MonsterTypeHeader);