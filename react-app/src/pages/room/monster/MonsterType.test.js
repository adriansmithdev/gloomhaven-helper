import { sortByToken } from './MonsterType';

describe('Monster Type: instance sorting', () => {
  // Test Data.
  const monsterInstances = [{
    id: 1003,
    currentHealth: 9,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 2
  }, {
    id: 1002,
    currentHealth: 10,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 1
  }, {
    id: 1004,
    currentHealth: 8,
    isElite: true,
    activeStatuses: ['Invisible'],
    monsterId: 512,
    token: 3
  }, {
    id: 1005,
    currentHealth: 5,
    isElite: false,
    activeStatuses: ['Invisible'],
    monsterId: 512,
    token: 4
  }, {
    id: 1007,
    currentHealth: 3,
    isElite: false,
    activeStatuses: [],
    monsterId: 512,
    token: 6
  }, {
    id: 1008,
    currentHealth: 2,
    isElite: false,
    activeStatuses: [],
    monsterId: 512,
    token: 7
  }, {
    id: 1009,
    currentHealth: 1,
    isElite: false,
    activeStatuses: [],
    monsterId: 512,
    token: 8
  }, {
    id: 1006,
    currentHealth: 4,
    isElite: false,
    activeStatuses: ['Strengthen'],
    monsterId: 512,
    token: 5
  }, {
    id: 1010,
    currentHealth: 0,
    isElite: false,
    activeStatuses: [],
    monsterId: 512,
    token: 9
  }, {
    id: 1012,
    currentHealth: 6,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 11
  }, {
    id: 1013,
    currentHealth: 5,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 12
  }, {
    id: 1014,
    currentHealth: 4,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 13
  }, {
    id: 1011,
    currentHealth: 7,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 10
  }, {
    id: 1015,
    currentHealth: 3,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 14
  }, {
    id: 1016,
    currentHealth: 2,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 15
  }, {
    id: 1017,
    currentHealth: 1,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 16
  }, {
    id: 1018,
    currentHealth: 0,
    isElite: true,
    activeStatuses: [],
    monsterId: 512,
    token: 17
  }];

  function isSorted(instances) {
    let isSorted = true;
    for(let i = 0; i < instances.length - 1; i++) {
      // Ensure tokens are in correct order.
      if (instances[i].token > instances[i + 1].token) {
        isSorted = false;
      }
    }

    return isSorted;
  }

  it('Monsters are unsorted before sort', () => {
    let status = isSorted(monsterInstances);
    expect(status).toBe(false);
  })
  it('Sorts monster instances in ascending order by token.', () => {
    let sorted = sortByToken(monsterInstances);
    let status = isSorted(sorted);
    expect(status).toBe(true);
  });
});