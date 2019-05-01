import React, { Component } from 'react';

 import AttackIcon   from './../../../assets/icons/stats/attack.svg';
 import MoveIcon     from './../../../assets/icons/stats/movement.svg';
 import RangeIcon    from './../../../assets/icons/stats/range.svg';


class MonsterAbility extends Component {  

    
    constructor(props) {
        super(props);

        this.monsterSelect = React.createRef();
    }

    
    reshuffle(shuffle){

        if(shuffle){
            return "Shuffle Deck"
        }
    }

    getIcon(name) {
        switch(name) {
            case '%attack%':
                return `<img src="${AttackIcon}" className="action-icon" alt="Attack: " />`;
            case '%range%':
                return `<img src="${RangeIcon}" className="action-icon" alt="Range: " />`;
            case '%move%':
                return `<img src="${MoveIcon}" className="action-icon" alt="Move: " />`;
            default:
                return undefined;
        }
    }

    generateAction(action){

        let counter = 0;

        const splitted = action.split(' ');

        const parsed = splitted.map(item => {
            const icon = this.getIcon(item)

            if(icon !== undefined) {
                counter++
                return this.getIcon(item)
            } else {
                return item;
            }
        }).join('');
        return ((counter > 0) ? parsed : action).replace(/[*]/g, '');

    }

    render() {
        if(this.props.monster.monsterAction === null || this.props.monster.monsterAction === undefined) {
            return (
                <div className="monster-action">
                    <button type="button">Draw Action</button>
                </div>
            );
        }

        // Filter out types without any instances   
        const actions = this.props.monster.monsterAction.actionDeck.map(action =>
            <p dangerouslySetInnerHTML={{ __html: this.generateAction(action) }}></p>
        );
    
        return(
            <div className="monster-action">
                {actions}
            </div>
        );
    }
}

export default MonsterAbility;