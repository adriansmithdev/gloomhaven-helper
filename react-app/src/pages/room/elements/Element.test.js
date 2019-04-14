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
});