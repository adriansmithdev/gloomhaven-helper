import React, { Component } from 'react';
import { connect } from 'react-redux';
import Page from './../common/Page';

import { createRoom } from './../../store/actions/actions';
class Home extends Component {

    

  render() {
    return (
      <Page>
        <div className="card">
          <div className="card-content">
            <h1 className="title themed-font level-item">Gloomhaven Helper</h1>

            <p className="level">
                The Gloomhaven Helper is a tool for Gloomhaven Players who want assistance managing
                the game, the Gloomhaven Helper is a web-based board game assistance tool that speeds
                up the pace of play, while making it easier to manage all content. Unlike the base
                game out of the box, our project reduces the number of physical tokens and trackers
                players need to manage.
            </p>

            <span className="level-item">
              <button className="button is-dark is-large themed-font m-2" type="button"
                onClick={this.props.createRoom}>
                Create Room
              </button>
            </span>
          </div>
        </div>
      </Page>
    );
  }
}


const mapStateToProps = (state) => {
  return {};
}

const mapDispachToProps = (dispatch, ownProps) => {
  const rerouteToRoomPage = (response) => {
    console.log(response);
    ownProps.history.push(`/room/${response.data.hash}`);
  }

  return {
    createRoom: () => dispatch(createRoom(rerouteToRoomPage))
  }
}



export default connect(mapStateToProps, mapDispachToProps)(Home);