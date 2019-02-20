import React, { Component } from 'react';
import { connect } from 'react-redux';
import { removeMonster } from './../../store/actions/actions';

class Monster extends Component {

  render() {
    console.log(this.props.instance)
    return (
      <li className="">
        Name: {this.props.instance.monster.name} HP: 
          Current
          <input type="number" defaultValue={this.props.instance.currentHealth} max={this.props.instance.maxHealth} min="0" />
          /
          Max: {this.props.instance.maxHealth}
          <button type="button">X</button>
      </li>
    );
  }
}

const mapStateToProps = (state) => {
  return {};
}

const mapDispatchToProps = (dispatch) => {
  return {
    removeMonster: (monster) => dispatch(removeMonster(monster))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(Monster);