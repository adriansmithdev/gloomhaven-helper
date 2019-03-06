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
          <div className="monster-stat">
            <img src={Attack} className="inline-icon" alt="Attack: " title="Allows a character to do a base X amount of damage to an enemy within their range." />
            {monster.attack}
          </div>
          <div className="monster-stat">
            <img src={Movement} className="inline-icon" alt="Movement: " title="Allows a character to move up to X number of hexes on the map."/>
            {monster.movement}
          </div>
          <div className="monster-stat">
            <img src={Range} className="inline-icon" alt="Range: " title="Any enemy within Y hexes can be targeted by the attack."/>
            {monster.range}
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