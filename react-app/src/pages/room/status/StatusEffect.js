import React, { Component } from 'react';
import { updateMonster } from '../../../store/actions/actions';
import { connect } from 'react-redux';


class StatusEffect extends Component {

  baseStyles = {
    display: 'inline-block',
    width: '2.5rem',
    height: '2.5rem',
  }
  inactiveStyles = {
    opacity: 0.25
  }

  statusIsActive() {
    return this.props.instance.activeStatuses.find(current => 
      current === this.props.status.name
    ) !== undefined;
  }

  toggleStatus() {
    let newStatuses = [...this.props.instance.activeStatuses];
    if(this.statusIsActive()) {
      const indexOfActive = newStatuses.indexOf(this.props.status);
      newStatuses.splice(indexOfActive, 1);
    } else {
      newStatuses.push(this.props.status.name);
    }

    const newMonster = {
      ...this.props.instance,
      activeStatuses: newStatuses
    }

    console.log(newMonster);

    this.props.updateMonster(this.props.hash, newMonster);
    
  }

  render() {
    const usedStyles = (this.statusIsActive()) ? {
      ...this.baseStyles,
    } : {
      ...this.baseStyles,
      ...this.inactiveStyles
    }
    return (
      <div style={usedStyles} className="status-toggle">
        <img src={require(`./../../../assets/icons/statuses/${this.props.status.name}.svg`)} 
          alt={this.props.status.name} 
          title={this.props.status.tooltip}
          onClick={this.toggleStatus.bind(this)}
        />
      </div>
    );
  }

  
}

const mapStateToProps = (state) => {
  return {
    hash: state.session.room.hash
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    updateMonster: (hash, newMonster) => dispatch(updateMonster(hash, newMonster))
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(StatusEffect);