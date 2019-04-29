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
                return <img src={AttackIcon} className="action-icon" alt="Attack: " />;
            case '%range%':
                return <img src={RangeIcon} className="action-icon" alt="Range: " />;
            case '%move%':
                return <img src={MoveIcon} className="action-icon" alt="Move: " />;
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
        });
        return (counter > 0) ? parsed : action;

    }

    render() {

        const MonsterAbilityStat = {
            shuffle: false,
            initiative: "28",
            action: ["%attack% +0", "%range% +0", "All adjacent enemies suffer 2 damage"]
        }

        // Filter out types without any instances   
        const actions = MonsterAbilityStat.action.map(action =>
            <p>{this.generateAction(action)}</p>
        );
    
        return(
            <div className="monster-action">
                {actions}{this.reshuffle(MonsterAbilityStat.shuffle)}
            </div>
        );
    }
}

export default MonsterAbility;