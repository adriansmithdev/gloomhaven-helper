import React from 'react';
import { connect } from 'react-redux';

import Element from './Element';
import './element.scss';

const ElementList = (props) => {
  console.log(props);
  const generateElements = () => {
    return props.elements.map(element => 
      <Element key={element.name} element={element} />)
  }

  return (
    <div className="element-list column">
      { generateElements() }
    </div>
  );
}

const mapStateToProps = (state) => {
  return {
    elements: [...state.session.room.elements]
  }
}

const mapDispatchToProps = (dispatch) => {
  return {

  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ElementList);