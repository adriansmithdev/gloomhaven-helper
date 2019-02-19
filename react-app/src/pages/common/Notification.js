import React, { Component } from 'react';
import { connect } from 'react-redux';

class Notification extends Component {

    render() {
        return (
            <div className="notification">
                
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


export default connect(mapStateToProps, mapDispachToProps)(Notification);