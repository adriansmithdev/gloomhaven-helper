import React, { Component } from 'react';
import { connect } from 'react-redux';
import NotificationList from './NotificationList';

class Page extends Component {

    render() {
        return (
            <div className="container">
                {this.props.children}
                <NotificationList />
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


export default connect(mapStateToProps, mapDispachToProps)(Page);