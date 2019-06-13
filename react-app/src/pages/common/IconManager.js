
// Stat Icons
import AttackIcon    from './../../assets/icons/stats/attack.svg';
import FlyIcon       from './../../assets/icons/stats/flying.svg';
import HealIcon      from './../../assets/icons/stats/heal.svg'
import JumpIcon      from './../../assets/icons/stats/jump.svg';
import MoveIcon      from './../../assets/icons/stats/movement.svg';
import RangeIcon     from './../../assets/icons/stats/range.svg';
import RetaliateIcon from './../../assets/icons/stats/retaliate.svg'
import ShieldIcon    from './../../assets/icons/stats/shield.svg';
import LootIcon      from './../../assets/icons/stats/loot.svg'

// Elements Icons
import AirIcon    from './../../assets/icons/elements/filled/air.svg';
import DarkIcon   from './../../assets/icons/elements/filled/dark.svg';
import EarthIcon  from './../../assets/icons/elements/filled/earth.svg';
import FireIcon   from './../../assets/icons/elements/filled/fire.svg';
import IceIcon    from './../../assets/icons/elements/filled/ice.svg';
import LightIcon  from './../../assets/icons/elements/filled/light.svg';
import AnyElement from './../../assets/icons/elements/filled/any-element.svg';
import UseElement from './../../assets/icons/elements/use-element.svg';

// Statuses Icons
import BlessIcon      from './../../assets/icons/statuses/Bless.svg';
import CurseIcon      from './../../assets/icons/statuses/Curse.svg';
import DisarmIcon     from './../../assets/icons/statuses/Disarm.svg';
import ImmobilizeIcon from './../../assets/icons/statuses/Immobilize.svg';
import InvisibleIcon  from './../../assets/icons/statuses/Invisible.svg';
import MuddleIcon     from './../../assets/icons/statuses/Muddle.svg';
import PierceIcon     from './../../assets/icons/statuses/Pierce.svg';
import PoisonIcon     from './../../assets/icons/statuses/Poison.svg';
import PushIcon       from './../../assets/icons/statuses/Push.svg';
import PullIcon       from './../../assets/icons/statuses/Pull.svg';
import StrengthenIcon from './../../assets/icons/statuses/Strengthen.svg';
import StunIcon       from './../../assets/icons/statuses/Stun.svg';
import TargetIcon     from './../../assets/icons/statuses/Target.svg';
import WoundIcon      from './../../assets/icons/statuses/Wound.svg';

// AOE icons
import AOECirclewBlack from './../../assets/icons/aoe/aoe-circle-with-middle-black.png';
import AOEline6withBlack from './../../assets/icons/aoe/aoe-line-6-with-black.png';
import AOE4withBlack from './../../assets/icons/aoe/aoe-4-with-black.png';
import AOELine3withBlack from './../../assets/icons/aoe/aoe-line-3-with-black.png';
import AOECircle from './../../assets/icons/aoe/aoe-circle.png';
import AOETriangle2SidewithBlack from './../../assets/icons/aoe/aoe-triangle-2-side-with-black.png';
import AOEline4withBlack from './../../assets/icons/aoe/aoe-line-4-with-black.png';
import AOETriangle3swcBlack from './../../assets/icons/aoe/aoe-triangle-3-side-with-corner-black.png';
import AOETriangle2Side from './../../assets/icons/aoe/aoe-triangle-2-side.png';
import AOECirclewithsideBlack from './../../assets/icons/aoe/aoe-circle-with-side-black.png';


