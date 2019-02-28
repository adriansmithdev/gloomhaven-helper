import React, {Component} from 'react';
import {connect} from 'react-redux';

class MonsterInfo extends Component {

  render() {
    let monster = this.props.monster;
    return (
      <>
        {console.log("MONSTER")}
        {console.log(monster)}
        <div className="columns">
          <div className="title themed-font has-text-light is-size-4 column">
            {monster.name}
          </div>
          <div className="column">
            Attack: {monster.attack}
          </div>
          <div className="column">
            Movement: {monster.movement}
          </div>
          <div className="column">
            Range: {monster.range}
          </div>
        </div>
      </>
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