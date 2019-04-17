import React from 'react';
import { shallow, configure, mount, render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';


import { Monster } from './Monster';

configure({ adapter: new Adapter() });

describe('Testing <Monster />:', function() {
  it('should render without throwing an error', function() {
    expect(shallow(<Monster/>));
  });

  it('test monster button controls', function () {
    const dummyInstance = {
      id: 1254,
      currentHealth: 6,
      isElite: false,
      activeStatuses: [],
      monsterId: 1674,
      token: 1
    };

    const dummyType = {
      id: 1674,
      name: 'Hound',
      attributes: [
          'Retaliate 1'
      ],
      eliteAttributes: [
          'Retaliate 2'
      ],
      level: 2,
      health: 6,
      movement: 4,
      attack: 2,
      range: 0,
      eliteHealth: 7,
      eliteMove: 5,
      eliteAttack: 3,
      eliteRange: 0
    }

    // Ignore Stupid table error.
    console.error = jest.fn();

    // Create mock functions to track.
    const updateMonster = jest.fn();
    const deleteMonster = jest.fn();

    

    // Create Element with mock function for onclick.


    const wrapper = mount(
          <Monster
            type={dummyType}
            instance={dummyInstance} 
            updateMonster={updateMonster}
            deleteMonster={deleteMonster}/>
    );


    const healthButton = wrapper.find('.button-remove-health').at(0);
    const removeButton = wrapper.find('.button-remove').at(0);
    // Watch onclick for activity.
    jest.spyOn(wrapper.props(), 'updateMonster');
    jest.spyOn(wrapper.props(), 'deleteMonster');

    healthButton.simulate('click');
    removeButton.simulate('click');
    expect(wrapper.props().updateMonster).toHaveBeenCalled();
  });
});