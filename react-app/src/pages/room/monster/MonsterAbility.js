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

// Use Elements Icons 
//TODO change svgs
import AirUseIcon from './../../../assets/icons/elements/air.svg';
import DarkUseIcon from './../../../assets/icons/elements/dark.svg';
import EarthUseIcon from './../../../assets/icons/elements/earth.svg';
import FireUseIcon from './../../../assets/icons/elements/fire.svg';
import IceUseIcon from './../../../assets/icons/elements/ice.svg';
import LightUseIcon from './../../../assets/icons/elements/light.svg';

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

// AOE icons
//TODO CHANGE ICONS W/ REAL ONES
import AOECirclewBlack from './../../../assets/icons/elements/air.svg';
import AOEline6withBlack from './../../../assets/icons/elements/air.svg';
import AOE4withBlack from './../../../assets/icons/elements/air.svg';
import AOELine3withBlack from './../../../assets/icons/elements/air.svg';
import AOECircle from './../../../assets/icons/elements/air.svg';
import AOETriangle2SidewithBlack from './../../../assets/icons/elements/air.svg';
import AOEline4withBlack from './../../../assets/icons/elements/air.svg';
import AOETriangle3swcBlack from './../../../assets/icons/elements/air.svg';
import AOETriangle2Side from './../../../assets/icons/elements/air.svg';
import AOECirclewithsideBlack from './../../../assets/icons/elements/air.svg';


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
                return `<img src="${AirIcon}" class="action-icon" alt="Air" title="Air Element"/>`;
            case '%dark%':
                return `<img src="${DarkIcon}" class="action-icon" alt="Dark" title="Dark Element"/>`;
            case '%earth%':
                return `<img src="${EarthIcon}" class="action-icon" alt="Earth" title="Earth Element"/>`;
            case '%fire%':
                return `<img src="${FireIcon}" class="action-icon" alt="Fire" title="Fire Element"/>`;
            case '%ice%':
                return `<img src="${IceIcon}" class="action-icon" alt="Ice" title="Ice Element"/>`;
            case '%light%':
                return `<img src="${LightIcon}" class="action-icon" alt="Light" title="Light Element"/>`;
            
            case '%air-use-element%':
                return `<img src="${AirUseIcon}" class="action-icon" alt="Use Air" title="Use Air Element"/>`;
            case '%dark-use-element%':
                return `<img src="${DarkUseIcon}" class="action-icon" alt="Use Dark" title="Use Dark Element"/>`;
            case '%earth-use-element%':
                return `<img src="${EarthUseIcon}" class="action-icon" alt="Use Earth" title="Use Earth Element"/>`;
            case '%fire-use-element%':
                return `<img src="${FireUseIcon}" class="action-icon" alt="Use Fire" title="Use Fire Element"/>`;
            case '%ice-use-element%':
                return `<img src="${IceUseIcon}" class="action-icon" alt="Use Ice" title="Use Ice Element"/>`;
            case '%light-use-element%':
                return `<img src="${LightUseIcon}" class="action-icon" alt="use Light" title="Use Light Element"/>`;
              
            // Stats
            case '%attack%':
                return `<img src="${AttackIcon}" class="action-icon" alt="Attack: " title="Attack"/>`;
            case '%range%':
                return `<img src="${RangeIcon}" class="action-icon" alt="Range: " title="Range"/>`;
            case '%move%':
                return `<img src="${MoveIcon}" class="action-icon" alt="Move: " title="Movement"/>`;
            case '%shield%':
                return `<img src="${ShieldIcon}" class="action-icon" alt="Shield: " title="Shield"/>`;
            case '%heal%':
                return `<img src="${HealIcon}" class="action-icon" alt="Heal: " title="Heal"/>`;
            case '%flying%':
                return `<img src="${FlyIcon}" class="action-icon" alt="Flying: " title="FLying"/>`;
            case '%jump%':
                return `<img src="${JumpIcon}" class="action-icon" alt="Jump: " title="Jump"/>`;
            case '%retaliate%':
                return `<img src="${RetaliateIcon}" class="action-icon" alt="Retaliate: " title="Retaliate"/>`;

            //Statuses
            case '%bless%':
                return `<img src="${BlessIcon}" class="action-icon" alt="Bless" title="Bless"/>`;
            case '%curse%':
                return `<img src="${CurseIcon}" class="action-icon" alt="Curse" title="Curse"/>`;
            case '%disarm%':
                return `<img src="${DisarmIcon}" class="action-icon" alt="Disarm" title="Disarm"/>`;
            case '%immobilize%':
                return `<img src="${ImmobilizeIcon}" class="action-icon" alt="Immobilize" title="Immobilize"/>`;
            case '%invisible%':
                return `<img src="${InvisibleIcon}" class="action-icon" alt="Invisible" title="Invisible"/>`;
            case '%muddle%':
                return `<img src="${MuddleIcon}" class="action-icon" alt="Muddle" title="Muddle"/>`;
            case '%pierce%':
                return `<img src="${PierceIcon}" class="action-icon" alt="Pierce" title="Pierce"/>`;
            case '%poison%':
                return `<img src="${PoisonIcon}" class="action-icon" alt="Poison" title="Poison"/>`;
            case '%push%':
                return `<img src="${PushIcon}" class="action-icon" alt="Push" title="Push"/>`;
            case '%strengthen%':
                return `<img src="${StrengthenIcon}" class="action-icon" alt="Strengthen" title="Strengthen"/>`;
            case '%stun%':
                return `<img src="${StunIcon}" class="action-icon" alt="Stun" title="Stun"/>`;
            case '%target%':
                return `<img src="${TargetIcon}" class="action-icon" alt="Target" title="Target"/>`;
            case '%wound%':
                return `<img src="${WoundIcon}" class="action-icon" alt="Wound" title="Wound"/>`;
            
            //AOE
            case '%aoe-circle-with-middle-black%':
                return `<img src="${AOECirclewBlack}" class="action-icon" alt="AOE circle with black" title="AOE circle with black" />`;
            
            case '%aoe-line-6-with-black%':
                return `<img src="${AOEline6withBlack}" class="action-icon" alt="AOE line 6 with black" title="AOE line 6 with black" />`;
            
            case '%aoe-4-with-black%':
                return `<img src="${AOE4withBlack}" class="action-icon" alt="AOE 4 with black" title="AOE 4 with black" />`;
            
            case '%aoe-line-3-with-black%':
                return `<img src="${AOELine3withBlack}" class="action-icon" alt="AOE Line 3 with Black" title="AOE Line 3 with Black" />`;
            
            case '%aoe-circle%':
                return `<img src="${AOECircle}" class="action-icon" alt="AOE Circle" title="AOE Circle" />`;
            
            case '%aoe-triangle-2-side-with-black%':
                return `<img src="${AOETriangle2SidewithBlack}" class="action-icon" alt="AOE triangle 2 side with black" title="AOE triangle 2 side with black" />`;
            
            case '%aoe-line-4-with-black%':
                return `<img src="${AOEline4withBlack}" class="action-icon" alt="AOE circle with black" title="AOE circle with black" />`;
            
            case '%aoe-triangle-3-side-with-corner-Black%':
                return `<img src="${AOETriangle3swcBlack}" class="action-icon" alt="AOE circle with black" title="AOE circle with black" />`;
            
            case '%aoe-triangle-2-side%':
                return `<img src="${AOETriangle2Side}" class="action-icon" alt="AOE circle with black" title="AOE circle with black" />`;
            
            case '%aoe-circle-with-side-black%':
                return `<img src="${AOECirclewithsideBlack}" class="action-icon" alt="AOE circle with black" title="AOE circle with black" />`;
            
            default:
                return undefined;
        }
    }

    generateAction(action){

        let counter = 0;

        const splitted = action.split(' ');

        const parsed = splitted.map(item => {
            console.log(item);
            const icon = this.getIcon(item);

            if(icon !== undefined) {
                counter++;
                return this.getIcon(item);
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
            <p key={Math.round(Math.random() * 10000)} dangerouslySetInnerHTML={{__html: this.generateAction(action)}}/>
        );
    
        return(
            <div className="monster-action">
                {actions}
            </div>
        );
    }
}

export default MonsterAbility;