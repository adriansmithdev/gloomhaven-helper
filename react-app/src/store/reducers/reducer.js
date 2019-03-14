const initialState = {
  status: 'INITIAL',
  session: {
    room: {

    }
  },
  notifications: [],
  modalIsOpen: false,

  // Temp until database is setup.
  elements: [
    {
      name: 'wind',
      strength: 0,
    },
    {
      name: 'fire',
      strength: 1,
    },
    {
      name: 'earth',
      strength: 0,
    },
    {
      name: 'ice',
      strength: 2,
    },
    {
      name: 'light',
      strength: 0,
    },
    {
      name: 'dark',
      strength: 0,
    }
  ]

  
}

const reducer = (state = initialState, action) => {

  let newState = {
    ...state, 
    session: { 
      ...state.session, 
      room: {
        ...state.session.room
      }
    }
  };

  switch(action.type) {
    case 'SET_ROOM': 
      newState.session.room = {...action.value};
      break;
    case 'SET_SESSION': 
      newState.session = {...action.value};
      break;
    case 'ADD_ERROR':
      newState.notifications.push(action.error);
      break;
    case 'SET_STATUS':
      newState.status = action.value;
      break;
    case 'ADD_MONSTER':
      newState.session.monsters.push(action.monster);
      break;
    case 'DELETE_MONSTER':
      // Filter out old monster.
      const newMonsters = newState.monsters.filter(monster =>
        monster.id !== action.monster.id
      );
      // Replace old state.
      newState.session.room.monsters = newMonsters;
      break;
    case 'UPDATE_MONSTER':
      const targetIndex = newState.monsters.findIndex(current => 
        current.id === action.monster.id
      );
      newState.session.monsters.splice(targetIndex, 1, action.monster);
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