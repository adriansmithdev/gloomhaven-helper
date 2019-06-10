import React, {Component} from 'react';
import {connect} from 'react-redux';

import ElementList from './elements/ElementList';

import { updateElement, incrementRound } from '../../store/actions/actions';
import RoundManager from './RoundManager';


export class StickyToolbar extends Component {
  
  render() {

    const hasProps = this.props.room !== undefined;

    const { hash, elements } = (hasProps) ? this.props.room : {};
    return (
      <div className="sticky-toolbar">
        <div className="sticky-toolbar-content">   
          <ElementList 
            hash={hash} 
            elements={elements} 
            updateElement={this.props.updateElement}
            />

          <RoundManager room={this.props.room} incrementRound={this.props.incrementRound}/>
        </div>
      </div>
      
    );
  }

}

const mapStateToProps = (state) => {
  return {
    ...state.session
  };
}

const mapDispachToProps = (dispatch) => {
  return {
    
    updateElement: (hash, element) => dispatch(updateElement(hash, element)),
    incrementRound: (room) => dispatch(incrementRound(room)),
  };
}


export default connect(mapStateToProps, mapDispachToProps)(StickyToolbar);