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
        <div className="columns has-text-light themed-font">
          <div className="column is-size-4">
            {monster.name}
          </div>
          <div className="column">
            <img src={Attack} className="icon-small" alt="Attack: "/> 
            <span className="is-size-4">{monster.attack}</span>
            <img src={Movement} className="icon-small" alt="Movement: "/> 
            <span className="is-size-4">{monster.movement}</span>
            <img src={Range} className="icon-small" alt="Range: "/> 
            <span className="is-size-4">{monster.range}</span>
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