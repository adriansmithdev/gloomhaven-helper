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
        <button className={`elite-toggle ${(isElite ? 'elite' : '')}`}
          onClick={this.props.toggleElite}>
          {buttonText}
        </button>
        <button className="add-monster-button" onClick={this.addMonster}>
          + Monster
        </button>   
      </div>
    );
  }
  
}

export default AddMonsterWidget;