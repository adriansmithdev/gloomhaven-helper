# Room Queries
###### Get
```nginx
GET /api/rooms?hash={hash}
```

###### Create

```
POST /api/rooms
```

###### Update
```nginx
PUT /api/rooms?hash={hash}
```

###### Delete
```nginx
DEL /api/rooms?hash={hash}
```

Status code 200 if the delete was successful

#### Input
```json
{
    "scenarioNumber": 5,
    "scenarioLevel": 2,
    "round": 2
}
```

#### Output
```json
{
    "createdAt": "2019-05-23T20:21:23.51",
    "updatedAt": "2019-05-23T20:21:23.588",
    "id": 5752,
    "hash": "71147d5",
    "scenarioNumber": 10,
    "scenarioLevel": 2,
    "round": 0
}
```

#### Example

GET /api/rooms?hash=ABCDEF