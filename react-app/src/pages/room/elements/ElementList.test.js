import React from 'react';
import { shallow, configure, mount, render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';


import { ElementList, cycleElement } from './ElementList';

configure({ adapter: new Adapter() });

describe('Testing <ElementList />:', function() {
  it('should render without throwing an error', function() {
    expect(render(<ElementList/>));
  });

  it('cycle elements properly cycles an element.', () => {

    const oldElement = {
      id: 2152,
      type: 'FIRE',
      strength: 0
    };
    const cycledElement = cycleElement(oldElement);
    expect(cycledElement.strength).not.toBe(oldElement.strength);

  });
});