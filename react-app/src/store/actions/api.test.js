import axios from 'axios';



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

  
});
