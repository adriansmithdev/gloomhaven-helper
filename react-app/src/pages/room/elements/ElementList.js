import React from 'react';
import { connect } from 'react-redux';

import { updateElement } from './../../../store/actions/actions';

import Element from './Element';
import './element.scss';

const ElementList = (props) => {

  

  const generateElements = () => {
    return props.elements.map(element => {

      const cycleElementStatus = () => {
        props.updateElement(props.hash, element);
      }

      return (
        <Element key={element.id} 
          element={element} 
          cycleElementStatus={cycleElementStatus} 
        />
      )     
    });
  }

  return (
    <div className="element-list column">
      { generateElements() }
    </div>
  );
}





// Redux interactions.
const mapStateToProps = (state) => {
  return {
    hash: state.session.room.hash,
    elements: [...state.session.room.elements]
  }
}


const mapDispatchToProps = (dispatch) => {
  return {
    updateElement: (hash, element) => dispatch(updateElement(hash, element))
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ElementList);