import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import MonsterList from './MonsterList';
import axios from 'axios';


class Room extends Component {

    constructor(props){
        super(props);

        this.changeScenario = this.changeScenario.bind(this);
    }

    changeScenario(event) {
        console.log("changeScenario called");
        axios.post(`http://localhost:5000/api/room/${this.props.match.params.roomid}/scenario/${event.target.value}` )
            .then(function (response){
                console.log(response.data.scenarioNumber);
            })
            .catch(function (error){
                console.log(error);
            });
    }

    render() {

      return (
        <div>
          <nav className="navbar is-black">
            <div class="navbar-brand">
              <div className="navbar-item">
                <h1 className="title themed-font has-text-light">Gloomhaven Helper</h1>
              </div>
              <div className="navbar-item">
                <strong className="has-text-light">Room: {this.props.match.params.roomid}</strong>

              </div>
            </div>
            <div className="navbar-end">
              <div className="navbar-item">
                  <div className="field has-addons mr-1">
                    <div className="control">
                      <span className="button is-static">Scenario</span>
                    </div>
                    <div className="control is-expanded">
                      <input className="input" type="number" onBlur={this.changeScenario} min="1" max="150"/>
                    </div>
                  </div>
                </div>
            </div>
          </nav>

          <MonsterList/>

          <span className="input-group-btn">
            <Link className="button is-dark is-large themed-font m-2" to={`/`}>Back to home!</Link>
          </span>
        </div>
      );
    }


}

export default Room;