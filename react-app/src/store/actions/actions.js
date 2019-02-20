import axios from 'axios';
import {toast} from 'react-toastify'

export const getRoom = function(hash) {
  return async (dispatch) => {

    const response = await axios.get(`http://localhost:5000/api/rooms/${hash}`)
      .catch((error) => {
        console.log(error.response);
        dispatch(addError(error.response.data));
        dispatch(setStatus('ROOM_NOT_FOUND'));
        toast.error('That room doesn\'t exist, please enter a valid room');
        return error.response.data;
      });

    console.log(response);
    dispatch(setRoom(response.data));
    dispatch(setStatus('ROOM_FOUND'));
    return response.data;
  }
};

export const createRoom = function(callback) {
  return async (dispatch) => {
    const response = await axios.post('http://localhost:5000/api/rooms')
      .catch(function (error) {
        dispatch(addError(error.response.data));
        dispatch(setStatus('FAILED_TO_CREATE_ROOM'));
        toast.error('There was an error trying to create that room, please try again later');
        return error.response.data;
      });
    dispatch(setRoom(response.data));
    dispatch(setStatus('ROOM_FOUND'));
    callback(response);
    return response.data;
  }
};

export const updateScenario = function(room) {
  return async dispatch => {
    const response = await axios.put(`http://localhost:5000/api/rooms/${room.hash}`, room)
      .catch(function (error) {
        dispatch(addError(error.response.data));
        dispatch(setStatus('FAILED_TO_UPDATE_SCENARIO'));
        toast.error('Failed to update scenario!');
        return error.response.data;
      });

    dispatch(setRoom(response.data));
    dispatch(setStatus('UPDATED_SCENARIO'));
    return response.data;
  };
};

export const addMonster = function(hash, monsterName) {

  return async dispatch => {
    const response = await axios.post(`http://localhost:5000/api/rooms/${hash}/monsterinstances`, {
      name: monsterName
    }).catch(function (error) {
      dispatch(addError(error.response.data));
      dispatch(setStatus('FAILED_TO_ADD_MONSTER'));
      toast.error('Unable to add monster!');
      return error.response.data;
    });

    dispatch(pushMonster(response.data));
    dispatch(setStatus('ADDED_MONSTER'));
    return response.data;
  }
};

export const updateMonster = function(hash, monster) {
  return async dispatch => {
    const response = await axios.put(`http://localhost:5000/api/rooms/${hash}/monsterinstances/${monster.id}`, monster)
    .catch(function (error) {
      dispatch(addError(error.response.data));
      dispatch(setStatus('FAILED_TO_UPDATE_MONSTER'));
      toast.error('Unable to update monster!');
      return error.response.data;
    });

    dispatch(replaceMonster(response.data));
    dispatch(setStatus('UPDATED_MONSTER'));
    //dispatch(getRoom(hash));
    return response.data;
  }
};

export const deleteMonster = function(hash, monster) {
  return async dispatch => {
    const response = await axios.delete(`http://localhost:5000/api/rooms/${hash}/monsterinstances/${monster.id}`)
      .catch(function (error) {
        dispatch(addError(error.response.data));
        dispatch(setStatus('FAILED_TO_UPDATE_MONSTER'));
        toast.error('Unable to remove monster!');
        return error.response.data;
      });

    dispatch(setStatus('DELETED_MONSTER'));
    dispatch(popMonster(monster));
    return response.data;
  }
};

export const pushMonster = function(monster) {
  return {type: 'ADD_MONSTER', monster: monster}
};

export const popMonster = function(monster) {
  return {type: 'DELETE_MONSTER', monster: monster}
};

export const replaceMonster = function(monster) {
  return {type: 'UPDATE_MONSTER', monster: monster};
};

export const setScenario = function(val) {
  return {type: 'CHANGE_SCENARIO', value: val}
};

export const addError = function(err) {
  return {type: 'ADD_ERROR', error: err}
};

export const setRoom = function(data) {
  return {type: 'SET_ROOM', value: data}
};

export const setStatus = function(status) {
  return {type: 'SET_STATUS', value: status}
};

export const clearRoom = function() {
  return {type: 'CLEAR_ROOM'};
};
