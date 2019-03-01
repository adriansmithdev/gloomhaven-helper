import axios from 'axios';
import { toast } from 'react-toastify';
import { addError, setStatus, setSession } from './storeActions';

export const getSession = function(hash) {
  return async (dispatch) => {
    const response = await axios.get(`http://localhost:5000/api/sessions?hash=${hash}`)
    .then(response => {
      dispatch(setSession(response.data.content[0]));
      dispatch(setStatus('SESSION_FOUND'));
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      dispatch(addError(error));
      dispatch(setStatus('SESSION_NOT_FOUND'));
      toast.error('That session doesn\'t exist, please enter a valid session');
      return error;
    });
    
    return response;
  }

  
};
