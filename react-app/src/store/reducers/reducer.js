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
      let monsterIndex = newState.room.monsterInstances.indexOf(action.monster);
      let array1 = newState.room.monsterInstances.slice(monsterIndex + 1)
        .concat(newState.room.monsterInstances.slice(0, monsterIndex));
      newState.room.monsterInstances = array1;
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