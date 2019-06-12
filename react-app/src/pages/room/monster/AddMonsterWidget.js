import React, { Component } from 'react';
class AddMonsterWidget extends Component {

  constructor(props) {
    super(props);
    this.monsterSelect = React.createRef();
    this.addMonster = this.addMonster.bind(this);
  }

  addMonster() {
    const monsterId = this.monsterSelect.current.value;
    this.props.addMonster(this.props.hash, monsterId, this.props.eliteToggle);
  }
  
  render() {
    const isElite = (this.props.eliteToggle)
    const buttonText = (isElite) ? 'Elite' : 'Normal';
    const monsterTypes = (this.props.monsters !== undefined) ? 
      this.props.monsters.map((type, index) =>
        <option value={type.id} key={index}>{type.name}</option>
      ) : '';
    return (
      <div className="add-monster-widget">
        <select className="monster-select" ref={this.monsterSelect}>
          {monsterTypes || ''}
        </select>
        <div className={`switch elite-toggle ${(isElite ? 'elite' : '')} themed-font`}
          onClick={this.props.toggleElite} title="Toggle whether monster is elite or not">
            <div className="elite-toggle-slider">
              <div className="elite-toggle-text elite">
                Elite
              </div>
              <div className="elite-toggle-divider"></div>
              <div className="elite-toggle-text"> 
                Normal
              </div>
            </div>
        </div>
        <button className="add-monster-button" onClick={this.addMonster}>
          {String.fromCharCode(10010)} <span className="hide-for-devices themed-font">Monster</span>
        </button>   
      </div>
    );
  }
  
}

export default AddMonsterWidget;