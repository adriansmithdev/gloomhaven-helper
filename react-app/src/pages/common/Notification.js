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

const mapDispatchToProps = (dispatch) => {
    return {
        //UI event functions
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(Notification);