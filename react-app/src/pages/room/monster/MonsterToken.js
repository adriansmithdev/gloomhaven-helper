import React, { Component } from 'react';
import { toast } from 'react-toastify';

class MonsterToken extends Component {
 
  constructor(props) {
    super(props);

    this.state = {
      active: false
    }

    this.tokenInput = React.createRef();

    this.setFocus = this.setFocus.bind(this);
    this.setBlur  = this.setBlur.bind(this);
    this.enterPressed = this.enterPressed.bind(this);
    this.submitToken = this.submitToken.bind(this);
  }

  pickInputClasses() {
    return (this.state.active) ?
      "monster-token-container active" : "monster-token-container";
  }

  setFocus() {
    this.setState({
      ...this.state,
      active: true
    });
  }

  setBlur(e) {
    this.submitToken();
  }

  enterPressed(e) {
    // Submit if enter is pressed
    if(e.charCode === 13) {
      // Blur input which will trigger submit event.
      e.target.blur();
    }
  }

  submitToken() {
    this.setState({
      ...this.state,
      active: false
    });

    const newToken = this.tokenInput.current.value;

    if(this.validToken(newToken)) {
      this.props.updateMonsterToken(newToken);
    } else {
      toast.error("Invalid monster token");
    }
  }
  
  validToken(token) {
    // Token is defined, and a number, and is not empty.
    return (token !== undefined && 
      isNaN(token) === false && token !== '')
  }

  render() {
    return (
      <div className={this.pickInputClasses()} title="Edit monster token">
        <label>
          #
          <input className="monster-token-input" 
            type="number" 
            ref={this.tokenInput}
            defaultValue={this.props.token}
            onFocus={this.setFocus}
            onBlur={this.setBlur}
            onKeyPress={this.enterPressed}
            min="1"
            step="1"
          />
        </label>
      </div>
    );
  }
}

export default MonsterToken;