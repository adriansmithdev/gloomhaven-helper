import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import Page from './../common/Page';

import { getRoom } from './../../store/actions/actions'
 
class Room extends Component {

  validateRoom() {
    if(this.props.room.hash !== undefined) {
      return true;
    } else {
      
    }
  }

  render() {
    if(this.props.room.hash === undefined) {
      this.props.getRoom(this.props.match.params.hash);
    }
    return (
      <Page>
        <nav className="navbar is-black">
          <div className="navbar-brand">
            <div className="navbar-item">
              <h1 className="title themed-font has-text-light">Gloomhaven Helper</h1>
            </div>
            <div className="navbar-item">
              <strong className="has-text-light">Room: {this.props.room.hash}</strong>

            </div>
          </div>
          <div className="navbar-end">
            <div className="navbar-item">
              <div className="field has-addons mr-1">
                <div className="control">
                  <span className="button is-static">Scenario</span>
                </div>
                <div className="control is-expanded">
                  <input className="input input-short" type="number" min="1" max="150"
                    defaultValue={this.props.room.scenarioNumber}
                  />
                </div>
              </div>
            </div>
          </div>
        </nav>

        <span className="input-group-btn">
          <Link className="button is-dark is-large themed-font m-2" to={`/`}>Back to home!</Link>
        </span>
      </Page>
    );
  }


}

const mapStateToProps = (state) => {
  return {
    room: state.room
  };
}

const mapDispachToProps = (dispatch) => {
  return {
    getRoom: (hash) => {dispatch(getRoom(hash))}
  };
}


export default connect(mapStateToProps, mapDispachToProps)(Room);