import React from 'react';
import renderer from 'react-test-renderer';
import { shallow, configure, mount, render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';


import Element from './Element';

configure({ adapter: new Adapter() });

describe('Testing <Element />:', function() {
  it('should render without throwing an error', function() {
    expect(render(<Element/>));
  });

  it('should call cycleElementStatus when clicked on', () => {
    // Create mock function to track.
    const cycleElementStatus = jest.fn();

    // Create Element with mock function for onclick.
    const wrapper = mount(<Element cycleElementStatus={cycleElementStatus}/>);
    const element = wrapper.find('.element').at(0);

    // Watch onclick for activity.
    jest.spyOn(wrapper.props(), 'cycleElementStatus')

    element.simulate('click');
    expect(wrapper.props().cycleElementStatus).toHaveBeenCalled();

  });
});