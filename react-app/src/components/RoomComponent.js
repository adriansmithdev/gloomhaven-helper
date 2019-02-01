import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import MonsterList from './MonsterList';
import axios from 'axios';


class RoomComponent extends Component {

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
                <h1>Gloomhaven Room # {this.props.match.params.roomid} </h1>
                <input type="number" onBlur={this.changeScenario} min="1" max="150"/>

            <MonsterList/>

            <span className="input-group-btn">
            <Link className="button is-dark is-large themed-font m-2" to={`/`}>Back to home!</Link>
        </span>
            </div>
        );
    }


}

export default RoomComponent;