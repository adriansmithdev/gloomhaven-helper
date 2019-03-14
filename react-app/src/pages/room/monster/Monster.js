import React, {Component} from 'react';
import {connect} from 'react-redux';
import {deleteMonster, updateMonster} from "../../../store/actions/actions";
import ProgressBar from './../../common/ProgressBar';
import StatusEffect from '../status/StatusEffect';

class Monster extends Component {

  constructor(props) {
    super(props);

    this.state = {
      showInactiveConditions: true
    }

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
    this.props.updateMonster(this.props.hash, newMonster);
  }

  deleteMonster() {
    this.props.deleteMonster(this.props.hash, this.props.instance);
  }

  toggleInactiveStatuses() {
    this.setState({
      ...this.state,
      showInactiveConditions: !this.state.showInactiveConditions
    });
  }

  

  render() {

    const statuses = this.props.statuses.map(status => (
        <StatusEffect key={status.id} 
          showInactive={this.state.showInactiveConditions} 
          instance={this.props.instance} 
          status={status}
        />
    ));

    const maxHealth = this.props.instance.isElite ?
      this.props.type.eliteHealth : this.props.type.health;

    const eliteColor =  this.props.instance.isElite ?
      'has-text-warning monster-identifier' : 'monster-identifier';


    return (
      <tbody>
      <tr className="monster-instance" key={this.props.key}>
        <td className={eliteColor} >
          #: {this.props.index + 1}
        </td>

        <td className="monster-healthbar">
          <ProgressBar
            title="HP"
            current={this.props.instance.currentHealth}
            max={maxHealth}
          />
        </td>
        <td className="monster-health-buttons">
          <button type="button" className="button-add-health" onClick={this.decreaseHealth}>
            -
          </button>
          <button type="button" className="button-remove-health" onClick={this.increaseHealth}>
            +
          </button>
        </td>
        <td className="monster-statuses">
          {statuses}
          <button className="button is-dark" 
            onClick={this.toggleInactiveStatuses.bind(this)}>
            Statuses
          </button>
        </td>
        <td className="monster-remove">
          <button type="button" onClick={this.deleteMonster}>X</button>
        </td>
      </tr>
      </tbody>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    hash: state.session.room.hash,
    statuses: state.session.statuses
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    updateMonster: (hash, monster) => dispatch(updateMonster(hash, monster)),
    deleteMonster: (hash, monster) => dispatch(deleteMonster(hash, monster))
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(Monster);