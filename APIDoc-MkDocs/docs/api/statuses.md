# Status Queries
###### Get
```nginx
GET /api/statuses
```

#### Output
```json
{
    "content": [
        {
            "createdAt": "2019-05-06T00:19:23.77",
            "updatedAt": "2019-05-06T00:19:23.77",
            "id": 2302,
            "name": "Poison",
            "tooltip": "+1 Attack vs figures. Heal removes poison and heal has no other effect."
        },
        {
            "createdAt": "2019-05-06T00:19:23.771",
            "updatedAt": "2019-05-06T00:19:23.771",
            "id": 2303,
            "name": "Wound",
            "tooltip": "Suffers 1 damage at the start of each turn. Heals removes and heals continues normal."
        },
        {
            "createdAt": "2019-05-06T00:19:23.771",
            "updatedAt": "2019-05-06T00:19:23.771",
            "id": 2304,
            "name": "Immobilize",
            "tooltip": "Cannot perform move abilities. Removed at end of its next turn."
        },
        {
            "createdAt": "2019-05-06T00:19:23.772",
            "updatedAt": "2019-05-06T00:19:23.772",
            "id": 2305,
            "name": "Disarm",
            "tooltip": "Cannot perform any attack abilities. Removed at end of its next turn."
        },
        {
            "createdAt": "2019-05-06T00:19:23.772",
            "updatedAt": "2019-05-06T00:19:23.772",
            "id": 2306,
            "name": "Stun",
            "tooltip": "Cannot perform any abilities/items. Must play cards like normal. Removed at end of next turn."
        },
        {
            "createdAt": "2019-05-06T00:19:23.772",
            "updatedAt": "2019-05-06T00:19:23.772",
            "id": 2307,
            "name": "Muddle",
            "tooltip": "Gains disadvantage on all attacks. Removed at end of next turn."
        },
        {
            "createdAt": "2019-05-06T00:19:23.773",
            "updatedAt": "2019-05-06T00:19:23.773",
            "id": 2308,
            "name": "Curse",
            "tooltip": "Must shuffle curse into attack modifier deck."
        },
        {
            "createdAt": "2019-05-06T00:19:23.773",
            "updatedAt": "2019-05-06T00:19:23.773",
            "id": 2309,
            "name": "Invisible",
            "tooltip": "Cannot be focused or targeted by enemy. Removed at end of next turn."
        },
        {
            "createdAt": "2019-05-06T00:19:23.773",
            "updatedAt": "2019-05-06T00:19:23.773",
            "id": 2310,
            "name": "Strengthen",
            "tooltip": "Figure gains advantage on all of its attacks. Removed at end of next turn."
        },
        {
            "createdAt": "2019-05-06T00:19:23.773",
            "updatedAt": "2019-05-06T00:19:23.773",
            "id": 2311,
            "name": "Bless",
            "tooltip": "Must shuffle bless into attack modifier deck."
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
    "totalElements": 10,
    "size": 20,
    "number": 0,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 10,
    "empty": false
}
```