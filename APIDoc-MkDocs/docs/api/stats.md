# Stat Queries
###### Get
```nginx
GET /api/stats
```

#### Output
```json
{
    "content": [
        {
            "createdAt": "2019-05-06T00:19:23.785",
            "updatedAt": "2019-05-06T00:19:23.785",
            "id": 2102,
            "name": "Attack",
            "tooltip": "The amount of damage done."
        },
        {
            "createdAt": "2019-05-06T00:19:23.786",
            "updatedAt": "2019-05-06T00:19:23.786",
            "id": 2103,
            "name": "Flying",
            "tooltip": "Fly from units."
        },
        {
            "createdAt": "2019-05-06T00:19:23.786",
            "updatedAt": "2019-05-06T00:19:23.786",
            "id": 2104,
            "name": "Heal",
            "tooltip": "Recover lost health."
        },
        {
            "createdAt": "2019-05-06T00:19:23.786",
            "updatedAt": "2019-05-06T00:19:23.786",
            "id": 2105,
            "name": "Jump",
            "tooltip": "Walk over obstacles."
        },
        {
            "createdAt": "2019-05-06T00:19:23.787",
            "updatedAt": "2019-05-06T00:19:23.787",
            "id": 2106,
            "name": "Movement",
            "tooltip": "Distance a target can move."
        },
        {
            "createdAt": "2019-05-06T00:19:23.787",
            "updatedAt": "2019-05-06T00:19:23.787",
            "id": 2107,
            "name": "Range",
            "tooltip": "The in block spaces."
        },
        {
            "createdAt": "2019-05-06T00:19:23.787",
            "updatedAt": "2019-05-06T00:19:23.787",
            "id": 2108,
            "name": "Retaliate",
            "tooltip": "Damage reflected when hurt."
        },
        {
            "createdAt": "2019-05-06T00:19:23.787",
            "updatedAt": "2019-05-06T00:19:23.787",
            "id": 2109,
            "name": "Shield",
            "tooltip": "The amount of damage absorbed."
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 20,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 8,
    "size": 20,
    "number": 0,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 8,
    "empty": false
}
```