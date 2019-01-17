

import React, { Component } from 'react';
import './MonsterList.css';


class MonsterList extends Component {

  constructor(props){
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.addMonster = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  addMonster(event) {
    let newMonster = {
      name: "",
      maxHealth: 0,
      currentHealth: 0
    };

    this.props.monsters.push(newMonster);
  }

  render() {
    
    let rows = this.props.monsters.reduce((html, monster) => 
    (
      <li>
        Name: <input type="text" value={monster.name} onChange={this.handleChange} />
        HP
          <input type="number" value={monster.currentHealth} max={monster.maxHealth} min="0" onChange={this.handleChange} />
          /
          <input type="number" value={monster.maxHealth} onChange={this.handleChange} />
          <button type="button">X</button>
      </li>
    ), "");

    return (
      <div>
        <button onClick={this.addMonster}>Add Monster</button>
        <ul>{ rows }</ul>
      </div>
      
    );
  }
}

export default MonsterList;