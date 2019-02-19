import React, { Component } from 'react';
import { connect } from 'react-redux';
import {ToastContainer} from 'react-toastify';

class Page extends Component {

    render() {
        return (
            <div className="container">
                {this.props.children}
                <ToastContainer/>            
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