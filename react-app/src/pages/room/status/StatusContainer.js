import React,{Component} from 'react';
import {connect} from 'react-redux';
import Modal from 'react-modal';
import {showModal, hideModal} from '../../../store/actions/actions'


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

        baseStyles = {
            display: 'inline-block',
            width: '2.5rem',
            height: '2.5rem',
          }
          inactiveStyles = {
            opacity: 0.25
          }

    render() {

        const usedStyles = (this.props.activeStatuses === true) ? {
            ...this.baseStyles,
          } : {
            ...this.baseStyles,
            ...this.inactiveStyles
          }

        const statuses = this.props.statuses.map(status => (
                <td>
                <div style={usedStyles} className="status-toggle">
                    <img src={require(`./../../../assets/icons/statuses/${status.name}.svg`)} alt={status.name} title={status.tooltip}/>
                </div>
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


