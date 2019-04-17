import React from 'react';
import { shallow, configure, mount, render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

import { RoundManager, incrementRoundForRoom } from './RoundManager';

configure({ adapter: new Adapter() });

describe('Testing <RoomToolbar />:', function() {
  const dummyRoom = {
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
  };

  it('should render without throwing an error', function() {
    expect(shallow(<RoundManager/>));
    
  });

  it('should call incrementRound when clicked on', () => {

    // Create mock function to track.
    const incrementRound = jest.fn();

    // Create Element with mock function for onclick.
    const wrapper = mount(<RoundManager room={dummyRoom} incrementRound={incrementRound}/>);
    const button = wrapper.find('.round-increment-button').at(0);

    // Watch onclick for activity.
    jest.spyOn(wrapper.props(), 'incrementRound')

    button.simulate('click');
    expect(wrapper.props().incrementRound).toHaveBeenCalled();

  });

  it('Should properly increment a round', function() {
    const newRoom = incrementRoundForRoom(dummyRoom);
    expect(newRoom.round).not.toBe(dummyRoom.round);
    expect(newRoom.round).toBe(dummyRoom.round + 1);
  });
});