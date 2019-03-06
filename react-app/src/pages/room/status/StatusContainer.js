import React,{Component} from 'react';
import {connect} from 'react-redux';
import Modal from 'react-modal';
import {showModal, hideModal} from '../../../store/actions/actions'
import StatusEffect from './StatusEffect';


const customStyles = {
  overlay: {
    position: 'fixed',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: 'rgba(255, 255, 255, 0)'
  },
  content : {
    top                   : '50%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)'
  }
};

Modal.setAppElement(this);

class StatusContainer extends Component {


  render() {
    const statuses = this.props.statuses.map(status => (
      <td>
        <StatusEffect key={status.id} instance={this.props.instance} status={status}/>
      </td>
    ));

    const statusesNames = this.props.statuses.map(status => (
      <th>{status.name}</th>
    ));

    return(
      <div>
        <button className="button is-dark themed-font" onClick={this.props.showModal}>Open Modal</button>
        <Modal
          isOpen={this.props.modalIsOpen}
          onRequestClose={this.props.hideModal}
          style={customStyles}
          contentLabel="Modal"
        >
        

        <table class="table">
            <thead><tr>{statusesNames}</tr></thead>
            <tbody><tr class="has-text-centered">{statuses}</tr></tbody>
        </table>

        </Modal>
      </div>
    )

  }

}


const mapStateToProps = (state) => {
    return {
        modalIsOpen: state.modalIsOpen
    };
  }
  
  const mapDispatchToProps = (dispatch) => {
    return {
      showModal: (status) => dispatch(showModal(status)),
      hideModal: (status) => dispatch(hideModal(status))
    };
  }
  
  
export default connect(mapStateToProps, mapDispatchToProps)(StatusContainer);


