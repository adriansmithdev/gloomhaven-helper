# Monster Instance Queries
###### Get
```nginx
GET /api/monsterinstances?hash={hash}
```

###### Create

Creating a monster instance requires a monsterId to be passed in the body of the request

```
POST /api/monsterinstances
```

###### Update
```nginx
PUT /api/monsterinstances?hash={hash}&id={id}
```

###### Delete
```nginx
DEL /api/monsterinstances?hash={hash}&id={id}
```

Status code 200 if the delete was successful

#### Input
```json
{
	"monsterId": 202,
	"currentHealth": 50,
	"isElite": true
}
```

#### Output
```json
{
    "createdAt": "2019-05-27T16:47:01.593",
    "updatedAt": "2019-05-27T16:47:01.593",
    "id": 5203,
    "currentHealth": 50,
    "isElite": true,
    "activeStatuses": [],
    "monsterId": 8209,
    "token": 1
}
```

#### Example

GET /api/rooms?hash=ABCDEF