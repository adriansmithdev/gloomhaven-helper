import React, { Component } from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import IndexComponent from './IndexComponent';
import RoomComponent from './RoomComponent';

class Routing extends Component {
  render() {
    return (
    <Switch>
        <Route path="/" exact={true} component={IndexComponent} />
        <Route path="/room/:roomid" component={RoomComponent} /> 
        <Redirect to="/" />
    </Switch>
    );
  }
}

export default Routing;