import axios from 'axios';
import { toast } from 'react-toastify';
import { 
  addError, setStatus, setRoom, setShowModal, setHideModal
 } from './storeActions';
import { getSession } from './session';

export const getRoom = function(hash) {
  return async (dispatch) => {
    const response = await axios.get(`http://localhost:5000/api/rooms/${hash}`)
      .catch((error) => {
        dispatch(addError(error.response.data));
        dispatch(setStatus('ROOM_NOT_FOUND'));
        toast.error('That room doesn\'t exist, please enter a valid room');
        return error.response.data;
      });
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
    dispatch(setStatus('ROOM_FOUND'));
    callback(response);
    return response.data;
  }
};

export const updateRoom = function(room) {
  return async dispatch => {
    const response = await axios.put(`http://localhost:5000/api/rooms?hash=${room.hash}`, room)
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

export const addMonster = function(hash, monsterId, isElite) {
  return async dispatch => {
    const response = await axios.post(`http://localhost:5000/api/monsterinstances?hash=${hash}`, {
      monsterId: monsterId,
      isElite: isElite
    }).catch(function (error) {
      dispatch(addError(error.response.data));
      dispatch(setStatus('FAILED_TO_ADD_MONSTER'));
      toast.error('Unable to add monster!');
      return error.response.data;
    });

    dispatch(getSession(hash));
    dispatch(setStatus('ADDED_MONSTER'));
    return response.data;
  }
};

export const updateMonster = function(hash, monster) {
  return async dispatch => {
    const response = await axios.put(`http://localhost:5000/api/monsterinstances?hash=${hash}&id=${monster.id}`, monster)
    .catch(function (error) {
      dispatch(addError(error.response.data));
      dispatch(setStatus('FAILED_TO_UPDATE_MONSTER'));
      toast.error('Unable to update monster!');
      return error.response.data;
    });

    dispatch(getSession(hash));
    dispatch(setStatus('UPDATED_MONSTER'));
    return response.data;
  }
};

export const deleteMonster = function(hash, monster) {
  return async dispatch => {
    const response = await axios.delete(`http://localhost:5000/api/monsterinstances?hash=${hash}&id=${monster.id}`)
      .catch(function (error) {
        dispatch(addError(error.response.data));
        dispatch(setStatus('FAILED_TO_UPDATE_MONSTER'));
        toast.error('Unable to remove monster!');
        return error.response.data;
      });

    dispatch(setStatus('DELETED_MONSTER'));
    dispatch(getSession(hash));
    return response.data;
  }
};

export const showModal = () => dispatch => {
  dispatch(setShowModal("SHOW_MODAL"))
};

export const hideModal = () => dispatch => {
  dispatch(setHideModal("HIDE_MODAL"))
};

export const incrementRound = function(room) {
  return async dispatch => {
    const response = await axios.put(`http://localhost:5000/api/rooms?hash=${room.hash}`, room)
      .catch(function (error) {
        dispatch(addError(error.response.data));
        dispatch(setStatus('FAILED_TO_INCREMENT_ROUND'));
        toast.error('Failed to increment round!');
        return error.response.data;
      });

    dispatch(setRoom(response.data));
    dispatch(setStatus('INCREMENTED_ROUND'));
    return response.data;
  }
};