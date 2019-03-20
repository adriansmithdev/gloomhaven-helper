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
          <div className="monster-stat" title="Base damage dealt by monsters with each attack.">
            <img src={Attack} className="inline-icon" alt="Attack: " />
            <div>
              <p className="monster-elite-stat">{monster.eliteAttack}</p>
              <p className="monster-normal-stat">{monster.attack}</p>
            </div>
          </div>
          <div className="monster-stat" title="Number of tiles the monsters is allowed to move.">
            <img src={Movement} className="inline-icon" alt="Movement: " />
            <div>
              <p className="monster-elite-stat">{monster.eliteMove}</p>
              <p className="monster-normal-stat">{monster.movement}</p>
            </div>
          </div>
          <div className="monster-stat" title="Number of tiles that the monsters can attack from">
            <img src={Range} className="inline-icon" alt="Range: " />
            <div>
              <p className="monster-elite-stat">{monster.eliteRange}</p>
              <p className="monster-normal-stat">{monster.range}</p>
            </div>
          </div>
          
        </div>
    
        <div className="monster-attributes">
          <div className="elite-attributes">
            {monster.eliteAttributes.join(', ')}
          </div>
          <div className="normal-attributes">
            {monster.attributes.join(', ')}
          </div>
          
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