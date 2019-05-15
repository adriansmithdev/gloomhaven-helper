import { configuredAxios as axios} from './axios.config';
import { toast } from 'react-toastify';
import { 
  addError, setStatus
 } from './storeActions';

export const getRoom = function(hash) {
  return async (dispatch) => {
    const response = await axios.get(`rooms/${hash}`)
      .catch((error) => {
        dispatch(addError(error.response));
        dispatch(setStatus('ROOM_NOT_FOUND'));
        toast.error('That room doesn\'t exist, please enter a valid room');
        return error.response.data;
      });
    dispatch(setStatus('ROOM_FOUND'));
    return response.data;
  }
};

export const createRoom = function(callback, room) {
  return async (dispatch) => {
    const response = await axios.post('rooms', room)
      .catch(function (error) {
        console.log(error);
        dispatch(addError(error.response));
        dispatch(setStatus('FAILED_TO_CREATE_ROOM'));
        toast.error('There was an error trying to create your room, please try again later');
      });
    dispatch(setStatus('ROOM_FOUND'));
    callback(response);
    return response.data;
  }
};

export const updateRoom = function(room) {
  return async dispatch => {
    const response = await axios.put(`rooms?hash=${room.hash}`, room)
      .catch(function (error) {
        dispatch(addError(error.response));
        dispatch(setStatus('FAILED_TO_UPDATE_SCENARIO'));
        toast.error('Failed to update scenario!');
        return error.response.data;
      });

    dispatch(setStatus('UPDATED_SCENARIO'));
    return response.data;
  };
};

export const addMonster = function(hash, monsterId, isElite) {
  return async dispatch => {
    const response = await axios.post(`monsterinstances?hash=${hash}`, {
      monsterId: monsterId,
      isElite: isElite
    }).catch(function (error) {
      dispatch(addError(error.response));
      toast.error('Unable to add monster!');
      return error.response.data;
    });
    return response.data;
  }
};

export const updateMonster = function(hash, monster) {
  return async dispatch => {
    const response = await axios.put(`monsterinstances?hash=${hash}&id=${monster.id}`, monster)
    .catch(function (error) {
      dispatch(addError(error.response));
      dispatch(setStatus('FAILED_TO_UPDATE_MONSTER'));
      toast.error('Unable to update monster!');
      return error.response.data;
    });

    
    dispatch(setStatus('UPDATED_MONSTER'));
    return response.data;
  }
};

export const deleteMonster = function(hash, monster) {
  return async dispatch => {
    const response = await axios.delete(`monsterinstances?hash=${hash}&id=${monster.id}`)
      .catch(function (error) {
        dispatch(addError(error.response));
        dispatch(setStatus('FAILED_TO_UPDATE_MONSTER'));
        toast.error('Unable to remove monster!');
        return error.response.data;
      });

    dispatch(setStatus('DELETED_MONSTER'));
    
    return response.data;
  }
};

export const updateElement = function(hash, element) {
  return async dispatch => {
    const response = await axios.put(`elements?hash=${hash}&id=${element.id}`, element)
    .catch(function (error) {
      dispatch(addError(error.response));
      dispatch(setStatus('FAILED_TO_UPDATE_ELEMENT'));
      toast.error('Unable to update element!');
      return error.response.data;
    });

    
    dispatch(setStatus('UPDATED_ELEMENT'));
    return response.data;
  }
};

export const incrementRound = function(room) {
  return async dispatch => {
    const response = await axios.put(`rooms?hash=${room.hash}`, room)
      .catch(function (error) {
        dispatch(addError(error.response));
        dispatch(setStatus('FAILED_TO_INCREMENT_ROUND'));
        toast.error('Failed to increment round!');
        return error.response.data;
      });
    dispatch(setStatus('INCREMENTED_ROUND'));
    return response.data;
  }
};