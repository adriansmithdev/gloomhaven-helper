import React, { Component } from 'react';
import { BrowserRouter as Router, Link, Route, Switch, Redirect } from 'react-router-dom';
import logo from './logo.svg';
import './App.css';
import IndexComponent from './components/IndexComponent';
import RoomComponent from './components/RoomComponent';

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Switch>
            <Route path="/" exact={true} component={IndexComponent} />
            <Route path="/room/:roomid" component={RoomComponent} /> 
            <Redirect to="/" />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
