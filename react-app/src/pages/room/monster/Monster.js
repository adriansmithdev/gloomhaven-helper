import React, {Component} from 'react';
import {connect} from 'react-redux';

// Redux Actions
import {deleteMonster, updateMonster} from "../../../store/actions/actions";

// Sub Components
import ProgressBar from './../../common/ProgressBar';
import StatusEffect from '../status/StatusEffect';
import MonsterToken from './MonsterToken';

class Monster extends Component {

  constructor(props) {
    super(props);

    this.state = {
      showInactiveConditions: true
    }

    this.increaseHealth = this.increaseHealth.bind(this);
    this.decreaseHealth = this.decreaseHealth.bind(this);
    this.deleteMonster = this.deleteMonster.bind(this);
    this.updateMonsterToken = this.updateMonsterToken.bind(this);
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

  updateMonsterToken(newToken) {
    const newMonster = {
      ...this.props.instance,
      token: newToken
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

    const rowClasses = (this.props.instance.isElite) ?
      'monster-instance elite' : 'monster-instance'

    const maxHealth = this.props.instance.isElite ?
      this.props.type.eliteHealth : this.props.type.health;

    return (
      <tr className={rowClasses} key={this.props.key}>
        <td className="monster-identifier" >
          <MonsterToken token={this.props.instance.token} 
            updateMonsterToken={this.updateMonsterToken}
          />
        </td>

        <td className="monster-healthbar">
          <ProgressBar
            title="HP"
            current={this.props.instance.currentHealth}
            max={maxHealth}
          />
          <div className="button-bar">
            <button type="button" className="button-add-health" onClick={this.decreaseHealth}>
              -
            </button>
            <button type="button" className="button-remove-health" onClick={this.increaseHealth}>
              +
            </button>
          </div>
          
        </td>
        <td className="monster-statuses">
          <div className="status-container">
            {statuses}
          </div>
        </td>
        <td className="monster-controls">
          <div className="button-bar">
            <button className="status-display-button"
              onClick={this.toggleInactiveStatuses.bind(this)}>
              Statuses
            </button>
            <button className="button-remove" type="button" onClick={this.deleteMonster}>X</button>

          </div>
          
        </td>
      </tr>
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