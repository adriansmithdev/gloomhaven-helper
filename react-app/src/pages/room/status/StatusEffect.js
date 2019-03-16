import React, { Component } from 'react';
import { updateMonster } from '../../../store/actions/actions';
import { connect } from 'react-redux';


class StatusEffect extends Component {


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
    const classes = this.statusIsActive() ? 'status-toggle active' : 'status-toggle'
    return (this.props.showInactive && !this.statusIsActive()) ? (
      <></>
    ) : (
      <div className={classes}>
        <img src={require(`./../../../assets/icons/statuses/${this.props.status.name}.svg`)} 
          alt=""
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