const IconManager = (icon) => {
  switch(icon) {
    //Elements
    case '%air%':
        return {
          type: 'element',
          src: AirIcon,
          alt: 'Air'  
        }
    case '%dark%':
        return {
          type: 'element',
          src: DarkIcon,
          alt: 'Dark'
        }
    case '%earth%':
        return {
          type: 'element',
          src: EarthIcon,
          alt: 'Earth'
        }
    case '%fire%':
        return {
          type: 'element',
          src: FireIcon,
          alt: 'Fire'
        }
    case '%ice%':
        return {
          type: 'element',
          src: IceIcon,
          alt: 'Ice'
        }
    case '%light%':
        return {
          type: 'element',
          src: LightIcon,
          alt: 'Light'
        }

    // Use Elements
    case '%air_use_element%:':
        return {
          type: 'element',
          src: AirIcon,
          alt: 'Use air element',
          overlay: UseElement  
        }
    case '%dark_use_element%:':
        return {
          type: 'element',
          src: DarkIcon,
          alt: 'Use fire element',
          overlay: UseElement  
        }
    case '%earth_use_element%:':
        return {
          type: 'element',
          src: EarthIcon,
          alt: 'Use dark element',
          overlay: UseElement  
        }
    case '%fire_use_element%:':
        return {
          type: 'element',
          src: FireIcon,
          alt: 'Use earth element',
          overlay: UseElement  
        }
    case '%ice_use_element%:':
        return {
          type: 'element',
          src: IceIcon,
          alt: 'Use ice element',
          overlay: UseElement  
        }
    case '%light_use_element%:':
        return {
          type: 'element',
          src: LightIcon,
          alt: 'Use light element',
          overlay: UseElement  
        }
    case '%any_use_element%:':
        return {
          type: 'element',
          src: AnyElement,
          alt: 'Use Any Element',
          overlay: UseElement  
        }

      
    // Stats
    case '%attack%':
        return {
          type: 'stat',
          src: AttackIcon,
          alt: 'Attack'
        }
    case '%range%':
        return {
          type: 'stat',
          src: RangeIcon,
          alt: 'Range'
        }
    case '%move%':
        return {
          type: 'stat',
          src: MoveIcon,
          alt: 'Move'
        }
    case '%shield%':
        return {
          type: 'stat',
          src: ShieldIcon,
          alt: 'Shield'
        }
    case '%heal%':
        return {
          type: 'stat',
          src: HealIcon,
          alt: 'Heal'
        }
    case '%flying%':
        return {
          type: 'stat',
          src: FlyIcon,
          alt: 'Flying'
        }
    case '%jump%':
        return {
          type: 'stat',
          src: JumpIcon,
          alt: 'Jump'
        }
    case '%retaliate%':
        return {
          type: 'stat',
          src: RetaliateIcon,
          alt: 'Retaliate'
        }
    case '%loot%':
        return {
          type: 'stat',
          src: LootIcon,
          alt: 'Loot'
        }

    //Statuses
    case '%bless%':
        return {
          type: 'status',
          src: BlessIcon,
          alt: 'Bless'
        }
    case '%curse%':
        return {
          type: 'status',
          src: CurseIcon,
          alt: 'Curse'
        }
    case '%disarm%':
        return {
          type: 'status',
          src: DisarmIcon,
          alt: 'Disarm'
        }
    case '%immobilize%':
        return {
          type: 'status',
          src: ImmobilizeIcon,
          alt: 'Immobilize'
        }
    case '%invisible%':
        return {
          type: 'status',
          src: InvisibleIcon,
          alt: 'Invisible'
        }
    case '%muddle%':
        return {
          type: 'status',
          src: MuddleIcon,
          alt: 'Muddle'
        }
    case '%pierce%':
        return {
          type: 'status',
          src: PierceIcon,
          alt: 'Pierce'
        }
    case '%poison%':
        return {
          type: 'status',
          src: PoisonIcon,
          alt: 'Poison'
        }
    case '%push%':
        return {
          type: 'status',
          src: PushIcon,
          alt: 'Push'
        }
    case '%pull%':
        return {
          type: 'status',
          src: PullIcon,
          alt: 'Pull'
        }
    case '%strengthen%':
        return {
          type: 'status',
          src: StrengthenIcon,
          alt: 'Strengthen'
        }
    case '%stun%':
        return {
          type: 'status',
          src: StunIcon,
          alt: 'Stun'
        }
    case '%target%':
        return {
          type: 'status',
          src: TargetIcon,
          alt: 'Target'
        }
    case '%wound%':
        return {
          type: 'status',
          src: WoundIcon,
          alt: 'Wound'
        }

    // AOE
    case '%aoe-circle-with-middle-black%':
        return {
          type: 'AOE',
          src: AOECirclewBlack,
          alt: 'AOE Circle w/ Black'
        }
    case '%aoe-line-6-with-black%':
        return {
          type: 'AOE',
          src: AOEline6withBlack,
          alt: 'AOE line 6 w/ Black'
        }
    case '%aoe-4-with-black%':
        return {
          type: 'AOE',
          src: AOE4withBlack,
          alt: 'AOE 4 with Black'
        };
    case '%aoe-line-3-with-black%':
        return {
          type: 'AOE',
          src: AOELine3withBlack,
          alt: 'AOE Line 3 with Black'
        }
    case '%aoe-circle%':
        return {
          type: 'AOE',
          src: AOECircle,
          alt: 'AOE Circle'
        }
    case '%aoe-triangle-2-side-with-black%':
        return {
          type: 'AOE',
          src: AOETriangle2SidewithBlack,
          alt: 'AOE Triangle 2 Side with Black'
        }
    case '%aoe-line-4-with-black%':
        return {
          type: 'AOE',
          src: AOEline4withBlack,
          alt: 'AOE line 4 with Black'
        }
    case '%aoe-triangle-3-side-with-corner-black%':
        return {
          type: 'AOE',
          src: AOETriangle3swcBlack,
          alt: 'AOE Triangle 3 side with corner Black'
        }
    case '%aoe-triangle-2-side%':
        return {
          type: 'AOE',
          src: AOETriangle2Side,
          alt: 'AOE Triangle 2 Side'
        }
    case '%aoe-circle-with-side-black%':
        return {
          type: 'AOE',
          src: AOECirclewithsideBlack,
          alt: 'AOE Circle with side Black'
        }
    default:
        return undefined;
  }
}

export default IconManager; 