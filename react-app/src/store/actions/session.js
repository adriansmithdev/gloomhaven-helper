import axios from 'axios';
import { toast } from 'react-toastify';
import { addError, setStatus, setSession } from './storeActions';

export const getSession = function(hash) {
  return async (dispatch) => {
    const response = await axios.get(`http://localhost:5000/api/sessions?hash=${hash}`)
    .catch((error) => {
      dispatch(addError(error.response.data));
      dispatch(setStatus('ROOM_NOT_FOUND'));
      toast.error('That room doesn\'t exist, please enter a valid room');
      return error.response.data;
    });
    console.log(response);
    dispatch(setSession(response.data.content[0]));
    dispatch(setStatus('ROOM_FOUND'));
    return response.data;
  }

  
};
