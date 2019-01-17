

import React, { Component } from 'react';
import './MonsterList.css';


class MonsterList extends Component {

  constructor(props){
    super(props);
    this.state = {value: ''};

    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  render() {

    let listItems = this.props.monsters.map(monster => {
      console.log(monster.name);
    return monster;
    })
    console.log(listItems)

    var rows=[];
    for(var i=0; i<2;i++){
      rows.push(<li key={i}>
      Name: 
        <input type="text" value={listItems[i].name} onChange={this.handleChange} />
      Health
      Current
        <input type="number" value={listItems[i].currentHealth} onChange={this.handleChange} />
      Max
        <input type="number" value={listItems[i].maxHealth} onChange={this.handleChange} />
    </li>)
    }
    

    // let listItems = this.props.monsters.reduce(monster =>
    //   <li><input type="text" name="name" />><input type="number" /></li>
    // , "");

    return (
        <ul>
             New Monster Name: <input value={this.state.value} onChange={this.handleChange} fullWidth={true} /> <button>Add Monster</button>
             Health
              Current
                <input type="number" value={this.state.value} onChange={this.handleChange} />
              Max
                <input type="number" value={this.state.value} onChange={this.handleChange} />
            { rows }
        </ul>
    );
  }
}

export default MonsterList;