# Element Queries
###### Get

All elements for a room
```nginx
GET /api/elements?hash={hash}
```
Specific element for a room
```nginx
GET /api/elements?hash={hash}&id={id}
```

###### Update
```nginx
PUT /api/elements?hash={hash}&id={id}
```

#### Input

Example query `PUT http://localhost:5000/api/elements?hash=a5d5e9d&id=2559`

```json
{
	"strength": 5
}
```

#### Output
```json
{
    "createdAt": "2019-05-06T00:19:23.862",
    "updatedAt": "2019-05-27T15:30:54.884",
    "id": 2559,
    "type": "ICE",
    "strength": 5
}
```