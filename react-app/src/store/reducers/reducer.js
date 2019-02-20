const initialState = {
  status: 'INITIAL',
  room: {
  },
  notifications: []
}

const reducer = (state = initialState, action) => {

  const newState = {
    ...state, 
    room: { ...state.room }
  };

  switch(action.type) {
    case 'SET_ROOM': 
      newState.room = {...action.value};
      break;
    case 'ADD_ERROR':
      newState.notifications.push(action.error);
      break;
    case 'SET_STATUS':
      newState.status = action.value;
      break;
    case 'ADD_MONSTER':
      newState.room.monsterInstances.push(action.monster);
      break;
    case 'DELETE_MONSTER':
      // Filter out old monster.
      const newMonsters = newState.room.monsterInstances.filter(monster =>
        monster.id !== action.monster.id
      );

      // Replace old state.
      newState.room.monsterInstances = newMonsters;
      break;
    case 'UPDATE_MONSTER':
      const targetIndex = newState.room.monsterInstances.findIndex(current => 
        current.id === action.monster.id
      );

      newState.room.monsterInstances.splice(targetIndex, 1, action.monster);
      break;
    case 'CLEAR_ROOM':
      newState.room = {};
      break;
    default: 
      break;
  }

  return newState;
};


export default reducer;