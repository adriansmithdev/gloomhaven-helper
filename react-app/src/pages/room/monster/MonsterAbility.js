import React, { Component } from 'react';

// Stat Icons
import AttackIcon   from './../../../assets/icons/stats/attack.svg';
import FlyIcon from './../../../assets/icons/stats/flying.svg';
import HealIcon     from './../../../assets/icons/stats/heal.svg'
import JumpIcon from './../../../assets/icons/stats/jump.svg';
import MoveIcon     from './../../../assets/icons/stats/movement.svg';
import RangeIcon    from './../../../assets/icons/stats/range.svg';
import RetaliateIcon     from './../../../assets/icons/stats/retaliate.svg'
import ShieldIcon   from './../../../assets/icons/stats/shield.svg';

// Elements Icons
import AirIcon from './../../../assets/icons/elements/air.svg';
import DarkIcon from './../../../assets/icons/elements/dark.svg';
import EarthIcon from './../../../assets/icons/elements/earth.svg';
import FireIcon from './../../../assets/icons/elements/fire.svg';
import IceIcon from './../../../assets/icons/elements/ice.svg';
import LightIcon from './../../../assets/icons/elements/light.svg';

// Statuses Icons
import BlessIcon from './../../../assets/icons/statuses/Bless.svg';
import CurseIcon from './../../../assets/icons/statuses/Curse.svg';
import DisarmIcon from './../../../assets/icons/statuses/Disarm.svg';
import ImmobilizeIcon from './../../../assets/icons/statuses/Immobilize.svg';
import InvisibleIcon from './../../../assets/icons/statuses/Invisible.svg';
import MuddleIcon from './../../../assets/icons/statuses/Muddle.svg';
import PierceIcon from './../../../assets/icons/statuses/Pierce.svg';
import PoisonIcon from './../../../assets/icons/statuses/Poison.svg';
import PushIcon from './../../../assets/icons/statuses/Push.svg';
import StrengthenIcon from './../../../assets/icons/statuses/Strengthen.svg';
import StunIcon from './../../../assets/icons/statuses/Stun.svg';
import TargetIcon from './../../../assets/icons/statuses/Target.svg';
import WoundIcon from './../../../assets/icons/statuses/Wound.svg';

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
            //Elements
            case '%air%':
                return `<img src="${AirIcon}" className="action-icon" alt="Air" />`;
            case '%dark%':
                return `<img src="${DarkIcon}" className="action-icon" alt="Dark" />`;
            case '%earth%':
                return `<img src="${EarthIcon}" className="action-icon" alt="Earth" />`;
            case '%fire%':
                return `<img src="${FireIcon}" className="action-icon" alt="Fire" />`;
            case '%ice%':
                return `<img src="${IceIcon}" className="action-icon" alt="Ice" />`;
            case '%light%':
                return `<img src="${LightIcon}" className="action-icon" alt="Light" />`;
              
            // Stats
            case '%attack%':
                return `<img src="${AttackIcon}" className="action-icon" alt="Attack: " />`;
            case '%range%':
                return `<img src="${RangeIcon}" className="action-icon" alt="Range: " />`;
            case '%move%':
                return `<img src="${MoveIcon}" className="action-icon" alt="Move: " />`;
            case '%shield%':
                return `<img src="${ShieldIcon}" className="action-icon" alt="Shield: " />`;
            case '%heal%':
                return `<img src="${HealIcon}" className="action-icon" alt="Heal: " />`;
            case '%flying%':
                return `<img src="${FlyIcon}" className="action-icon" alt="Flying: " />`;
            case '%jump%':
                return `<img src="${JumpIcon}" className="action-icon" alt="Jump: " />`;
            case '%retaliate%':
                return `<img src="${RetaliateIcon}" className="action-icon" alt="Retaliate: " />`;

            //Statuses
            case '%bless%':
                return `<img src="${BlessIcon}" className="action-icon" alt="Bless" />`;
            case '%curse%':
                return `<img src="${CurseIcon}" className="action-icon" alt="Curse" />`;
            case '%disarm%':
                return `<img src="${DisarmIcon}" className="action-icon" alt="Disarm" />`;
            case '%immobilize%':
                return `<img src="${ImmobilizeIcon}" className="action-icon" alt="Immobilize" />`;
            case '%invisible%':
                return `<img src="${InvisibleIcon}" className="action-icon" alt="Invisible" />`;
            case '%muddle%':
                return `<img src="${MuddleIcon}" className="action-icon" alt="Muddle" />`;
            case '%pierce%':
                return `<img src="${PierceIcon}" className="action-icon" alt="Pierce" />`;
            case '%poison%':
                return `<img src="${PoisonIcon}" className="action-icon" alt="Poison" />`;
            case '%push%':
                return `<img src="${PushIcon}" className="action-icon" alt="Push" />`;
            case '%strengthen%':
                return `<img src="${StrengthenIcon}" className="action-icon" alt="Strengthen" />`;
            case '%stun%':
                return `<img src="${StunIcon}" className="action-icon" alt="Stun" />`;
            case '%target%':
                return `<img src="${TargetIcon}" className="action-icon" alt="Target" />`;
            case '%wound%':
                return `<img src="${WoundIcon}" className="action-icon" alt="Wound" />`;
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