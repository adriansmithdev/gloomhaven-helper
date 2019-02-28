import React, {Component} from 'react';
import {connect} from 'react-redux';
import {deleteMonster, updateMonster} from "../../store/actions/actions";
import ProgressBar from './../common/ProgressBar';
import MonsterInfo from "./MonsterInfo";

class Monster extends Component {

  constructor(props) {
    super(props);

    this.increaseHealth = this.increaseHealth.bind(this);
    this.decreaseHealth = this.decreaseHealth.bind(this);
    this.deleteMonster = this.deleteMonster.bind(this);
  }

  increaseHealth() {
    const newHealth = this.props.instance.currentHealth + 1;

    this.updateMonsterHealth(newHealth);
  }

  decreaseHealth() {
    const newHealth = this.props.instance.currentHealth - 1;

    this.updateMonsterHealth(newHealth);
  }

  updateMonsterHealth(newHealth) {
    const newMonster = {
      ...this.props.instance,
      currentHealth: newHealth
    }
    console.log(newMonster);
    this.props.updateMonster(this.props.hash, newMonster);
  }

  deleteMonster() {
    this.props.deleteMonster(this.props.hash, this.props.instance);
  }

  render() {
    return (
      <>
        <li className="columns" key={this.props.key}>
          <div className="column has-text-light">#: {this.props.index}</div>

          <div className="column">
            <ProgressBar
              title="HP"
              current={this.props.instance.currentHealth}
              max={this.props.type.maxHealth}
            />

          </div>
          <div className="column">
            <button type="button" className="button is-dark" onClick={this.decreaseHealth}>
              -
            </button>
            <button type="button" className="button is-dark" onClick={this.increaseHealth}>
              +
            </button>
          </div>
          <div className="column">
            <button type="button" className="button is-dark themed-font" onClick={this.deleteMonster}>X</button>
          </div>
        </li>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    hash: state.session.room.hash
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    updateMonster: (hash, monster) => dispatch(updateMonster(hash, monster)),
    deleteMonster: (hash, monster) => dispatch(deleteMonster(hash, monster))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(Monster);