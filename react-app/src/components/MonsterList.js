

import React, { Component } from 'react';
import './MonsterList.css';


class MonsterList extends Component {

  constructor(props){
    super(props);
    this.state = {
      monsters: props.monsters
    };

    console.log(this.state.monsters);
    this.handleChange = this.handleChange.bind(this);
    this.addMonster = this.addMonster.bind(this);
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

    this.state.monsters.push(newMonster)

    this.forceUpdate();
  }

  removeMonster(index) {
    this.state.monsters.splice(index, 1)

    this.forceUpdate();
  }

  render() {
    
    let rows = this.state.monsters.map((monster, index) => 
    (
      <li key={index}>
        Name: <input type="text" defaultValue={monster.name} onChange={this.handleChange} />
        HP
          <input type="number" defaultValue={monster.currentHealth} max={monster.maxHealth} min="0" onChange={this.handleChange} />
          /
          <input type="number" defaultValue={monster.maxHealth} onChange={this.handleChange} />
          <button type="button" onClick={()=>{this.removeMonster(index)}}>X</button>
      </li>
    ));

    return (
      <div>
        <button onClick={this.addMonster}>Add Monster</button>
        <ul>{ rows }</ul>
      </div>
      
    );
  }
}

export default MonsterList;