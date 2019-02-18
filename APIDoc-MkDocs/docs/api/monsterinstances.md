# Monster Instance Properties

* name : String (of existing Monster)
* maxHealth : Integer
* currentHealth : Integer
* moveRange : Integer

# Monster Instance Queries

### Get All Monster Instances

    GET /api/room/{hash}/monsterinstances

### Get Specific Monster Instance

    GET /api/room/{hash}/monsterinstances/{id}

### Create Monster Instances

    POST /api/room/{hash}/monsterinstances/

### Update Monster Instances

    PUT /api/room/{hash}/monsterinstances/{id}

### Delete Monster Instances

    DEL /api/room/{hash}/monsterinstances/{id}

# Aggregate Monster Instance Information

Under each monster instance the Monster that it is an instance of is returned
with all details of the Monster.
