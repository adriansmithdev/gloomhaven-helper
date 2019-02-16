import React, {Component} from 'react';
import axios from 'axios';
import {connect} from 'react-redux';


class Home extends Component {

    state = {
        persons: []
    }
    showHashInput = false;

    constructor() {
        super();

        this.requestRoom = this.requestRoom.bind(this);
        this.joinRoom = this.joinRoom.bind(this);
        this.setShowHash = this.setShowHash.bind(this);
    }

    setShowHash(){
        this.setState({showHashInput : true});
    }

    joinRoom(event) {
        axios.get(`http://localhost:5000/api/rooms/${event.target.value}`)
            .then(function (response) {
                console.log("ROOM FOUND SUCCESSFULLY");
                window.location = `/room/${response.data.hash}`;
            })
            .catch(function (error) {
                console.log("ROOM NOT FOUND");
            });
    }

    requestRoom() {
        axios.post('http://localhost:5000/api/rooms')
            .then(function (response) {
                console.log(response.data.roomHash);
                window.location = `/room/${response.data.hash}`;
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    renderJoinRoomBtn() {
        return <button className="button is-dark is-large themed-font m-2" type="button"
                       onClick={this.setShowHash}>Join Room</button>;
    }

    renderHashInput(){
        return <input type="text" className="text-input" onBlur={this.joinRoom} name="hash"
                      placeholder="Room ID"/>;
    }

    render() {
        return (
            <div className="container">
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
                                    onClick={this.requestRoom}>Create Room</button>
                            {this.state.showHashInput ? this.renderHashInput() : this.renderJoinRoomBtn()}
                        </span>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {};
}

const mapDispachToProps = (dispach) => {
    return {
        //UI event functions
    };
}


export default connect(mapStateToProps, mapDispachToProps)(Home);