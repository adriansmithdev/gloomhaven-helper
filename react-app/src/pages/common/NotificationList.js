import React, { Component } from 'react';
import { connect } from 'react-redux';

class NotificationList extends Component {

    render() {
        return (
            <div className="notification-list">
                
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


export default connect(mapStateToProps, mapDispachToProps)(NotificationList);