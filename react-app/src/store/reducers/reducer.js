import axios from 'axios';

const initialState = {
  status: "INITIAL",
  room: {
  }
  
}

const reducer = (state = initialState, action) => {

  const newState = {...state };

  switch(action.type) {
      
    case "CREATE_ROOM": 
      requestRoom();
      break;
    case "SET_ROOM": 
      newState.room = {...action.value};
      break;
    case "SET_STATUS":
      newState.status = action.value;
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