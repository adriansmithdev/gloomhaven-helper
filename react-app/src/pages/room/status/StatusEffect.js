import React, { Component } from 'react';


class StatusEffect extends Component {

  baseStyles = {
    display: 'inline-block',
    width: '2.5rem',
    height: '2.5rem',
  }
  inactiveStyles = {
    opacity: 0.25
  }

  render() {
    const usedStyles = (this.props.active === true) ? {
      ...this.baseStyles,
    } : {
      ...this.baseStyles,
      ...this.inactiveStyles
    }
    return (
      <div style={usedStyles} className="status-toggle">
        <img src={require(`./../../assets/icons/statuses/${this.props.status.name}.svg`)} alt={this.props.status.name} title={this.props.status.tooltip}/>
      </div>
    );
  }

  
}


export default StatusEffect;