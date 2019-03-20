import React, {Component} from 'react';
import { connect } from 'react-redux';

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

const mapStateToProps = (state) => {
    return {};
}

const mapDispatchToProps = (dispatch) => {
    return {
        //UI event functions
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(EliteSwitch);