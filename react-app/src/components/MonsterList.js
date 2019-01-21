

import React, { Component } from 'react';
import Monster from './Monster'

class MonsterList extends Component {

  // Dummy object to be replaced with live data later.
  monsters = [
    {
      id: 0,
      name: "Skeleton",
      maxHealth: 100,
      currentHealth: 100
    },
    {
      id: 1,
      name: "Zombie",
      maxHealth: 125,
      currentHealth: 75
    }
  ];

  constructor(props){
    super(props);
    this.state = {
      monsters: this.monsters
    };
    this.changeMonsterName = this.changeMonsterName.bind(this);
    this.changeCurrentHealth = this.changeCurrentHealth.bind(this);
    this.changeMaxHealth = this.changeMaxHealth.bind(this);
    this.addMonster = this.addMonster.bind(this);
    this.removeMonster = this.removeMonster.bind(this);
  }

  changeMonsterName(value, index) {
    this.setState(state => {
      let newMonster = this.state.monsters[index];
      newMonster.name = value;
      let newMonsters = this.state.monsters.slice();
      newMonsters.splice(index, 1, newMonster);

      return {monsters: newMonsters};
    });
  }

  changeCurrentHealth(value, index) {
    this.setState(state =>  {
      let newMonster = this.state.monsters[index];
      newMonster.currentHealth = value;
      let newMonsters = this.state.monsters.slice();
      newMonsters.splice(index, 1, newMonster);

      return {monsters: newMonsters};
    });
  }

  changeMaxHealth(value, index) {
    this.setState(state =>  {
      let newMonster = this.state.monsters[index];
      newMonster.maxHealth = value;
      let newMonsters = this.state.monsters.slice();
      newMonsters.splice(index, 1, newMonster);

      return {monsters: newMonsters};
    });
  }

  addMonster(event) {
    this.setState(state =>  {
      let newMonster = {
        id: Math.round(Math.random() * 99999),
        name: "",
        maxHealth: 0,
        currentHealth: 0
      };
      let newMonsters = this.state.monsters.concat([newMonster]);

      return {monsters: newMonsters};
    });

  }

  removeMonster(index) {
    this.setState(state => {
      let newMonsters = this.state.monsters.slice();
      newMonsters.splice(index, 1);
      return {monsters: newMonsters};
    });
  }

  render() {
    let rows = this.state.monsters.map((monster, index) => 
    (
      <Monster key={monster.id} monster={monster}
        index={index} 
        removeMonster={this.removeMonster}
        changeMonsterName={this.changeMonsterName} 
        changeCurrentHealth={this.changeCurrentHealth}
        changeMaxHealth={this.changeMaxHealth}
      />
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