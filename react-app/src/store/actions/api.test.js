import axios from 'axios';
import { updateMonster } from './actions';



describe('Test API functionality:', () => {
  
  // Test Create Room
  it('Can create new room on API', async () => {
    // Mock data for database.
    const room = {
      scenarioNumber: 1,
      scenarioLevel: 3
    };

    const response = await axios.post('http://localhost:5000/api/rooms', room);

    // Make sure that response exists.
    expect(response).not.toBe(undefined);

    expect(response.data).not.toBe(undefined);

    expect(response.data.hash).not.toBe(undefined);
    
    expect(response.data.scenarioNumber).toBe(1);

    expect(response.data.scenarioLevel).toBe(3);
  });

  // Test getSession
  it('Can retrieve a session', async () => {

    const response = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');

    // Test Response structure
    expect(response).not.toBe(undefined);

    expect(response.data).not.toBe(undefined);

    expect(response.data.content).not.toBe(undefined);


    // Test Room Structure.
    expect(response.data.content[0].room).not.toBe(undefined);

    const room = response.data.content[0].room;

    expect(room.hash).not.toBe(undefined);
    
    expect(room.scenarioNumber).not.toBe(undefined);

    expect(room.scenarioLevel).not.toBe(undefined);

    expect(room.round).not.toBe(undefined);

    

    // Test Elements
    expect(room.elements).not.toBe(undefined);

    const element = room.elements[0];

    expect(element.type).not.toBe(undefined);

    expect(element.strength).not.toBe(undefined);

  
    // Test MonsterTypes
    expect(response.data.content[0].monsters).not.toBe(undefined);

    const monsterTypes = response.data.content[0].monsters;

    expect(monsterTypes[0]).not.toBe(undefined);

    expect(monsterTypes[0].name).not.toBe(undefined);

    expect(monsterTypes[0].level).not.toBe(undefined);

    expect(monsterTypes[0].health).not.toBe(undefined);

    expect(monsterTypes[0].movement).not.toBe(undefined);

    expect(monsterTypes[0].attack).not.toBe(undefined);

    expect(monsterTypes[0].range).not.toBe(undefined);

    expect(monsterTypes[0].eliteHealth).not.toBe(undefined);

    expect(monsterTypes[0].eliteMove).not.toBe(undefined);

    expect(monsterTypes[0].eliteAttack).not.toBe(undefined);

    expect(monsterTypes[0].eliteRange).not.toBe(undefined);

    expect(monsterTypes[0].monsterInstances).not.toBe(undefined);

    expect(monsterTypes[0].attributes).not.toBe(undefined);

    expect(monsterTypes[0].eliteAttributes).not.toBe(undefined);

  });


  // Test changeScenario Number.
  it('Change Scenario Data', async () => {

    //Get Room object
    const response = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');
    const room = response.data.content[0].room;

    //Checking Scenario Number
    expect(room.scenarioNumber).not.toBe(undefined);
    //Checking Scenario Level
    expect(room.scenarioLevel).not.toBe(undefined);
    //Checking Round Counter
    expect(room.round).not.toBe(undefined);

    //Randomize values to be updated with
    const newScenarioNumber = Math.round(Math.random() * 10000);
    const newScenarioLevel = Math.round(Math.random() * 7);
    const newRound = Math.round(Math.random() * 10000);
  
    //New room object to be updated
    const newRoom = {
      ...room, 
      scenarioNumber: newScenarioNumber,
      scenarioLevel: newScenarioLevel,
      round: newRound
    }

    //Update Room
    await axios.put(`http://localhost:5000/api/rooms?hash=ABCDEF`, newRoom);

    //Get Updated Room
    const updatedResponse = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');
    const updatedRoom = updatedResponse.data.content[0].room;

    //Checking if updated Scenario Number
    expect(updatedRoom.scenarioNumber).not.toBe(undefined);
    expect(updatedRoom.scenarioNumber).toBe(newScenarioNumber);

    //Checking if updated Scenario Level
    expect(updatedRoom.scenarioLevel).not.toBe(undefined);
    expect(updatedRoom.scenarioLevel).toBe(newScenarioLevel);

    //Checking if updated round counter
    expect(updatedRoom.round).not.toBe(undefined);
    expect(updatedRoom.round).toBe(newRound);
  });

  
  // Test Create/Delete Monsters Number.
  it('Create/Delete Monsters', async () => {
    
   //Get Room object
   const response = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');

   const monsters = response.data.content[0].monsters;
   const randomMonster = Math.round(Math.random() * monsters.length)
   const monsterId = monsters[randomMonster].id;
   const instanceCount = monsters[randomMonster].monsterInstances.length;
 
   
   //Create normal monster
   const normalResponse = await axios.post(`http://localhost:5000/api/monsterinstances?hash=ABCDEF`, {
      monsterId: monsterId,
      isElite: false
   });
  
   //Create elite monster
   const eliteResponse = await axios.post(`http://localhost:5000/api/monsterinstances?hash=ABCDEF`, {
       monsterId: monsterId,
       isElite: true
   });
    
   const updatedResponse = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');
   const updatedMonsters = updatedResponse.data.content[0].monsters;
   const updatedInstanceCount = updatedMonsters[randomMonster].monsterInstances.length;

   expect(updatedInstanceCount).not.toBe(undefined);
   expect(updatedInstanceCount).not.toBe(instanceCount);
   expect(updatedInstanceCount).toBe(instanceCount + 2);
  
  });


  // Test updating Health.
  it('Testing Health Functions Calls', async () => {

    //Get Room object
    const response = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');

    const monsters = response.data.content[0].monsters;
    const randomMonster = Math.round(Math.random() * monsters.length)
    const monsterId = monsters[randomMonster].id;
    const startingHealth = monsters[randomMonster].maxHealth;

    //Create normal monster
    const normalResponse = await axios.post(`http://localhost:5000/api/monsterinstances?hash=ABCDEF`, {
      monsterId: monsterId,
      isElite: false
    });

    const newInstance = normalResponse.data;
    const instanceId = newInstance.id;

    const newHealth = newInstance.currentHealth - 1;

    const updateInstance = {
      ...newInstance,
      currentHealth: newHealth
    };
    
    const updateResult = await axios.put(`http://localhost:5000/api/monsterinstances?hash=ABCDEF&id=${newInstance.id}`, updateInstance);

    const updatedMonster = updateResult.data;
    expect(updatedMonster.currentHealth).not.toBe(undefined);
    expect(updatedMonster.currentHealth).not.toBe(newInstance.currentHealth);
    expect(updatedMonster.currentHealth).toBe(newHealth);

  });


  // Test Elements updating with rounds.
  it('Elements update with round', async () => {

    //Get Room object
    const response = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');

    //get element to be checked
    const elements = response.data.content[0].room.elements
    const randomElement = Math.round(Math.random() * elements.length)
    const element = elements[randomElement];

    const updateElement = {
      ...element,
      strength: newStrength
    };

    expect(element).not.toBe(undefined);
    expect(element.state).toBe(2);

    //push to database
    await axios.put(`http://localhost:5000/api/elements?hash=ABCDEF&id=${element.id}`, element);
    
    const updatedResponseOne = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');

    //get element to be checked
    const updatedElementsOne = updatedResponseOne.data.content[0].elements
    const updatedElementOne = updatedElementsOne[randomElement];

    //Test to element was pushed correctly
    expect(updatedElementOne).not.toBe(undefined);
    //Check to make sure the state is in max
    expect(updatedElementOne.state).toBe(2);
    
    const newRound = updatedResponseOne.data.content[0].room.round++;

    //New room object to be updated
    const newRoom = {
      ...room, 
      scenarioNumber: 1,
      scenarioLevel: 0,
      round: newRound
    }

    //Update Room
    await axios.put(`http://localhost:5000/api/rooms?hash=ABCDEF`, newRoom);

    //Get Updated Room
    const updatedResponseTwo = await axios.get('http://localhost:5000/api/sessions?hash=ABCDEF');
    //get element to be checked if decreased with round increment
    const updatedElementsTwo = updatedResponseTwo.data.content[0].elements
    const updatedElementTwo = updatedElementsTwo[randomElement];


    //Test to element was pushed correctly
    expect(updatedElementTwo).not.toBe(undefined);
    //Check to make sure the state is in max
    expect(updatedElementTwo.state).toBe(1);

  });
  

});
