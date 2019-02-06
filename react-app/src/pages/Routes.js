import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import Home from './home/Home';
import Room from './room/Room';

class Routes extends Component {
  render() {
    return (
    <Router>
      <Switch>
        <Route path="/" exact={true} component={Home} />
        <Route path="/room/:roomid" component={Room} /> 
        <Redirect to="/" />
      </Switch>
    </Router>
    
    );
  }
}

export default Routes;