
// Stat Icons
import AttackIcon    from './../../assets/icons/stats/attack.svg';
import FlyIcon       from './../../assets/icons/stats/flying.svg';
import HealIcon      from './../../assets/icons/stats/heal.svg'
import JumpIcon      from './../../assets/icons/stats/jump.svg';
import MoveIcon      from './../../assets/icons/stats/movement.svg';
import RangeIcon     from './../../assets/icons/stats/range.svg';
import RetaliateIcon from './../../assets/icons/stats/retaliate.svg'
import ShieldIcon    from './../../assets/icons/stats/shield.svg';

// Elements Icons
import AirIcon   from './../../assets/icons/elements/air.svg';
import DarkIcon  from './../../assets/icons/elements/dark.svg';
import EarthIcon from './../../assets/icons/elements/earth.svg';
import FireIcon  from './../../assets/icons/elements/fire.svg';
import IceIcon   from './../../assets/icons/elements/ice.svg';
import LightIcon from './../../assets/icons/elements/light.svg';

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
import StrengthenIcon from './../../assets/icons/statuses/Strengthen.svg';
import StunIcon       from './../../assets/icons/statuses/Stun.svg';
import TargetIcon     from './../../assets/icons/statuses/Target.svg';
import WoundIcon      from './../../assets/icons/statuses/Wound.svg';

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
          alt: 'Fly'
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
          alt: 'Poison'
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
    default:
        return undefined;
  }
}

export default IconManager; 