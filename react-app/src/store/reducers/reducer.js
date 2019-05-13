const initialState = {
  status: 'INITIAL',
  session: {
    room: {

    }
  },
  notifications: [],
  modalIsOpen: false,
  eliteToggle: false
  
}



const reducer = (state = initialState, action) => {

  console.log(state.session);
  let newState = {
    ...state, 
    session: { 
      ...state.session, 
      room: {
        ...state.session.room
      }
    }
  };

  function setSession() {
    newState.session = {...action.data.content[0]};
  }

  switch(action.type) {
    case 'TOGGLE_ELITE':
      newState.eliteToggle = !state.eliteToggle;
      break;
    case 'SET_ROOM': 
      newState.session.room = {...action.value};
      break;
    case 'PUT_ROOM':
      setSession();
      break;
    case 'GET_SESSION':
      setSession();
      break;
    case 'ADD_ERROR':
      newState.notifications.push(action.error);
      break;
    case 'SET_STATUS':
      newState.status = action.value;
      break;
    
    // Monster instance modifiers.
    case 'POST_MONSTER_INSTANCE': {
        const monsterTypeIndex = newState.session.monsters.findIndex(monster =>
          monster.id === action.data.monsterId
        );

        const monsterType = newState.session.monsters[monsterTypeIndex];

        const newInstances = [...monsterType.monsterInstances, action.data];

        monsterType.monsterInstances = newInstances;
      }
        
      break;
    case 'PUT_MONSTER_INSTANCE': {
        const monsterType = newState.session.monsters.find(monster =>
          monster.id === action.data.monsterId
        );

        const instanceIndex = monsterType.monsterInstances.findIndex(instance => 
          instance.id === action.data.id
        );

        monsterType.monsterInstances.splice(instanceIndex, 1, action.data);
      }
      break;
    case 'DELETE_MONSTER_INSTANCE': {
        const monsterType = newState.session.monsters.find(monster =>
          monster.monsterInstances.findIndex(instance => 
            instance.id === action.data.id
          ) !== -1
        );

        const instanceIndex = monsterType.monsterInstances.findIndex(instance => 
          instance.id === action.data.id
        );

        monsterType.monsterInstances.splice(instanceIndex, 1);
      }
      break;

    case 'PUT_ELEMENT': {
        const elements = newState.session.room.elements;
        const elementIndex = elements.findIndex(element => 
          element.id === action.data.id
        );
        elements.splice(elementIndex, 1, action.data);
      }
      break;
    
    case 'CLEAR_SESSION':
      newState = initialState;
      break;

    case 'SHOW_MODAL':
      newState.modalIsOpen = true;
      break;

    case 'HIDE_MODAL':
      newState.modalIsOpen = false;
      break;

    default: 
      break;
  }

  return newState;
};


export default reducer;