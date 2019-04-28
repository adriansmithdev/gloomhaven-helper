import React from 'react';

import Element from './Element';

export const cycleElement = (element) => {
  // Pick next element strength in cycle.
  const newVal = 
    (element.strength === 1) ? (0) : 
    (element.strength === 2) ? (1) : (2);
  const newElement = {
    ...element,
    strength: newVal
  }

  return newElement;
}

export const ElementList = (props) => {
  const generateElements = (elements) => {
    return elements.map(element => {

      const cycleElementStatus = () => {
        const newElement = cycleElement(element);
        props.updateElement(props.hash, newElement);
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
    <div className="element-list">
      { (props.elements !== undefined) ? generateElements(props.elements) : '' }
    </div>
  );
}

export default ElementList;