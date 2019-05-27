import React, {Component} from 'react';

import MonsterAttribute from './MonsterAttribute';
import IconManager from './../../common/IconManager';

class MonsterTypeHeader extends Component {

  constructor(props) {
    super(props);

    this.addMonsterEvent = this.addMonsterEvent.bind(this);
  }

  parseMonsterAttribute(attribute) {
    const splitStat = attribute.split(' ');

    const icon = IconManager(`%${splitStat[0].toLowerCase()}%`);
    if(icon !== undefined) {
      const value = (splitStat[1] !== undefined) ?
        splitStat[1].replace(',', '') : '';
      return <MonsterAttribute src={icon.src} alt={icon.alt} value={value}/>
    } else {
      return <span className="text-attribute">{attribute}</span>;
    }
  }

  addMonsterEvent(event) {

    const hash = this.props.hash;

    const monsterid = this.props.type.id;
    
    const eliteStatus = this.props.eliteToggle;

    this.props.addMonster(hash, monsterid, eliteStatus);
  }

  render() {
    let monster = this.props.type;
    let portrait;

    const attack = IconManager('%attack%');
    const move   = IconManager('%move%');
    const range  = IconManager('%range%');

    try {
      portrait = require(`./../../../assets/portraits/monsters/mini/${monster.name}.png`);
    } catch(e) {
      console.log('Failed to find portrait!');
      portrait = require('./../../../assets/portraits/monsters/mini/default.svg');
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
            <img src={attack.src} className="inline-icon" alt={attack.alt} />
            <div>
              <p className="monster-elite-stat">{monster.eliteAttack}</p>
              <p className="monster-normal-stat">{monster.attack}</p>
            </div>
          </div>
          <div className="monster-stat" title="Number of tiles the monsters is allowed to move.">
            <img src={move.src} className="inline-icon" alt={move.alt} />
            <div>
              <p className="monster-elite-stat">{monster.eliteMove}</p>
              <p className="monster-normal-stat">{monster.movement}</p>
            </div>
          </div>
          <div className="monster-stat" title="Number of tiles that the monsters can attack from">
            <img src={range.src} className="inline-icon" alt={range.alt} />
            <div>
              <p className="monster-elite-stat">{monster.eliteRange}</p>
              <p className="monster-normal-stat">{monster.range}</p>
            </div>
          </div>
          
        </div>
    
        <div className="monster-attributes">
          <div className="elite-attributes">
            {monster.eliteAttributes.map(attribute =>
              this.parseMonsterAttribute(attribute))}
          </div>
          <div className="normal-attributes">
            {monster.attributes.map(attribute =>
              this.parseMonsterAttribute(attribute))}
          </div>
          
        </div>
        <div className="monster-type-controls">
          <button className="monster-header-add-button" onClick={this.addMonsterEvent}>
            {String.fromCharCode(10010)}
          </button>
        </div>
      </div>
    );
  }
}

export default MonsterTypeHeader;