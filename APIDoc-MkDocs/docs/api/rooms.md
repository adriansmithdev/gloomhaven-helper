# Room Properties

* hash : String
* scenarioNumber : Integer

# Room Queries

### Get All Rooms

    GET /api/rooms

### Get Specific Room

    GET /api/rooms/{hash}

### Create Room

    POST /api/rooms/

### Update Room

    PUT /api/rooms/{hash}

### Delete Room

    DEL /api/rooms/{hash}

# Aggregate Room Information

Rooms return all information that is nested in them. One query on a room is
enough to pull all information for a users game session.
