import React,{Component} from 'react';
import {connect} from 'react-redux';


class StatusCheckbox extends Component {

    constructor(props){
        super(props);

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


    handleChange(e) {
        const item = e.target.name;
        const isChecked = e.target.checked;
        this.setState(prevState => ({ checkedItems: prevState.checkedItems.set(item, isChecked) }));
      }

    render() {

        const statuses = checkboxes.map(checkbox => (

                <label key={checkbox.key}>
                {checkbox.label}
                <input type="checkbox" checked={this.props.activeStatuses.includes(checkbox.label)} name={checkbox.name} 
                    onChange={this.handleChange}/>
                </label>

        ));

        return{

        
        }


    }







}


const mapStateToProps = (state) => {
    return {

    };
  }
  
  const mapDispatchToProps = (dispatch) => {
    return {
      
    };
  }
  
  
export default connect(mapStateToProps, mapDispatchToProps)(StatusCheckbox);


