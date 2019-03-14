import React from 'react';
import { connect } from 'react-redux';

import './switch.css';

const Element = (props) => {
  

  return (
    <div>
        <span className="themed-font has-text-white subtitle is-3 toggle-button-label">Elite: </span>
        <input class="toggle" type="checkbox"  />
    </div>
  );
}

const mapStateToProps = (state) => {
    return {};
}

const mapDispatchToProps = (dispatch) => {
    return {
        //UI event functions
    };
}


export default connect(mapStateToProps, mapDispatchToProps)(Element);