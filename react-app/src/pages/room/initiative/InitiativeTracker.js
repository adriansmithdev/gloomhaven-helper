import React, { Component } from 'react';

import InitiativeItem from './InitiativeItem';

const leftArrow = String.fromCharCode(10096);
const rightArrow = String.fromCharCode(10097);

class InitiativeTracker extends Component {

  initialState = {
    hidden: true
  }

  constructor(props) {
    super(props);
    this.state = this.initialState;
    this.toggleHide = this.toggleHide.bind(this);
    this.getHidden = this.getHidden.bind(this);
    this.renderTypes = this.renderTypes.bind(this);
    this.sortTypesByInitiative = this.sortTypesByInitiative.bind(this);
  }

  toggleHide() {
    this.setState({
      ...this.state,
      hidden: !this.state.hidden
    });
  }

  getSymbol() {
    return (this.state.hidden) ?
      leftArrow : rightArrow;
  }

  getHidden() {
    return (this.state.hidden) ? 'hide' : '';
  }

  hasInstances(type) {
    return type.monsterInstances.length > 0;
  }

  hasAction(type) {
    return type.monsterAction !== undefined && type.monsterAction !== null;
  }

  renderTypes() {

    const validTypes = this.props.monsters.filter(type => 
      this.hasInstances(type) && this.hasAction(type)
    );

    const sortedTypes = this.sortTypesByInitiative(validTypes);

    return sortedTypes.map(type => 
      <InitiativeItem type={type} key={type.id} />
    );
  }

  sortTypesByInitiative(types) {
    return [...types].sort((type1, type2) => this.compareInitiativeOfTypes(type1, type2));
  }

  /**
   * Function used to sort types based on initiative in ascending 
   * order.
   */
  compareInitiativeOfTypes(type1, type2) {
    return type1.monsterAction.initiative - type2.monsterAction.initiative;
  }
  
  render() {
    return (
      <div className={`initiative-tracker ${this.getHidden()}`}>
        <div className="initiative-tracker-background"></div>
        <div className="initiative-tracker-container">
          <button type="button" 
            className="initiative-tracker-toggle" 
            onClick={this.toggleHide}
            title="Display Initiative order"
            >
            {this.getSymbol()}
          </button>
          <div className="initiative-list">
            {this.renderTypes()}
          </div>
        </div>
      </div>
    );
  }
}

export default InitiativeTracker;