import React, {Component} from 'react';
import {connect} from 'react-redux';

class MonsterInfo extends Component{

  render() {
    let monster = this.props.monster;
    return (
    <div className="title themed-font has-text-light is-size-4">
      {monster.name}
      Attack: {monster.attack}
      Movement: {monster.movement}
      Range: {monster.range}

      </div>

    );
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


export default connect(mapStateToProps, mapDispatchToProps)(MonsterInfo);