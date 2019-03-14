import React from 'react';
import { connect } from 'react-redux';

import Element from './Element';
import './element.scss';

const ElementList = (props) => {
  console.log(props);
  const generateElements = () => {
    return props.elements.map(element => 
      <Element element={element} />)
  }

  return (
    <div className="element-list">
      { generateElements() }
    </div>
  );
}

const mapStateToProps = (state) => {
  return {
    elements: [...state.elements]
  }
}

const mapDispatchToProps = (dispatch) => {
  return {

  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ElementList);