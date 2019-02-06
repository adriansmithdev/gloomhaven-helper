import axios from 'axios';

export const getRoom = (hash) => {
  return dispach => {
    axios.get(`http://localhost:5000/api/room/${hash}`)
    .then((response) => {
      console.log(response);
      dispach(setRoom(response.data));
    })
    .catch((error) => {
      dispach(setRoom(error));
    });
    
  }
}

export const setRoom = (data) => {
  return { type: 'SET_ROOM', value: data }
}

export const changeScenario = (hash, value) => {
  return dispach => {
    axios.post(`http://localhost:5000/api/room/${hash}/scenario/${value}`)
    .then(function (response){
        console.log(response);
    })
    .catch(function (error){
        console.log(error);
    });
  }
}

export const addMonster = (hash, monster) => {

  return dispach => {
    axios.post(`http://localhost:5000/api/room/${hash}/newMonster/`, {
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