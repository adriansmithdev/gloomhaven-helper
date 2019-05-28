# Stream Queries
###### Get
```nginx
GET /api/stream?hash={hash}
```

#### Input
`GET /api/stream?hash=`


#### Output

All events are formatted as

data:
event: message

Data contains a field "action" which describes the type of action that occured in the room

These can be 

* PUT_ROOM
* DELETE_ROOM
* PUT_ELEMENT
* POST_MONSTER_INSTANCE
* PUT_MONSTER_INSTANCE
* DELETE_MONSTER_INSTANCE
* GET_SESSION

```json
data:{  
   "action":"GET_SESSION",
   "response":{  
      "content":[  
         {  
            "room":{  
               "hash":"bdefec6",
               "scenarioNumber":0,
               "scenarioLevel":0,
               "round":0,
               "elements":[  
                  {  
                     "id":4358,
                     "type":"FIRE",
                     "strength":0
                  },
                  {  
                     "id":4359,
                     "type":"ICE",
                     "strength":0
                  },
                  {  
                     "id":4360,
                     "type":"AIR",
                     "strength":0
                  },
                  {  
                     "id":4361,
                     "type":"EARTH",
                     "strength":0
                  },
                  {  
                     "id":4362,
                     "type":"LIGHT",
                     "strength":0
                  },
                  {  
                     "id":4363,
                     "type":"DARK",
                     "strength":0
                  }
               ]
            },
            "monsters":[  
               {  
                  "id":8202,
                  "name":"Ancient Artillery",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":0,
                  "attack":2,
                  "range":4,
                  "eliteHealth":7,
                  "eliteMove":0,
                  "eliteAttack":3,
                  "eliteRange":5,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8210,
                  "name":"Bandit Archer",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":2,
                  "attack":2,
                  "range":3,
                  "eliteHealth":6,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8218,
                  "name":"Bandit Guard",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":2,
                  "range":0,
                  "eliteHealth":9,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8226,
                  "name":"Black Imp",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  
                     "Poison"
                  ],
                  "level":0,
                  "health":3,
                  "movement":1,
                  "attack":1,
                  "range":3,
                  "eliteHealth":4,
                  "eliteMove":1,
                  "eliteAttack":2,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8234,
                  "name":"Cave Bear",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":7,
                  "movement":3,
                  "attack":3,
                  "range":0,
                  "eliteHealth":11,
                  "eliteMove":3,
                  "eliteAttack":4,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8242,
                  "name":"City Archer",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":1,
                  "attack":2,
                  "range":3,
                  "eliteHealth":6,
                  "eliteMove":1,
                  "eliteAttack":3,
                  "eliteRange":4,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8250,
                  "name":"City Guard",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  
                     "Shield 1"
                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":2,
                  "range":0,
                  "eliteHealth":6,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8258,
                  "name":"Cultist",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":2,
                  "attack":1,
                  "range":0,
                  "eliteHealth":7,
                  "eliteMove":2,
                  "eliteAttack":2,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8266,
                  "name":"Deep Terror",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":3,
                  "movement":0,
                  "attack":2,
                  "range":0,
                  "eliteHealth":5,
                  "eliteMove":0,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8274,
                  "name":"Earth Demon",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":7,
                  "movement":1,
                  "attack":3,
                  "range":0,
                  "eliteHealth":10,
                  "eliteMove":2,
                  "eliteAttack":4,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8282,
                  "name":"Flame Demon",
                  "attributes":[  
                     "Flying",
                     "Shield 2"
                  ],
                  "eliteAttributes":[  
                     "Flying",
                     "Shield 3"
                  ],
                  "level":0,
                  "health":2,
                  "movement":3,
                  "attack":2,
                  "range":3,
                  "eliteHealth":3,
                  "eliteMove":3,
                  "eliteAttack":2,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8290,
                  "name":"Frost Demon",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":3,
                  "range":0,
                  "eliteHealth":10,
                  "eliteMove":3,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8298,
                  "name":"Forest Imp",
                  "attributes":[  
                     "Flying",
                     "Shield 1"
                  ],
                  "eliteAttributes":[  
                     "Flying",
                     "Shield 1"
                  ],
                  "level":0,
                  "health":1,
                  "movement":3,
                  "attack":1,
                  "range":3,
                  "eliteHealth":4,
                  "eliteMove":3,
                  "eliteAttack":1,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8306,
                  "name":"Giant Viper",
                  "attributes":[  
                     "Poison"
                  ],
                  "eliteAttributes":[  
                     "Poison"
                  ],
                  "level":0,
                  "health":2,
                  "movement":2,
                  "attack":1,
                  "range":0,
                  "eliteHealth":3,
                  "eliteMove":2,
                  "eliteAttack":2,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8314,
                  "name":"Harrower Infester",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":6,
                  "movement":2,
                  "attack":2,
                  "range":0,
                  "eliteHealth":12,
                  "eliteMove":2,
                  "eliteAttack":2,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8322,
                  "name":"Hound",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":3,
                  "attack":2,
                  "range":0,
                  "eliteHealth":6,
                  "eliteMove":5,
                  "eliteAttack":2,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8330,
                  "name":"Inox Archer",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":2,
                  "range":2,
                  "eliteHealth":7,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8338,
                  "name":"Inox Guard",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  
                     "Retaliate 1"
                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":2,
                  "range":0,
                  "eliteHealth":9,
                  "eliteMove":1,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8346,
                  "name":"Inox Shaman",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":1,
                  "attack":2,
                  "range":3,
                  "eliteHealth":6,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8354,
                  "name":"Living Bones",
                  "attributes":[  
                     "Target 2"
                  ],
                  "eliteAttributes":[  
                     "Target 2"
                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":1,
                  "range":0,
                  "eliteHealth":6,
                  "eliteMove":4,
                  "eliteAttack":2,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8362,
                  "name":"Living Corpse",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":5,
                  "movement":1,
                  "attack":3,
                  "range":0,
                  "eliteHealth":10,
                  "eliteMove":1,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8370,
                  "name":"Living Spirit",
                  "attributes":[  
                     "Flying",
                     "Shield 1"
                  ],
                  "eliteAttributes":[  
                     "Flying",
                     "Shield 2"
                  ],
                  "level":0,
                  "health":2,
                  "movement":2,
                  "attack":2,
                  "range":2,
                  "eliteHealth":3,
                  "eliteMove":3,
                  "eliteAttack":3,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8378,
                  "name":"Lurker",
                  "attributes":[  
                     "Target 2"
                  ],
                  "eliteAttributes":[  
                     "Target 2",
                     "Shield 1"
                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":2,
                  "range":0,
                  "eliteHealth":7,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8386,
                  "name":"Ooze",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":4,
                  "movement":1,
                  "attack":2,
                  "range":2,
                  "eliteHealth":8,
                  "eliteMove":1,
                  "eliteAttack":2,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8394,
                  "name":"Night Demon",
                  "attributes":[  
                     "Attackers gain Disadvantage"
                  ],
                  "eliteAttributes":[  
                     "Attackers gain Disadvantage"
                  ],
                  "level":0,
                  "health":3,
                  "movement":3,
                  "attack":3,
                  "range":0,
                  "eliteHealth":5,
                  "eliteMove":4,
                  "eliteAttack":4,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8402,
                  "name":"Rending Drake",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":5,
                  "movement":3,
                  "attack":3,
                  "range":0,
                  "eliteHealth":7,
                  "eliteMove":4,
                  "eliteAttack":4,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8410,
                  "name":"Savvas Icestorm",
                  "attributes":[  
                     "Pierce 3"
                  ],
                  "eliteAttributes":[  
                     "Pierce 3"
                  ],
                  "level":0,
                  "health":7,
                  "movement":2,
                  "attack":2,
                  "range":3,
                  "eliteHealth":12,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":4,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8418,
                  "name":"Savvas Lavaflow",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":8,
                  "movement":3,
                  "attack":2,
                  "range":0,
                  "eliteHealth":13,
                  "eliteMove":3,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8426,
                  "name":"Spitting Drake",
                  "attributes":[  
                     "Flying"
                  ],
                  "eliteAttributes":[  
                     "Flying"
                  ],
                  "level":0,
                  "health":5,
                  "movement":3,
                  "attack":3,
                  "range":3,
                  "eliteHealth":8,
                  "eliteMove":3,
                  "eliteAttack":4,
                  "eliteRange":4,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8434,
                  "name":"Stone Golem",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  
                     "Shield 1"
                  ],
                  "level":0,
                  "health":10,
                  "movement":1,
                  "attack":3,
                  "range":0,
                  "eliteHealth":10,
                  "eliteMove":2,
                  "eliteAttack":4,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8442,
                  "name":"Sun Demon",
                  "attributes":[  
                     "Flying",
                     "Advantage",
                     "Shield 1"
                  ],
                  "eliteAttributes":[  
                     "Flying",
                     "Advantage",
                     "Shield 1"
                  ],
                  "level":0,
                  "health":5,
                  "movement":2,
                  "attack":2,
                  "range":0,
                  "eliteHealth":9,
                  "eliteMove":2,
                  "eliteAttack":3,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8450,
                  "name":"Vermling Scout",
                  "attributes":[  

                  ],
                  "eliteAttributes":[  

                  ],
                  "level":0,
                  "health":2,
                  "movement":3,
                  "attack":1,
                  "range":0,
                  "eliteHealth":4,
                  "eliteMove":3,
                  "eliteAttack":2,
                  "eliteRange":0,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8458,
                  "name":"Vermling Shaman",
                  "attributes":[  
                     "Shield 2"
                  ],
                  "eliteAttributes":[  
                     "Shield 2"
                  ],
                  "level":0,
                  "health":2,
                  "movement":2,
                  "attack":1,
                  "range":3,
                  "eliteHealth":3,
                  "eliteMove":3,
                  "eliteAttack":2,
                  "eliteRange":3,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               },
               {  
                  "id":8466,
                  "name":"Wind Demon",
                  "attributes":[  
                     "Flying",
                     "Shield 1"
                  ],
                  "eliteAttributes":[  
                     "Flying",
                     "Shield 1"
                  ],
                  "level":0,
                  "health":3,
                  "movement":3,
                  "attack":2,
                  "range":3,
                  "eliteHealth":5,
                  "eliteMove":4,
                  "eliteAttack":3,
                  "eliteRange":4,
                  "monsterInstances":[  

                  ],
                  "monsterAction":null
               }
            ],
            "statuses":[  
               {  
                  "id":2302,
                  "name":"Poison",
                  "tooltip":"+1 Attack vs figures. Heal removes poison and heal has no other effect."
               },
               {  
                  "id":2303,
                  "name":"Wound",
                  "tooltip":"Suffers 1 damage at the start of each turn. Heals removes and heals continues normal."
               },
               {  
                  "id":2304,
                  "name":"Immobilize",
                  "tooltip":"Cannot perform move abilities. Removed at end of its next turn."
               },
               {  
                  "id":2305,
                  "name":"Disarm",
                  "tooltip":"Cannot perform any attack abilities. Removed at end of its next turn."
               },
               {  
                  "id":2306,
                  "name":"Stun",
                  "tooltip":"Cannot perform any abilities/items. Must play cards like normal. Removed at end of next turn."
               },
               {  
                  "id":2307,
                  "name":"Muddle",
                  "tooltip":"Gains disadvantage on all attacks. Removed at end of next turn."
               },
               {  
                  "id":2308,
                  "name":"Curse",
                  "tooltip":"Must shuffle curse into attack modifier deck."
               },
               {  
                  "id":2309,
                  "name":"Invisible",
                  "tooltip":"Cannot be focused or targeted by enemy. Removed at end of next turn."
               },
               {  
                  "id":2310,
                  "name":"Strengthen",
                  "tooltip":"Figure gains advantage on all of its attacks. Removed at end of next turn."
               },
               {  
                  "id":2311,
                  "name":"Bless",
                  "tooltip":"Must shuffle bless into attack modifier deck."
               }
            ],
            "stats":[  
               {  
                  "id":2102,
                  "name":"Attack",
                  "tooltip":"The amount of damage done."
               },
               {  
                  "id":2103,
                  "name":"Flying",
                  "tooltip":"Fly from units."
               },
               {  
                  "id":2104,
                  "name":"Heal",
                  "tooltip":"Recover lost health."
               },
               {  
                  "id":2105,
                  "name":"Jump",
                  "tooltip":"Walk over obstacles."
               },
               {  
                  "id":2106,
                  "name":"Movement",
                  "tooltip":"Distance a target can move."
               },
               {  
                  "id":2107,
                  "name":"Range",
                  "tooltip":"The in block spaces."
               },
               {  
                  "id":2108,
                  "name":"Retaliate",
                  "tooltip":"Damage reflected when hurt."
               },
               {  
                  "id":2109,
                  "name":"Shield",
                  "tooltip":"The amount of damage absorbed."
               }
            ]
         }
      ],
      "pageable":"INSTANCE",
      "last":true,
      "totalPages":1,
      "totalElements":1,
      "size":0,
      "number":0,
      "first":true,
      "sort":{  
         "sorted":false,
         "unsorted":true,
         "empty":true
      },
      "numberOfElements":1,
      "empty":false
   }
}event:message
```