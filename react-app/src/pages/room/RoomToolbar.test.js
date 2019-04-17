import React from 'react';
import { shallow, configure, mount, render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';


import { RoomToolbar } from './RoomToolbar';

configure({ adapter: new Adapter() });

describe('Testing <RoomToolbar />:', function() {
  it('should render without throwing an error', function() {
    expect(shallow(<RoomToolbar/>));
  });

  it('should run addMonster function data when add monster button is pressed.', () => {
    const dummySession = {
      room: {
        hash: 'ABCDEF',
        scenarioNumber: 2756,
        scenarioLevel: 2,
        round: 360,
        elements: [
          {
              id: 2152,
              type: 'FIRE',
              strength: 0
          },
          {
              id: 2153,
              type: 'ICE',
              strength: 0
          },
          {
              id: 2154,
              type: 'AIR',
              strength: 0
          },
          {
              id: 2155,
              type: 'EARTH',
              strength: 0
          },
          {
              id: 2156,
              type: 'LIGHT',
              strength: 0
          },
          {
              id: 2157,
              type: 'DARK',
              strength: 0
          }
        ]
      },
      monsters: [
        {
            id: 1602,
            name: 'City Guard',
            attributes: [
                'Shield 1'
            ],
            eliteAttributes: [
                'Shield 2'
            ],
            level: 2,
            health: 7,
            movement: 2,
            attack: 2,
            range: 0,
            eliteHealth: 9,
            eliteMove: 2,
            eliteAttack: 3,
            eliteRange: 0,
            monsterInstances: []
        }
      ]
    }

    // Create mock function to track.
    const addMonster = jest.fn();

    // Create Element with mock function for onclick.
    const wrapper = mount(<RoomToolbar {...dummySession} addMonster={addMonster}/>);
    const element = wrapper.find('.add-monster-button').at(0);

    // Watch onclick for activity.
    jest.spyOn(wrapper.props(), 'addMonster');

    element.simulate('click');
    expect(wrapper.props().addMonster).toHaveBeenCalled();
    expect(wrapper.props().addMonster).toBeCalledWith(
      dummySession.room.hash,
      `${dummySession.monsters[0].id}`,
      wrapper.state('isElite')
    )
  });
});