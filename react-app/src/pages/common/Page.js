import React, { Component } from 'react';
import {ToastContainer} from 'react-toastify';

class Page extends Component {

  render() {
    return (
      <>
        {this.props.children}
        <ToastContainer/>            
      </>
    );
  }
}


export default Page;