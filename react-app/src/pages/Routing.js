import React, { Component } from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import Home from './home/Home';
import Room from './room/Room';

class Routing extends Component {
  render() {
    return (
    <Switch>
        <Route path="/" exact={true} component={Home} />
        <Route path="/room/:roomid" component={Room} /> 
        <Redirect to="/" />
    </Switch>
    );
  }
}

export default Routing;