import axios from 'axios';

export const getRoom = (hash) => {
  return dispatch => {
    axios.get(`http://localhost:5000/api/rooms/${hash}`)
    .then((response) => {
      console.log(response);
      dispatch(setRoom(response.data));
    })
    .catch((error) => {
      dispatch(setRoom(error));
    });
    
  }
}

export const setRoom = (data) => {
  return { type: 'SET_ROOM', value: data }
}

export const changeScenario = (hash, room) => {
  return dispatch => {
    console.log("ROOM BEING PUT");
    console.log(room);
    axios.put(`http://localhost:5000/api/rooms/${hash}`, room)
    .then(function (response){
        dispatch(setRoom(response.data));
    })
    .catch(function (error){
        console.log(error);
    });
  }
}

export const addMonster = (hash, monster) => {

  return dispatch => {
    axios.post(`http://localhost:5000/api/rooms/${hash}/newMonster/`, {
      monster: {...monster}
    })
    .then(function (response){
        console.log(response);
    })
    .catch(function (error){
        console.log(error);
    });
  }
}

export const setScenario = (val) => {
  return {type: 'CHANGE_SCENARIO', value: val}
}