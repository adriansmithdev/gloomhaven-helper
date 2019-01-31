import React, { Component } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Routing from './components/Routing';

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Routing />
        </div>
      </Router>
    );
  }
}

export default App;
