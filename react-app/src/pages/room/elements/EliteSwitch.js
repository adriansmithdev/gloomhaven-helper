import React, {Component} from 'react';

import './switch.scss';

class EliteSwitch extends Component {

    render(){ 
      return (
        <div className="">
          <span className="themed-font has-text-white subtitle is-3 toggle-button-label">Elite: </span>
          <input className="toggle" type="checkbox" onClick={ this.props.updateEliteStatus } />
        </div>
      );
    }
    
}


export default EliteSwitch;