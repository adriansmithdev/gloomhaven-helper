import React, {Component} from 'react';
import {connect} from 'react-redux';
import Attack from './../../assets/icons/stats/attack.svg';
import Movement from './../../assets/icons/stats/movement.svg';
import Range from './../../assets/icons/stats/range.svg';


class MonsterInfo extends Component {

  render() {
    let monster = this.props.monster;
    return (
      <>
        {console.log("MONSTER")}
        {console.log(monster)}
        <div className="row has-text-light">
          <div className="title themed-font has-text-light is-size-4 column">
            {monster.name}
          </div>
          <div className="column">
            <img src={Attack} className="icon-small" /> {monster.attack}
          </div>
          <div className="column">
            <img src={Movement} className="icon-small" /> {monster.movement}
          </div>
          <div className="column">
            <img src={Range} className="icon-small" /> {monster.range}
          </div>
          
          
          <div className="column">
            {monster.attributes !== undefined ? "Traits: " + monster.attributes : ''}
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