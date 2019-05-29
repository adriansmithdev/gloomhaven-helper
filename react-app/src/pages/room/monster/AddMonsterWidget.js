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
        &nbsp;&nbsp;
        <span className="themed-font has-text-light is-size-4">Toggle Elite: </span> &nbsp;&nbsp;
        <button className={`elite-toggle ${(isElite ? 'elite' : '')} button is-dark themed-font`}
          onClick={this.props.toggleElite}>
          <div className="elite-toggle-fill">
            
          </div>
          <p className="toggle-text">{buttonText}</p>
        </button>
        <button className="add-monster-button" onClick={this.addMonster}>
          + <span className="hide-for-devices">Monster</span>
        </button>   
      </div>
    );
  }
  
}

export default AddMonsterWidget;