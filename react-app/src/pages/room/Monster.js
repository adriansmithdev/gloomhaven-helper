
import React, { Component } from 'react';


class Monster extends Component {

  constructor(props){
    super(props);

    this.changeMonsterName = this.changeMonsterName.bind(this);
    this.changeCurrentHealth = this.changeCurrentHealth.bind(this);
    this.changeMaxHealth = this.changeMaxHealth.bind(this);
    this.removeMonster = this.removeMonster.bind(this);
  }

  changeMonsterName(event) {
    this.props.changeMonsterName(event.target.value, this.props.index);
  }
  changeCurrentHealth(event) {
    this.props.changeCurrentHealth(event.target.value, this.props.index);
  }
  changeMaxHealth(event) {
    this.props.changeMaxHealth(event.target.value, this.props.index);
  }

  removeMonster(event) {
    this.props.removeMonster(this.props.index);
  }

  render() {
    return (
      <li className="has-text-light">
        Name: 
        <input className="input is-small input-short" type="text" value={this.props.monster.name} onChange={this.changeMonsterName} />
        HP
        <input className="input is-small input-short" type="number" value={this.props.monster.currentHealth} max={this.props.monster.maxHealth} min="0" onChange={this.changeCurrentHealth} />
        /
        <input className="input is-small input-short" type="number" value={this.props.monster.maxHealth} onChange={this.changeMaxHealth} />
        <button className="button is-dark is-small" type="button" onClick={this.removeMonster}>X</button>
      </li>
      
    );
  }
}

export default Monster;