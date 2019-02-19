import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

import Home from './home/Home';
import Room from './room/Room';

class Routes extends Component {
  

  render() {
    return (
      <Router>
        <Switch>
          <Route path="/" exact={true} component={Home} />
          <Route path="/room/:hash" component={Room}/>
          <Redirect to="/" />
        </Switch>
      </Router>
    );
  }
}

const mapStateToProps = (state) => {
  return  {
    roomHash: state.room.hash
  };
}

const mapDispatchToProps = (dispatch) => {
  return {};
}

export default connect(mapStateToProps, mapDispatchToProps)(Routes);