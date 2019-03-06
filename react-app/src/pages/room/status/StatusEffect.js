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
    let newStatuses;
    if(this.statusIsActive()) {
      newStatuses = this.props.instance.activeStatuses.filter(current =>
         this.props.status.name !== current
      );
        
    } else {
      newStatuses = [
        ...this.props.instance.activeStatuses,
        this.props.status.name
      ];
    }

    const newMonster = {
      ...this.props.instance,
      activeStatuses: newStatuses
    }
    
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