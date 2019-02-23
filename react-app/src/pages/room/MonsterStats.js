import React, {Component} from 'react';
import {connect} from 'react-redux';

class MonsterStats extends Component{

  render(){

  }
}

const mapStateToProps = (state) => {
  return {
    //state needed as props like stats
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    //UI Events
  };
}


export default connect(mapStateToProps, mapDispatchToProps)(MonsterStats);