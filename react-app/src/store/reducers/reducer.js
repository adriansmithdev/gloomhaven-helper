import axios from 'axios';

const initialState = {
  status: 'INITIAL',
  room: {
  },
  notifications: []
}

const reducer = (state = initialState, action) => {

  const newState = {...state };

  switch(action.type) {
      
    case 'CREATE_ROOM': 
      requestRoom();
      break;
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
    default: 
      break;
  }

  return newState;

};

function requestRoom() {
  axios.post('http://localhost:5000/api/rooms')
  .then(function (response) {
    window.location = `/room/${response.data.roomHash}`;
  })
  .catch(function (error) {
  });
}


export default reducer;