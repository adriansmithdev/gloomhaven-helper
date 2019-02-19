import axios from 'axios';

export const getRoom = function(hash) {
  return async (dispatch) => {
    
    const response = await axios.get(`http://localhost:5000/api/rooms/${hash}`)
    .catch((error) => {
      console.log(error.response);
      dispatch(addError(error.response.data));
      dispatch(setStatus("ROOM_NOT_FOUND"));
      return error.response.data;
    });

    console.log(response);

    if(response.data.status === 404) {
      dispatch(setStatus("ROOM_NOT_FOUND"));
      return response.data;
    } else {
      dispatch(setRoom(response.data));
      return response.data;
    }
    
    
    
  }
}

export const createRoom = function(callback) {
  return async (dispatch) => { 
    const response = await axios.post('http://localhost:5000/api/rooms')
    .catch(function(error) {
      dispatch(addError(error.response.data));
      dispatch(setStatus("FAILED_TO_CREATE_ROOM"));
      return error.response.data;
    })
    dispatch(setRoom(response.data))
    callback(response);
    return response.data;
  }
}

export const addError = function(err) {
  return { type: 'ADD_ERROR', error: err}
}

export const setRoom = function(data) {
  return { type: 'SET_ROOM', value: data }
}

export const setStatus = function(status) {
  return { type: 'SET_STATUS', value: status }
}

export const updateScenario = function(room) {
  return async dispatch => {
    return await axios.put(`http://localhost:5000/api/rooms/${room.hash}`, room)
    .then(function (response){
      dispatch(setRoom(response.data));
      return response.data;
    })
    .catch(function (error){
      dispatch(addError(error.response.data));
      return error.response.data;
    });
  }
}

export const addMonster = (hash, monsterName) => {

  return async dispatch => {
    return await axios.post(`http://localhost:5000/api/rooms/${hash}/monsterinstance/`, {
      monsterName: monsterName
    })
    .then(function (response){
      return response.data;
    })
    .catch(function (error){
      dispatch(addError(error.response.data));
      return error.response.data
    });
  }
}

export const setScenario = (val) => {
  return {type: 'CHANGE_SCENARIO', value: val}
}