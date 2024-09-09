# mongodb-shard-example

Subir containers
```
docker-compose up
```
<br>

### Comandos Mongo

Check da fragmentação
```
db.shardedPersons.getShardDistribution()
```

Consultas de totais
```
db.persons.countDocuments()
db.shardedPersons.countDocuments()

db.persons.find({name: "Joana"}).count()
db.shardedPersons.find({name: "Joana"}).count()

db.persons.find({name: /Jo/}).count()
db.shardedPersons.find({name: /Jo/}).count()
```

<br>

### CURL's
```
curl --location 'localhost:8080/v1/persons?name=Jo'
curl --location 'localhost:8080/v1/sharded-persons?name=Jo'
```