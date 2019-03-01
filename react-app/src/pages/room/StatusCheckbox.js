import React,{Component} from 'react';
import {connect} from 'react-redux';
import Modal from 'react-modal';
import {showModal, hideModal} from './../../store/actions/actions'


const customStyles = {
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

class StatusCheckbox extends Component {

    constructor() {
        super();
        }


    

    checkboxes = [
        {
          name: 'poison-check',
          key: 'poison-check',
          label: 'Poison',
        },
        {
          name: 'check-box-2',
          key: 'checkBox2',
          label: 'Check Box 2',
        },
        {
          name: 'check-box-3',
          key: 'checkBox3',
          label: 'Check Box 3',
        },
        {
          name: 'check-box-4',
          key: 'checkBox4',
          label: 'Check Box 4',
        },
    ];

    render() {

        const statuses = this.checkboxes.map(checkbox => (

                <label key={checkbox.key}>
                {checkbox.label}
                <input type="checkbox" checked={this.props.activeStatuses.includes(checkbox.label)} name={checkbox.name} />
                </label>

        ));

        return(
            <div>
                <button onClick={this.props.showModal}>Open Modal</button>
                <Modal
                isOpen={this.props. modalIsOpen}
                onRequestClose={this.props.hideModal}
                style={customStyles}
                contentLabel="Modal"
                >
                
                {statuses}

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
  
  
export default connect(mapStateToProps, mapDispatchToProps)(StatusCheckbox);


